import org.grails.web.binding.StructuredPropertyEditor

import java.beans.PropertyEditorSupport

//import org.codehaus.groovy.grails.web.binding.StructuredPropertyEditor

class DistanceEditor extends PropertyEditorSupport implements StructuredPropertyEditor{
	
	public DistanceEditor(){}

	@Override
	public List getRequiredFields() {
		return ['magnitude', 'unit']
	}

	@Override
	public List getOptionalFields() {
		return []
	}

	@Override
	Object assemble(Class type, Map fieldValues) throws IllegalArgumentException {
		if (!fieldValues.get('magnitude') && !fieldValues.get('unit')){
			return null
		}
		def distance = new Distance()
		distance.set(fieldValues.get('magnitude') ?: '', fieldValues.get('unit') ?: '')
		return distance
	}
/*
	@Override
	public Object assemble(Class type, Map<String,String> fieldValues) 
			throws IllegalArgumentException {
		

	}*/
}
