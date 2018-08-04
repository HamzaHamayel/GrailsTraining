package edu.training

import geb.Page

class LoginPage extends Page {
    static url = '/TrainingApplication/login/auth'

    static at = {
        title == 'Login'
    }

    static content = {
        loginButton { $('#submit', 0) }
        usernameInputField { $('#username', 0) }
        passwordInputField { $('#password', 0) }
    }

    void login(String username, String password) {
        usernameInputField << username
        passwordInputField << password
        loginButton.click()
        waitFor { at LoginPage }
    }
}