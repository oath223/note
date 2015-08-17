package com.iktz.nio.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOClient {

	
	
	//ip和端口的封装
	private final static InetSocketAddress SERVER_ADDRESS = new InetSocketAddress("192.168.1.105",7080);
	
	//客户端驱动
	public static void main(String[] args) throws Exception {
		
		int flag = 1;
		int blockSize = 4096;
		ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
		ByteBuffer receiveBuffer = ByteBuffer.allocate(blockSize);
		
		//1、socketChannel
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);

		//打开选择器
		//2、selector
		Selector selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		socketChannel.connect(SERVER_ADDRESS);
		
		Set<SelectionKey> selectionKeys;
		Iterator<SelectionKey> it;
		SelectionKey selectionKey;
		SocketChannel client;
		String receiveText;
		String sendText;
		int count = 0;
		
		while(true){
			//3、
			selectionKeys = selector.selectedKeys();
			it = selectionKeys.iterator();
			
			while(it.hasNext()){
				selectionKey = it.next();
			
				if(selectionKey.isConnectable()){
					System.out.println("client connet");	
					client = (SocketChannel) selectionKey.channel();
					if(client.isConnectionPending()){
						client.finishConnect();
						System.out.println("客户端完成连接操作");	
						sendBuffer.clear();
						sendBuffer.put("hello server".getBytes());///****************************
						sendBuffer.flip();
						count = client.write(sendBuffer);
					}
					client.register(selector, SelectionKey.OP_READ);
				}
				if(selectionKey.isReadable()){
					//
					client = (SocketChannel) selectionKey.channel();
					receiveBuffer.clear();
					client.read(receiveBuffer);
					if(count>0){
						receiveText = new String(receiveBuffer.array(),0,count);
						System.out.println("客户端接收到服务端的数据："+receiveText);
						client.register(selector, SelectionKey.OP_WRITE);
					}
				}
				if(selectionKey.isWritable()){
					sendBuffer.clear();
					client = (SocketChannel) selectionKey.channel();
					sendText = "Msg send to server > " + flag++;
					sendBuffer.put(sendText.getBytes());
					sendBuffer.flip();
					client.write(sendBuffer);
					System.out.println("客户端发送数据给服务端："+sendText);
					client.register(selector, SelectionKey.OP_READ);
				}
			}
			selectionKeys.clear();
		}
	}
}