package edu.training

import org.springframework.web.multipart.MultipartFile

class RegistrationCommand implements grails.validation.Validateable {

    MultipartFile multipartFile

    byte[] photo

    Long id

    String username
    String password
    String homepage
    String applicationName

    String fullName
    String bio
    String email
    String timezone
    String address

    Double salary

    Date dateOfBirth

    String description

    Long countryId
    String countryName



    static constraints = {
        username(size: 3..20, unique: true)
        password(password: true, size: 6..8, validator: { val, obj, errors ->
            if (val == obj.username) {
                errors.rejectValue('password', 'user.password.errorValue')
            }
        })
        homepage(url: true, nullable: true)
        applicationName(nullable: true, blank: false)


        photo(nullable: true)

        salary(nullable: true, display: false)
        fullName(nullable: false, blank: false)
        bio(nullable: true, blank: false)
        email(nullable: true, blank: false, email: true)
        timezone(nullable: true, blank: false)
        address(nullable: true, blank: false)
        description(nullable: true, blank: true, widget: 'textarea')
        dateOfBirth(nullable: false)


        countryId(nullable: false)
        countryName(nullable: true)


        id(nullable: true)


        multipartFile nullable: true, validator: { val, obj ->
            if ( val == null ) {
                return true
            }
            if ( val.empty ) {
                return true
            }

                ['jpeg', 'jpg', 'png'].any { extension ->
                    val.originalFilename?.toLowerCase()?.endsWith(extension)
                }
        }

    }

}
