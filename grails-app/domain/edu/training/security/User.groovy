package edu.training.security

import edu.training.Profile
import edu.training.Transaction
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

//    static auditable = true
    static auditable = [ignore:['accountExpired'],auditableProperties:['username','homepage','applicationName']]

    String username
    String password
    Boolean enabled = Boolean.TRUE
    Boolean accountExpired = Boolean.FALSE
    Boolean accountLocked= Boolean.FALSE
    Boolean passwordExpired= Boolean.FALSE

    String homepage
    String applicationName
    Date dateCreated
    Date lastUpdated


    static hasOne = [profile:Profile]

    static hasMany = [following:User,transactions:Transaction]


    static constraints = {
        username(nullable: false, blank: false,size: 3..20,unique: true)
        password(nullable: false, blank: false,password:true)
//        password(nullable: false, blank: false,password:true,validator:{val,obj, errors ->
//            if (val == obj.username) {
//                errors.rejectValue('password', 'user.password.errorValue')
//            }
//        })
        homepage(url: true,nullable: true)
        profile(nullable: true)
        applicationName(nullable: true,blank: false)
    }



    static mapping = {
        table '`USER`'
        password column: '`password`'
        profile lazy: false //tell grails to load profile with user
    }


    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }


    def beforeInsert(){
        if(this.username == "ali"){
            this.applicationName = 'APP_1'
        }else{
            this.applicationName = 'APP_2'
        }
    }

    def beforeUpdate() {
        if (!this.applicationName) {
            if (this.username == "ali") {
                this.applicationName = 'APP_1'
            } else {
                this.applicationName = 'APP_2'
            }
        }
    }


    def onSave = {
        println "new user inserted"
        // may optionally refer to newState map
    }
    def onDelete = {
        println "user was deleted"
        // may optionally refer to oldState map
    }
    def onChange = { oldMap,newMap ->
        println "user was changed with old data :$oldMap and new data: $newMap ..."
    }//*/




    @Override
    public String toString() {
        return  username
    }
}
