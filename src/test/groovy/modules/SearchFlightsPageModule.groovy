package modules

import geb.Module

/**
 * Created by vikasv on 10/18/2015.
 */
class SearchFlightsPageModule extends Module{

    static content = {
        resultSideBar{$("#result-sidebar")}
        resultsContainer{$("#resultcontainer")}
        progressBar{$("#bar_progress")}
        progressBarMsg{$("#bar_progress_msg")}
        cityTravelInfoDeparture{$("#resultheader_0 .res-route-info.flL")}
        DepartureDateInfo{$("#resultheader_0 .res-date")}

        cityTravelInfoArrival{$("#resultheader_1 .res-route-info.flL")}
        ArrivalDateInfo{$("#resultheader_1 .res-date")}


        allFlightsAirLineNamesText{$("label[style*='block'] .airline-name.txtTiny")*.text()}
        allFlightsStopOverInformationText{$("label[style*='block'] .stop-over")*.text()}

        departureFlightPriceHeader{$("#resultheader_0 li[class^='res-price']>a")}
        departureFlightPriceOrderArrowValue{$("#resultheader_0 li[class^='res-price']>a span")}
        allDepartureFlightsPriceText{$("#resultbody_0 label[style*='block'] h3>span[id\$='_price']")*.text()}

        arrivalFlightPriceHeader{$("#resultheader_1 li[class^='res-price']>a")}
        arrivalFlightPriceOrderArrowValue{$("#resultheader_1 li[class^='res-price']>a span")}
        allArrivalFlightsPriceText{$("#resultbody_1 label[style*='block'] h3>span[id\$='_price']")*.text()}

        allFlightsBlocksOfDeparture{$("#resultbody_0 label[style*='block']")}
        allDepartureFlightsPriceLocators{$("#resultbody_0 label[style*='block'] h3>span[id\$='_price']")}
        allDepartureFlightNames{$("#resultbody_0 label[style*='block'] span[class='txtTiny']")}
        allFlightsBlocksOfArrival{$("#resultbody_1 label[style*='block']")}
        allArrivalFlightsPriceLocators{$("#resultbody_1 label[style*='block'] h3>span[id\$='_price']")}
        allArrivalFlightNames{$("#resultbody_1 label[style*='block'] span[class='txtTiny']")}

        departureFlightName_SelectionSection{$("#selectionSummary_div ul>.ssCol.tar:nth-child(1) .logoNum")}
        departureFlightCityNames_SelectionSection{$("#selectionSummary_div ul>.ssCol.tar:nth-child(1) .res-route-info.cities")}

        arrivalFlightName_SelectionSection{$("#selectionSummary_div ul>.ssCol.tar:nth-child(2) .logoNum")}
        arrivalFlightCityNames_SelectionSection{$("#selectionSummary_div ul>.ssCol.tar:nth-child(2) .res-route-info.cities")}

        totalSelectedFlightFair{$("#selectionSummary_div #totalFare")}
        bookNowBtn{$("#selectionSummary_div input[title='Book Now']")}
        confirmMsgPopup{$("#popoverWindow .confirmMsg")}
        priceChangeContinueButton{$("#popoverWindow input[value='Continue']")}
        priceChangeDifferenceValue{$("#popoverWindow .fareUp .priceDiffr.clearfix>b")}




    }


}
