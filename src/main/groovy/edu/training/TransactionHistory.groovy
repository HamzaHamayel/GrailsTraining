package edu.training

class TransactionHistory {


    String createdBy
    String lastUpdateBy

    static constraints = {
        createdBy(nullable: true)
        lastUpdateBy(nullable: true)
    }

}