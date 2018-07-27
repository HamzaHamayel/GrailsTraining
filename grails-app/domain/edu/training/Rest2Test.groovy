package edu.training

import grails.rest.*

//@Resource(uri='/restDo')
@Resource(uri='/restDo', readOnly=true)
class Rest2Test {

    String name
    String address

    static constraints = {
    }

}