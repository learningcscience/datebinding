import datebindingtest.TimeService
import org.grails.web.binding.StructuredDateEditor
import org.springframework.beans.PropertyEditorRegistrar
import org.springframework.beans.PropertyEditorRegistry

import java.text.SimpleDateFormat

class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar{

    TimeService timeService

    @Override
    void registerCustomEditors(PropertyEditorRegistry registry) {


        registry.registerCustomEditor(java.util.Date, new MyStructuredDateEditor(timeService))


    }
}
