package edu.training

import grails.converters.JSON
import static org.springframework.http.HttpStatus.*

class TagController {

    TagService tagService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index = {
        redirect(action: "list")
    }

    def list = {
    }


    def filter = {
        render tagService.renderDataTableData(params)
    }

    def show = {
        if (params.long("id")) {
            respond tagService.get(params)
        }else{
            notFound()
        }
    }

    def create(){
        respond new Tag(params)
    }

    def save = {
        Tag tag = tagService.save(params)
        if(tag.hasErrors()){
            respond tag.errors, view:'create' // return [tag:tag]
        }else{
            flash.message = message(code: 'default.created.message', args: [message(code: 'tag.label', default: 'Tag'), tag.id])
            redirect(action: "list")
        }
    }

    def edit = {
        Tag tag = tagService.get(params)
        if(tag){
            respond tag
        }else{
            notFound()
        }
    }

    def update = {
        Tag tag = tagService.save(params)
        if(tag.hasErrors()){
            respond tag.errors, view:'edit'
        }else{
            flash.message = message(code: 'default.updated.message', args: [message(code: 'tag.label', default: 'Tag'), tag.id])
            redirect(action: "list")
        }
    }


    def delete = {
        Boolean deleted = tagService.delete(params)
        if(deleted){
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tag.label', default: 'Tag'), params.id])
            redirect(action: "list")
        }else{
            notFound()
        }
    }

    def autoComplete = {
        render tagService.search(params)?.collect{return [value:it.id,text:it.name]} as JSON
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tag.label', default: 'Tag'), params.id])
                redirect action: "list", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
