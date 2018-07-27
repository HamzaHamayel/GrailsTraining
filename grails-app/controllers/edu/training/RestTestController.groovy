package edu.training

import grails.rest.RestfulController
import grails.web.servlet.mvc.GrailsParameterMap

class RestTestController extends RestfulController {

    static responseFormats = ['json', 'xml']

    RestTestController() {
        super(RestTest)
    }

    @Override
    protected Integer countResources() {
        RestTest.count()
    }

    @Override
    protected List<RestTest> listAllResources(Map params) {
        RestTest.where {
            if(params?.orderNumber) {
                orderNumber =~ params?.orderNumber
            }
        }.findAll()
    }
    @Override
    protected RestTest queryForResource(Serializable id) {
        RestTest.where {
            id == id
        }.find()
    }

}
