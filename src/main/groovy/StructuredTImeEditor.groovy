
import datebindingtest.TimeService
import grails.databinding.DataBindingSource
import grails.databinding.TypedStructuredBindingEditor
import io.micronaut.core.util.StringUtils
import org.grails.databinding.converters.AbstractStructuredDateBindingEditor

import java.sql.Time
import java.text.ParseException

//import org.codehaus.groovy.grails.web.binding.StructuredPropertyEditor

class StructuredTImeEditor extends AbstractStructuredDateBindingEditor<java.sql.Time> implements TypedStructuredBindingEditor<java.sql.Time> {

	TimeService timeService
	private static final Set<String> timeZoneIdSet = TimeZone.getAvailableIDs() as Set

	@Override
	public List getRequiredFields() {
		return ['hourMin', 'meridian']
	}

	@Override
	public List getOptionalFields() {
		return ['timeZone']
	}

	@Override
	Time getDate(Calendar c) {
		return null
	}

	@Override
	java.sql.Time assemble(String propertyName, DataBindingSource fieldValues)
			throws IllegalArgumentException {
		final prefix = propertyName + '_'
		//assert fieldValues.containsProperty(prefix + "dayMonthYear"), "Can't populate a day, month, and year"


		String hourMin = fieldValues.getPropertyValue(prefix + 'hourMin')
		String meridian = fieldValues.getPropertyValue(prefix + 'meridian')
		String tz = fieldValues.getPropertyValue(prefix + 'timeZone')


		if (!StringUtils.hasText(hourMin) && !StringUtils.hasText(meridian)){
			return null
		}
		def formattedTime = hourMin + (meridian ? " $meridian" : "")
		TimeZone timeZone
		if (timeZoneIdSet.contains(tz)) timeZone = TimeZone.getTimeZone(tz)
		try{
			Date date = timeService.parseTime(formattedTime, timeZone)
			return new java.sql.Time(date.getTime())
		}
		catch(ParseException e){
			// As of grails 2.2.0, throwing an IllegalArgumentException is swallowed
			// and the value remains unchanged.  By returning a string, a TypeMismatchException
			// will be thrown during binding and an appropriate error message shown.
			return formattedTime
		}


	}

	@Override
	Class getTargetType() {
		java.sql.Time
	}
}