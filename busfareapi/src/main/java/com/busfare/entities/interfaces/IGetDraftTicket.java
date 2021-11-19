package com.busfare.entities.interfaces;

import com.busfare.entities.BusTicket;
import com.busfare.entities.DraftTicket;

import java.math.BigDecimal;

public interface IGetDraftTicket {

    DraftTicket GetBusDraftTicketWithFares(BusTicket busTicket, float baseFare, BigDecimal VAT);
}
