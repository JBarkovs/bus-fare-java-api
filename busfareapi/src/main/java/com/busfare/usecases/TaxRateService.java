package com.busfare.usecases;

import com.busfare.usecases.interfaces.ITaxRateService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/*
Dummy service to return tax rate value for the day.
*/

@Service
public class TaxRateService implements ITaxRateService {

    /*
    In a real example would get CountryId, Date or something similar.
    Then would make an API call or check Database.
    */
    public BigDecimal GetTaxRateForTransaction() {
        return new BigDecimal("0.21");
    }
}
