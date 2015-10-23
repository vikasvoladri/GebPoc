package pages

import geb.Page
import geb.waiting.WaitTimeoutException
import modules.SearchFlightsPageModule
import org.openqa.selenium.WebElement

/**
 * Created by vikasv on 10/18/2015.
 */
class SearchFlightsPage extends Page{

    UtilityMethods um = new UtilityMethods()

    static at = {
        waitFor{searchFlightsPageModule.resultSideBar.displayed}
    }

    static content = {
        searchFlightsPageModule{module(SearchFlightsPageModule)}
    }

    def waitUntilProgressBarDisappears(){
        waitFor("longwait"){!searchFlightsPageModule.progressBar.displayed}
    }

    def verifyFirstWayCityInformation(String sExpectedText){
        waitFor {searchFlightsPageModule.cityTravelInfoDeparture.displayed}
        println("text value:"+searchFlightsPageModule.cityTravelInfoDeparture.text().trim().replaceAll("[\\r\\n\\s]", ""))
        return searchFlightsPageModule.cityTravelInfoDeparture.text().trim().replaceAll("[\\r\\n\\s]", "")==sExpectedText.replaceAll("[\\r\\n\\s]", "")
    }

    def verifySecondWayCityInformation(String sExpectedText){
        waitFor {searchFlightsPageModule.cityTravelInfoArrival.displayed}
        println("text value:"+searchFlightsPageModule.cityTravelInfoArrival.text().trim().replaceAll("[\\r\\n\\s]", ""))
        return searchFlightsPageModule.cityTravelInfoArrival.text().trim().replaceAll("[\\r\\n\\s]", "")==sExpectedText.replaceAll("[\\r\\n\\s]", "")
    }

    def verifyDepartureDateText(int iDays){
        waitFor {searchFlightsPageModule.DepartureDateInfo.displayed}
        println("text value:"+searchFlightsPageModule.DepartureDateInfo.text().trim().replaceAll("[\\r\\n\\s]", ""))
        return searchFlightsPageModule.DepartureDateInfo.text().trim().replaceAll("[\\r\\n\\s]", "")==um.getIncrementalDateFromCurrentDate(iDays,"EEE, d MMM").replaceAll("[\\r\\n\\s]", "")
    }

    def verifyArrivalDateText(int iDays){
        waitFor {searchFlightsPageModule.ArrivalDateInfo.displayed}
        println("text value:"+searchFlightsPageModule.ArrivalDateInfo.text().trim().replaceAll("[\\r\\n\\s]", ""))
        return searchFlightsPageModule.ArrivalDateInfo.text().trim().replaceAll("[\\r\\n\\s]", "")==um.getIncrementalDateFromCurrentDate(iDays,"EEE, d MMM").replaceAll("[\\r\\n\\s]", "")
    }

    def verifyAirlineText(String sExpectedAirlineText){
        List<String> allAirlineTexts = searchFlightsPageModule.allFlightsAirLineNamesText
        println("size of airlines:"+allAirlineTexts.size())
        for (String item : allAirlineTexts) {
            assert item.trim()==sExpectedAirlineText
        }
        println("iteration done")
        return true
    }

    def verifyStopOverText(String sExpectedStopOverText){
        List<String> allStopOverText = searchFlightsPageModule.allFlightsStopOverInformationText
        println("size of stop overs:"+allStopOverText.size())
        for (String item : allStopOverText) {
            assert item.trim()==sExpectedStopOverText
        }
        println("iteration done")
        return true
    }

    def getAllDepartureFlightsPrices(){
        List<String> sAllPrices = searchFlightsPageModule.allDepartureFlightsPriceText
        List<Integer> iAllPrices = new ArrayList<Integer>()
        for(String item:sAllPrices){
            iAllPrices.add(Integer.parseInt(item.trim().replace(",","")))
        }
        return iAllPrices
    }

    def sortDepartureFlightsInAscendingOrderBasedOnPrices(){
        waitFor{searchFlightsPageModule.departureFlightPriceOrderArrowValue.displayed}
        if(!searchFlightsPageModule.departureFlightPriceOrderArrowValue.getAttribute("class").equalsIgnoreCase("ico-arrow-up")){
            waitFor {searchFlightsPageModule.departureFlightPriceHeader.displayed}
            searchFlightsPageModule.departureFlightPriceHeader.click()
        }
    }

    def sortDepartureFlightsInDecendingOrderBasedOnPrices(){
        waitFor{searchFlightsPageModule.departureFlightPriceOrderArrowValue.displayed}
        if(!searchFlightsPageModule.departureFlightPriceOrderArrowValue.getAttribute("class").equalsIgnoreCase("ico-arrow-down")){
            waitFor {searchFlightsPageModule.departureFlightPriceHeader.displayed}
            searchFlightsPageModule.departureFlightPriceHeader.click()
        }
    }

