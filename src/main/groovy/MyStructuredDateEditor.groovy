import datebindingtest.TimeService
import org.grails.web.binding.StructuredPropertyEditor

import java.beans.PropertyEditorSupport
import java.text.ParseException

class MyStructuredDateEditor extends PropertyEditorSupport implements StructuredPropertyEditor{

    TimeService timeService
    private static final Set<String> timeZoneIdSet = TimeZone.getAvailableIDs() as Set

    public MyStructuredDateEditor(TimeService timeService){
        this.timeService = timeService
    }

    @Override
    public List getRequiredFields() {
        return ['dayMonthYear']
    }

    @Override
    public List getOptionalFields() {
        return ['hourMin', 'meridian', 'timeZone']
    }

    @Override
    public Object assemble(Class type, Map fieldValues)
            throws IllegalArgumentException {

        String hourMin = fieldValues.get('hourMin')
        String meridian = fieldValues.get('meridian')
        String dayMonthYear = fieldValues.get('dayMonthYear')
        String tz = fieldValues.get('timeZone')


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

}
