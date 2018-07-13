package edu.training

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TestFieldServiceSpec extends Specification {

    TestFieldService testFieldService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new TestField(...).save(flush: true, failOnError: true)
        //new TestField(...).save(flush: true, failOnError: true)
        //TestField testField = new TestField(...).save(flush: true, failOnError: true)
        //new TestField(...).save(flush: true, failOnError: true)
        //new TestField(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //testField.id
    }

    void "test get"() {
        setupData()

        expect:
        testFieldService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<FieldTest> testFieldList = testFieldService.list(max: 2, offset: 2)

        then:
        testFieldList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        testFieldService.count() == 5
    }

    void "test delete"() {
        Long testFieldId = setupData()

        expect:
        testFieldService.count() == 5

        when:
        testFieldService.delete(testFieldId)
        sessionFactory.currentSession.flush()

        then:
        testFieldService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        FieldTest testField = new FieldTest()
        testFieldService.save(testField)

        then:
        testField.id != null
    }
}
