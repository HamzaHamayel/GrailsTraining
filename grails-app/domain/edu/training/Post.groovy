package edu.training

class Post extends Transaction {

    String content

    static hasMany = [tags:Tag]

    static constraints = {
        content(blank: false,maxSize: 1024)
    }

    public Post(){
        classification = TransactionClassification.POST
    }


    @Override
    public String toString() {
        return "content: $content for user: $user"
    }
}
