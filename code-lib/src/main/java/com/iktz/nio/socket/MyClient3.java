package com.iktz.nio.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClient3 {

	private final static Logger logger = Logger.getLogger(MyClient3.class.getName());

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 100; i++) {
			SocketChannel socketChannel = null;
			try {
				socketChannel = SocketChannel.open();
				SocketAddress socketAddress = new InetSocketAddress("localhost", 10000);
				socketChannel.connect(socketAddress);

				// MyRequestObject myRequestObject = new
				// MyRequestObject("request_" + idx, "request_" + idx);

				String msg = "msg01";
				logger.log(Level.INFO, msg);
				sendData(socketChannel, msg);

				String myResponseObject = receiveData(socketChannel);
				logger.log(Level.INFO, myResponseObject.toString());
			} catch (Exception ex) {
				logger.log(Level.SEVERE, null, ex);
			} finally {
				try {
					socketChannel.close();
				} catch (Exception ex) {
				}
			}
		}
	}

	private static void sendData(SocketChannel socketChannel, String msg) throws IOException {
		byte[] bytes = msg.getBytes();
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		socketChannel.write(buffer);
		socketChannel.socket().shutdownOutput();
	}

	private static String receiveData(SocketChannel socketChannel) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] bytes;
		try {
			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

			int count = 0;
			while ((count = socketChannel.read(buffer)) >= 0) {
				buffer.flip();
				bytes = new byte[count];
				buffer.get(bytes);
				baos.write(bytes);
				buffer.clear();
			}
			bytes = baos.toByteArray();
			// Object obj = SerializableUtil.toObject(bytes);

			// myResponseObject = (String) obj;
			socketChannel.socket().shutdownInput();
		} finally {
			try {
				baos.close();
			} catch (Exception ex) {
			}
		}
		return new String(bytes);
	}
}
