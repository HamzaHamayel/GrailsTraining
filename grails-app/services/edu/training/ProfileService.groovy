package edu.training

import grails.converters.JSON
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import grails.orm.PagedResultList
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.util.WebUtils
import org.springframework.web.multipart.MultipartFile

import java.text.DecimalFormat

@Transactional
class ProfileService {

    GrailsApplication grailsApplication

    @Transactional(readOnly = true)
    PagedResultList search(GrailsParameterMap params) {

        Integer max = params.int("max")
        Integer offset = params.int("offset")
        String fullName = params["fullName"]
        Long id = params.long("id")
        Double salary = params.double("salary")
        Date dateOfBirth = params.date("dateOfBirth")
        String q = params["q"]
        String sSearch = params["sSearch"]
        Double sSearchInt = params.double("sSearch")
        String orderBy = params["orderBy"]
        String orderDirection = params["dir"]
        Long username = params.long("username")

        String applicationName = params["applicationName"]
        String bio = params["bio"]
        String email = params["email"]
        String timezone = params["timezone"]
        String address = params["address"]



        PagedResultList result = Profile.createCriteria().list(offset: offset, max: max) {

            if (q) {
                like("fullName", "%${q}%")
            }

            if (sSearch) {
                or {
                    like("fullName", "%${sSearch}%")
                    eq("salary", sSearchInt)

                }
            }

            if(username || applicationName){

                user {
                    if (username) {
                        eq('id', username)
                    }


                    if (applicationName) {
                        like("applicationName", "%${applicationName}%")
                    }
                }
            }



            if (id) {
                eq("id", id)
            }
            if (salary) {
                eq("salary", salary)
            }

            if (dateOfBirth) {
                ge("dateOfBirth", dateOfBirth)
            }


            if (bio) {
                like("bio", "%${bio}%")
            }

            if (email) {
                like("email", "%${email}%")
            }

            if (timezone) {
                like("timezone", "%${timezone}%")
            }

            if (address) {
                like("address", "%${address}%")
            }

            if (fullName) {
                like("fullName", "%${fullName}%")
            }

            if(orderBy && orderDirection){
                order(orderBy,orderDirection)
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

            MultipartFile multipartFile = WebUtils.retrieveGrailsWebRequest().getCurrentRequest()?.getFile('multipartFile')
            profile.photo = multipartFile?.bytes

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

    String renderDataTableData(GrailsParameterMap params){
        PagedResultList pagedResultList = this.search(params)
        def g = grailsApplication.mainContext.getBean('org.grails.plugins.web.taglib.FormatTagLib')

        Map map = [:]
        map.data = pagedResultList.collect{Profile profile->
            return [
                    id:profile?.id,
                    user:profile?.user?.username,
                    fullName:profile?.fullName,
                    bio:profile?.bio,
                    email:profile?.email,
                    timezone:profile?.timezone,
                    country:profile?.country?.name,
                    salary: g.formatNumber(number:profile?.salary,format:'###,##0'),
                    dateOfBirth:profile?.dateOfBirth?.format("dd/MM/yyyy"),
            ]
        }
        map.draw = params.draw
        map.recordsTotal = pagedResultList?.totalCount
        map.recordsFiltered = pagedResultList?.totalCount
        return map as JSON
    }

}
