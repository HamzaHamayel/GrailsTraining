package edu.training

import edu.training.security.User
import grails.buildtestdata.BuildDataTest
import grails.buildtestdata.mixin.Build
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

@Build([User,Profile,Country])
class RegistrationServiceUnitSpec extends Specification implements ServiceUnitTest<RegistrationService>,BuildDataTest {

    void "test save"() {
        Country country = Country.build(code:'ab').save(flush:true)
        User user = User.build(username: "abcdef",save:false)//.save(flush:true)
        Profile profile = Profile.build()//.save(flush:true)
        RegistrationCommand registrationCommand = new RegistrationCommand(
                 fullName:profile.fullName,
                 bio:profile.bio,
                 email:profile.email,
                 timezone:profile.timezone,
                 address:profile.address,
                 salary:profile.salary,
                 dateOfBirth:profile.dateOfBirth,
                username:user.username,
                password: user.password,
                countryId: country.id,
                countryName: country.name
        )
        when:
        RegistrationCommand registrationCommandIns = service.save(registrationCommand)

        then:
        registrationCommandIns.hasErrors() == false
        registrationCommandIns.id != null
    }

}






