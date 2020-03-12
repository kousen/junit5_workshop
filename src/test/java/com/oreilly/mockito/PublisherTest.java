package com.oreilly.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublisherTest {

    private final Publisher publisher = new Publisher();
    private final Subscriber sub1 = mock(Subscriber.class);
    private final Subscriber sub2 = mock(Subscriber.class);

    @BeforeEach
    public void init() {
        publisher.subscribe(sub1);
        publisher.subscribe(sub2);
    }

    @Test
    void testSend() {
        publisher.send("Hello");

        verify(sub1).onNext("Hello");
        verify(sub2).onNext("Hello");
    }

    @Test
    void misbehavingSubscriber() {
        // sub1 throws an exception every time
        doThrow(RuntimeException.class).when(sub1).onNext(anyString());
        publisher.send("message 1");
        publisher.send("message 2");

        // sub2 still receives the messages
        verify(sub2, times(2))
                .onNext(argThat(s -> s.matches("message \\d")));
    }
}