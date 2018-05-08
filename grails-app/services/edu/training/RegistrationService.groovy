package edu.training

import grails.gorm.transactions.Transactional

@Transactional
class RegistrationService {

    RegistrationCommand save(RegistrationCommand registration) {
        try {

            User user = new User()
            user.properties = registration.properties
            user.save(flush: true, failOnError: true)

            Profile profile = new Profile()

            profile.properties = registration.properties
            profile.country = Country.get(registration.countryId)
            profile.user = user
            profile.photo = registration?.multipartFile?.bytes

            profile.save(flush: true, failOnError: true)


            if(!user.hasErrors() && !profile.hasErrors()){
                registration.id = user?.id
            }

        } catch (Exception e) {
            e.printStackTrace()
            transactionStatus.setRollbackOnly()
        }

        return registration

    }
}
