package com.busfare.controllers.helper;

import com.busfare.entities.BusTicket;
import com.busfare.entities.DraftTicket;

public interface IBusTicketSerializer {

    BusTicket DeserializeToBusTicket(String payload);
    String SerializeFromDraftTicket(DraftTicket draftTicket);
}
