package com.busfare;

import com.busfare.helper.DraftTicketTestConstants;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BusTicketPriceDraftAcceptanceTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Description("Executes the API with request body then expects status: 200, response body: Json format and response body equals the expected result")
    void SendBusDraftTicketPriceAcceptanceTest() throws Exception {
        mockMvc.perform(post("/ticket")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(DraftTicketTestConstants.PAYLOAD))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(equalTo(DraftTicketTestConstants.RESULT)));
    }

}