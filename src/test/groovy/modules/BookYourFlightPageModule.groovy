package modules

import geb.Module

/**
 * Created by vikasv on 10/19/2015.
 */
class BookYourFlightPageModule extends Module{

    static content = {

        clientInfoSection{$("#clientInfo_res")}
        departureFlightName{$("#mainCont>div:nth-child(1) .airline-CODE")}
        departureAirLineName{$("#mainCont>div:nth-child(1) .airline-NAME")}
        departureCityInformation{$("#mainCont>div:nth-child(1) .departCity")*.text()}
        departureDateAndTimeInfo{$("#mainCont>div:nth-child(1) .dat-n-tim")*.text()}
        arrivalFlightName{$("#mainCont>div:nth-child(2) .airline-CODE")}
        arrivalAirLineName{$("#mainCont>div:nth-child(2) .airline-NAME")}
        arrivalCityInformation{$("#mainCont>div:nth-child(2) .departCity")*.text()}
        arrivalDateAndTimeInfo{$("#mainCont>div:nth-child(2) .dat-n-tim")*.text()}
        fairDetailsText{$("#fareDetails_cont small")}
        continueButtonOnReviewItinerary{$("#logged_in_continue input")}
        passengerDetailsSectionOnTravellerDetails{$("#travellerDetails .pasengrDetail")}
        passengerListTitle{val -> $("#passengerList_"+val+"_title")}
        passengerFirstName{val ->$("#passengerFirstName"+val)}
        passengerMiddleName{val ->$("#passengerMiddleName"+val)}
        passengerLastName{val ->$("#passengerLastName"+val)}
        conditionsCheckbox{$("input[id^='condition_addons']+span")}
        travelProtectionCheckbox{$("input[data-relation^='condition_addons']+span")}
        continueButtonOnTravellersSection{$("#step2Continue")}
        paymentSection{$(".cpmt_Paytabbox")}
        //convenienceFewPrice{$("div:contains('Convenience Fee')+span")}
        convenienceFewPrice{$("#fareDetails_cont>ul>li:nth-child(3)>.fare-brk-rs.fr")}




    }
}
