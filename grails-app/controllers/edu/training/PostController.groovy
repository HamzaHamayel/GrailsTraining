package edu.training

import grails.converters.JSON
import static org.springframework.http.HttpStatus.*

class PostController {

    PostService postService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index = {
        redirect(action: "list")
    }

    def list = {
    }


    def filter = {
        render postService.renderDataTableData(params)
    }

    def show = {
        if (params.long("id")) {
            respond postService.get(params)
        }else{
            notFound()
        }
    }

    def create(){
        respond new Post(params)
    }

    def save = {
        Post post = postService.save(params)
        if(post.hasErrors()){
            respond post.errors, view:'create' // return [post:post]
        }else{
            flash.message = message(code: 'default.created.message', args: [message(code: 'post.label', default: 'Post'), post.id])
            redirect(action: "list")
        }
    }

    def edit = {
        Post post = postService.get(params)
        if(post){
            respond post
        }else{
            notFound()
        }
    }

    def update = {
        Post post = postService.save(params)
        if(post.hasErrors()){
            respond post.errors, view:'edit'
        }else{
            flash.message = message(code: 'default.updated.message', args: [message(code: 'post.label', default: 'Post'), post.id])
            redirect(action: "list")
        }
    }


    def delete = {
        Boolean deleted = postService.delete(params)
        if(deleted){
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'post.label', default: 'Post'), params.id])
            redirect(action: "list")
        }else{
            notFound()
        }
    }

    def autoComplete = {
        render postService.search(params)?.collect{return [value:it.id,text:it.content]} as JSON
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'post.label', default: 'Post'), params.id])
                redirect action: "list", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
