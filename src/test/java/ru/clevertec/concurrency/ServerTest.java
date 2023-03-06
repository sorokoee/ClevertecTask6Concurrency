package ru.clevertec.concurrency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

class ServerTest {
    private Server server;

    @BeforeEach
    void setUp() {
        server = new Server();
    }

    @ParameterizedTest
    @MethodSource("getResponseRequestArgumets")
    void checkRequestResponseAccordance(int requestValue, int responseValue) {
        Response actualResponse = server.processRequest(new Request(requestValue));
        assertThat(actualResponse.getValue()).isEqualTo(responseValue);
    }

    static Stream<Arguments> getResponseRequestArgumets() {
        return Stream.of(
                Arguments.of(10, 90),
                Arguments.of(20, 80),
                Arguments.of(30, 70),
                Arguments.of(40, 60)
        );
    }

}