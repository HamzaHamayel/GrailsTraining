package edu.training

import grails.converters.JSON
import static org.springframework.http.HttpStatus.*

class TagPostController {

    TagPostService tagPostService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index = {
        redirect(action: "list")
    }

    def list = {
    }


    def filter = {
        render tagPostService.renderDataTableData(params)
    }

    def show = {
        if (params.long("id")) {
            respond tagPostService.get(params)
        }else{
            notFound()
        }
    }
    def create(){
        respond new TagPost(params)
    }

    def save = {
        TagPost tagPost = tagPostService.save(params)
        if(tagPost.hasErrors()){
            respond tagPost.errors, view:'create' // return [tagPost:tagPost]
        }else{
            flash.message = message(code: 'default.created.message', args: [message(code: 'tagPost.label', default: 'TagPost'), tagPost.id])
            redirect(action: "list")
        }
    }

    def edit = {
        TagPost tagPost = tagPostService.get(params)
        if(tagPost){
            respond tagPost
        }else{
            notFound()
        }
    }

    def update = {
        TagPost tagPost = tagPostService.save(params)
        if(tagPost.hasErrors()){
            respond tagPost.errors, view:'edit'
        }else{
            flash.message = message(code: 'default.updated.message', args: [message(code: 'tagPost.label', default: 'TagPost'), tagPost.id])
            redirect(action: "list")
        }
    }


    def delete = {
        Boolean deleted = tagPostService.delete(params)
        if(deleted){
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tagPost.label', default: 'TagPost'), params.id])
            redirect(action: "list")
        }else{
            notFound()
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tagPost.label', default: 'TagPost'), params.id])
                redirect action: "list", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
