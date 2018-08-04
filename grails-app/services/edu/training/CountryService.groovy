package edu.training

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.gorm.PagedResultList
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class CountryService {

    @Transactional(readOnly = true)
    PagedResultList search(GrailsParameterMap params) {


        Integer max = params.int("max")
        Integer offset = params.int("offset")
        String code = params["code"]
        String name = params["name"]
        Long id = params.long("id")
        String sSearch = params["sSearch"]
        String orderBy = params["orderBy"]
        String orderDirection = params["dir"]



        PagedResultList result = Country.createCriteria().list(offset: offset, max: max) {

            if (sSearch) {
                or {
                    like("code", "%${sSearch}%")
                    like("name", "%${sSearch}%")
                }
            }

            if (id) {
                eq("id", id)
            }
            if (code) {
                like("code", "%${code}%")
            }
            if (name) {
                like("name", "%${name}%")
            }

            if(orderBy && orderDirection){
                order(orderBy,orderDirection)
            }

        }

        return result
    }

    @Transactional(readOnly = true)
    Country get(GrailsParameterMap params){
        Country country
        PagedResultList resultList = this.search(params)
        if(resultList){
            country = resultList?.first()
        }
        return country
    }

    Country save(GrailsParameterMap params) {
        Country country
        try {
            //it's update here
            if (params.long("id")) {
                country = Country.get(params.long("id"))
            } else {
                //it's save here
                country = new Country()
            }

            country.properties = params
            country.save(flush: true, failOnError: true)
        } catch (Exception e) {
            e.printStackTrace()
            transactionStatus.setRollbackOnly()
        }

        return country

    }

    Boolean delete(GrailsParameterMap params) {
        Boolean deleted = false
        try {
            PagedResultList resultList = this.search(params)
            if(resultList){
                resultList*.delete(flush: true)
                deleted = true
            }
        } catch (Exception e) {
        }
        return deleted
    }

    String renderDataTableData(GrailsParameterMap params){
        PagedResultList pagedResultList = this.search(params)
        Map map = [:]
        map.data = pagedResultList.collect{Country country->
            return [
                    id:country?.id,
                    code:country?.code,
                    name:country?.name,
            ]
        }
        map.draw = params.draw
        map.recordsTotal = pagedResultList?.totalCount
        map.recordsFiltered = pagedResultList?.totalCount
        return map as JSON
    }

}
