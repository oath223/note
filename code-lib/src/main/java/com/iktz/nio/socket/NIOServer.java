package com.iktz.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

	private int flag = 1;
	private int blockSize = 4096;
	private ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
	private ByteBuffer receiveBuffer = ByteBuffer.allocate(blockSize);
	
	private Selector selector;
	public NIOServer(int port) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		//1、设置是否阻塞
		serverSocketChannel.configureBlocking(false);
		ServerSocket serverSocket = serverSocketChannel.socket();
		//2、绑定ip和端口
		serverSocket.bind(new InetSocketAddress(port));
		//3、打开选择器
		selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("Server start at "+port);
		
	}
	
	//监听
	public void listen() throws Exception{
		
		while(true){
		
			//轮询选择器，或者事件列表
			
			selector.select();
			System.out.println("mark server 01 ");	
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> it = selectedKeys.iterator();
			System.out.println("mark server 02 ");	
			int count1 =0;
			while(it.hasNext()){
				System.out.println("mark server 03 "+count1++);	
				SelectionKey selectionKey = it.next();//事件
				it.remove();//取到事件以后，删除这个事件
				//业务逻辑
				handleKey(selectionKey);
			}
		}
	}
	public void handleKey(SelectionKey selectionKey) throws Exception{
		//判断是什么事件发生了
		ServerSocketChannel server = null;
		SocketChannel client = null;
		
		String reciveText ;
		String sendText;
		int count = 0;
		
		if(selectionKey.isAcceptable()){//是可接收的  服务端
			server = (ServerSocketChannel)selectionKey.channel();
			client = server.accept();
			client.configureBlocking(false);//
			//读数据
			client.register(selector, SelectionKey.OP_READ);//注册读事件
		}else if(selectionKey.isReadable()){//可用来读取，客户端
			client = (SocketChannel)selectionKey.channel();//客户端的channel
			count = client.read(receiveBuffer);
			
			if(count > 0){
				reciveText = new String(receiveBuffer.array(),0,count);
				System.out.println("服务端接收到客户端信息："+reciveText);
			}
			
			//注册写事件
			client.register(selector, SelectionKey.OP_WRITE);
			
		}else if(selectionKey.isWritable()){
			sendBuffer.clear();
			client = (SocketChannel) selectionKey.channel();
			//要发送的数据
			sendText = "msg send to client:"+flag ++;
			//数据放入缓冲区
			sendBuffer.put(sendText.getBytes());
			sendBuffer.flip();//写入缓冲区
			client.write(sendBuffer);//发送数据
			
			System.out.println("服务端发送数据给客户端："+sendText);
		}
		
	}
	
	//服务端驱动
	public static void main(String[] args) {
		try{
			int port = 7080;
			NIOServer server = new NIOServer(port);
			server.listen();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
