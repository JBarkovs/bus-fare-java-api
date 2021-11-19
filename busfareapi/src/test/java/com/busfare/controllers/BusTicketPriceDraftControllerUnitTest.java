package com.busfare.controllers;

import com.busfare.entities.*;
import com.busfare.helper.DraftTicketTestBase;
import com.busfare.helper.DraftTicketTestConstants;
import com.busfare.controllers.helper.IBusTicketSerializer;
import com.busfare.usecases.interfaces.IPriceDraftService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BusTicketPriceDraftControllerUnitTest extends DraftTicketTestBase {

    private IPriceDraftService priceDraftService;
    private IBusTicketSerializer serializerService;
    private BusTicketPriceDraftController busTicketController;

    @BeforeEach
    void Setup() {
        priceDraftService = mock(IPriceDraftService.class);
        serializerService = mock(IBusTicketSerializer.class);
        busTicketController = new BusTicketPriceDraftController(priceDraftService, serializerService);
    }

    @Test
    @Description("Tests if sendBusDraftTicketPrice() returns mocked value")
    void TestSendBusDraftTicketPrice() {
        //Arrange
        BusTicket busTestTicket = GetTestBusTicket();
        DraftTicket draftTestTicket = GetTestDraftTicket();

        when(serializerService.DeserializeToBusTicket(DraftTicketTestConstants.PAYLOAD)).thenReturn(busTestTicket);
        when(serializerService.SerializeFromDraftTicket(draftTestTicket)).thenReturn(DraftTicketTestConstants.RESULT);
        when(priceDraftService.GetBusDraftTicketPrice(busTestTicket)).thenReturn(draftTestTicket);

        //Act
        var resultString = busTicketController.sendBusDraftTicketPrice(DraftTicketTestConstants.PAYLOAD);

        //Assert
        assertEquals(DraftTicketTestConstants.RESULT, resultString);
    }

}