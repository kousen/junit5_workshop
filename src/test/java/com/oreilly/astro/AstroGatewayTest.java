package com.oreilly.astro;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class AstroGatewayTest {
    private final AstroGateway gateway = new AstroGateway();

    private HttpResponse<Void> makeHeadRequest() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://api.open-notify.org"))
                .method("HEAD", HttpRequest.BodyPublishers.noBody()) // .HEAD() in Java 18+
                .build();
        return client.send(req, HttpResponse.BodyHandlers.discarding());
    }

    @BeforeEach
    void setUp() throws Exception {
        // Check response to an HTTP HEAD request
        Assumptions.assumeTrue(
                makeHeadRequest().statusCode() == HttpURLConnection.HTTP_OK);
    }

    @Test
    void astroResponse() {
        AstroResponse response = gateway.getAstroResponse();
        System.out.println(response);
        assertNotNull(response);
        assertAll(
                () -> assertEquals("success", response.getMessage()),
                () -> assertTrue(response.getNumber() >= 0),
                () -> assertEquals(response.getNumber(), response.getPeople().size())
        );
    }
}