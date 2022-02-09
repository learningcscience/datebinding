beans = {
    myCustomStructuredDateEditor(MyStructuredDateEditor) {
        it.autowire = 'byName'
    }

    myCustomStructuredSqlDateEditor(MyStructuredSqlDateEditor) {
        it.autowire = 'byName'
    }



    myCustomStructuredTimeEditor(StructuredTImeEditor) {
        it.autowire = 'byName'
    }


    myCustomStructuredDistanceEditor(DistanceEditor) {
        it.autowire = 'byName'
    }

}
