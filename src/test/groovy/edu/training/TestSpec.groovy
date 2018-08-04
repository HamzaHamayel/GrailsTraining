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


        when:
        value = "Test2"

        then:
        value == "Test2"


        when:
        value = "Test3"

        then:
        value == "Test3"
    }
}






