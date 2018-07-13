package edu.training

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FieldTestController {

    FieldTestService fieldTestService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond fieldTestService.list(params), model:[fieldTestCount: fieldTestService.count()]
    }

    def show(Long id) {
        respond fieldTestService.get(id)
    }

    def create() {
        respond new FieldTest(params)
    }

    def save(FieldTest fieldTest) {
        if (fieldTest == null) {
            notFound()
            return
        }

        try {
            fieldTestService.save(fieldTest)
        } catch (ValidationException e) {
            respond fieldTest.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fieldTest.label', default: 'FieldTest'), fieldTest.id])
                redirect fieldTest
            }
            '*' { respond fieldTest, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond fieldTestService.get(id)
    }

    def update(FieldTest fieldTest) {
        if (fieldTest == null) {
            notFound()
            return
        }

        try {
            fieldTestService.save(fieldTest)
        } catch (ValidationException e) {
            respond fieldTest.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'fieldTest.label', default: 'FieldTest'), fieldTest.id])
                redirect fieldTest
            }
            '*'{ respond fieldTest, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        fieldTestService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'fieldTest.label', default: 'FieldTest'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fieldTest.label', default: 'FieldTest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
