package edu.training


class RegistrationController {

    RegistrationService registrationService

    def index = {
        redirect(action: "list")
    }

    def create(){
        respond  new RegistrationCommand()
    }



    def save(RegistrationCommand registrationCommand){

        if(registrationCommand?.hasErrors()){
            Map map  = [registration:registrationCommand]
            respond map, view: 'create' // return [user:user]
        }else{
            registrationCommand = registrationService.save(registrationCommand)
            String successMessage = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), registrationCommand?.id])
            if (registrationCommand.hasErrors()) {
                respond registration.errors, view: 'create' // return [user:user]
            } else {
                flash.message = successMessage
                redirect(controller:"userManagement",action: "show",id:registrationCommand?.id)
            }
        }
    }
}
