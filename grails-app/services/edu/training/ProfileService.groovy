package edu.training

import grails.gorm.transactions.Transactional
import grails.orm.PagedResultList
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class ProfileService {

    @Transactional(readOnly = true)
    PagedResultList search(GrailsParameterMap params){


        Integer max = params.int("max")
        Integer offset = params.int("offset")
        String fullName = params["fullName"]
        Long id = params.long("id")
        Double salary = params.double("salary")
        Date dateOfBirth //= params.date("dateOfBirth","dd/MM/yyyy")




        PagedResultList result = Profile.createCriteria().list (offset:offset,max:max) {

            if(id){
                eq("id",id)
            }
            if(salary){
                eq("salary",salary)
            }

            if(dateOfBirth){
                ge("id",dateOfBirth)
            }

            if(fullName){
                like("fullName","%${fullName}%")
            }

        }

        return result
    }

    @Transactional(readOnly = true)
    Profile get(GrailsParameterMap params){
        Profile profile
        PagedResultList resultList = this.search(params)
        if(resultList){
            profile = resultList?.first()
        }
        return profile
    }

    Profile save(GrailsParameterMap params) {
        println("in save")
        Profile profile
        try {
            //it's update here
            if (params.long("id")) {
                profile = Profile.get(params.long("id"))
            } else {
                //it's save here
                profile = new Profile()
            }

            profile.properties = params
            profile.save(flush: true, failOnError: true)
        } catch (Exception e) {
            e.printStackTrace()
            transactionStatus.setRollbackOnly()
        }

        return profile

    }

    Boolean delete(GrailsParameterMap params) {
        Boolean deleted = false
        try {
            PagedResultList resultList = this.search(params)
            if(resultList){
                resultList*.delete(flush: true)
                deleted = true
            }
        } catch (Exception e) {
        }
        return deleted
    }

}
