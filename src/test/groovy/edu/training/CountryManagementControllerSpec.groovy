package edu.training

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import grails.web.servlet.mvc.GrailsParameterMap
import spock.lang.Specification

class CountryManagementControllerSpec extends Specification implements ControllerUnitTest<edu.training.CountryManagementController>, DomainUnitTest<Country> {

    def countryManagementService = Mock(CountryManagementService) {
        //get method
        get(_) >> { GrailsParameterMap params ->
            if(params.long("id")){
                return new Country(code:'ps',name:'Palestine')
            }else{
                return null
            }
        }

        //declare render data table method
        renderDataTableData(_) >> [data:[],recordsTotal:0,recordsFiltered:0]

    }
    


    void "Test index"(){
        when:"The index action is executed"
        controller.index()

        then:"Then redirect to list"
        response.redirectedUrl == "/countryManagement/list"
    }


    void "Test the list action returns empty model"() {

        when:"The list action is executed"
        controller.list()

        then:"The model is correct"
        model == [:]
    }


    void "Test the filter action returns the correct model"() {
        given:
        request.makeAjaxRequest()
        controller.countryManagementService = countryManagementService

        when:"The filter action is executed"
        controller.filter()

        then:
        response.json.recordsTotal != null
        response.json.recordsFiltered == 0
    }


    void "Test the show action with a null id"() {
        given:
        controller.countryManagementService = countryManagementService

        when:"The show action is executed with a null domain"
        controller.show(params)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the show action with a valid id"() {
        given:
        controller.countryManagementService = countryManagementService

        when:"A domain instance is passed to the show action"
        params.id = 1
        controller.show(params)

        then:"A model is populated containing the domain instance"
        model.country != null
        model.country instanceof Country
    }
}






