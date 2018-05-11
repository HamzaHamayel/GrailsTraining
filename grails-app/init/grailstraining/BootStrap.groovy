package grailstraining

import edu.training.*

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
            User ahmadUser = User.findByUserId("ahmad")?:new User(userId:"ahmad",password:"password").save(flush:true,failOnError:true)
            User aliUser = User.findByUserId("ali")?:new User(userId:"ali",password:"password").save(flush:true,failOnError:true)
            User danaUser = User.findByUserId("dana")?:new User(userId:"dana",password:"password").save(flush:true,failOnError:true)

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

        }


    }
    def destroy = {
    }
}
