package com.busfare.helper;

import com.busfare.entities.BusTicket;
import com.busfare.entities.BusTicketEntry;
import com.busfare.entities.DraftTicket;
import com.busfare.entities.DraftTicketEntry;
import com.busfare.entities.helper.enums.BusPassengerType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DraftTicketTestBase {
    /* Commonly used values for testing */
    protected final float baseFare = 10f;
    protected final BigDecimal VAT = new BigDecimal("0.21");
    protected final float childDiscountModifier = 0.5f;
    protected final float passengerFareAdult = 12.1f;
    protected final float passengerFareChild = 6.05f;
    protected final int passengerTypeAdult = BusPassengerType.ADULT.ordinal();
    protected final int passengerTypeChild = BusPassengerType.CHILD.ordinal();

    protected BusTicket GetTestBusTicket() {
        List<BusTicketEntry> busTicketEntries = new ArrayList<>();
        busTicketEntries.add(new BusTicketEntry(passengerTypeAdult, 2));
        busTicketEntries.add(new BusTicketEntry(passengerTypeChild, 1));
        return new BusTicket(busTicketEntries);
    }

    protected DraftTicket GetTestDraftTicket() {
        List<DraftTicketEntry> draftList = new ArrayList<>();
        draftList.add(new DraftTicketEntry(12.1f, 7.26f, 0f));
        draftList.add(new DraftTicketEntry(6.05f, 3.63f, childDiscountModifier));
        return new DraftTicket(baseFare, VAT, 29.04f, draftList);
    }
}
