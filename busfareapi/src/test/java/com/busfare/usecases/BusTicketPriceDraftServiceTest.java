package com.busfare.usecases;

import com.busfare.entities.BusTicket;
import com.busfare.entities.DraftTicket;
import com.busfare.entities.interfaces.IGetDraftTicket;
import com.busfare.helper.DraftTicketTestBase;
import com.busfare.usecases.interfaces.IBusStationService;
import com.busfare.usecases.interfaces.ITaxRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BusTicketPriceDraftServiceTest extends DraftTicketTestBase {

    private IBusStationService busStationService;
    private ITaxRateService taxRateService;
    private IGetDraftTicket draftTicket;
    private BusTicketPriceDraftService priceDraftService;

    @BeforeEach
    void Setup() {
        busStationService = mock(IBusStationService.class);
        taxRateService = mock(ITaxRateService.class);
        draftTicket = mock(IGetDraftTicket.class);
        priceDraftService = new BusTicketPriceDraftService(busStationService, taxRateService, draftTicket);
    }

    @Test
    @Description("Tests if GetBusDraftTicketPrice() returns mocked value")
    void TestGetBusDraftTicketPrice() {
        //Arrange
        BusTicket busTestTicket = GetTestBusTicket();
        DraftTicket draftTestTicket = GetTestDraftTicket();

        when(busStationService.GetBasePriceForBusStations()).thenReturn(baseFare);
        when(taxRateService.GetTaxRateForTransaction()).thenReturn(VAT);
        when(priceDraftService.GetBusDraftTicketPrice(busTestTicket)).thenReturn(draftTestTicket);

        //Act
        var resultTicket = draftTicket.GetBusDraftTicketWithFares(busTestTicket,
                busStationService.GetBasePriceForBusStations(),
                taxRateService.GetTaxRateForTransaction());

        //Assert
        assertEquals(draftTestTicket, resultTicket);
    }

}