package edu.training

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.orm.PagedResultList
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class TagPostService {

    @Transactional(readOnly = true)
    PagedResultList search(GrailsParameterMap params) {


        Integer max = params.int("max")
        Integer offset = params.int("offset")
        Long id = params.long("id")
        String sSearch = params["sSearch"]
        String orderBy = params["orderBy"]
        String orderDirection = params["dir"]



        PagedResultList result = TagPost.createCriteria().list(offset: offset, max: max) {

            if (sSearch) {
                or {
                    tag{
                        like("name", "%${sSearch}%")

                    }
                    post{
                        like("content", "%${sSearch}%")

                    }
                }
            }

            if (id) {
                eq("id", id)
            }

            if(orderBy && orderDirection){
                order(orderBy,orderDirection)
            }

        }

        return result
    }

    @Transactional(readOnly = true)
    TagPost get(GrailsParameterMap params){
        TagPost tagPost
        PagedResultList resultList = this.search(params)
        if(resultList){
            tagPost = resultList?.first()
        }
        return tagPost
    }

    TagPost save(GrailsParameterMap params) {
        TagPost tagPost
        try {
            //it's update here
            if (params.long("id")) {
                tagPost = TagPost.get(params.long("id"))
            } else {
                //it's save here
                tagPost = new TagPost()
            }

            tagPost.properties = params
            tagPost.save(flush: true, failOnError: true)
        } catch (Exception e) {
            e.printStackTrace()
            transactionStatus.setRollbackOnly()
        }

        return tagPost

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
        map.data = pagedResultList.collect{TagPost tagPost->
            return [
                    id:tagPost?.id,
                    tag:tagPost?.tag?.name,
                    post:tagPost?.post?.content,
            ]
        }
        map.draw = params.draw
        map.recordsTotal = pagedResultList?.totalCount
        map.recordsFiltered = pagedResultList?.totalCount
        return map as JSON
    }

}
