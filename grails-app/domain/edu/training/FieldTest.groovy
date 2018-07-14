package edu.training

class FieldTest {

    String name
    String password
    Double salary
    Date dateOfBirth

    static constraints = {
        name(blank: false,nullable: false)
        password(blank: false,nullable: false,password:true)
        salary(nullable: false,display:true)
        dateOfBirth(nullable: false)
    }
}
