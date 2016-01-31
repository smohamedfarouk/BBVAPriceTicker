package com.bbva.ticker.service;

import com.bbva.ticker.model.Request;
import com.bbva.ticker.model.Response;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class ClientConnection {
    private final String m_identifier;
    private final Socket m_socket;
    private final InputStream m_inputStream;
    private final OutputStream m_outputStream;

    public ClientConnection(String m_identifier, Socket m_socket,
                            InputStream m_inputStream, OutputStream m_outputStream) {
        super();
        this.m_identifier = m_identifier;
        this.m_socket = m_socket;
        this.m_inputStream = m_inputStream;
        this.m_outputStream = m_outputStream;
    }

    public String getIdentifier() {
        return m_identifier;
    }

    public Socket getSocket() {
        return m_socket;
    }

    public Request read() {
        try {
            return Request.parseDelimitedFrom(m_inputStream);

        } catch (IOException e) {
            close();
            throw new com.bbva.ticker.service.ServerException(
                    "Failed to read the request from connection: " + this, e);
        }
    }

    public void write(Response response) {
        try {
            synchronized (this) {
                response.writeDelimitedTo(m_outputStream);
            }
        } catch (IOException e) {
            close();
            throw new com.bbva.ticker.service.ServerException("Failed to write response to the connection: " + this, e);
        }
    }

    public void close() {
        try {
            m_socket.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        close(m_inputStream, m_outputStream);
    }

    private void close(Closeable... closeableResourses) {
        for (Closeable closeableResource : closeableResourses) {
            try {
                closeableResource.close();
            } catch (IOException e) {
                throw new com.bbva.ticker.service.ServerException("Failed to close: " + this, e);
            }
        }
    }

    public String toString() {
        return "ClientConnection[identifier=" + m_identifier + "m_socket"
                + m_socket + "]";
    }
}