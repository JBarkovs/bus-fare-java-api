package com.busfare.usecases;

import com.busfare.entities.BusTicket;
import com.busfare.entities.DraftTicket;
import com.busfare.entities.interfaces.IGetDraftTicket;
import com.busfare.usecases.interfaces.IBusStationService;
import com.busfare.usecases.interfaces.IPriceDraftService;
import com.busfare.usecases.interfaces.ITaxRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusTicketPriceDraftService implements IPriceDraftService {

    private final IBusStationService busStationService;
    private final ITaxRateService taxRateService;

    private final IGetDraftTicket draftTicket;

    /*
    Retrieves and passes the necessary information:
    -bus ticket data;
    -base price for the trip;
    -tax rate for the day;
    to an entity method that makes calculations and returns a DraftTicket
    */
    @Override
    public DraftTicket GetBusDraftTicketPrice(BusTicket busTicket) {
        return draftTicket.GetBusDraftTicketWithFares(busTicket,
                busStationService.GetBasePriceForBusStations(),
                taxRateService.GetTaxRateForTransaction());
    }
}
