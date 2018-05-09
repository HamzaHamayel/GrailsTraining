package edu.training

import grails.converters.JSON
import static org.springframework.http.HttpStatus.*

class TransactionController {

    TransactionService transactionService

    def index = {
        redirect(action: "list")
    }

    def list = {
    }


    def filter = {
        render transactionService.renderDataTableData(params)
    }

    def show = {
        if (params.long("id")) {
            Map map = [transaction:transactionService.get(params)]
            respond map
        }else{
            notFound()
        }
    }

    def autoComplete = {
        render transactionService.search(params)?.collect{return [value:it.id,text:(it.user+"-"+it.classification)]} as JSON
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
                redirect action: "list", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
