import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class test extends WebSocketClient {

    static test webSocket = null;

    public test(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public static void main(String[] args) throws URISyntaxException {
        String uri = "ws://127.0.0.1:13528";
        webSocket = new test(new URI(uri), new Draft_17());
        //建立连接
        webSocket.connect();

    }


    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        //获取打印机列表
        String getPrinterListCmd = "{\"requestID\":\"12345678901234567890\",\"verson\":\"1.0\",\"cmd\":\"getPrinters\"}";
        webSocket.send(getPrinterListCmd);

        //发送打印任务
        String printCmd = "打印任务报文，内容过长此处不粘贴";
        webSocket.send(printCmd);
    }

    //WebSocket回调函数
    @Override
    public void onMessage(String message) {
        //TODO 对打印服务返回的数据进行处理
        System.out.println(message);
    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }


}
