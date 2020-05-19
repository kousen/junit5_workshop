package com.oreilly.mockito;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private final List<Subscriber> subscribers = new ArrayList<>();

    public void send(String message) {
        for(Subscriber subscriber : subscribers) {
            try {
                subscriber.onNext(message);
            } catch (Exception ignored) {
                // ignored, yikes
            }
        }
    }

    public void subscribe(Subscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }
}
