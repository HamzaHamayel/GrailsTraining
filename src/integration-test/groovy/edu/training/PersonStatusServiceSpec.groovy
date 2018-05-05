package edu.training

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PersonStatusServiceSpec extends Specification {

    PersonStatusService personStatusService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PersonStatus(...).save(flush: true, failOnError: true)
        //new PersonStatus(...).save(flush: true, failOnError: true)
        //PersonStatus personStatus = new PersonStatus(...).save(flush: true, failOnError: true)
        //new PersonStatus(...).save(flush: true, failOnError: true)
        //new PersonStatus(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //personStatus.id
    }

    void "test get"() {
        setupData()

        expect:
        personStatusService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PersonStatus> personStatusList = personStatusService.list(max: 2, offset: 2)

        then:
        personStatusList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        personStatusService.count() == 5
    }

    void "test delete"() {
        Long personStatusId = setupData()

        expect:
        personStatusService.count() == 5

        when:
        personStatusService.delete(personStatusId)
        sessionFactory.currentSession.flush()

        then:
        personStatusService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PersonStatus personStatus = new PersonStatus()
        personStatusService.save(personStatus)

        then:
        personStatus.id != null
    }
}
