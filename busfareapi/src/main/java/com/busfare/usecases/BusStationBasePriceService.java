package com.busfare.usecases;

import com.busfare.usecases.interfaces.IBusStationService;
import org.springframework.stereotype.Service;

/*
Dummy service to return bus fare value for Riga-Vilnius.
*/

@Service
public class BusStationBasePriceService implements IBusStationService {

    /*
    In a real example would ask for (DepartureStation, DestinationStation, Route, etc.).
    Then would make an API call or check Database.
    */
    public float GetBasePriceForBusStations() {
        return 10f;
    }
}
