package ru.clevertec.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
    private Lock lock=new ReentrantLock();

    public Response processRequest(Request request) {
        lock.lock();
        try {
            Thread.sleep((int)(Math.random()*2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        int responseValue=100 - request.getValue();
        return new Response(responseValue);
    }
}
