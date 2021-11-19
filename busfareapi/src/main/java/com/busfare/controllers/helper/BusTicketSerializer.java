package com.busfare.controllers.helper;

import com.busfare.entities.BusTicket;
import com.busfare.entities.DraftTicket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class BusTicketSerializer implements IBusTicketSerializer {

    /*
    Deserializes string(json) to a BusTicket object
    */
    @Override
    public BusTicket DeserializeToBusTicket(String payload) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(payload, BusTicket.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    Serializes a DraftTicket object to a string(json)
    */
    @Override
    public String SerializeFromDraftTicket(DraftTicket draftTicket) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(draftTicket);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
