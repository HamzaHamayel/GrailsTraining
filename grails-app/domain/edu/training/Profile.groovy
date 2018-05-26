package edu.training

import edu.training.security.User

import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

class Profile {

    Byte[] photo

    String fullName
    String bio
    String email
    String timezone
    String address

    Double salary

    Integer age

    Date dateOfBirth

    String description

    Country country
    User user


    static transients = ['age']

    static belongsTo = [Country,User]



    static constraints = {
        photo(nullable: true)
        salary(nullable: true,display:false)
        fullName(nullable: false,blank: false)
        bio(nullable: true,blank: false)
        email(nullable: true,blank: false,email: true)
        timezone(nullable: true,blank: false)
        address(nullable: true,blank: false)
        description(nullable: true,blank: true,widget:'textarea')
        country(nullable: false)
        user(nullable: false)
        dateOfBirth(nullable: false)
    }

    static mapping = {
        description type: 'text',sqlType: "CLOB"
        photo sqlType: "BLOB"
    }


    Integer getAge() {
        return Period.between(dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();
    }


    @Override
    public String toString() {
        return "fullName='" + fullName + '\'' +
                ", bio='" + bio + '\'' +
                ", email='" + email + '\'' +
                ", timezone='" + timezone + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", dateOfBirth=" + dateOfBirth +
                ", country=" + country +
                ", user=" + user
    }
}
