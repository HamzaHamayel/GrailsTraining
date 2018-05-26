package edu.training

import edu.training.security.User

class Transaction {

    transient springSecurityService
    
    TransactionClassification classification
    User user
    Date dateCreated
    Date lastUpdated

    TransactionHistory history


    static belongsTo = [User]

    static embedded = ['history']
    
    static transients = ['springSecurityService']


    static constraints = {
        classification(nullable: false)
        user(nullable: false)
        history(nullable: true)
    }

    static mapping = {
        tablePerHierarchy false
    }


    @Override
    public String toString() {
        return "classification=" + classification +
                ", user=" + user;
    }



    def beforeInsert(){
        if(!history){
            history = new TransactionHistory()
        }
        history.createdBy = springSecurityService?.principal?.username
        history.lastUpdateBy = springSecurityService?.principal?.username
    }

    def beforeUpdate() {
        if(!history){
            history = new TransactionHistory()
            history.createdBy = springSecurityService?.principal?.username
        }
        history.lastUpdateBy = springSecurityService?.principal?.username
    }

}
