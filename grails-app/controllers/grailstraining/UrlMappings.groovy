package grailstraining

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }


//        "/users"(controller: "user",action: "list")
//
//        "/myPanel/view/$id?" {
//            controller = "user"
//            action = "show"
//
//            constraints {
//                // apply constraints here
//                id(validator: {
//                    if (it == "21") {
//                        return false
//                    }else{
//                        return true
//                    }
//                })
//            }
//
//        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
