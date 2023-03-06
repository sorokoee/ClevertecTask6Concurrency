package ru.clevertec.concurrency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientIT {
    private Client client;
    private Server server;

    @BeforeEach
    void setUp() {
        List<Request> requestList =
                List.of(new Request(10), new Request(20));
        server = new Server();
        client = new Client(server, requestList, 2);
    }

    @Test
    void checkValuesOfResponseList() {
        client.sendRequests();
        assertAll(
                () -> assertThat(client.getResponseList().get(0).getValue()).isEqualTo(90),
                () -> assertThat(client.getResponseList().get(1).getValue()).isEqualTo(80)
        );
    }

    @Test
    void checkSizeOfResponseList() {
        List<Response> responseList = new ArrayList<>();
        client.sendRequests();
        List<Response> actulList = client.getResponseList();
        assertThat(actulList).hasSize(2);
    }
}