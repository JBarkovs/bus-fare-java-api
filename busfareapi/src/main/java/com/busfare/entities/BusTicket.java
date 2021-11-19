package com.busfare.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class BusTicket {

    @Getter
    private List<BusTicketEntry> busTicketEntries;
}