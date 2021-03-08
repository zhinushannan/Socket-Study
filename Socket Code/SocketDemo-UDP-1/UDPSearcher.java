import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author zhinushannan
 * @date 2021/3/8
 * @description
 */
public class UDPSearcher {

    public static void main(String[] args) throws IOException {

        System.out.println("UDP Searcher Started...");

        // 作为搜索方，无需指定端口，由系统直接分配
        DatagramSocket ds = new DatagramSocket();

        // 构建一份回送数据
        String responseData = "HelloWorld";
        byte[]responseDataBytes = responseData.getBytes();
        // 直接根据发送者构建一份回送信息
        DatagramPacket responsePacket = new DatagramPacket(responseDataBytes, responseDataBytes.length);
        // 发送给本机的20000端口
        responsePacket.setAddress(InetAddress.getLocalHost());
        responsePacket.setPort(20000);
        // 发送
        ds.send(responsePacket);

        // 构建接收实体
        final byte[] buf = new byte[512];
        DatagramPacket receivePack = new DatagramPacket(buf, buf.length);

        // 接收
        ds.receive(receivePack);

        // 打印接收到的信息与发送者的信息
        // 发送者的IP地址
        String ip = receivePack.getAddress().getHostAddress();
        int port = receivePack.getPort();
        int dataLength = receivePack.getLength();
        String data = new String(receivePack.getData(), 0, dataLength);
        System.out.println("UDP Searcher receive from ip:" + ip + "\tport:" + port + "\tdata:" + data);

        // 完成
        System.out.println("UDP Searcher Finished...");
        ds.close();
    }

}
