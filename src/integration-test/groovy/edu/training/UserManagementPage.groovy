package edu.training

import geb.Page

class UserManagementPage extends Page {

    static url = '/TrainingApplication/userManagement/list'

    static at = {
        $('#myTable').text()?.contains 'username'
    }
}