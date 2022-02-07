package datebindingtest

class BookController {

    def index() {

        def books = Book.findAll()

        [books: books]

    }



    def create(){

    }


    def save(){


        def book = new Book()

        def whiteList = ['published_hourMin', 'published_meridian', 'published_timeZone', 'published_dayMonthYear', 'published', 'title',  'completed', 'completed_dayMonthYear']


        bindData(book, params, [include: whiteList])


        // this should be moved to a transactional service
        // but I am starting a transaction here just so the code
        // won't throw an exception...
        Book.withNewTransaction {
            book.save(flush: true, failOnError: true)
        }

        redirect(actionName: "index")


    }




}
