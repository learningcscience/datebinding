import datebindingtest.TimeService
import grails.databinding.DataBindingSource
import grails.databinding.TypedStructuredBindingEditor
import io.micronaut.core.util.StringUtils
import org.grails.databinding.converters.AbstractStructuredDateBindingEditor

import java.text.ParseException
import java.time.LocalDate

class MyStructuredDateEditor extends AbstractStructuredDateBindingEditor<Date> implements TypedStructuredBindingEditor<Date> {

    TimeService timeService
    private static final Set<String> timeZoneIdSet = TimeZone.getAvailableIDs() as Set

    @Override
    public List getRequiredFields() {
        return ['dayMonthYear']
    }

    @Override
    public List getOptionalFields() {
        return ['hourMin', 'meridian', 'timeZone']
    }

    @Override
    Date assemble(String propertyName, DataBindingSource fieldValues)
            throws IllegalArgumentException {
        final prefix = propertyName + '_'
        assert fieldValues.containsProperty(prefix + "dayMonthYear"), "Can't populate a day, month, and year"


        String hourMin = fieldValues.getPropertyValue(prefix + 'hourMin')
        String meridian = fieldValues.getPropertyValue(prefix + 'meridian')
        String dayMonthYear = fieldValues.getPropertyValue(prefix + 'dayMonthYear')
        String tz = fieldValues.getPropertyValue(prefix + 'timeZone')


        Date date
        String formattedDate = dayMonthYear
        TimeZone timeZone
        if (timeZoneIdSet.contains(tz)) timeZone = TimeZone.getTimeZone(tz)
        try{
            if (StringUtils.hasText(hourMin) || StringUtils.hasText(meridian)){
                formattedDate =  formattedDate + " $hourMin $meridian"
                date = timeService.parseDateTime(formattedDate, timeZone)
            }
            else{
                date = timeService.parseDate(formattedDate, timeZone)
                if (type == java.sql.Date.class){
                    date = new java.sql.Date(date.getTime())
                }
            }
            return date
        }
        catch(ParseException e){
            // As of grails 2.2.0, throwing an IllegalArgumentException is swallowed
            // and the value remains unchanged.  By returning a string, a TypeMismatchException
            // will be thrown during binding and an appropriate error message shown.
            return formattedDate
        }
    }

    // this method may not be called for this implementation
    // but is abstract in the parent class
    @Override
    Date getDate(Calendar c) {
        c.getTime()
    }

    @Override
    Class getTargetType() {
        Date
    }
}
