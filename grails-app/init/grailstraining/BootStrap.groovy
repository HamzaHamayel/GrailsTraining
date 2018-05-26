package grailstraining

import edu.training.*
import edu.training.security.Requestmap
import edu.training.security.Role
import edu.training.security.User
import edu.training.security.UserRole

class BootStrap {

    Boolean executeBootStrapData = true

    def init = { servletContext ->
        //added init data
        if (executeBootStrapData) {
            //country
            Country psCountry = Country.findByCode("ps")?:new Country(code: "ps",name: "Palestine").save(flush:true,failOnError:true)
            Country joCountry = Country.findByCode("jo")?:new Country(code: "jo",name: "Jordan").save(flush:true,failOnError:true)
            Country egCountry = Country.findByCode("eg")?:new Country(code: "eg",name: "Egypt").save(flush:true,failOnError:true)

            //user
            User ahmadUser = User.findByUsername("ahmad")?:new User(username:"ahmad",password:"password").save(flush:true,failOnError:true)
            User aliUser = User.findByUsername("ali")?:new User(username:"ali",password:"password").save(flush:true,failOnError:true)
            User danaUser = User.findByUsername("dana")?:new User(username:"dana",password:"password").save(flush:true,failOnError:true)

            //following
            aliUser.addToFollowing(ahmadUser).save(flush:true)
            danaUser.addToFollowing(ahmadUser).save(flush:true)

            //profile
            Profile.findByUser(aliUser)?:new Profile(user:aliUser , country:psCountry,fullName:  "ali full name", bio:"bio", email:"ali@email.com", timezone:"Asia/Hebron",address: "Ramallah",salary:1000,dateOfBirth: new Date("01/01/1980")).save(flush:true,failOnError:true)
            Profile.findByUser(danaUser)?:new Profile(user:danaUser , country:joCountry,fullName:  "dana full name", bio:"bio", email:"dana@email.com", timezone:"Asia/Amman",address: "Amman",salary:2000,dateOfBirth: new Date("01/01/1995")).save(flush:true,failOnError:true)

            //transaction
            User user
            String postContent
            String tagName
            Post post
            Tag tag
            1000.times { Integer index ->
                postContent = "post_content_${index}"
                tagName = "tag_name_${index}"
                if (index % 2 == 0) {
                    user = aliUser
                } else {
                    user = danaUser
                }

                post = Post.findByContentAndUserAndClassification(postContent,user,TransactionClassification.POST)?: new Post(content: postContent,user:user,classification: TransactionClassification.POST).save(flush:true,failOnError:true)
                tag = Tag.findByNameAndUserAndClassificationAndPost(tagName,user,TransactionClassification.TAG,post)?: new Tag(name: tagName,user:user,classification: TransactionClassification.TAG,post:post).save(flush:true,failOnError:true)
                TagPost.findByTagAndPost(tag,post)?:new TagPost(tag:tag,post:post).save(flush:true,failOnError:true)

                postContent = null
                tagName = null
                user = null
                post = null
                tag = null
            }


            //security


            //roles
            Role adminRole = Role.findByAuthority("ROLE_ADMIN")?:new Role(authority: "ROLE_ADMIN").save(flush:true,failOnError:true)
            Role userRole = Role.findByAuthority("ROLE_USER")?:new Role(authority: "ROLE_USER").save(flush:true,failOnError:true)

            //assign admin users
            UserRole.findByRoleAndUser(adminRole,aliUser)?:new UserRole(user:aliUser,role: adminRole).save(flush:true,failOnError:true)

            //assign normal users
            UserRole.findByRoleAndUser(userRole,danaUser)?:new UserRole(user:danaUser,role: userRole).save(flush:true,failOnError:true)
            UserRole.findByRoleAndUser(userRole,ahmadUser)?:new UserRole(user:ahmadUser,role: userRole).save(flush:true,failOnError:true)


            //requestmap
            Requestmap.executeUpdate('delete from Requestmap')
            for (String url in ['/', '/error', '/index', '/index.gsp',
                                '/**/favicon.ico', '/shutdown', '/**/js/**', '/**/css/**',
                                '/**/images/**', '/login', '/login.*', '/login/*', '/logout',
                                '/logout.*', '/logout/*']) {
                new Requestmap(url: url, configAttribute: 'permitAll').save()
            }

            //system screens

            //just admin
            new Requestmap(url: '/profile/**', configAttribute: 'ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/country/**', configAttribute: 'ROLE_ADMIN').save(flush: true)

            //ui
            new Requestmap(url: '/user/**', configAttribute: 'ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/role/**', configAttribute: 'ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/aclClass/**', configAttribute: 'ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/aclEntry/**', configAttribute: 'ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/aclObjectIdentity/**', configAttribute: 'ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/aclSid/**', configAttribute: 'ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/persistentLogin/**', configAttribute: 'ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/register/**', configAttribute: 'ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/registrationCode/**', configAttribute: 'ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/requestmap/**', configAttribute: 'ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/securityInfo/**', configAttribute: 'ROLE_ADMIN').save(flush: true)



            new Requestmap(url: '/test/**', configAttribute: 'ROLE_USER,ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/userManagement/**', configAttribute: 'ROLE_USER,ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/transaction/**', configAttribute: 'ROLE_USER,ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/post/**', configAttribute: 'ROLE_USER,ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/tag/**', configAttribute: 'ROLE_USER,ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/tagPost/**', configAttribute: 'ROLE_USER,ROLE_ADMIN').save(flush: true)
            new Requestmap(url: '/userActivity/**', configAttribute: 'ROLE_USER,ROLE_ADMIN').save(flush: true)


        }


    }
    def destroy = {
    }
}
