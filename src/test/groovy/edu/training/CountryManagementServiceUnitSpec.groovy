package edu.training

import grails.buildtestdata.BuildDomainTest
import grails.testing.services.ServiceUnitTest
import org.junit.*
import grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.mock.web.MockHttpServletRequest
import spock.lang.Specification
import grails.gorm.PagedResultList


class CountryManagementServiceUnitSpec extends Specification implements ServiceUnitTest<CountryManagementService>, BuildDomainTest<Country> {

    private Country generateCountry(String code,String name){
        return Country.build(code:code,name: name).save(flush: true)
    }

    @Before
    void injectService(){
        service.countryService = Mock(CountryService){
            search(_) >> { GrailsParameterMap params ->
                return Country.createCriteria().list(max:params.max,offset:params.offset){

                }
            }
        }

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
        PagedResultList pagedResultList = service.search(params)

        then:
        pagedResultList.totalCount == 3
        pagedResultList.size() == 2
        pagedResultList[0].code == "eg"
    }

}






