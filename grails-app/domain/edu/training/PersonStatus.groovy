package edu.training

class PersonStatus {

    String code
    String name

    static constraints = {
        name display:false
    }

    static mapping = {
        id generator: 'assigned'
        version false
        id name:'code'
    }
}
