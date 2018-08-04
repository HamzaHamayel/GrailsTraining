package edu.training

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import grails.web.servlet.mvc.GrailsParameterMap
import spock.lang.*


class CountryControllerSpec extends Specification implements ControllerUnitTest<CountryController>, DomainUnitTest<Country> {

    def countryService = Mock(CountryService) {
        //save method
        save(_) >> { GrailsParameterMap params ->
            def country = new Country()
            if(params["code"]){
                country.code = params["code"]
            }
            if(params["name"]){
                country.name = params["name"]
            }
            country.save()
            return country
        }

        //get method
        get(_) >> { GrailsParameterMap params ->
            if(params.long("id")){
                return new Country(code:'ps',name:'Palestine')
            }else{
                return null
            }
        }


        //delete method
        delete(_) >> { GrailsParameterMap params ->
            if(params.long("id")){
                return true
            }else{
                return false
            }
        }

        //declare render data table method
        renderDataTableData(_) >> [data:[],recordsTotal:0,recordsFiltered:0]

    }

    def populateValidParams(params) {
        assert params != null
        params["code"] = "ps"
        params["name"] = "Palestine"
    }


    void "Test index"(){
        when:"The index action is executed"
        controller.index()

        then:"Then redirect to list"
        response.redirectedUrl == "/country/list"
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
        controller.countryService = countryService

        when:"The filter action is executed"
        controller.filter()

        then:
        response.json.recordsTotal != null
        response.json.recordsFiltered == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.country != null
    }


    void "Test the create action returns the correct model with params"() {
        given:
        params.code = "jo"

        when:"The create action is executed"
        controller.create(params)

        then:"The model is correctly created"
        model.country != null
        model.country.code != null
        model.country.code == "jo"
    }


    void "Test the save action correctly persists"() {
        given:
        controller.countryService = countryService

        when:"The save action is executed with a valid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        controller.save(params)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/country/list'
        controller.flash.message != null
        controller.flash.message.contains("default.created.message") == true
    }

    void "Test the save action with an invalid params"() {
        given:
        controller.countryService = countryService

        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        controller.save(params)

        then:"object with errors is returned"
        view == 'create'
        model != [:]
        model.country.hasErrors() == true
    }


    void "Test the show action with a null id"() {
        given:
        controller.countryService = countryService

        when:"The show action is executed with a null domain"
        controller.show(params)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the show action with a valid id"() {
        given:
        controller.countryService = countryService

        when:"A domain instance is passed to the show action"
        params.id = 1
        controller.show(params)

        then:"A model is populated containing the domain instance"
        model.country != null
        model.country instanceof Country
    }

    void "Test the edit action with a null id"() {
        given:
        controller.countryService = countryService

        when:"The show action is executed with a null domain"
        controller.edit(params)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the edit action with a valid id"() {
        given:
        controller.countryService = countryService

        when:"A domain instance is passed to the show action"
        params.id = 2
        controller.edit(params)

        then:"A model is populated containing the domain instance"
        model.country instanceof Country
    }


    void "Test the update action with an invalid params"() {
        given:
        controller.countryService = countryService

        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(params)

        then:"object with errors is returned"
        view == 'edit'
        model != [:]
        model.country.hasErrors() == true
    }

    void "Test the update action correctly persists"() {
        given:
        controller.countryService = countryService

        when:"The update action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        populateValidParams(params)
        controller.save(params)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/country/list'
        controller.flash.message != null

    }

    void "Test the delete action with a invalid params"() {
        given:
        controller.countryService = countryService

        when:"The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(params)

        then:"A 404 is returned"
        response.redirectedUrl == '/country/list'
        flash.message != null
    }

    void "Test the delete action with an instance"() {
        given:
        controller.countryService = countryService

        when:"The domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        params.id = 2
        controller.delete(params)

        then:"The user is redirected to list"
        response.redirectedUrl == '/country/list'
        flash.message != null
        flash.message.contains("default.deleted.message") == true
    }
}






