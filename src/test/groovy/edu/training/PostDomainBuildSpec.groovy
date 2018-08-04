package edu.training

import edu.training.security.User
import grails.buildtestdata.BuildDomainTest
import grails.buildtestdata.TestDataConfigurationHolder
import org.apache.commons.lang.StringUtils
import spock.lang.Specification

class PostDomainBuildSpec extends Specification implements BuildDomainTest<Post> {

    void setup() {
        //reset counter in config
        TestDataConfigurationHolder.reset()
    }

    void "test post content blank"(){
        Post post
        when:
        post = Post.build(save:false)
        post.content = " "
        post.validate()

        then:
        post.errors.fieldErrors.find{it.field == "content" } != null
        post.errors.fieldErrors.find{it.field == "content" }.code == "blank"


        when:
        post = Post.build(content:"abc",save:false)
        post.validate()

        then:
        post.errors.fieldErrors.find{it.field == "content" } == null
    }

    void "test post content max size"(){
        Post post

        when:
        post = Post.build(content:StringUtils.repeat("abcd", 500),save:false)
        post.validate()

        then:
        post.errors.fieldErrors.find{it.field == "content" } != null
        post.errors.fieldErrors.find{it.field == "content" }.code == "maxSize.exceeded"

        when:
        post = Post.build(content:"abc",save:false)
        post.validate()

        then:
        post.errors.fieldErrors.find{it.field == "content" } == null
    }

    void "test empty user"(){
        def post
        when:
        post = Post.build(user: null,save:false)
        post.validate()

        then:
        post.errors.fieldErrors.find{it.field == "user" } != null
        post.errors.fieldErrors.find{it.field == "user" }.code == "nullable"

        when:
//        User user = User.build(username: "testUser")
//        post = Post.build(user:user,save:false)
        post = Post.build(save:false) //allow if config are found
        post.validate()

        then:
        println("user: ${post.user}")
        println("content: ${post.content}")
        post.errors.fieldErrors.find{it.field == "user" } == null
    }
}






