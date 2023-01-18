package com.abramchik.TaskOnePatterns.Structural.Adapter;

public class Adapter {
    public static void main(String[] args) {
        Radio radio = new Radio();
        AmericanSocket americanSocket = new SimpleAmericanSocket();
        EvroSocket evroSocket = new SocketAdapter(americanSocket);
        radio.listenMusic(evroSocket);
    }
}

interface EvroSocket {
    void getPower();
}

interface AmericanSocket {
    void getPower();
}

class SimpleAmericanSocket implements AmericanSocket{
    @Override
    public void getPower() {
        System.out.println("110v");
    }
}

class Radio{
    public void listenMusic(EvroSocket evroSocket){
        evroSocket.getPower();
    }
}

class SocketAdapter implements EvroSocket{

    public SocketAdapter(AmericanSocket americanSocket){
        americanSocket.getPower();
    }
    @Override
    public void getPower() {

    }
}
