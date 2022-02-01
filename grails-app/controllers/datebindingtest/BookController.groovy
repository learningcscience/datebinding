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


        def whiteList = ['published_hourMin', 'published_meridian', 'published_timeZone', 'published_dayMonthYear', 'published', 'title']


        bindData(book, params, [include: whiteList])


        book.save(flush:true, failOnError:true)

        redirect(actionName: "index")


    }




}
