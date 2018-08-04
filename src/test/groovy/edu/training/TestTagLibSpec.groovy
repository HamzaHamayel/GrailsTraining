package edu.training

import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification

class TestTagLibSpec extends Specification implements TagLibUnitTest<TestTagLib> {

    void "test simple tag as method"() {

        given:
        String value = "value1"
        String body = "<p>body</p>"
        String result = "<div>${value}</div><div>${body}</div>"

        expect:
        tagLib.example(value: value){body} == result
        applyTemplate("""<custom:example value="${value}">${body}</custom:example>""") == result
    }
}