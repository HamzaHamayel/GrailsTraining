package edu.training

class RestTest {

    String orderNumber
    String firstName
    String lastName

    static constraints = {
        orderNumber()
        firstName()
        lastName()
    }

}