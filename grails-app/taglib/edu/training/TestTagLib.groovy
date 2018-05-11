package edu.training

import grails.artefact.TagLibrary
import grails.gsp.TagLib

@TagLib
class TestTagLib implements TagLibrary{

    static namespace = "custom"

    /**
     * @attr value REQUIRED
     * @attr value2
     * @attr value3
     * @attr var
     */
    def example = { attrs, body ->

        def var = attrs.var

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