package edu.training

class UserCommand implements grails.validation.Validateable {

    Long id
    String userId
    String password
    String homepage
    String applicationName

    static constraints = {
        id(nullable: true)
        userId(size: 3..20,unique: true)
        password(password:true,size: 6..8,validator:{val, obj, errors ->
            if (val == obj.userId) {
                errors.rejectValue('password', 'user.password.errorValue')
            }
        })
        homepage(url: true,nullable: true)
        applicationName(nullable: true,blank: false)
    }

}
