package com.busfare.entities;

import lombok.Getter;

public class DraftTicketEntry {

    @Getter
    private float passengerFare;

    @Getter
    private float luggageFare;

    @Getter
    private float discountModifier;

    public DraftTicketEntry(float passengerFare, float luggageFare, float discountModifier) {
        this.passengerFare = passengerFare;
        this.luggageFare = luggageFare;
        this.discountModifier = discountModifier;
    }
}
