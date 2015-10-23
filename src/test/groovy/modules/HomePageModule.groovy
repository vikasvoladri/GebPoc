package modules

import geb.Module

/**
 * Created by vikasv on 10/15/2015.
 */
class HomePageModule extends Module{

    static content = {
        myAccountLink{$("#userSignInStrip>a")}
        loginLink{$("#signInBtn")}
        loggedInUserName{$("#userShowName")}
        searchSection{$("#booking_engine_modues")}
        flightsTab{$("a[data-tab='Flights']")}
        roundTripRadioBtn{$("span[data-trackvalue='Flights Tab - Round Trip'] i")}
        oneWayRadioBtn{$("span[data-trackvalue='Flights Tab - One Way'] i")}
        originCityField{$("#BE_flight_origin_city")}
        //resultItems{val->$(".ac_results li:nth-child("+val+")")}
        resultItems{cityname,val -> $("strong",val, text:contains(cityname))}
        arrivalCityField{$("#BE_flight_arrival_city")}
        selectDate_SearchSection{val->$("#td_"+val)}
        economyDropdown{$("#flight_class_select_title")}
        //economyDropdown{$("#flight_class_select_msdd .sprite.pointDwnPax")}
        economyDropDownViewPort{$("#flight_class_select_child .viewport")}
        economyOption{val->$("#flight_class_select_child span",text:val)}
        preferredAirlineDropDown{$("#BE_flight_preferred_airline_title")}
        preferredAirlineOption{val->$("#BE_flight_preferred_airline_child span",text:val)}
        travellersDrodown{$("#BE_flight_form #BE_flight_paxInfoBox")}
        adultAddSymbolInTravellerSection{$("div[data-flightagegroup='adult'][class^='pax-limit']  .ddSpinnerPlus")}
        doneButtonInTravellerSection{$(".done")}
        totalTravellerCount{$("#BE_flight_form #BE_flight_paxInfoBox .totalCount")}
        nonStopCheckBox{$("label[For='BE_flight_non_stop']>i")}
        searchBtn{$("#BE_flight_form #BE_flight_flsearch_btn")}




    }
}
