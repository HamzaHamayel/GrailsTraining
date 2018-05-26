package edu.training

class UserCommand implements grails.validation.Validateable {

    Long id
    String username
    String password
    String homepage
    String applicationName

    static constraints = {
        id(nullable: true)
        username(size: 3..20,unique: true)
        password(password:true,size: 6..8,validator:{val, obj, errors ->
            if (val == obj.username) {
                errors.rejectValue('password', 'user.password.errorValue')
            }
        })
        homepage(url: true,nullable: true)
        applicationName(nullable: true,blank: false)
    }

}
