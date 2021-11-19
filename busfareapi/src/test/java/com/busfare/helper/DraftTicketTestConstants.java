package com.busfare.helper;

public class DraftTicketTestConstants {
    /* Constants for testing */
    public static final String PAYLOAD = "{ \"busTicketEntries\": [ { \"passengerType\": 0, \"luggageCount\": 2 }, { \"passengerType\": 1, \"luggageCount\": 1 } ] }";
    public static final String RESULT = "{\"baseFare\":10.0,\"totalPrice\":29.04,\"draftList\":[{\"passengerFare\":12.1,\"luggageFare\":7.26,\"discountModifier\":0.0},{\"passengerFare\":6.05,\"luggageFare\":3.63,\"discountModifier\":0.5}],\"vat\":0.21}";

}
