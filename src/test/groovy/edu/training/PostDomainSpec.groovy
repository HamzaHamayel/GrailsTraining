package edu.training

import edu.training.security.User
import grails.buildtestdata.TestDataConfigurationHolder
import grails.testing.gorm.DomainUnitTest
import org.apache.commons.lang.StringUtils
import spock.lang.Specification

class PostDomainSpec extends Specification implements DomainUnitTest<Post> {


    void "test post content blank"(){
        Post post
        when:
        post = new Post()
        post.content = " "
        post.save()

        then:
        post.errors.fieldErrors.find{it.field == "content" } != null
        post.errors.fieldErrors.find{it.field == "content" }.code == "blank"


        when:
        post = new Post(content:"abc")
        post.save()

        then:
        post.errors.fieldErrors.find{it.field == "content" } == null
    }

    void "test post content max size"(){
        Post post

        when:
        post = new Post(content:StringUtils.repeat("abcd", 500))
        post.save()

        then:
        post.errors.fieldErrors.find{it.field == "content" } != null
        post.errors.fieldErrors.find{it.field == "content" }.code == "maxSize.exceeded"

        when:
        post = new Post(content:"abc")
        post.save()

        then:
        post.errors.fieldErrors.find{it.field == "content" } == null
    }

    void "test empty user"(){
        def post
        when:
        post = new Post(user: null)
        post.save()

        then:
        post.errors.fieldErrors.find{it.field == "user" } != null
        post.errors.fieldErrors.find{it.field == "user" }.code == "nullable"

        when:
        User user = new User(username: "testUser",password: "123")
        post = new Post(user:user)
        post.save()

        then:
        post.errors.fieldErrors.find{it.field == "user" } == null
    }
}






