package edu.training

import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification

class MessageTagLibSpec extends Specification implements TagLibUnitTest<MessageTagLib> {

    void "test simple tag as method"() {

        given:
        String value = "abc"
        String result = """<div class="message" role="status">${value}</div>"""

        expect:
        tagLib.formatMessage(message: value) == result
        applyTemplate("""<msg:formatMessage message="${value}" />""") == result
    }
}