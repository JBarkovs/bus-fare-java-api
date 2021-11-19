package com.busfare.entities;

import com.busfare.entities.helper.enums.BusPassengerType;
import com.busfare.entities.interfaces.IGetDraftTicket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class DraftTicket implements IGetDraftTicket {

    private final float childDiscount = 0.5f;
    private final float luggagePriceModifier = 0.3f;

    @Getter
    private float baseFare;

    @Getter
    private BigDecimal VAT;

    @Getter
    private float totalPrice;

    @Getter
    private List<DraftTicketEntry> draftList;

    protected DraftTicket(float baseFare, BigDecimal VAT) {
        this.baseFare = baseFare;
        this.VAT = VAT;
        draftList = new ArrayList<>();
    }

    @Override
    public DraftTicket GetBusDraftTicketWithFares(BusTicket busTicket, float baseFare, BigDecimal VAT) {
        return new DraftTicket(baseFare, VAT).SetBusDraftTicketFares(busTicket);
    }

    private DraftTicket SetBusDraftTicketFares(@NonNull BusTicket busTicket) {
        SetBusDraftTicketFaresList(busTicket.getBusTicketEntries());
        totalPrice = CalculateDraftTotalPrice(draftList);
        return this;
    }

    private void SetBusDraftTicketFaresList(@NonNull List<BusTicketEntry> busTicketEntries) {
        for (BusTicketEntry ticketEntry : busTicketEntries) {
            draftList.add(new DraftTicketEntry(CalculatePassengerFare(ticketEntry.getPassengerType()),
                    CalculateLuggageFare(ticketEntry.getLuggageCount()),
                    GetDiscountModifier(ticketEntry.getPassengerType())));
        }
    }

    protected float CalculatePassengerFare(int passengerType) {
        var passengerFare = IsChildPassenger(passengerType) ? baseFare * childDiscount : baseFare;
        return ConvertToTwoDecimalFloat(CalculateValueWithVAT(passengerFare));
    }

    protected float CalculateLuggageFare(int luggageCount) {
        var luggageFare = (luggageCount < 1) ? 0f : CalculateValueWithVAT(baseFare * luggageCount * luggagePriceModifier);
        return ConvertToTwoDecimalFloat(luggageFare);
    }

    protected boolean IsChildPassenger(int passengerType) {
        return passengerType == BusPassengerType.CHILD.ordinal();
    }

    protected float GetDiscountModifier(int passengerType) {
        return IsChildPassenger(passengerType) ? childDiscount : 0;
    }

    protected float CalculateDraftTotalPrice(@NonNull List<DraftTicketEntry> draftList) {
        float totalPrice = 0;
        for (DraftTicketEntry ticketEntry : draftList) {
            totalPrice += ticketEntry.getPassengerFare() + ticketEntry.getLuggageFare();
        }
        return ConvertToTwoDecimalFloat(totalPrice);
    }

    private float CalculateValueWithVAT(float price) {
        return price *= 1 + VAT.floatValue();
    }

    private float ConvertToTwoDecimalFloat(float value) {
        return Float.parseFloat(String.format("%.2f", value));
    }
}
