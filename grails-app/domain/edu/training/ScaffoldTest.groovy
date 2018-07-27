package edu.training

class ScaffoldTest {

    String firstName
    String lastName
    String orderNumber
    Double salary
    Date created

    static constraints = {
        firstName()
        lastName()
        orderNumber()
        created()
        salary()
    }

}