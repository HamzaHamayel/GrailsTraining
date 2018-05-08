package edu.training

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.orm.PagedResultList
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class TransactionService {

    @Transactional(readOnly = true)
    PagedResultList search(GrailsParameterMap params) {


        Integer max = params.int("max")
        Integer offset = params.int("offset")
        Long id = params.long("id")
        String sSearch = params["sSearch"]
        String orderBy = params["orderBy"]
        String orderDirection = params["dir"]



        PagedResultList result = Transaction.createCriteria().list(offset: offset, max: max) {

            if (sSearch) {
                or {
                    user {
                        like("userId", "%${sSearch}%")
                    }
                }
            }

            if (id) {
                eq("id", id)
            }

            if(orderBy && orderDirection){
                order(orderBy,orderDirection)
            }

        }

        return result
    }

    @Transactional(readOnly = true)
    Transaction get(GrailsParameterMap params){
        Transaction transaction
        PagedResultList resultList = this.search(params)
        if(resultList){
            transaction = resultList?.first()
        }
        return transaction
    }

    String renderDataTableData(GrailsParameterMap params){
        PagedResultList pagedResultList = this.search(params)
        Map map = [:]
        map.data = pagedResultList.collect{Transaction transaction->
            return [
                    id:transaction?.id,
                    user:transaction?.user?.userId,
                    transaction:transaction?.classification,
                    dateCreated:transaction?.dateCreated?.format("dd/MM/yyyy")
            ]
        }
        map.draw = params.draw
        map.recordsTotal = pagedResultList?.totalCount
        map.recordsFiltered = pagedResultList?.totalCount
        return map as JSON
    }

}
