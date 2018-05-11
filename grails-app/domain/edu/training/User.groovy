package edu.training

class User {

    String userId
    String password
    String homepage
    String applicationName
    Date dateCreated
    Date lastUpdated


    static hasOne = [profile:Profile]

    static hasMany = [following:User,transactions:Transaction]

    static constraints = {
        userId(size: 3..20,unique: true)
        password(password:true,size: 6..8,validator:{val, obj, errors ->
        if (val == obj.userId) {
            errors.rejectValue('password', 'user.password.errorValue')
        }
        })
        homepage(url: true,nullable: true)
        profile(nullable: true)
        applicationName(nullable: true,blank: false)
    }

    static mapping = {
        table '`USER`'
        password column: '`password`'
        profile lazy: false //tell grails to load profile with user
    }


    def beforeInsert(){
        if(this.userId == "ali"){
            this.applicationName = 'APP_1'
        }else{
            this.applicationName = 'APP_2'
        }
    }

    def beforeUpdate() {
        if (!this.applicationName) {
            if (this.userId == "ali") {
                this.applicationName = 'APP_1'
            } else {
                this.applicationName = 'APP_2'
            }
        }
    }


    @Override
    public String toString() {
        return  userId
    }
}
