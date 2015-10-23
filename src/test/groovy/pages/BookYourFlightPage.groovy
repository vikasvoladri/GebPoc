package pages

import geb.waiting.WaitTimeoutException
import geb.Page
import modules.BookYourFlightPageModule
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

/**
 * Created by vikasv on 10/19/2015.
 */
class BookYourFlightPage extends Page{
    UtilityMethods um = new UtilityMethods()

    static at = {
        title=="Review Flights"
        waitFor("mediumwait") {bookYourFlightPageModule.clientInfoSection.displayed}
    }

    static content = {
        bookYourFlightPageModule{module(BookYourFlightPageModule)}
    }


    def verifyDepartureFlightName(String sFlightName){
        waitFor {bookYourFlightPageModule.departureFlightName.displayed}
        return bookYourFlightPageModule.departureFlightName.text().trim()==sFlightName
    }

    def verifyDepartureAirlineName(String sAirlineName){
        waitFor {bookYourFlightPageModule.departureAirLineName.displayed}
        return bookYourFlightPageModule.departureAirLineName.text().trim()==sAirlineName
    }

    def verifyDepartureCityInformation(String sFrom, String sTo){
        List<String> fromAndToValues = bookYourFlightPageModule.departureCityInformation
        println("book tickets values:"+fromAndToValues)
        return fromAndToValues.get(0).contains(sFrom)&& fromAndToValues.get(1).contains(sTo)
    }

    def verifyDepartureDateAndTimeInfo(int iDays){
        List<String> datetimes = bookYourFlightPageModule.departureDateAndTimeInfo
        String sExpecetdDate =um.getIncrementalDateFromCurrentDate(iDays,"EEE, d MMM").replaceAll("[\\r\\n\\s]", "")
        return datetimes.get(0).replaceAll("[\\r\\n\\s]", "").equalsIgnoreCase(sExpecetdDate) && datetimes.get(1).replaceAll("[\\r\\n\\s]", "").equalsIgnoreCase(sExpecetdDate)
    }

    def verifyArrivalFlightName(String sFlightName){
        waitFor {bookYourFlightPageModule.arrivalFlightName.displayed}
        return bookYourFlightPageModule.arrivalFlightName.text().trim()==sFlightName
    }

    def verifyArrivalAirlineName(String sAirlineName){
        waitFor {bookYourFlightPageModule.arrivalAirLineName.displayed}
        return bookYourFlightPageModule.arrivalAirLineName.text().trim()==sAirlineName
    }

    def verifyArrivalCityInformation(String sFrom, String sTo){
        List<String> fromAndToValues = bookYourFlightPageModule.arrivalCityInformation
        return fromAndToValues.get(0).contains(sFrom)&& fromAndToValues.get(1).contains(sTo)
    }

    def verifyArrivalDateAndTimeInfo(int iDays){
        List<String> datetimes = bookYourFlightPageModule.arrivalDateAndTimeInfo
        String sExpecetdDate =um.getIncrementalDateFromCurrentDate(iDays,"EEE, d MMM").replaceAll("[\\r\\n\\s]", "")
        return datetimes.get(0).replaceAll("[\\r\\n\\s]", "").equalsIgnoreCase(sExpecetdDate) && datetimes.get(1).replaceAll("[\\r\\n\\s]", "").equalsIgnoreCase(sExpecetdDate)
    }


    def verifyTotalFarePrice(int iExpectedPrice){
        waitFor {bookYourFlightPageModule.fairDetailsText.displayed}
        String sActualText = bookYourFlightPageModule.fairDetailsText.text().replace("Rs.","").replace(",","").trim()
        return Integer.parseInt(sActualText)==iExpectedPrice
    }


    def clickOnContinueButtonOnReviewItineraryPage(){
        waitFor {bookYourFlightPageModule.continueButtonOnReviewItinerary.displayed}
        bookYourFlightPageModule.continueButtonOnReviewItinerary.click()
    }

    def verifyThatPassengersSectionIsDisplayed(){
        return waitFor("mediumwait"){bookYourFlightPageModule.passengerDetailsSectionOnTravellerDetails.displayed}
    }

    def fillPassengerDetails(int iRowNumber, String sTitleText, String sFirstName, String sMiddleName, String sLastName){
        WebElement dropdown = bookYourFlightPageModule.passengerListTitle(iRowNumber).firstElement()
        Select s  = new Select(dropdown)
        s.selectByValue(sTitleText)
        bookYourFlightPageModule.passengerFirstName(iRowNumber).value(sFirstName)
        bookYourFlightPageModule.passengerMiddleName(iRowNumber) << sMiddleName
        bookYourFlightPageModule.passengerLastName(iRowNumber).value(sLastName)
    }

    def checkConditionsCheckbox(){
        waitFor {bookYourFlightPageModule.conditionsCheckbox.displayed}
        bookYourFlightPageModule.conditionsCheckbox.click()
    }

    def unCheckTravelProtectionCheckbox(){
        waitFor {bookYourFlightPageModule.travelProtectionCheckbox.displayed}
        bookYourFlightPageModule.travelProtectionCheckbox.click()
    }

    def clickOnContinueButtonOnTravellersPage(){
        waitFor {bookYourFlightPageModule.continueButtonOnTravellersSection.displayed}
        bookYourFlightPageModule.continueButtonOnTravellersSection.click()
    }

    def verifyThatPaymentSectionIsDisplayed(){
        return waitFor("mediumwait"){bookYourFlightPageModule.paymentSection.displayed}
    }


    def getConvenienceFeeFairPrice(){
        waitFor {bookYourFlightPageModule.convenienceFewPrice.displayed}
        return Integer.parseInt(bookYourFlightPageModule.convenienceFewPrice.text().replace("Rs.","").replace(",","").trim())
    }


}
