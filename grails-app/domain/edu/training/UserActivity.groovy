package edu.training

class UserActivity implements Serializable{

    User user
    String activityName

    static constraints = {
        user(nullable: false)
        activityName(nullable: false)
    }

    static mapping = {
        version false
        //supposed to make a composite PK
        id composite: ['user', 'activityName']
    }
}

