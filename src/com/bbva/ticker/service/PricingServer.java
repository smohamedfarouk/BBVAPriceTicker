package com.bbva.ticker.service;

import com.bbva.ticker.DataSource.PriceSourceAdapter;
import com.bbva.ticker.DataSource.PriceSourceDataAdapterImpl;
import com.bbva.ticker.client.UUIDIdentifierFactory;
import com.bbva.ticker.model.Request;
import com.bbva.ticker.model.Response;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * Created by moham on 26/01/2016.
 */
public class PricingServer {
    private ServerSocket m_socket;
    private final Map<Integer, PricingService> m_clients = new ConcurrentHashMap<>();
    private final Map<String, Request> m_requests = new ConcurrentHashMap<>();
    private final Map<String, ClientConnection> m_clientConnections = new ConcurrentHashMap<>();
    private int m_clientId;
    final static int portNo = 9005;

    public static void main(String[] args) {
        final PricingServer server = new PricingServer();

        System.out.println("Starting lesson server Port  " + portNo);
        new Thread(new Runnable() {
            public void run() {
                try {
                    server.m_socket = new ServerSocket(portNo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                server.startServer();
            }
        }).start();
        new ShutDownHookImpl() {
            public void shutDownProcess() {
                System.out.println("Shutting down lesson server");
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.add();
    }

    private void startServer() {
     final   PriceSourceAdapter priceSourceAdapter = new PriceSourceDataAdapterImpl();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                m_clientId++;
                //SimplePricingService(Socket clientSocket, Map<Integer, SimplePricingService> m_clients)
                PricingService service = new PricingService() {

                    @Override
                    public void subscribePriceData(Request request, Response.Builder responseBuilder) {

                    }

                    @Override
                    public void unsubscribePriceData(Request request, Response.Builder responseBuilder) {

                    }
                };
                PriceSubscriptionEventExecutor priceSubscriptionEventExecutor = new PriceSubscriptionEventExecutor(
                        Executors.newFixedThreadPool(10), m_requests);

                PriceSubscriptionEventPublisher priceSubscriptionEventPublisher = new PriceSubscriptionEventPublisher(
                        Executors.newFixedThreadPool(10), m_requests, m_clientConnections);

                priceSubscriptionEventPublisher.addListener(priceSubscriptionEventExecutor);

                priceSubscriptionEventExecutor
                        .addListener(new GetPriceDataConnectionEventHandler(
                                Request.MessageType.subscribePriceData, service,priceSourceAdapter));

                priceSubscriptionEventPublisher.start();
            }
        }


        );
        t1.start();
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                m_clientId++;
                //SimplePricingService(Socket clientSocket, Map<Integer, SimplePricingService> m_clients)
                PricingService service = new PricingService() {

                    @Override
                    public void subscribePriceData(Request request, Response.Builder responseBuilder) {

                    }

                    @Override
                    public void unsubscribePriceData(Request request, Response.Builder responseBuilder) {

                    }
                };
                m_clients.put(m_clientId, service);

                ClientConnectionEventExecutor clientConnectionEventExecutor = new ClientConnectionEventExecutor(
                        Executors.newFixedThreadPool(10), m_requests, m_clientConnections);

                clientConnectionEventExecutor
                        .addListener(new GetPriceDataConnectionEventHandler( Request.MessageType.subscribePriceData, service,priceSourceAdapter));
                clientConnectionEventExecutor
                        .addListener(new GetPriceDataConnectionEventHandler(
                                Request.MessageType.priceDataHistory, service,priceSourceAdapter));
                clientConnectionEventExecutor
                        .addListener(new GetPriceDataConnectionEventHandler(
                                Request.MessageType.unsubscribePriceData, service,priceSourceAdapter));
                ClientConnectionEventPublisher clientConnectionEventPublisher = new ClientConnectionEventPublisher(
                        Executors.newFixedThreadPool(10));
                clientConnectionEventPublisher
                        .addListener(clientConnectionEventExecutor);
             /*   clientConnectionEventPublisher
                        .addListener(priceSubscriptionEventExecutor);*/
                ClientConnectionPublisher clientConnectionPublisher = new ClientConnectionPublisher(
                        new UUIDIdentifierFactory());
                clientConnectionPublisher
                        .addListener(clientConnectionEventPublisher);
                ClientSocketPublisher clientSocketPublisher = new ClientSocketPublisher(
                        new ServerSocketFactory(portNo + 1));
                clientSocketPublisher.addListener(clientConnectionPublisher);

                    clientSocketPublisher.start();


            }
        });
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}