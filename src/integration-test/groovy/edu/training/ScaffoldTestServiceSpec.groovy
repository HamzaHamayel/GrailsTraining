package edu.training

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ScaffoldTestServiceSpec extends Specification {

    ScaffoldTestService scaffoldTestService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ScaffoldTest(...).save(flush: true, failOnError: true)
        //new ScaffoldTest(...).save(flush: true, failOnError: true)
        //ScaffoldTest scaffoldTest = new ScaffoldTest(...).save(flush: true, failOnError: true)
        //new ScaffoldTest(...).save(flush: true, failOnError: true)
        //new ScaffoldTest(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //scaffoldTest.id
    }

    void "test get"() {
        setupData()

        expect:
        scaffoldTestService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ScaffoldTest> scaffoldTestList = scaffoldTestService.list(max: 2, offset: 2)

        then:
        scaffoldTestList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        scaffoldTestService.count() == 5
    }

    void "test delete"() {
        Long scaffoldTestId = setupData()

        expect:
        scaffoldTestService.count() == 5

        when:
        scaffoldTestService.delete(scaffoldTestId)
        sessionFactory.currentSession.flush()

        then:
        scaffoldTestService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ScaffoldTest scaffoldTest = new ScaffoldTest()
        scaffoldTestService.save(scaffoldTest)

        then:
        scaffoldTest.id != null
    }
}
