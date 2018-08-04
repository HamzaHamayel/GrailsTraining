package edu.training

import edu.training.security.User
import grails.gorm.transactions.Rollback
import grails.orm.PagedResultList
import grails.plugin.springsecurity.SpringSecurityService
import grails.testing.mixin.integration.Integration
import grails.testing.spock.*
import grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockHttpServletRequest
import spock.lang.Shared
import spock.lang.Specification

@Integration
@Rollback
class PostServiceSpec extends Specification {

    @Autowired
    PostService postService

    @Shared
    SpringSecurityService springSecurityService

//    @Before
//    @RunOnce
    @OnceBefore
    void setupSecurityMethod() {
        println("setup security data ....")
        springSecurityService = Mock(SpringSecurityService) {
            getPrincipal() >> [id: 19L, username: 'systemTest']
            encodePassword(_) >> "password"
            reauthenticate(_) >> true
            isLoggedIn() >> true
        }
    }


    private Post generatePost(String content = null){
        Post post = Post.build()
        post.springSecurityService = springSecurityService
        post.save(flush:true)
        return post
    }

    private Long setupData() {
        generatePost()
        generatePost()
        Post post = generatePost()
        assert post.id != null
        return post.id
    }

    private GrailsParameterMap getParams(){
        return new GrailsParameterMap([:],new MockHttpServletRequest())
    }


    void "test list"() {
        Long postId = setupData()
        GrailsParameterMap params = getParams()
        given:
        params.max = 2
        params.offset = 0

        when:
        PagedResultList pagedResultList = postService.search(params)
        then:
        pagedResultList.totalCount == 3
        pagedResultList.size() == 2
        Post post = pagedResultList[0]
        println("<<post history>> createdBy: ${post.history.createdBy} lastUpdateBy: ${post.history.lastUpdateBy}")

    }

    void "test save"() {
        GrailsParameterMap params = getParams()
        given:
        params['content'] = "cont_face"
        params['user.id'] = User.build()?.id

        when:
        Post post = postService.save(params)

        then:
        post.id != null
        post.hasErrors() == false
    }

    void "test fail save"() {

        GrailsParameterMap params = getParams()

        when:
        Post post = postService.save(params)

        then:
        post.id == null
        post.hasErrors() == true
    }
}
