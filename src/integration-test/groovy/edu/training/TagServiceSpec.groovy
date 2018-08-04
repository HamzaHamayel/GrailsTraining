package edu.training

import edu.training.security.User
import grails.gorm.transactions.Rollback
import grails.orm.PagedResultList
import grails.testing.mixin.integration.Integration
import grails.web.servlet.mvc.GrailsParameterMap
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockHttpServletRequest
import spock.lang.Specification

@Integration
@Rollback
class TagServiceSpec extends Specification {

    @Autowired
    TagService tagService

    SessionFactory sessionFactory

    private Long setupData() {

        Tag.build(name: "Tag1").save(flush:true)
        Tag.build(name: "Tag2").save(flush:true)
        Tag tag = Tag.build(name: "Tag3").save(flush:true)
        return tag.id
    }

    private GrailsParameterMap getParams(){
        return new GrailsParameterMap([:],new MockHttpServletRequest())
    }


    void "test list"() {
        setupData()
        GrailsParameterMap params = getParams()
        given:
        params.max = 2
        params.offset = 0

        when:
        PagedResultList pagedResultList = tagService.search(params)

        then:
        pagedResultList.totalCount == 3
        pagedResultList.size() == 2
        pagedResultList[0].name.toLowerCase() == "tag1"
    }

    void "test list search sSearch"() {
        setupData()
        GrailsParameterMap params = getParams()

        given:
        params.sSearch = "g1"

        when:
        PagedResultList pagedResultList = tagService.search(params)

        then:
        pagedResultList.totalCount == 1
        pagedResultList.size() == 1
        pagedResultList[0].name == "Tag1"
    }

    void "test save"() {
        User user = User.build().save(flush:true)
        Post post = Post.build().save(flush:true)
        GrailsParameterMap params = getParams()
        given:
        params['user.id'] = user.id
        params['post.id'] = post.id
        params.name = "TagTag"

        when:
        Tag tag = tagService.save(params)

        then:
        tag.id != null
        tag.hasErrors() == false
    }

    void "test fail save"() {

        GrailsParameterMap params = getParams()

        when:
        Tag tag = tagService.save(params)

        then:
        tag.id == null
        tag.hasErrors() == true
    }

    void "test edit"() {
        Tag tag = Tag.build(name:'tagToEdit').save(flush:true)
        GrailsParameterMap params = getParams()
        Integer oldCount = Tag.count()

        given:
        params.id = tag.id
        params.name = "NewTag"


        expect:
        tag.name == "tagToEdit"

        when:
        tag = tagService.save(params)

        then:
        tag.id != null
        tag.hasErrors() == false
        Tag.count() == oldCount
    }

    void "test fail edit"() {
        Tag tagToEdit = Tag.build(name:'tagToEdit').save(flush:true)
        GrailsParameterMap params = getParams()
        Integer oldCount = Tag.count()

        given:
        params['user.id'] = tagToEdit.user.id
        params['post.id'] = tagToEdit.post.id
        params.name = "NewTag"

        expect:
        tagToEdit.name == "tagToEdit"

        when:
        Tag tag = tagService.save(params)

        then:
        tag.id != null
        tag.hasErrors() == false
        tag.id != tagToEdit.id
        Tag.count() > oldCount
    }
}
