package edu.training

import grails.converters.JSON
import static org.springframework.http.HttpStatus.*

class CountryController {

    CountryService countryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index = {
        redirect(action: "list")
    }

    def list = {
    }


    def filter = {
        println(params)
        render countryService.renderDataTableData(params)
    }

    def show = {
        if (params.long("id")) {
            respond countryService.get(params)
        }else{
            notFound()
        }
    }

    def create(){
        respond new Country(params)
    }

    def save = {
        Country country = countryService.save(params)
        if(country.hasErrors()){
            respond country.errors, view:'create' // return [country:country]
        }else{
            flash.message = message(code: 'default.created.message', args: [message(code: 'country.label', default: 'Country'), country.id])
            redirect(action: "list")
        }
    }

    def edit = {
        Country country = countryService.get(params)
        if(country){
            respond country
        }else{
            notFound()
        }
    }

    def update = {
        Country country = countryService.save(params)
        if(country.hasErrors()){
            respond country.errors, view:'edit'
        }else{
            flash.message = message(code: 'default.updated.message', args: [message(code: 'country.label', default: 'Country'), country.id])
            redirect(action: "list")
        }
    }


    def delete = {
        Boolean deleted = countryService.delete(params)
        if(deleted){
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'country.label', default: 'Country'), params.id])
            redirect(action: "list")
        }else{
            notFound()
        }
    }

    def autoComplete = {
        render countryService.search(params)?.collect{return [value:it.id,text:it.name]} as JSON
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'country.label', default: 'Country'), params.id])
                redirect action: "list", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
