package com.busfare.usecases.interfaces;

import com.busfare.entities.BusTicket;
import com.busfare.entities.DraftTicket;

public interface IPriceDraftService {

    DraftTicket GetBusDraftTicketPrice(BusTicket busTicket);
}
