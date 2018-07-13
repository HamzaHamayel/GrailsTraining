package edu.training

import edu.training.security.User
import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.acl.AclEntry
import grails.plugin.springsecurity.acl.AclUtilService
import grails.plugins.jasper.JasperExportFormat
import grails.plugins.jasper.JasperReportDef
import org.apache.commons.io.FileUtils
import org.springframework.security.acls.domain.BasePermission

import static org.springframework.http.HttpStatus.*

class UserManagementController {

    UserService userService
    AclUtilService aclUtilService
    SpringSecurityService springSecurityService


    static allowedMethods = [save: "POST",saveCommand: "POST", update: "PUT"]

    def index = {
        redirect(action: "list")
    }

    def list = {
    }


    def filter = {
        render userService.renderDataTableData(params)
    }

    def show = {
        if (params.long("id")) {
            respond userService.get(params)
        }else{
            notFound()
        }
    }

    def create(){
        respond new User(params)
    }

    def createByCommand(){
        respond new User(params)
    }

    def save = {
        User user = userService.save(params)
        String successMessage = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user?.id])
        if (request.xhr) {
            def json = [:]
            json.success = !user.hasErrors() && user?.id;
            json.message = json.success ? msg.formatMessage(message:successMessage) : msg.formatErrorList(errorsObject:  user);
            json.alert = json.success ? alert.successAlert(value: successMessage) : alert.errorListAlert(errorsObject:  user);
            json.data = json.success ? user : null
            json.errorList = user?.hasErrors() ? user?.errors?.fieldErrors?.field : []
            render text: (json as JSON), contentType: "application/json"
        }else {
            if (user.hasErrors()) {
                respond user.errors, view: 'create' // return [user:user]
            } else {
                flash.message = successMessage
                flash.alert =  alert.successAlert(value: successMessage)
                redirect(action: "list")
            }
        }
    }


    def saveCommand(UserCommand owner){

        println("owner username: ${owner?.username}")
        println("owner homepage: ${owner?.homepage}")
        println("owner password: ${owner?.password}")
        println("owner applicationName: ${owner?.applicationName}")

        if(owner?.hasErrors()){
            println("errors: ${owner?.errors}")
            Map map = [user:owner]
            respond map, view: 'create' // return [user:user]
        }else{
            User user = userService.saveByCommand(owner)
            String successMessage = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
            if (user.hasErrors()) {
                respond user.errors, view: 'create' // return [user:user]
            } else {
                flash.message = successMessage
                redirect(action: "list")
            }
        }
    }

    def edit = {
        User user = userService.get(params)
        if(user){
            respond user
        }else{
            notFound()
        }
    }

    def update = {
        User user = userService.save(params)
        if(user.hasErrors()){
            respond user.errors, view:'edit'
        }else{
            flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
            flash.alert =  alert.successAlert(value: message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id]))
            redirect(action: "list")
        }
    }


    def delete = {
        Boolean deleted = userService.delete(params)
        if(deleted){
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
        }else{
            notFound()
        }
    }

    def autoComplete = {
        render userService.search(params)?.collect{return [value:it.id,text:it.username]} as JSON
    }


    def aclManage = {

    }

    @Transactional
    def saveAcl() {
        Country country = Country.load(params.long("countryId"))
        String username = params["userId"]
        BasePermission basePermission = params["permission"] == "write" ? BasePermission.WRITE : BasePermission.READ

        aclUtilService.addPermission(country, username, basePermission)
        String successMessage = message(code: 'default.created.message', args: ['ACL'])

        flash.alert = alert.successAlert(value: successMessage)
        redirect(action: "list")

    }


    def getAcl = {

        def username = springSecurityService?.currentUser?.username
        List<Long> objectIds = AclEntry.createCriteria().list {
            sid { eq('sid', username) }
            aclObjectIdentity {
                projections { property("objectId") }
            }
        }
        println("objectIds: $objectIds")
        if(objectIds){
            render "user has acl on country: ${Country.findAllByIdInList(objectIds)?.name}"
        } else {
            render "no acl for current user"
        }
    }


    def deleteAcl = {

        Country country = Country.findByCode("ps")
        aclUtilService.deleteAcl(country)

    }


    def jasperService
    def report = {
        params.username = springSecurityService.principal.username
        List reportDetails = userService.search(params)
        chain(controller: 'jasper', action: 'index', model: [data: reportDetails], params: params)


//        JasperReportDef reportDef = new JasperReportDef(reportData: reportDetails,parameters: params,name: 'user-report.jrxml', fileFormat: JasperExportFormat.PDF_FORMAT)
//        reportDef.fileFormat = JasperExportFormat.PDF_FORMAT
//        reportDef.reportData = jasperService.getReportData([data:reportDetails], params)
//        reportDef.contentStream = jasperService.generateReport(reportDef)
//        reportDef.jasperPrinter = jasperService.generatePrinter(reportDef)
//        generateResponse(reportDef)
    }

    def generateResponse(JasperReportDef reportDef) {
        if (!reportDef.fileFormat.inline && !reportDef.parameters._inline) {
            response.setHeader("Content-disposition", "attachment; filename=" + (reportDef.parameters._name ?: reportDef.name) + "." + reportDef.fileFormat.extension)
            response.contentType = reportDef.fileFormat.mimeTyp
            response.characterEncoding = "UTF-8"
            response.outputStream << reportDef.contentStream.toByteArray()
        } else {
            render(text: reportDef.contentStream, contentType: reportDef.fileFormat.mimeTyp, encoding: reportDef.parameters.encoding ? reportDef.parameters.encoding : 'UTF-8')
        }
    }


    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "list", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
