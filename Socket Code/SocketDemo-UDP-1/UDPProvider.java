import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author zhinushannan
 * @date 2021/3/8
 * @description
 */
public class UDPProvider {

    public static void main(String[] args) throws IOException {

        System.out.println("UDP Provider Started...");

        // 作为接收者，指定一个端口用于数据接收
        DatagramSocket ds = new DatagramSocket(20000);


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
        System.out.println("UDP Provider receive from ip:" + ip + "\tport:" + port + "\tdata:" + data);

        // 构建一份回送数据
        String responseData = "Receive data with len:" + dataLength;
        byte[]responseDataBytes = responseData.getBytes();
        // 直接根据发送者构建一份回送信息
        DatagramPacket responsePacket = new DatagramPacket(responseDataBytes,
                responseDataBytes.length,
                receivePack.getAddress(),
                receivePack.getPort());

        ds.send(responsePacket);

        // 完成
        System.out.println("UDP Provider Finished...");
        ds.close();
    }

}
