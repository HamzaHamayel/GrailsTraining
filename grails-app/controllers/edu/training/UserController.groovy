package edu.training

import grails.converters.JSON
import static org.springframework.http.HttpStatus.*

class UserController {

    UserService userService

    static allowedMethods = [save: "POST",saveCommand: "POST", update: "PUT", delete: "DELETE"]

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

        println("owner userId: ${owner?.userId}")
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
        render userService.search(params)?.collect{return [value:it.id,text:it.userId]} as JSON
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
