package datebindingtest

import java.sql.Time
import java.text.SimpleDateFormat
import org.springframework.util.Assert


/**
 * Provides methods to manipulate Date and Calendar objects so they are
 * formatted, parsed, and retrieved in a consistent manner.
 * @author Ryan Padilla
 *
 */
class TimeService {

    static transactional = false

    static final List<TimeZone> TIME_ZONES = [
            "US/Hawaii",
            "US/Alaska",
            "US/Pacific",
            "US/Arizona",
            "US/Mountain",
            "US/Central",
            "US/Eastern",
            "US/East-Indiana",
    ].collect{
        TimeZone.getTimeZone(it)
    }.sort{ a, b ->
        a.rawOffset.compareTo(b.rawOffset)
    }

    /**
     * Formats Date object show display time and date
     */
    static final String DATE_TIME_FORMAT = "MM/dd/yyyy h:mm a"
    /**
     * Formats Date object to show time, date, and time zone correctly
     */
    static final String DATE_TIME_ZONE_FORMAT = "MM/dd/yyyy h:mm a z"
    /**
     * Formats Date object to show only the date
     */
    static final String DATE_FORMAT = "MM/dd/yyyy"


    /**
     * Formats Date object to show only the date, year with 2 characters
     */
    static final String DATE_FORMAT_YEAR_TWO_CHARACTERS = "MM/dd/yy"


    /**
     * Format Date object to show the time
     */
    static final String TIME_FORMAT = "h:mm a"
    /**
     * Format Date object to show just the hour and minutes
     */
    static final String HOUR_MIN_FORMAT = "h:mm"
    /**
     * Format Date object to retrieve the meridian
     */
    static final String MERIDIAN_FORMAT = "a"

    /**
     * @param date day, month, and year
     * @param hours hour of the day
     * @param min minute within the hour
     * @param meridian morning or evening
     * @return a Date object
     */
    def parseDateTime(String date, String hours, String min, String meridian){
        return parseDateTime(hours + ":" + min + " " + meridian + " " + date)
    }

    /**
     * Format a date object with the given format
     * @param format format used in the SimpleDateFormat
     * @param date date object to be formatted
     * @return string representing the passed in Date
     */
    def formatDate(String format, Date date, TimeZone timeZone = null){


        SimpleDateFormat formatter = new SimpleDateFormat(format)
        if (timeZone) formatter.setTimeZone(timeZone)
        return formatter.format(date)
    }

    /**
     * Format a date using DATE_FORMAT
     * @param date date object to be formatted
     * @return string representing the passed in Date
     */
    def formatDate(Date date, TimeZone timeZone = null){
        def formatter = new SimpleDateFormat(DATE_FORMAT)
        if (timeZone) formatter.setTimeZone(timeZone)
        return formatter.format(date)
    }





    /**
     * Format a date using DATE_FORMAT
     * @param date date object to be formatted
     * @return string representing the passed in Date
     */
    def formatDateYearTwoCharacters(Date date, TimeZone timeZone = null){
        def formatter = new SimpleDateFormat(DATE_FORMAT_YEAR_TWO_CHARACTERS)
        if (timeZone) formatter.setTimeZone(timeZone)
        return formatter.format(date)
    }






    /**
     * Parse a date using DATE_FORMAT
     * @param date string object to be parsed
     * @return a new date object with the specified date
     */
    def parseDate(String date, TimeZone timeZone = null){
        def formatter = new SimpleDateFormat(DATE_FORMAT.replace('yyyy', 'yy'))
        if (timeZone) formatter.setTimeZone(timeZone)
        return formatter.parse(date)
    }

    /**
     * Format a date using DATE_TIME_FORMAT
     * @param date date object to be formatted
     * @return string representing the passed in Date
     */
    def formatDateTime(Date date, TimeZone timeZone = null){
        def formatter = new SimpleDateFormat(DATE_TIME_FORMAT)
        if (timeZone) formatter.setTimeZone(timeZone)
        return formatter.format(date)
    }

    /**
     * Format a date using DATE_TIME_FORMAT
     * @param date date object to be formatted
     * @return string representing the passed in Date using the defined timeZone
     */
    def formatDateTimeZone(Date date, TimeZone timeZone = null){
        def formatter = new SimpleDateFormat(DATE_TIME_ZONE_FORMAT)
        if (timeZone) formatter.setTimeZone(timeZone)
        return formatter.format(date)
    }

    /**
     * Parse a date using DATE_TIME_FORMAT
     * @param date string object to be parsed
     * @return a new date object with the specified date
     */
    def parseDateTime(String date, TimeZone timeZone = null){
        def formatter = new SimpleDateFormat(DATE_TIME_FORMAT.replace('yyyy', 'yy'))
        if (timeZone) formatter.setTimeZone(timeZone)
        return formatter.parse(date)
    }

    /**
     * Parse a date using DATE_TIME_ZONE_FORMAT
     * @param date string object to be parsed
     * @return a new date object with the specified date and timeZone
     */
    def parseDateTimeZone(String date, TimeZone timeZone = null){
        def formatter = new SimpleDateFormat(DATE_TIME_ZONE_FORMAT.replace('yyyy', 'yy'))
        if (timeZone) formatter.setTimeZone(timeZone)
        return formatter.parse(date)
    }

