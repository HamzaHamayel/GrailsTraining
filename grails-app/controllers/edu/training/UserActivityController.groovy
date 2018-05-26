package edu.training

import grails.converters.JSON
import static org.springframework.http.HttpStatus.*

class UserActivityController {

    UserActivityService userActivityService


    def index = {
        redirect(action: "list")
    }

    def list = {
    }


    def filter = {
        render userActivityService.renderDataTableData(params)
    }

    def show = {
        if (params["user"] && params["activityName"]) {
            params.username = params.remove("user")
            respond userActivityService.get(params)
        }else{
            notFound()
        }
    }

    def create(){
        respond new UserActivity(params)
    }

    def createByCommand(){
        respond new UserActivity(params)
    }

    def save = {
        UserActivity userActivity = userActivityService.save(params)
        String successMessage = message(code: 'default.created.message', args: [message(code: 'userActivity.label', default: 'UserActivity'), userActivity?.user?.username])
        if (request.xhr) {
            def json = [:]
            json.success = !userActivity.hasErrors() && userActivity?.id;
            json.message = json.success ? msg.formatMessage(message:successMessage) : msg.formatErrorList(errorsObject:  userActivity);
            json.alert = json.success ? alert.successAlert(value: successMessage) : alert.errorListAlert(errorsObject:  userActivity);
            json.data = json.success ? userActivity : null
            json.errorList = userActivity?.hasErrors() ? userActivity?.errors?.fieldErrors?.field : []
            render text: (json as JSON), contentType: "application/json"
        }else {
            if (userActivity.hasErrors()) {
                respond userActivity.errors, view: 'create' // return [userActivity:userActivity]
            } else {
                flash.message = successMessage
                flash.alert =  alert.successAlert(value: successMessage)
                redirect(action: "list")
            }
        }
    }


    def edit = {
        UserActivity userActivity = userActivityService.get(params)
        if(userActivity){
            respond userActivity
        }else{
            notFound()
        }
    }

    def update = {
        UserActivity userActivity = userActivityService.save(params)
        if(userActivity.hasErrors()){
            respond userActivity.errors, view:'edit'
        }else{
            flash.message = message(code: 'default.updated.message', args: [message(code: 'userActivity.label', default: 'UserActivity'), userActivity.id])
            flash.alert =  alert.successAlert(value: message(code: 'default.updated.message', args: [message(code: 'userActivity.label', default: 'UserActivity'), userActivity.id]))
            redirect(action: "list")
        }
    }


    def delete = {
        Boolean deleted = userActivityService.delete(params)
        if(deleted){
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'userActivity.label', default: 'UserActivity'), params.id])
            redirect(action: "list")
        }else{
            notFound()
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userActivity.label', default: 'UserActivity'), params.id])
                redirect action: "list", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
