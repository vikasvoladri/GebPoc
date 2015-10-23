package pages

import geb.Page
import geb.waiting.WaitTimeoutException
import modules.HomePageModule

/**
 * Created by vikasv on 10/15/2015.
 */
class HomePage extends Page{

    UtilityMethods um = new UtilityMethods()

    static at = {
        waitFor{title=="Flight, Cheap Air Tickets , Hotels, Holiday, Trains Package Booking - Yatra.com"}
    }

    static content={
        homePageModule{module(HomePageModule)}
    }

    def hoverTheCursorOnMyAccountLinkAndSelectLoginLink(){
        interact {
            waitFor("mediumwait"){homePageModule.myAccountLink.displayed}
            moveToElement(homePageModule.myAccountLink)
            //waitFor("mediumwait"){homePageModule.loginLink.displayed}
            click homePageModule.loginLink
        }
    }

    def verifyLoggedInUserName(String sExpectedUserName){
        return waitFor("mediumwait"){homePageModule.loggedInUserName.text()==sExpectedUserName}
    }

    def verifyThatSearchSectionIsDisplayed(){
        return waitFor("mediumwait"){homePageModule.searchSection.displayed}
    }

    def clickOnFlightsTab(){
        waitFor{homePageModule.flightsTab.click()}
    }

    def verifyThatOriginCityFieldIdDisplayed(){
        return waitFor{homePageModule.originCityField.displayed}
    }

    def selectRoundTripRadioButton(){
        def roundtrip = homePageModule.roundTripRadioBtn
        waitFor {roundtrip.displayed}
        roundtrip.click()
    }

    def enterOriginCityName(String sOriginCityName, int sOptionNumberToBeSelected){
        homePageModule.originCityField.value(sOriginCityName)
        waitFor{homePageModule.resultItems(sOriginCityName,sOptionNumberToBeSelected).displayed}
        homePageModule.resultItems(sOriginCityName,sOptionNumberToBeSelected).click()
    }

    def enterArrivalCityName(String sArrivalCityName, int sOptionNumberToBeSelected){
        homePageModule.arrivalCityField.value(sArrivalCityName)
        waitFor{homePageModule.resultItems(sArrivalCityName,sOptionNumberToBeSelected).displayed}
        homePageModule.resultItems(sArrivalCityName,sOptionNumberToBeSelected).click()
    }

    def selectDepartureDateFromCurrentDateInSearchSection(int iDays){
        waitFor {homePageModule.selectDate_SearchSection(um.getIncrementalDateFromCurrentDate(iDays,"yyyy_MM_d")).displayed}
        homePageModule.selectDate_SearchSection(um.getIncrementalDateFromCurrentDate(iDays,"yyyy_MM_d")).click()
    }

    def selectArrivalDateFromCurrentDateInSearchSection(int iDays){
        waitFor {homePageModule.selectDate_SearchSection(um.getIncrementalDateFromCurrentDate(iDays,"yyyy_MM_d")).displayed}
        homePageModule.selectDate_SearchSection(um.getIncrementalDateFromCurrentDate(iDays,"yyyy_MM_d")).click()
        //sleep(5000)
    }

    def selectSpecifiedEconomyOption(String sEconomyOption){
        waitFor{homePageModule.economyDropdown.displayed}
        homePageModule.economyDropdown.click()
        /*if(!homePageModule.economyDropDownViewPort.displayed){
            homePageModule.economyDropdown.click()
        }*/
        try{
            waitFor(2){homePageModule.economyDropDownViewPort.displayed}
        }
        catch(WaitTimeoutException e){
            println("Exception message:"+e.getMessage())
            homePageModule.economyDropdown.click()
        }
        //waitFor {homePageModule.economyDropDownViewPort.displayed}
        waitFor{homePageModule.economyOption(sEconomyOption).displayed}
        homePageModule.economyOption(sEconomyOption).click()
    }

    def selectSpecifiedAirlineOption(String sAirlineOption){
        waitFor{homePageModule.preferredAirlineDropDown.displayed}
        homePageModule.preferredAirlineDropDown.click()
        waitFor{homePageModule.preferredAirlineOption(sAirlineOption).displayed}
        homePageModule.preferredAirlineOption(sAirlineOption).click()
    }

    def increaseTravellerCountOfAdult(String sExpectedTravellerCount){
        clickOnTravellerDropDown()
        clickOnAddAdultPlusButton()
        clickOnDoneBtn()
        verifyTravellerCount(sExpectedTravellerCount)
    }

    private void verifyTravellerCount(String sExpectedTravellerCount) {
        waitFor { homePageModule.totalTravellerCount.displayed }
        assert homePageModule.totalTravellerCount.text().trim() == sExpectedTravellerCount
    }

    private void clickOnDoneBtn() {
        waitFor { homePageModule.doneButtonInTravellerSection.displayed }
        homePageModule.doneButtonInTravellerSection.click()
    }

    private void clickOnAddAdultPlusButton() {
        waitFor { homePageModule.adultAddSymbolInTravellerSection.displayed }
        homePageModule.adultAddSymbolInTravellerSection.click()
    }

    private void clickOnTravellerDropDown() {
        waitFor { homePageModule.travellersDrodown.displayed }
        homePageModule.travellersDrodown.click()
    }

    def checkNonStopCheckBox(){
        waitFor {homePageModule.nonStopCheckBox.displayed}
        homePageModule.nonStopCheckBox.click()
    }

    def clickOnSearchBtn(){
        waitFor {homePageModule.searchBtn.displayed}
        homePageModule.searchBtn.click()
    }
}
