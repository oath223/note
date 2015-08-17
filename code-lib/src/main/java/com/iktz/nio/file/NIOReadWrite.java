package com.iktz.nio.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * 测试 使用nio api读取和写入文件
 * 
 * @author John
 *
 */
public class NIOReadWrite {

	// 读取
	@Test
	public void testRead() throws Exception {
		try {
			// 1、获取通道。我们从 FileInputStream 获取通道：
			FileInputStream fin = new FileInputStream("test.txt");
			FileChannel fc = fin.getChannel();
			// 2、创建缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			int count = 0;
			StringBuilder sb = new StringBuilder();
			// 3、循环读取，将数据从通道读到缓冲区中
			int n = 0;
			while (true) {
				buffer.clear();
				n++;
				System.out.println(" num >> "+n);
				
				count = fc.read(buffer);
				System.out.println(" count >> "+count);
				//String line = new String(buffer.array(),0,count);
				//System.out.println(" line >> "+line);
				// char r2 = (char)r;
				// System.out.println(r);
				if (count == -1) {
					break;
				} else {
					sb.append(new String(buffer.array(),0,count));
				}
				buffer.flip();
			}
			fin.close();
			
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testWrite ()throws Exception{
		FileOutputStream fout = new FileOutputStream( "writesomebytes.txt" );
		FileChannel fc = fout.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate( 1024 );
	    buffer.put( "hello world ".getBytes());
		/*for (int i=0; i<message.length; ++i) {
		     buffer.put( message[i] );
		}*/
		buffer.flip();
		fc.write( buffer );
		fout.close();
	}
}
