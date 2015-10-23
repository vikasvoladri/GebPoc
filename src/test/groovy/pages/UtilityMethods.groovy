package pages

import org.codehaus.groovy.runtime.DateGroovyMethods

/**
 * Created by vikasv on 10/16/2015.
 */
class UtilityMethods {

    def getIncrementalDateFromCurrentDate(int iDays, String sFormat){
        def dateTime = new Date()
        dateTime = DateGroovyMethods.plus(dateTime,iDays)
        return DateGroovyMethods.format(dateTime, sFormat)
    }
}
