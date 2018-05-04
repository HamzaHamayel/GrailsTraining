package edu.training

import grails.gsp.TagLib
import org.grails.core.artefact.DomainClassArtefactHandler
import org.springframework.context.MessageSourceResolvable
import org.springframework.web.servlet.support.RequestContextUtils

@TagLib
class TestTagLib extends org.grails.plugins.web.taglib.FormTagLib{

    static namespace = "custom"

    /**
     * @attr value REQUIRED
     * @attr value2
     * @attr value3
     */
    def example = { attrs, body ->

        out << "<div>"
        out << attrs["value"]
        out << "</div>"
        out << "<div>"
        out << body()
        out << "</div>"

    }



    /**
     * @attr value REQUIRED
     * @attr value2
     * @attr value3
     */
    def isAdmin = { attrs, body ->

        if(attrs["value"] == 'admin') {
            out << "<div>"
            out << attrs["value"]
            out << "</div>"
            out << "<div>"
            out << body()
            out << "</div>"
        }

    }

    /**
     * @attr data REQUIRED
     */
    def loop = { attrs, body ->

        List data = attrs["data"]

        data.each {
            out << "<p> item ${it} </p>"
        }
    }
}