package edu.training

class Transaction {

    TransactionClassification classification
    User user
    Date dateCreated
    Date lastUpdated

    TransactionHistory history


    static belongsTo = [User]

    static embedded = ['history']


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
        history.createdBy = "SYSTEM_CREATED"
    }

    def beforeUpdate() {
        if(!history){
            history = new TransactionHistory()
            history.createdBy = "SYSTEM_CREATED"
        }
        history.lastUpdateBy = "SYSTEM_UPDATED"
    }

}