    /**
     * Format a date using TIME_FORMAT
     * @param date date object to be formatted
     * @return string representing the passed in Date
     */
    def formatTime(Date date, TimeZone timeZone = null){
        def formatter = new SimpleDateFormat(TIME_FORMAT)
        if (timeZone) formatter.setTimeZone(timeZone)
        return formatter.format(date)
    }

    /**
     * Parse a date using TIME_FORMAT
     * @param date string object to be parsed
     * @return a new date object with the specified date
     */
    def parseTime(String date, TimeZone timeZone = null){
        def formatter = new SimpleDateFormat(TIME_FORMAT.replace('yyyy', 'yy'))
        if (timeZone) formatter.setTimeZone(timeZone)
        return formatter.parse(date)
    }


    /**
     * Format a date using HOUR_MIN_FORMAT
     * @param date date object to be formatted
     * @return string representing the passed in Date
     */
    def formatHourMin(Date date, TimeZone timeZone = null){
        def formatter = new SimpleDateFormat(HOUR_MIN_FORMAT)
        if (timeZone) formatter.setTimeZone(timeZone)
        return formatter.format(date)
    }


    /**
     * Format a date using MERIDIAN_FORMAT
     * @param date date object to be formatted
     * @return string representing the passed in Date
     */
    def formatMeridian(Date date, TimeZone timeZone){
        def formatter = new SimpleDateFormat(MERIDIAN_FORMAT)
        if (timeZone) formatter.setTimeZone(timeZone)
        return formatter.format(date)
    }

    /**
     * Creates a new Date object
     * @return a new Date object
     */
    def now(){
        def now = new Date()
        return now
    }

    /**
     * Creates a new Calendar object with the specified timeZone
     * @return new Calendar object with the specified timeZone
     */
    def getCalendar(TimeZone timeZone = null){
        Calendar calendar
        if (timeZone) calendar = Calendar.getInstance(timeZone)
        else calendar = Calendar.getInstance()

        return calendar
    }


    Date firstDayOfYear(){


        def startDate
        startDate = Calendar.getInstance()

        startDate.set(Calendar.DAY_OF_YEAR, 1)

        startDate.set(Calendar.HOUR_OF_DAY, 0)
        startDate.set(Calendar.MINUTE, 0)
        startDate.set(Calendar.SECOND, 0)
        startDate.set(Calendar.MILLISECOND, 0)

        return startDate.getTime()



    }





    def getDateFromStartDayTime(Date startDate, int day, Time startTime){


        def date
        date = Calendar.getInstance()
        date.setTime(startDate)

        date.set(Calendar.HOUR_OF_DAY, 0)
        date.set(Calendar.MINUTE, 0)
        date.set(Calendar.SECOND, 0)
        date.set(Calendar.MILLISECOND, 0)

        date.add(Calendar.DATE, day - 1)



        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.setTime(startTime);



        Calendar result = Calendar.getInstance();
        result.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH) ,
                timeCalendar.get(Calendar.HOUR_OF_DAY), timeCalendar.get(Calendar.MINUTE), timeCalendar.get(Calendar.SECOND));

        Date resultDate = result.getTime();




        return resultDate
        //return new Date(date.getTimeInMillis() + startTime.getTime())

        //return date.getTime()




    }





    java.sql.Date eventDay(Date eventStartDate){

        def startDate
        startDate = Calendar.getInstance()
        startDate.setTime(eventStartDate)

        startDate.set(Calendar.HOUR_OF_DAY, 0)
        startDate.set(Calendar.MINUTE, 0)
        startDate.set(Calendar.SECOND, 0)
        startDate.set(Calendar.MILLISECOND, 0)
        new java.sql.Date(startDate.getTime().getTime())

    }




    /**
     * Create a new Date object the represent the current date at midnight
     * @return a new Date whose value is at the beginning of the current day.
     */
    java.sql.Date today(TimeZone timeZone = null){
        def today
        if (timeZone) today = Calendar.getInstance(timeZone)
        else today = Calendar.getInstance()
        today.set(Calendar.HOUR_OF_DAY, 0)
        today.set(Calendar.MINUTE, 0)
        today.set(Calendar.SECOND, 0)
        today.set(Calendar.MILLISECOND, 0)
        new java.sql.Date(today.getTime().getTime())
    }


    java.sql.Date yesterday(TimeZone timeZone = null){

        return today() - 1

    }


    int howManyDaysAgo(Date date){

        def tod = today()

        def diffTime = tod.getTime() - date.getTime()


        return diffTime / (1000 * 60 * 60 * 24)



    }




    int calculateAge(Date birthDate, Date asOf = null){
        Assert.notNull(birthDate)

        if (!asOf) asOf = new Date()
        Calendar start = getCalendar()
        start.setTime(birthDate)
        Calendar end = getCalendar()
        end.setTime(asOf)
        int age = end.get(Calendar.YEAR) - start.get(Calendar.YEAR)
        if (end.get(Calendar.MONTH) < start.get(Calendar.MONTH)) {
            age--
        } else if (end.get(Calendar.MONTH) == start.get(Calendar.MONTH)
                && end.get(Calendar.DAY_OF_MONTH) < start.get(Calendar.DAY_OF_MONTH)) {
            age--
        }
        return age
    }
}