    def verifyThatDepartureFlightsSortedInAscendingOrderBasedOnPrices(List<Integer> allPrices){
        List<Integer> prices = allPrices
        waitFor{searchFlightsPageModule.departureFlightPriceOrderArrowValue.displayed}
        assert searchFlightsPageModule.departureFlightPriceOrderArrowValue.getAttribute("class").equalsIgnoreCase("ico-arrow-up")
        List<Integer> allActualPrices = getAllDepartureFlightsPrices()
        prices = prices.sort()
        return allActualPrices == prices
    }

    def verifyThatDepartureFlightsSortedInDecendingOrderBasedOnPrices(List<Integer> allPrices){
        List<Integer> prices = allPrices
        waitFor{searchFlightsPageModule.departureFlightPriceOrderArrowValue.displayed}
        assert searchFlightsPageModule.departureFlightPriceOrderArrowValue.getAttribute("class").equalsIgnoreCase("ico-arrow-down")
        List<Integer> allActualPrices = getAllDepartureFlightsPrices()
        prices = prices.sort().reverse()
        return allActualPrices == prices
    }

    def getAllArrivalFlightsPrices(){
        List<String> sAllPrices = searchFlightsPageModule.allArrivalFlightsPriceText
        List<Integer> iAllPrices = new ArrayList<Integer>()
        for(String item:sAllPrices){
            iAllPrices.add(Integer.parseInt(item.trim().replace(",","")))
        }
        return iAllPrices
    }

    def sortArrivalFlightsInAscendingOrderBasedOnPrices(){
        waitFor{searchFlightsPageModule.arrivalFlightPriceOrderArrowValue.displayed}
        if(!searchFlightsPageModule.arrivalFlightPriceOrderArrowValue.getAttribute("class").equalsIgnoreCase("ico-arrow-up")){
            waitFor {searchFlightsPageModule.arrivalFlightPriceHeader.displayed}
            searchFlightsPageModule.arrivalFlightPriceHeader.click()
        }
    }

    def sortArrivalFlightsInDecendingOrderBasedOnPrices(){
        waitFor{searchFlightsPageModule.arrivalFlightPriceOrderArrowValue.displayed}
        if(!searchFlightsPageModule.arrivalFlightPriceOrderArrowValue.getAttribute("class").equalsIgnoreCase("ico-arrow-down")){
            waitFor {searchFlightsPageModule.arrivalFlightPriceHeader.displayed}
            searchFlightsPageModule.arrivalFlightPriceHeader.click()
        }
    }

    def verifyThatArrivalFlightsSortedInAscendingOrderBasedOnPrices(List<Integer> allPrices){
        List<Integer> prices = allPrices
        waitFor{searchFlightsPageModule.arrivalFlightPriceOrderArrowValue.displayed}
        assert searchFlightsPageModule.arrivalFlightPriceOrderArrowValue.getAttribute("class").equalsIgnoreCase("ico-arrow-up")
        List<Integer> allActualPrices = getAllArrivalFlightsPrices()
        prices = prices.sort()
        return allActualPrices == prices
    }

    def verifyThatArrivalFlightsSortedInDecendingOrderBasedOnPrices(List<Integer> allPrices){
        List<Integer> prices = allPrices
        waitFor{searchFlightsPageModule.arrivalFlightPriceOrderArrowValue.displayed}
        assert searchFlightsPageModule.arrivalFlightPriceOrderArrowValue.getAttribute("class").equalsIgnoreCase("ico-arrow-down")
        List<Integer> allActualPrices = getAllArrivalFlightsPrices()
        prices = prices.sort().reverse()
        return allActualPrices == prices
    }

    def selectSpecifiedRowsInDepartureAndArrivalFlightBlocks(int iDepartureFlightRow, int iArrivalFlightRow){
        waitFor {searchFlightsPageModule.allFlightsBlocksOfDeparture.displayed}
        List<WebElement> allDepartureBlocks = searchFlightsPageModule.allFlightsBlocksOfDeparture.allElements()
        waitFor {searchFlightsPageModule.allFlightsBlocksOfArrival.displayed}
        List<WebElement> allArrivalBlocks = searchFlightsPageModule.allFlightsBlocksOfArrival.allElements()


        allDepartureBlocks.get(iDepartureFlightRow).click()

        allArrivalBlocks.get(iArrivalFlightRow).click()

    }

