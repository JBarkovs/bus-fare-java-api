package com.busfare.controllers;

import com.busfare.controllers.helper.IBusTicketSerializer;
import com.busfare.usecases.interfaces.IPriceDraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BusTicketPriceDraftController implements ISendTicketPriceDraft {

    private final IPriceDraftService priceDraftService;
    private final IBusTicketSerializer serializerService;

    /*
    Handles a POST request with body (bus ticket data),
    deserializes string(json) to a POJO (BusTicket),
    passes it to the Service responsible for calculations,
    then returns a serialized response string(json)- draft ticket (DraftTicket) with prices
    */
    @Override
    @PostMapping(path = "/ticket",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String sendBusDraftTicketPrice(@RequestBody String payload) {
        var ticket = priceDraftService.GetBusDraftTicketPrice(serializerService.DeserializeToBusTicket(payload));
        return serializerService.SerializeFromDraftTicket(ticket);
    }
}
