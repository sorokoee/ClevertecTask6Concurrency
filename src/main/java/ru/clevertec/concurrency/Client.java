package ru.clevertec.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {
    private List<Request> requestList;
    private List<Response> responseList;
    private ExecutorService executorService;
    private Server server;

    public Client(Server server, List<Request> requestList, int threadPoolSize){
        this.server=server;
        this.requestList=requestList;
        executorService= Executors.newFixedThreadPool(threadPoolSize);
        responseList=new ArrayList<>();
    }

    public void sendRequests()  {
        for (Request request : requestList) {
            Future<Response> responseFuture = executorService.submit(
                    () -> server.processRequest(request));
            try {
                responseList.add(responseFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
    public List<Response> getResponseList() {
        return responseList;
    }

}
