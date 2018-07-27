package edu.training

import grails.gorm.services.Service

@Service(ScaffoldTest)
interface ScaffoldTestService {

    ScaffoldTest get(Serializable id)

    List<ScaffoldTest> list(Map args)

    Long count()

    void delete(Serializable id)

    ScaffoldTest save(ScaffoldTest scaffoldTest)

}