package ru.clevertec.concurrency;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        List<Request> requestList = List.of(
                new Request(10),
                new Request(20),
                new Request(30),
                new Request(40),
                new Request(50)
        );
        Client client = new Client(server, requestList, 3);
        client.sendRequests();
        client.getResponseList().stream().
                map(Response::getValue).
                forEach(System.out::println);

    }
}
