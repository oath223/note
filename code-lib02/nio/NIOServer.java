
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


public class NIOServer {

	public static void main(String[] args) {
		System.out.println("--start--");
		Selector selector = null;
		ServerSocketChannel serverSocketChannel = null;
		try {

			//1��ʹ��Selecoteor�ľ�̬����open��ȡһ��Selector����
			// Selector for incoming time requests
			selector = Selector.open();

			//2������һ��socket ͨ�����󣬲�����һ���˿�
			// Create a new server socket and set to non blocking mode
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			
			// Bind the server socket to the local host and port
			serverSocketChannel.socket().setReuseAddress(true);
			serverSocketChannel.socket().bind(new InetSocketAddress(10001));
			
			
			// Register accepts on the server socket with the selector. This
			// step tells the selector that the socket wants to be put on the
			// ready list when accept operations occur, so allowing multiplexed
			// non-blocking I/O to take place.
			//3����socketͨ������ע�ᵽselector�ϣ����ͨ������������Կͻ��˵� socketͨ��
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
	
			// Here's where everything happens. The select method will
			// return when any operations registered above have occurred, the
			// thread has been interrupted, etc.
			int scnt = 0;
			//
			while ((scnt = selector.select() )> 0) {
				
				System.out.println(" select ****** ----"+scnt+"-------------- ");
				//4����ȡע����� socketͨ�������������ǣ��ȴ��ͻ��˵�socket���������ݣ�
				// Someone is ready for I/O, get the ready keys
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				
				// Walk through the ready keys collection and process date requests.
				while (it.hasNext()) {
					SelectionKey readyKey = it.next();
					it.remove();
					
					// The key indexes into the selector so you
					// can retrieve the socket that's ready for I/O
					execute((ServerSocketChannel) readyKey.channel());
				}
			}
		} catch (ClosedChannelException ex) {
			//logger.log(Level.SEVERE, null, ex);
			System.out.println(ex);
		} catch (IOException ex) {
			//logger.log(Level.SEVERE, null, ex);
			System.out.println(ex);
		} finally {
			try {
				selector.close();
			} catch(Exception ex) {}
			try {
				serverSocketChannel.close();
			} catch(Exception ex) {}
		}
	}

	private static void execute(ServerSocketChannel serverSocketChannel) throws IOException {
		SocketChannel socketChannel = null;
		try {
			//5������
			socketChannel = serverSocketChannel.accept();
			
			//��������
			String myRequestObject = receiveData(socketChannel);
			System.out.println("������ˡ����յ����ݣ�"+myRequestObject);
			
			//logger.log(Level.INFO, myRequestObject.toString());
			
			//��������
			String retMsg="return msg from server";
			sendData(socketChannel, retMsg);
		
		} finally {
			try {
				socketChannel.close();
			} catch(Exception ex) {}
		}
	}
	
	private static String receiveData(SocketChannel socketChannel) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		byte[] bytes;
		try {
			int size = 0;
			while ((size = socketChannel.read(buffer)) >= 0) {
				buffer.flip();
				bytes = new byte[size];
				buffer.get(bytes);
				baos.write(bytes);
				buffer.clear();
			}
			bytes = baos.toByteArray();
		} finally {
			try {
				baos.close();
			} catch(Exception ex) {}
		}
		return new String(bytes);
	}

	private static void sendData(SocketChannel socketChannel, String myResponseObject) throws IOException {
		//byte[] bytes = SerializableUtil.toBytes(myResponseObject);
		byte[] bytes = myResponseObject.getBytes();
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		socketChannel.write(buffer);
	}
}