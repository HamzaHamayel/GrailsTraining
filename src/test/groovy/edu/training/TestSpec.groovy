package edu.training

import spock.lang.Specification

class TestSpec extends Specification {

    void "first test"(){
        when:
        1==1

        then:
        1==1
    }


    void "second test"(){

        String value

        given:
        value = "Test"

        expect:
        value == "Test"

        when:
        value = "Test1"

        then:
        value == "Test1"
    }
}






