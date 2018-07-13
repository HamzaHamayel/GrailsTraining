package grailstraining

import edu.training.UserService
import grails.web.servlet.mvc.GrailsParameterMap

import java.time.LocalDateTime

class MyFirstJob {

    UserService userService

    static triggers = {
        // execute job once in 5
        simple name: 'simpleTrigger', startDelay: 10000l, repeatInterval: 50000l, repeatCount: 3
//        cron name: 'cronTrigger',startDelay: 10000l, cronExpression: "0 58 19 ? * SAT"
//        cron name: 'cronTrigger',startDelay: 10000l, cronExpression: "0 58 19 ? * SAT 2018"
//        custom name: 'customTrigger', triggerClass: MyTriggerClass, myParam: myValue, myAnotherParam: myAnotherValue
    }

    def execute() {
        // execute job
        println("EXECUTING MY FIRST JOB IN: ${LocalDateTime.now()} ")
        println("ENABLED USERS ARE : ${userService.search(new GrailsParameterMap(["enabled":"true"],null))}")
    }

}
