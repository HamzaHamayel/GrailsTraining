package edu.training

import grails.orm.PagedResultList
import static org.springframework.http.HttpStatus.*

class ProfileController {

    ProfileService profileService



    // save action may be invoked from a POST
    // update action may be invoked from a PUT
    // list action has no restrictions
    // delete action may be invoked from a DELETE

//    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    //multiple values (delete action may be invoked from a POST or DELETE)
    //static allowedMethods = [delete:['POST', 'DELETE']]

    def index(Integer max) {
//        redirect(controller:"user",action: "list")
        redirect(action: "list")
    }

    def list = {
        //params (hash map from client that contains all parameters from client)
        PagedResultList pagedResultList = profileService.search(params)
        return [profileList:pagedResultList,profileCount:pagedResultList?.totalCount]
    }

    def show = {
        //respond (hash map from server to client)
        respond profileService.get(params)
    }

    def create(){
        respond new Profile(params)
    }

    def save = {
        Profile profile = profileService.save(params)
        if(profile.hasErrors()){
            respond profile.errors, view:'create' // return [profile:profile]
        }else{
            flash.message = message(code: 'default.created.message', args: [message(code: 'profile.label', default: 'Profile'), profile.id])
            redirect(action: "list")
        }
    }

    def edit = {
        Profile profile = profileService.get(params)
        if(profile){
            respond profile
        }else{
            notFound()
        }
    }

    def update = {
        Profile profile = profileService.save(params)
        if(profile.hasErrors()){
            respond profile.errors, view:'edit'
        }else{
            flash.message = message(code: 'default.updated.message', args: [message(code: 'profile.label', default: 'Profile'), profile.id])
            redirect(action: "list")
        }
    }


    def delete = {
        Boolean deleted = profileService.delete(params)
        if(deleted){
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'profile.label', default: 'Profile'), profile.id])
            redirect(action: "list")
        }else{
            notFound()
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'profile.label', default: 'Profile'), params.id])
                redirect action: "list", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
