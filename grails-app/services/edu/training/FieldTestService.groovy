package edu.training

import grails.gorm.services.Service

@Service(FieldTest)
interface FieldTestService {

    FieldTest get(Serializable id)

    List<FieldTest> list(Map args)

    Long count()

    void delete(Serializable id)

    FieldTest save(FieldTest fieldTest)

}