package edu.training

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.gorm.PagedResultList
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class CountryManagementService {

    CountryService countryService


    Integer getNumber(GrailsParameterMap params) {
        return 100
    }

    @Transactional(readOnly = true)
    PagedResultList search(GrailsParameterMap params) {
        return countryService.search(params)
    }

    @Transactional(readOnly = true)
    Country get(GrailsParameterMap params){
        countryService.get(params)
    }

    @Transactional(readOnly = true)
    String renderDataTableData(GrailsParameterMap params){
        countryService.renderDataTableData(params)
    }

}
