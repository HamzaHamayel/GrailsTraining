package grailstraining

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

//        "/myPanel/list/$id?"(controller: "user", action: "list")
//        "/myPanel/show/$id?"(controller: "user", action: "show")


        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
