package com.bbva.ticker.client;


import com.bbva.ticker.model.Request;
import com.bbva.ticker.model.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleSocketAdaptor implements SocketAdaptor {

	private final InputStream m_inputStream;
	private final OutputStream m_outputStream;

	public SimpleSocketAdaptor(Socket socket) throws Exception {
		try {
			m_inputStream = socket.getInputStream();
			m_outputStream = socket.getOutputStream();
		} catch (IOException e) {
			throw new Exception("Failed to initialize the socket Adaptor" + e);
		}

	}

	public void close() {
		try {
			m_outputStream.close();
			m_inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 

	public void write(Request request) {
		try {
			request.writeDelimitedTo(m_outputStream);
			System.out.println(this.getClass().getName() + "Client Sent Request " + request);
		} catch (IOException e) {
			throw new ClientException("Failed to write a request ", e);
		}
	}


	public Response read() {
		try {
			Response response = Response.parseDelimitedFrom(m_inputStream);
			//System.out.println(this.getClass().getName() +  "Client Received Response " + response);
			return response;
		} catch (IOException e) {
			throw new ClientException("Failed to read a response", e);
		}

	}

}
