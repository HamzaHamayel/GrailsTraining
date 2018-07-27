package edu.training

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TestScaffoldServiceSpec extends Specification {

    TestScaffoldService testScaffoldService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new TestScaffold(...).save(flush: true, failOnError: true)
        //new TestScaffold(...).save(flush: true, failOnError: true)
        //TestScaffold testScaffold = new TestScaffold(...).save(flush: true, failOnError: true)
        //new TestScaffold(...).save(flush: true, failOnError: true)
        //new TestScaffold(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //testScaffold.id
    }

    void "test get"() {
        setupData()

        expect:
        testScaffoldService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ScaffoldTest> testScaffoldList = testScaffoldService.list(max: 2, offset: 2)

        then:
        testScaffoldList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        testScaffoldService.count() == 5
    }

    void "test delete"() {
        Long testScaffoldId = setupData()

        expect:
        testScaffoldService.count() == 5

        when:
        testScaffoldService.delete(testScaffoldId)
        sessionFactory.currentSession.flush()

        then:
        testScaffoldService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ScaffoldTest testScaffold = new ScaffoldTest()
        testScaffoldService.save(testScaffold)

        then:
        testScaffold.id != null
    }
}
