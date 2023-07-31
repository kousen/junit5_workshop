package com.oreilly.astro;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AstroGateway {
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();
    private final HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://api.open-notify.org/astros.json"))
            .build();

    public AstroResponse getAstroResponse() {
        try {
            return gson.fromJson(client.send(request, HttpResponse.BodyHandlers.ofString()).body(), AstroResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
