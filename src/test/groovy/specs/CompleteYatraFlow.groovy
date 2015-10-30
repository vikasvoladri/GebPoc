package specs

import geb.spock.GebReportingSpec
import pages.BookYourFlightPage
import pages.HomePage
import pages.LoginPage
import pages.SearchFlightsPage
import spock.lang.IgnoreRest
import spock.lang.Shared
import spock.lang.Stepwise

/**
 * Created by vikasv on 10/15/2015.
 */

//Declared StepWise attribute as i want the methods defined in this spec class to
// execute in a sequential order as it is complete work flow
@Stepwise
class CompleteYatraFlow extends GebReportingSpec{

    //Declared below variables as shared, as i want to use their values from one featur emethods to other feature methods
    @Shared int iDepartureDateFromCurrentDate
    @Shared int iArrivalDateFromCurrentDate
    @Shared int iTotalFairValue
    @Shared String sDepartureFlightName
    @Shared String sArrivalFlightName

    //@IgnoreRest
    def "Login to yatra with valid credentials"(){
        given: "Navigating to yatra site and verifying that home page is displayed"
        to HomePage

        when: "Hover the cursor on My Account link and click on SignIn link"
        hoverTheCursorOnMyAccountLinkAndSelectLoginLink()

        then: "verify that login page is displayed"
        at LoginPage

        when: "Log in to yatra site with valid credentials"
        logInToYatraWithValidCredentials("vikas.voladri@gmail.com","vikas@123")

        then: "verify that home page is displayed and logged in username is displayed"
        at HomePage
        verifyLoggedInUserName("Vikas Reddy")
    }

    def "Search for flights by selecting all valid options"(){

        //Here below variables defines the date to be selected from current date while selecting flights
        iDepartureDateFromCurrentDate = 8
        iArrivalDateFromCurrentDate = 12

        when: "Get home page instance"
        at HomePage

        then: "Verify that search flights section is displayed on home page"
        verifyThatSearchSectionIsDisplayed()

        when: "Click on flights tab"
        clickOnFlightsTab()

        then: "Verify that origin city field is displayed"
        verifyThatOriginCityFieldIdDisplayed()

        when: "Enter all details like origin city, destination city, departure date, arrival date, no of travellers" +
                ",economy, airlines, non-stop check box and click on search button"
        selectRoundTripRadioButton()
        enterOriginCityName("Hyderabad",0)
        enterArrivalCityName("Bangalore",0)
        selectDepartureDateFromCurrentDateInSearchSection(iDepartureDateFromCurrentDate)
        selectArrivalDateFromCurrentDateInSearchSection(iArrivalDateFromCurrentDate)
        selectSpecifiedEconomyOption("Economy")
        selectSpecifiedAirlineOption("Indigo")
        increaseTravellerCountOfAdult("2")
        checkNonStopCheckBox()
        clickOnSearchBtn()

        then: "Verify that search flights page is displayed and verify that flights are displayed based on the search criteria that " +
                "was set in above when block"
        at SearchFlightsPage
        waitUntilProgressBarDisappears()
        verifyFirstWayCityInformation("HYD BLR")
        verifySecondWayCityInformation("BLR HYD")
        verifyDepartureDateText(iDepartureDateFromCurrentDate)
        verifyArrivalDateText(iArrivalDateFromCurrentDate)
        verifyAirlineText("Indigo")
        verifyStopOverText("Non Stop")
    }

    def"Sort Available Flights based on Price"(){

        when: "Store all flight prices and sort the flights in descending order based on price field"
        at SearchFlightsPage
        List<Integer> DeparturePrices = getAllDepartureFlightsPrices()
        List<Integer> ArrivalPrices = getAllArrivalFlightsPrices()
        sortDepartureFlightsInDecendingOrderBasedOnPrices()
        sortArrivalFlightsInDecendingOrderBasedOnPrices()

        then: "verify that all flights are sorted in descending order"
        verifyThatDepartureFlightsSortedInDecendingOrderBasedOnPrices(DeparturePrices)
        verifyThatArrivalFlightsSortedInDecendingOrderBasedOnPrices(ArrivalPrices)

        when: "Sort all flights in ascending order based on price field"
        sortDepartureFlightsInAscendingOrderBasedOnPrices()
        sortArrivalFlightsInAscendingOrderBasedOnPrices()

        then: "verify that all flights are sorted in ascending order"
        verifyThatDepartureFlightsSortedInAscendingOrderBasedOnPrices(DeparturePrices)
        verifyThatArrivalFlightsSortedInAscendingOrderBasedOnPrices(ArrivalPrices)
    }

    def "Select any flight from available flights"(){

        when: "Select any flight based on row numbers and get details like total expected price fair, flight names"
        at SearchFlightsPage
        selectSpecifiedRowsInDepartureAndArrivalFlightBlocks(1,1)
        iTotalFairValue = getExpectedFairForTotalJourney(1,1,2)
        sDepartureFlightName = getFlightNameOfSelectedDepartureFlight(1)
        sArrivalFlightName = getFlightNameOfSelectedArrivalFlight(1)

        then: "Verify that city names, flight names and expected total price is displayed correctly on current selection section"
        verifyDepartureFlightNameInCurrentSelectionWindow(sDepartureFlightName)
        verifyDepartureFlightCityInfoInCurrentSelectionWindow("HYD BLR")
        verifyArrivalFlightNameInCurrentSelectionWindow(sArrivalFlightName)
        verifyArrivalFlightCityInfoInCurrentSelectionWindow("BLR HYD")
        verifyTotalFairAmountInCurrentSelectionWindow(iTotalFairValue)

        when: "Click on Book Now button and get difference price value if price is increased"
        clickOnBookNowBtn()
        int iDiffPrice = getDifferencePriceIfPriceIncreases()
        iTotalFairValue = iTotalFairValue+iDiffPrice

        then: "Verify that flight names, airline names, city information, departure and arrival dates, price values " +
                "on Review Itinerary section"
        at BookYourFlightPage
        verifyDepartureFlightName(sDepartureFlightName)
        verifyDepartureAirlineName("Indigo")
        verifyDepartureCityInformation("HYD","BLR")
        verifyDepartureDateAndTimeInfo(iDepartureDateFromCurrentDate)
        verifyArrivalFlightName(sArrivalFlightName)
        verifyArrivalAirlineName("Indigo")
        verifyArrivalCityInformation("BLR","HYD")
        verifyArrivalDateAndTimeInfo(iArrivalDateFromCurrentDate)
        verifyTotalFarePrice(iTotalFairValue)
    }


    def "Book flight by going thorough 3 steps defined"(){

        when: "Click continue button on Review Itinerary section"
        at BookYourFlightPage
        clickOnContinueButtonOnReviewItineraryPage()

        then: "Verify that traveller's section is displayed and total price value is displayed correctly"
        verifyThatPassengersSectionIsDisplayed()
        verifyTotalFarePrice(iTotalFairValue)

        when: "Enter passenger details and uncheck travel protection checkbox and click on continue button"
        fillPassengerDetails(0,"Mr","FirstOne","MiddleOne","LastOne")
        fillPassengerDetails(1,"Mrs","First","Middle","Last")
        unCheckTravelProtectionCheckbox()
        clickOnContinueButtonOnTravellersPage()

        then: "verify that payment section is displayed and total expected flight fair price is displayed correctly"
        verifyThatPaymentSectionIsDisplayed()
        verifyTotalFarePrice(iTotalFairValue+getConvenienceFeeFairPrice())
    }
}
