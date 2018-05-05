package edu.training

import grails.gorm.services.Service

@Service(PersonStatus)
interface PersonStatusService {

    PersonStatus get(Serializable id)

    List<PersonStatus> list(Map args)

    Long count()

    void delete(Serializable id)

    PersonStatus save(PersonStatus personStatus)

}