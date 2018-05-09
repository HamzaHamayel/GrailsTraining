package grailstraining

import edu.training.*

class BootStrap {

    Boolean executeBooStrapData = true

    def init = { servletContext ->
        //added init data
        if (executeBooStrapData) {
            //country
            Country psCountry = Country.findOrSaveByCodeAndName("ps","Palestine")
            Country joCountry = Country.findOrSaveByCodeAndName("jo", "Jordan")
            Country egCountry = Country.findOrSaveByCodeAndName("eg","Egypt")

            //user
            User ahmadUser = User.findByUserId("ahmad")?:new User(userId:"ahmad",password:"password").save(flush:true,failOnError:true)
            User aliUser = User.findByUserId("ali")?:new User(userId:"ali",password:"password").save(flush:true,failOnError:true)
            User danaUser = User.findByUserId("dana")?:new User(userId:"dana",password:"password").save(flush:true,failOnError:true)

            //following
            aliUser.addToFollowing(ahmadUser).save(flush:true)
            danaUser.addToFollowing(ahmadUser).save(flush:true)

            //profile
            Profile.findOrSaveByUserAndCountryAndFullNameAndBioAndEmailAndTimezoneAndAddressAndSalaryAndDateOfBirth( aliUser , psCountry,  "ali full name", "bio", "ali@email.com", "Asia/Hebron", "ramallah",1000, new Date("01/01/1980"))
            Profile.findOrSaveByUserAndCountryAndFullNameAndBioAndEmailAndTimezoneAndAddressAndSalaryAndDateOfBirth( danaUser , joCountry,  "dana full name", "bio", "dana@email.com", "Asia/Amman", "Amman",2000, new Date("01/01/1995"))

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

                post = Post.findOrSaveByContentAndUserAndClassification(postContent,user, TransactionClassification.POST)
                tag = Tag.findOrSaveByNameAndUserAndClassificationAndPost(tagName,user,TransactionClassification.TAG,post)
                TagPost.findOrSaveByTagAndPost(tag,post)

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
