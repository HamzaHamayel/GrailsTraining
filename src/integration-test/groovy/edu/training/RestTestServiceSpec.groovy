package edu.training

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RestTestServiceSpec extends Specification {

    RestTestService restTestService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new RestTest(...).save(flush: true, failOnError: true)
        //new RestTest(...).save(flush: true, failOnError: true)
        //RestTest restTest = new RestTest(...).save(flush: true, failOnError: true)
        //new RestTest(...).save(flush: true, failOnError: true)
        //new RestTest(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //restTest.id
    }

    void "test get"() {
        setupData()

        expect:
        restTestService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<RestTest> restTestList = restTestService.list(max: 2, offset: 2)

        then:
        restTestList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        restTestService.count() == 5
    }

    void "test delete"() {
        Long restTestId = setupData()

        expect:
        restTestService.count() == 5

        when:
        restTestService.delete(restTestId)
        sessionFactory.currentSession.flush()

        then:
        restTestService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        RestTest restTest = new RestTest()
        restTestService.save(restTest)

        then:
        restTest.id != null
    }
}
