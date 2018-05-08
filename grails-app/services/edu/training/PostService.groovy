package edu.training

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.orm.PagedResultList
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class PostService {

    @Transactional(readOnly = true)
    PagedResultList search(GrailsParameterMap params) {


        Integer max = params.int("max")
        Integer offset = params.int("offset")
        String content = params["content"]
        Long id = params.long("id")
        String sSearch = params["sSearch"]
        String orderBy = params["orderBy"]
        String orderDirection = params["dir"]



        PagedResultList result = Post.createCriteria().list(offset: offset, max: max) {

            if (sSearch) {
                or {
                    like("content", "%${sSearch}%")
                    user {
                        like("userId", "%${sSearch}%")
                    }
                }
            }

            if (id) {
                eq("id", id)
            }
            if (content) {
                like("content", "%${content}%")
            }

            if(orderBy && orderDirection){
                order(orderBy,orderDirection)
            }

        }

        return result
    }

    @Transactional(readOnly = true)
    Post get(GrailsParameterMap params){
        Post post
        PagedResultList resultList = this.search(params)
        if(resultList){
            post = resultList?.first()
        }
        return post
    }

    Post save(GrailsParameterMap params) {
        Post post
        try {
            //it's update here
            if (params.long("id")) {
                post = Post.get(params.long("id"))
            } else {
                //it's save here
                post = new Post()
            }

            post.properties = params
            post.save(flush: true, failOnError: true)
        } catch (Exception e) {
            e.printStackTrace()
            transactionStatus.setRollbackOnly()
        }

        return post

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
        map.data = pagedResultList.collect{Post post->
            return [
                    id:post?.id,
                    content:post?.content,
                    user:post?.user?.userId,
                    dateCreated:post?.dateCreated?.format("dd/MM/yyyy")
            ]
        }
        map.draw = params.draw
        map.recordsTotal = pagedResultList?.totalCount
        map.recordsFiltered = pagedResultList?.totalCount
        return map as JSON
    }

}
