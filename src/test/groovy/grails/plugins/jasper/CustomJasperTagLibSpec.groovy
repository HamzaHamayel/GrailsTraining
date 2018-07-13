package grails.plugins.jasper

import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification

class CustomJasperTagLibSpec extends Specification implements TagLibUnitTest<CustomJasperTagLib> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