    def getFlightNameOfSelectedDepartureFlight(int iDepartureFlightRow){
        waitFor {searchFlightsPageModule.allDepartureFlightNames.displayed}
        List<WebElement> allDepartureFlightNames = searchFlightsPageModule.allDepartureFlightNames.allElements()
        return allDepartureFlightNames.get(iDepartureFlightRow).text.trim()
    }

    def getFlightNameOfSelectedArrivalFlight(int iDepartureFlightRow){
        waitFor {searchFlightsPageModule.allArrivalFlightNames.displayed}
        List<WebElement> allArrivalFlightNames = searchFlightsPageModule.allArrivalFlightNames.allElements()
        return allArrivalFlightNames.get(iDepartureFlightRow).text.trim()
    }

    def getExpectedFairForTotalJourney(int iDepartureFlightRow, int iArrivalFlightRow, int iTotalNumberOfTravellers){
        waitFor {searchFlightsPageModule.allDepartureFlightsPriceLocators.displayed}
        List<WebElement> allDepartureFlightsPrices = searchFlightsPageModule.allDepartureFlightsPriceLocators.allElements()
        waitFor {searchFlightsPageModule.allArrivalFlightsPriceLocators.displayed}
        List<WebElement> allArrivalFlightsPrices = searchFlightsPageModule.allArrivalFlightsPriceLocators.allElements()

        int iDeparturePricePerAdult = Integer.parseInt(allDepartureFlightsPrices.get(iDepartureFlightRow).getText().trim().replace(",",""))
        int iArrivalPricePerAdult = Integer.parseInt(allArrivalFlightsPrices.get(iDepartureFlightRow).getText().trim().replace(",",""))
        return (iDeparturePricePerAdult+iArrivalPricePerAdult)*iTotalNumberOfTravellers
    }


    def verifyDepartureFlightNameInCurrentSelectionWindow(String sDepartureFlightName){
        waitFor {searchFlightsPageModule.departureFlightName_SelectionSection.displayed}
        return searchFlightsPageModule.departureFlightName_SelectionSection.text().trim()==sDepartureFlightName
    }

    def verifyDepartureFlightCityInfoInCurrentSelectionWindow(String sExpectedCityInfo){
        waitFor {searchFlightsPageModule.departureFlightCityNames_SelectionSection.displayed}
        println("selection city info:"+searchFlightsPageModule.departureFlightCityNames_SelectionSection.text().trim().replaceAll("[\\r\\n\\s]", ""))
        return searchFlightsPageModule.departureFlightCityNames_SelectionSection.text().trim().replaceAll("[\\r\\n\\s]", "")==sExpectedCityInfo.replaceAll("[\\r\\n\\s]", "")
    }


    def verifyArrivalFlightNameInCurrentSelectionWindow(String sArrivalFlightName){
        waitFor {searchFlightsPageModule.arrivalFlightName_SelectionSection.displayed}
        return searchFlightsPageModule.arrivalFlightName_SelectionSection.text().trim()==sArrivalFlightName
    }

    def verifyArrivalFlightCityInfoInCurrentSelectionWindow(String sExpectedCityInfo){
        waitFor {searchFlightsPageModule.arrivalFlightCityNames_SelectionSection.displayed}
        println("selection city info:"+searchFlightsPageModule.arrivalFlightCityNames_SelectionSection.text().trim().replaceAll("[\\r\\n\\s]", ""))
        return searchFlightsPageModule.arrivalFlightCityNames_SelectionSection.text().trim().replaceAll("[\\r\\n\\s]", "")==sExpectedCityInfo.replaceAll("[\\r\\n\\s]", "")
    }

    def verifyTotalFairAmountInCurrentSelectionWindow(int iTotalExpectedFairAmount){
        waitFor {searchFlightsPageModule.totalSelectedFlightFair.displayed}
        int iActualValue = Integer.parseInt(searchFlightsPageModule.totalSelectedFlightFair.text().trim().replace(",",""))
        return iActualValue = iTotalExpectedFairAmount
    }


    def clickOnBookNowBtn(){
        waitFor {searchFlightsPageModule.bookNowBtn.displayed}
        searchFlightsPageModule.bookNowBtn.click()
    }

    def waitUntilPopUpWindowDisappears(){
        waitFor("longwait"){!searchFlightsPageModule.confirmMsgPopup.displayed}
    }


    def getDifferencePriceIfPriceIncreases(){
        int iPrice =0
        try{
            waitFor(3){searchFlightsPageModule.priceChangeContinueButton.displayed}
            iPrice = Integer.parseInt(searchFlightsPageModule.priceChangeDifferenceValue.text().replace("Rs.","").replace(",","").trim())
            searchFlightsPageModule.priceChangeContinueButton.click()
        }
        catch(WaitTimeoutException e){

        }
        return iPrice
    }


}
