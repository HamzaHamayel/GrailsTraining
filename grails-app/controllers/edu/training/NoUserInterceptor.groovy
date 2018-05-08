package edu.training


class NoUserInterceptor {

    NoUserInterceptor() {
//        matchAll().excludes(controller:"user")
//        match(controller:"post", action:"list") // using strings
//        match(controller: ~/(post|tag)/,action: "list|show") // using regex
    }

    boolean before() {
        redirect(controller: "user",action:"list")
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
