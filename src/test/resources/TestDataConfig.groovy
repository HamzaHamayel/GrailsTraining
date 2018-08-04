testDataConfig {
    sampleData {



        'edu.training.security.User' {
            def i = 1
            username = {-> "user${i++}" }
        }



    }
}