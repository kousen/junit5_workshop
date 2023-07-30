package com.oreilly.astro;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class AstroGatewayTest {
    private final AstroGateway gateway = new AstroGateway();

    @BeforeEach
    void setUp() {
        // Check response to an HTTP HEAD request
        HttpResponse<Void> response = gateway.getResponseToHeadRequest();
        System.out.println(response);
        Assumptions.assumeTrue(
                response.statusCode() == HttpURLConnection.HTTP_OK
        );
    }

    @Test
    void astroResponse() {
        AstroResponse response = gateway.getAstroResponse();
        System.out.println(response);
        assertEquals("success", response.getMessage());
        assertTrue(response.getNumber() >= 0);
        assertEquals(response.getNumber(), response.getPeople().size());
    }
}