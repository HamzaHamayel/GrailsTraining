package edu.training

import grails.gsp.TagLib
import org.grails.core.artefact.DomainClassArtefactHandler
import org.springframework.context.MessageSourceResolvable
import org.springframework.web.servlet.support.RequestContextUtils

@TagLib
class FormTagLib extends org.grails.plugins.web.taglib.FormTagLib{


    /**
     * Creates a new text field.
     *
     * @attr name REQUIRED the field name
     * @attr value the field value
     * @attr isRequired
     */
    Closure textField = { attrs ->
        attrs.type = "text"
        attrs.tagName = "textField"
        boolean isRequired = attrs.remove("isRequired")?.asBoolean()
        if(isRequired == true){
            attrs.required = ""
        }
        fieldImpl(out, attrs)
    }

    /**
     * A helper tag for creating HTML selects.<br/>
     *
     * Examples:<br/>
     * &lt;g:select name="user.age" from="${18..65}" value="${age}" /&gt;<br/>
     * &lt;g:select name="user.company.id" from="${Company.list()}" value="${user?.company.id}" optionKey="id" /&gt;<br/>
     *
     * @emptyTag
     *
     * @attr name REQUIRED the select name
     * @attr id the DOM element id - uses the name attribute if not specified
     * @attr from REQUIRED The list or range to select from
     * @attr keys A list of values to be used for the value attribute of each "option" element.
     * @attr optionKey By default value attribute of each &lt;option&gt; element will be the result of a "toString()" call on each element. Setting this allows the value to be a bean property of each element in the list.
     * @attr optionValue By default the body of each &lt;option&gt; element will be the result of a "toString()" call on each element in the "from" attribute list. Setting this allows the value to be a bean property of each element in the list.
     * @attr value The current selected value that evaluates equals() to true for one of the elements in the from list.
     * @attr multiple boolean value indicating whether the select a multi-select (automatically true if the value is a collection, sets to single-select either if multiple is missing or it is explicitly set to false)
     * @attr valueMessagePrefix By default the value "option" element will be the result of a "toString()" call on each element in the "from" attribute list. Setting this allows the value to be resolved from the I18n messages. The valueMessagePrefix will be suffixed with a dot ('.') and then the value attribute of the option to resolve the message. If the message could not be resolved, the value is presented.
     * @attr noSelection A single-entry map detailing the key and value to use for the "no selection made" choice in the select box. If there is no current selection this will be shown as it is first in the list, and if submitted with this selected, the key that you provide will be submitted. Typically this will be blank - but you can also use 'null' in the case that you're passing the ID of an object
     * @attr isDisabled boolean value indicating whether the select is disabled or enabled (defaults to false - enabled)
     * @attr isReadOnly boolean value indicating whether the select is read only or editable (defaults to false - editable)
     * @attr isRequired boolean value indicating whether the select is required
     * @attr dataAttrs a Map that adds data-* attributes to the &lt;option&gt; elements. Map's keys will be used as names of the data-* attributes like so: data-${key} (i.e. with a "data-" prefix). The object belonging to a Map's key determines the value of the data-* attribute. It can be a string referring to a property of beans in {@code from}, a Closure that accepts an item from {@code from} and returns the value or a List that contains a value for each of the &lt;option&gt;s.
     */
    Closure select = { attrs ->

        boolean isRequired = attrs.remove("isRequired")?.asBoolean()
        if(isRequired == true){
            attrs.required = ""
        }
        boolean isDisabled = attrs.remove("isDisabled")?.asBoolean()
        if(isDisabled == true){
            attrs.disabled = "true"
        }
        boolean isReadOnly = attrs.remove("isReadOnly")?.asBoolean()
        if(isReadOnly == true){
            attrs.readonly = "true"
        }

        if (!attrs.name) {
            throwTagError("Tag [select] is missing required attribute [name]")
        }
        if (!attrs.containsKey('from')) {
            throwTagError("Tag [select] is missing required attribute [from]")
        }
        def messageSource = grailsAttributes.getApplicationContext().getBean("messageSource")
        def locale = RequestContextUtils.getLocale(request)
        def writer = out
        def from = attrs.remove('from')
        def keys = attrs.remove('keys')
        def optionKey = attrs.remove('optionKey')
        def optionDisabled = attrs.remove('optionDisabled')
        def optionValue = attrs.remove('optionValue')
        def value = attrs.remove('value')
        def dataAttrs = attrs.remove('dataAttrs')
        if (value instanceof Collection && attrs.multiple == null) {
            attrs.multiple = 'multiple'
        }
        if(attrs.multiple == false){
            attrs.remove('multiple')
        }
        if (value instanceof CharSequence) {
            value = value.toString()
        }
        def valueMessagePrefix = attrs.remove('valueMessagePrefix')
        def noSelection = attrs.remove('noSelection')
        if (noSelection != null) {
            noSelection = noSelection.entrySet().iterator().next()
        }


        writer << "<select "
        // process remaining attributes
        outputAttributes(attrs, writer, true)

        writer << '>'
        writer.println()

        if (noSelection) {
            renderNoSelectionOptionImpl(writer, noSelection.key, noSelection.value, value)
            writer.println()
        }

        // create options from list
        from.eachWithIndex {el, i ->
            def keyDisabled
            def keyValue
            def dataAttrsMap = getDataAttr(el, dataAttrs, i)
            writer << '<option '
            if (keys) {
                keyValue = keys[i]
                writeValueAndCheckIfSelected(attrs.name, keyValue, value, writer, dataAttrsMap)
            }
            else if (optionKey) {
                def keyValueObject
                if (optionKey instanceof Closure) {
                    keyValue = optionKey(el)
                }
                else if (el != null && optionKey == 'id' && grailsApplication.getArtefact(DomainClassArtefactHandler.TYPE, el.getClass().name)) {
                    keyValue = el.ident()
                    keyValueObject = el
                }
                else {
                    keyValue = el[optionKey]
                    keyValueObject = el
                }
                if(optionDisabled) {
                    if (optionDisabled instanceof Closure) {
                        keyDisabled = optionDisabled(el)
                    }
                    else {
                        keyDisabled = el[optionDisabled]
                    }
                }
                writeValueAndCheckIfSelected(attrs.name, keyValue, value, writer, dataAttrsMap, keyValueObject, keyDisabled)
            }
            else {
                keyValue = el
                writeValueAndCheckIfSelected(attrs.name, keyValue, value, writer, dataAttrsMap)
            }
            writer << '>'
            if (optionValue) {
                if (optionValue instanceof Closure) {
                    writer << optionValue(el).toString().encodeAsHTML()
                }
                else {
                    writer << el[optionValue].toString().encodeAsHTML()
                }
            }
            else if (el instanceof MessageSourceResolvable) {
                writer << messageSource.getMessage(el, locale)
            }
            else if (valueMessagePrefix) {
                def message = messageSource.getMessage("${valueMessagePrefix}.${keyValue}", null, null, locale)
                if (message != null) {
                    writer << message.encodeAsHTML()
                }
                else if (keyValue && keys) {
                    def s = el.toString()
                    if (s) writer << s.encodeAsHTML()
                }
                else if (keyValue) {
                    writer << keyValue.encodeAsHTML()
                }
                else {
                    def s = el.toString()
                    if (s) writer << s.encodeAsHTML()
                }
            }
            else {
                def s = el.toString()
                if (s) writer << s.encodeAsHTML()
            }
            writer << '</option>'
            writer.println()
        }
        // close tag
        writer << '</select>'
    }

}