package edu.training

import grails.orm.PagedResultList
import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockHttpServletRequest
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CountryServiceSpec extends Specification {

    @Autowired
    CountryService countryService

    SessionFactory sessionFactory

    private Country generateCountry(String code,String name){
        return Country.findByCode(code) ?: new Country(code: code,name:name).save(flush: true)
    }

    private Long setupData() {
        generateCountry("eg","Egypt")
        generateCountry("jo","Jordan")
        Country country = generateCountry("ps","Palestine")
        assert country.id != null
        return country.id
    }

    private GrailsParameterMap getParams(){
        return new GrailsParameterMap([:],new MockHttpServletRequest())
    }


    void "test list"() {
        setupData()
        GrailsParameterMap params = getParams()
        given:
        params.max = 2
        params.offset = 0

        when:
        PagedResultList pagedResultList = countryService.search(params)

        then:
        pagedResultList.totalCount == 3
        pagedResultList.size() == 2
        pagedResultList[0].code == "eg"
    }

    void "test list search"() {
        setupData()
        GrailsParameterMap params = getParams()

        given:
        params.code = "jo"

        when:
        PagedResultList pagedResultList = countryService.search(params)

        then:
        pagedResultList.totalCount == 1
        pagedResultList.size() == 1
        pagedResultList[0].code == "jo"
    }

    void "test get"() {
        Long countryId = setupData()
        GrailsParameterMap params = getParams()

        given:
        params.id = countryId

        when:
        Country country = countryService.get(params)

        then:
        country.id != null
        country.code == "ps"

    }

    void "test save"() {

        GrailsParameterMap params = getParams()
        given:
        params.code = "en"
        params.name = "England"

        when:
        Country country = countryService.save(params)

        then:
        country.id != null
        country.hasErrors() == false
    }

    void "test fail save"() {

        GrailsParameterMap params = getParams()

        given:
        params.name = "israel"
        params.code = "il"

        when:
        Country country = countryService.save(params)

        then:
        country.id == null
        country.hasErrors() == true
    }


    void "test delete"() {

        Long countryId = setupData()
        GrailsParameterMap params = getParams()

        given:
        params.id = countryId

        expect:
        Country.count() == 3

        when:
        Boolean deleteResult = countryService.delete(params)
        sessionFactory.currentSession.flush()

        then:
        deleteResult == true
        Country.count() == 2
    }

    void "test renderDataTableData"() {
        setupData()
        GrailsParameterMap params = getParams()

        given:
        params.max = 10
        params.offset = 0

        when:
        String json = countryService.renderDataTableData(params)

        then:
        json != null
        json.contains("recordsTotal") == true
        json.contains("data") == true
    }
}
