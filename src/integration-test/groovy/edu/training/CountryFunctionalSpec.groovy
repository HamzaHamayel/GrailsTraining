package edu.training

import grails.testing.mixin.integration.Integration
import grails.transaction.*

import geb.spock.*
import org.springframework.boot.test.context.SpringBootTest

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration(applicationClass=grailstraining.Application)
@Rollback
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CountryFunctionalSpec extends GebSpec {

    def setup() {
    }

    def cleanup() {
    }

    void 'test login'() {
        when: 'try to go to user list'
        go '/TrainingApplication/userManagement/list'

        then: 'it is redirected to login page'
        at LoginPage

        when: 'signs in with a ROLE_TEST user'
        login("test", "test")

        then: 'he gets access to the user management page'
        at UserManagementPage
    }
}