beans = {
    myCustomStructuredDateEditor(MyStructuredDateEditor) {
        it.autowire = 'byName'
    }

    myCustomStructuredSqlDateEditor(MyStructuredSqlDateEditor) {
        it.autowire = 'byName'
    }



}
