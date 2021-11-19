package com.busfare.entities;

import com.busfare.helper.DraftTicketTestBase;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DraftTicketUnitTests extends DraftTicketTestBase {

    private DraftTicket draftTicket;

    /* Values used in tests */
    private final float luggageFareOneBag = 3.63f;
    private final float luggageFareFiveBags = 18.15f;
    private final float draftTotalPrice = 21.78f;

    @BeforeEach
    public void setUp() {
        draftTicket = new DraftTicket(baseFare, VAT);
    }

    @Test
    @Description("Test if CalculatePassengerFare() returns correct value for an Adult passenger")
    void TestCalculatePassengerFareForAdultShouldMatchExpected() {
        assertEquals(passengerFareAdult, GetCalculatedPassengerFareValue(passengerTypeAdult), "Adult passenger fare should match expected value");
    }

    @Test
    @Description("Test if CalculatePassengerFare() returns correct value for a Child passenger given base fare and VAT")
    void TestCalculatePassengerFareForChildShouldMatchExpected() {
        assertEquals(passengerFareChild, GetCalculatedPassengerFareValue(passengerTypeChild), "Child passenger fare should match expected value");
    }

    @Test
    @Description("Test when calculating passenger fare for an adult and child the values should not match")
    void TestCalculatePassengerFareShouldNotBeEqualForDifferentPassengerTypes() {
        var adultFare = draftTicket.CalculatePassengerFare(passengerTypeAdult);
        var childFare = draftTicket.CalculatePassengerFare(passengerTypeChild);

        assertNotEquals(adultFare, childFare, "Adult and child passenger fares should be different");
    }

    @Test
    @Description("Test if CalculateLuggageFare() returns correct values for luggage amounts: -1, 0, 1 and 5")
    void TestCalculateLuggageFaresShouldMatchExpected() {
        String expectedMessage = "Luggage fare should match expected value";
        assertEquals(0, GetCalculateLuggageFareValue(-1), expectedMessage);
        assertEquals(0, GetCalculateLuggageFareValue(0), expectedMessage);
        assertEquals(luggageFareOneBag, GetCalculateLuggageFareValue(1), expectedMessage);
        assertEquals(luggageFareFiveBags, GetCalculateLuggageFareValue(5), expectedMessage);
    }

    @Test
    @Description("Test if IsChildPassenger() returns true when given child passengerType")
    void TestIsChildPassengerShouldReturnTrue() {
        Assert.isTrue(draftTicket.IsChildPassenger(passengerTypeChild), "Method should return true for child passenger type");
    }

    @Test
    @Description("Test if IsChildPassenger() returns false when given adult passengerType")
    void TestIsChildPassengerShouldReturnFalse() {
        Assert.isTrue(!draftTicket.IsChildPassenger(passengerTypeAdult), "Method should return false for adult passenger type");
    }

    @Test
    @Description("Test if GetDiscountModifier() returns expected value when given adult passengerType")
    void TestGetDiscountModifierForAdultPassengerType() {
        assertEquals(0, draftTicket.GetDiscountModifier(passengerTypeAdult), "Method should return expected value for adult passenger type");
    }

    @Test
    @Description("Test if GetDiscountModifier() returns expected value when given child passengerType")
    void TestGetDiscountModifierForChildPassengerType() {
        assertEquals(childDiscountModifier, draftTicket.GetDiscountModifier(passengerTypeChild), "Method should return expected value for child passenger type");
    }

    @Test
    @Description("Test if CalculateLuggageFare() returns correct value when given a list with ticket prices")
    void TestCalculateDraftTotalPriceShouldReturnExpectedValue() {
        List<DraftTicketEntry> draftList = new ArrayList<>();
        draftList.add(new DraftTicketEntry(passengerFareAdult, luggageFareOneBag, 0));
        draftList.add(new DraftTicketEntry(passengerFareChild, 0, childDiscountModifier));
        assertEquals(draftTotalPrice, draftTicket.CalculateDraftTotalPrice(draftList), "Draft ticket total price should match expected value");
    }

    private float GetCalculatedPassengerFareValue(int passengerType) {
        return draftTicket.CalculatePassengerFare(passengerType);
    }

    private float GetCalculateLuggageFareValue(int luggageCount) {
        return draftTicket.CalculateLuggageFare(luggageCount);
    }
}