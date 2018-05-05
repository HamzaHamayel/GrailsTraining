package edu.training

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PersonStatusController {

    PersonStatusService personStatusService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond personStatusService.list(params), model:[personStatusCount: personStatusService.count()]
    }

    def show(Long id) {
        respond personStatusService.get(id)
    }

    def create() {
        respond new PersonStatus(params)
    }

    def save(PersonStatus personStatus) {
        if (personStatus == null) {
            notFound()
            return
        }

        try {
            personStatusService.save(personStatus)
        } catch (ValidationException e) {
            respond personStatus.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'personStatus.label', default: 'PersonStatus'), personStatus.id])
                redirect personStatus
            }
            '*' { respond personStatus, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond personStatusService.get(id)
    }

    def update(PersonStatus personStatus) {
        if (personStatus == null) {
            notFound()
            return
        }

        try {
            personStatusService.save(personStatus)
        } catch (ValidationException e) {
            respond personStatus.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'personStatus.label', default: 'PersonStatus'), personStatus.id])
                redirect personStatus
            }
            '*'{ respond personStatus, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        personStatusService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'personStatus.label', default: 'PersonStatus'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'personStatus.label', default: 'PersonStatus'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
