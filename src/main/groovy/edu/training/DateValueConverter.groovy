package edu.training

import grails.databinding.converters.ValueConverter

import java.text.SimpleDateFormat

class DateValueConverter implements ValueConverter {

    boolean canConvert(value) {
        return true
    }

    def convert(value) {
        println("in value converter with value: $value")
        SimpleDateFormat parser=new SimpleDateFormat("dd/MM/yyyy");
        Date date = parser.parse(value);
        return date
    }

    Class<?> getTargetType() {
        Date
    }
}
