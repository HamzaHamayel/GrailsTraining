package edu.training

import grails.gsp.TagLib

@TagLib
class MessageTagLib extends org.grails.plugins.web.taglib.FormTagLib{

    static namespace = "msg"


    /**
     * format error list.
     *
     * @attr errorsObject REQUIRED the field name
     */
    Closure formatErrorList = {  attrs   ->
        def errorsObject = attrs["errorsObject"]
        def errorsMessages
        def string = ""
        errorsMessages = errorsObject?.errors?.allErrors
        if (errorsMessages) {
            string+=""" <ul class="errors" role="alert"> """
            errorsMessages.each {
                string += """<li>${g.message(error: it)}</li>"""
            }
            string+=""" </ul>"""
        }
        out << string;
    }

    /**
     * format messagw.
     *
     * @attr message REQUIRED the field name
     */
    Closure formatMessage = {  attrs   ->
        def message = attrs["message"]
        out << """<div class="message" role="status">${message}</div> """;
    }
}