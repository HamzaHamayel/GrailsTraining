package edu.training

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FieldTestServiceSpec extends Specification {

    FieldTestService fieldTestService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new FieldTest(...).save(flush: true, failOnError: true)
        //new FieldTest(...).save(flush: true, failOnError: true)
        //FieldTest fieldTest = new FieldTest(...).save(flush: true, failOnError: true)
        //new FieldTest(...).save(flush: true, failOnError: true)
        //new FieldTest(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //fieldTest.id
    }

    void "test get"() {
        setupData()

        expect:
        fieldTestService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<FieldTest> fieldTestList = fieldTestService.list(max: 2, offset: 2)

        then:
        fieldTestList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        fieldTestService.count() == 5
    }

    void "test delete"() {
        Long fieldTestId = setupData()

        expect:
        fieldTestService.count() == 5

        when:
        fieldTestService.delete(fieldTestId)
        sessionFactory.currentSession.flush()

        then:
        fieldTestService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        FieldTest fieldTest = new FieldTest()
        fieldTestService.save(fieldTest)

        then:
        fieldTest.id != null
    }
}
