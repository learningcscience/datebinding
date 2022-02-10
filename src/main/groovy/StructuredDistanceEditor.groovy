
import datebindingtest.TimeService
import grails.databinding.DataBindingSource
import grails.databinding.TypedStructuredBindingEditor
import io.micronaut.core.util.StringUtils
import org.grails.databinding.converters.AbstractStructuredDateBindingEditor

import java.sql.Time
import java.text.ParseException

//import org.codehaus.groovy.grails.web.binding.StructuredPropertyEditor

class StructuredDistanceEditor extends AbstractStructuredDateBindingEditor<Distance> implements TypedStructuredBindingEditor<Distance> {

	@Override
	public List getRequiredFields() {
		return ['magnitude', 'unit']
	}

	@Override
	public List getOptionalFields() {
		return []
	}

	@Override
	Distance getDate(Calendar c) {
		return null
	}

	@Override
	Distance assemble(String propertyName, DataBindingSource fieldValues)
			throws IllegalArgumentException {
		final prefix = propertyName + '_'
		//assert fieldValues.containsProperty(prefix + "dayMonthYear"), "Can't populate a day, month, and year"


		String magnitude = fieldValues.getPropertyValue(prefix + 'magnitude')
		String unit = fieldValues.getPropertyValue(prefix + 'unit')


		def distance = new Distance()
		distance.set(magnitude ?: '', unit ?: '')
		return distance



	}

	@Override
	Class getTargetType() {
		Distance
	}
}