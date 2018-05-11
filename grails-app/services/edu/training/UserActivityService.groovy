package edu.training

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.orm.PagedResultList
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class UserActivityService {

    @Transactional(readOnly = true)
    PagedResultList search(GrailsParameterMap params) {


        Integer max = params.int("max")
        Integer offset = params.int("offset")
        String sSearch = params["sSearch"]
        String userId = params["userId"]
        Long userIdLong = params.long("user.id")
        String activityName = params["activityName"]
        String orderBy = params["orderBy"]
        String orderDirection = params["dir"]



        PagedResultList result = UserActivity.createCriteria().list(offset: offset, max: max) {

            if (sSearch) {
                or {
                    like("activityName", "%${sSearch}%")
                }
            }

            if (activityName) {
                like("activityName", "%${activityName}%")
            }

            if (userId || userIdLong) {
                user {
                    if (userId) {
                        like("userId", "%${userId}%")
                    }
                    if(userIdLong){
                       eq("id", userIdLong)
                   }
                }
            }

            if(orderBy && orderDirection){
                order(orderBy,orderDirection)
            }

        }

        return result
    }

    @Transactional(readOnly = true)
    UserActivity get(GrailsParameterMap params){
        UserActivity userActivity
        PagedResultList resultList = this.search(params)
        if(resultList){
            userActivity = resultList?.first()
        }
        return userActivity
    }

    UserActivity save(GrailsParameterMap params) {
        UserActivity userActivity
        try {
            //it's update here
            if (params.long("id")) {
                userActivity = UserActivity.get(params.long("id"))
            } else {
                //it's save here
                userActivity = new UserActivity()
            }

            userActivity.properties = params
            userActivity.save(flush: true, failOnError: true)
        } catch (Exception e) {
            e.printStackTrace()
            transactionStatus.setRollbackOnly()
        }

        return userActivity

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

    String renderDataTableData(GrailsParameterMap params){
        PagedResultList pagedResultList = this.search(params)
        Map map = [:]
        map.data = pagedResultList.collect{UserActivity userActivity->
            return [
                    user:userActivity?.user?.userId,
                    activityName:userActivity?.activityName,
            ]
        }
        map.draw = params.draw
        map.recordsTotal = pagedResultList?.totalCount
        map.recordsFiltered = pagedResultList?.totalCount
        return map as JSON
    }

}
