package edu.training

import edu.training.security.User
import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.orm.PagedResultList
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class UserService {

    @Transactional(readOnly = true)
    PagedResultList search(GrailsParameterMap params) {


        Integer max = params.int("max")
        Integer offset = params.int("offset")
        Long id = params.long("id")
        String sSearch = params["sSearch"]
        String username = params["username"]
        String homepage = params["homepage"]
        String applicationName = params["applicationName"]
        String orderBy = params["orderBy"]
        String orderDirection = params["dir"]



        PagedResultList result = User.createCriteria().list(offset: offset, max: max) {

            if (sSearch) {
                or {
                    like("username", "%${sSearch}%")
                    like("applicationName", "%${sSearch}%")
                }
            }

            if (id) {
                eq("id", id)
            }
            if (username) {
                like("username", "%${username}%")
            }
            if (applicationName) {
                like("applicationName", "%${applicationName}%")
            }
            if (homepage) {
                like("homepage", "%${homepage}%")
            }

            if(orderBy && orderDirection){
                order(orderBy,orderDirection)
            }

        }

        return result
    }

    @Transactional(readOnly = true)
    User get(GrailsParameterMap params){
        User user
        PagedResultList resultList = this.search(params)
        if(resultList){
            user = resultList?.first()
        }
        return user
    }

    User save(GrailsParameterMap params) {
        User user
        try {
            //it's update here
            if (params.long("id")) {
                user = User.get(params.long("id"))
            } else {
                //it's save here
                user = new User()
            }

            user.properties = params
            user.save(flush: true, failOnError: true)
        } catch (Exception e) {
            e.printStackTrace()
            transactionStatus.setRollbackOnly()
        }

        return user

    }

    User saveByCommand(UserCommand userCommand) {
        User user
        try {
            //it's update here
            if (userCommand?.id) {
                user = User.get(userCommand?.id)
            } else {
                //it's save here
                user = new User()
            }

            user.properties = userCommand.properties

            println("username: ${user?.username}")
            println("homepage: ${user?.homepage}")
            println("password: ${user?.password}")
            println("applicationName: ${user?.applicationName}")

            user.save(flush: true, failOnError: true)
        } catch (Exception e) {
            e.printStackTrace()
            transactionStatus.setRollbackOnly()
        }

        return user

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
        map.data = pagedResultList.collect{User user->
            return [
                    id:user?.id,
                    username:user?.username,
                    homepage:user?.homepage,
                    applicationName:user?.applicationName,
            ]
        }
        map.draw = params.draw
        map.recordsTotal = pagedResultList?.totalCount
        map.recordsFiltered = pagedResultList?.totalCount
        return map as JSON
    }

}
