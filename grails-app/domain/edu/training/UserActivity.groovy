package edu.training

import edu.training.security.User

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

