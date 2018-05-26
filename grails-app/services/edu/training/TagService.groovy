package edu.training

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.orm.PagedResultList
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class TagService {

    @Transactional(readOnly = true)
    PagedResultList search(GrailsParameterMap params) {


        Integer max = params.int("max")
        Integer offset = params.int("offset")
        String name = params["name"]
        Long id = params.long("id")
        String sSearch = params["sSearch"]
        String orderBy = params["orderBy"]
        String orderDirection = params["dir"]



        PagedResultList result = Tag.createCriteria().list(offset: offset, max: max) {

            if (sSearch) {
                or {
                    like("name", "%${sSearch}%")
                    user {
                        like("username", "%${sSearch}%")
                    }
                }
            }

            if (id) {
                eq("id", id)
            }
            if (name) {
                like("name", "%${name}%")
            }

            if(orderBy && orderDirection){
                order(orderBy,orderDirection)
            }

        }

        return result
    }

    @Transactional(readOnly = true)
    Tag get(GrailsParameterMap params){
        Tag tag
        PagedResultList resultList = this.search(params)
        if(resultList){
            tag = resultList?.first()
        }
        return tag
    }

    Tag save(GrailsParameterMap params) {
        Tag tag
        try {
            //it's update here
            if (params.long("id")) {
                tag = Tag.get(params.long("id"))
            } else {
                //it's save here
                tag = new Tag()
            }

            tag.properties = params
            tag.save(flush: true, failOnError: true)
        } catch (Exception e) {
            e.printStackTrace()
            transactionStatus.setRollbackOnly()
        }

        return tag

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
        map.data = pagedResultList.collect{Tag tag->
            return [
                    id:tag?.id,
                    name:tag?.name,
                    user:tag?.user?.username,
                    dateCreated:tag?.dateCreated?.format("dd/MM/yyyy")

            ]
        }
        map.draw = params.draw
        map.recordsTotal = pagedResultList?.totalCount
        map.recordsFiltered = pagedResultList?.totalCount
        return map as JSON
    }

}
