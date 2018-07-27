package edu.training

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class Rest2TestServiceSpec extends Specification {

    Rest2TestService rest2TestService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Rest2Test(...).save(flush: true, failOnError: true)
        //new Rest2Test(...).save(flush: true, failOnError: true)
        //Rest2Test rest2Test = new Rest2Test(...).save(flush: true, failOnError: true)
        //new Rest2Test(...).save(flush: true, failOnError: true)
        //new Rest2Test(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //rest2Test.id
    }

    void "test get"() {
        setupData()

        expect:
        rest2TestService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Rest2Test> rest2TestList = rest2TestService.list(max: 2, offset: 2)

        then:
        rest2TestList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        rest2TestService.count() == 5
    }

    void "test delete"() {
        Long rest2TestId = setupData()

        expect:
        rest2TestService.count() == 5

        when:
        rest2TestService.delete(rest2TestId)
        sessionFactory.currentSession.flush()

        then:
        rest2TestService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Rest2Test rest2Test = new Rest2Test()
        rest2TestService.save(rest2Test)

        then:
        rest2Test.id != null
    }
}
