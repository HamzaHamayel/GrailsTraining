package edu.training

import static org.springframework.http.HttpStatus.NOT_FOUND

class CountryManagementController {

    CountryManagementService countryManagementService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index = {
        redirect(action: "list")
    }

    def list = {
    }


    def filter = {
        render countryManagementService.renderDataTableData(params)
    }

    def show = {
        if (params.long("id")) {
            respond countryManagementService.get(params)
        }else{
            notFound()
        }
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
