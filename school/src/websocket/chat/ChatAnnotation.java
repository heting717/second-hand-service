package websocket.chat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.itcast.util.HTMLFilter;



public class ChatAnnotation {

    private static final Log log = LogFactory.getLog(ChatAnnotation.class);  
    
    private static final String GUEST_PREFIX = "Guest";  
    private static final AtomicInteger connectionIds = new AtomicInteger(0);  
    private static final Map<String,Object> connections = new HashMap<String,Object>();  
  
    private final String nickname;  
    private Session session;  
  
    public ChatAnnotation() {  
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();  
    }  
  
  
    @OnOpen  
    public void start(Session session) {  
        this.session = session;  
        connections.put(nickname, this);   
        String message = String.format("* %s %s", nickname, "has joined.");  
        broadcast(message);  
    }  
  
  
    @OnClose  
    public void end() {  
        connections.remove(this);  
        String message = String.format("* %s %s",  
                nickname, "has disconnected.");  
        broadcast(message);  
    }  
  
  
    /** 
     * ��Ϣ���ʹ������� 
     * @param message 
     */  
    @OnMessage  
    public void incoming(String message) {  
        // Never trust the client  
        String filteredMessage = String.format("%s: %s",  
                nickname, HTMLFilter.filter(message.toString()));  
        broadcast(filteredMessage);  
    }  
  
    @OnError  
    public void onError(Throwable t) throws Throwable {  
        log.error("Chat Error: " + t.toString(), t);  
    }  
  
    /** 
     * ��Ϣ���ͷ��� 
     * @param msg 
     */  
    private static void broadcast(String msg) {  
        if(msg.indexOf("Guest0")!=-1){  
            sendUser(msg);  
        } else{  
            sendAll(msg);  
        }  
    }   
      
    /** 
     * �������û����� 
     * @param msg 
     */  
    public static void sendAll(String msg){  
        for (String key : connections.keySet()) {  
            ChatAnnotation client = null ;  
            try {  
                client = (ChatAnnotation) connections.get(key);  
                synchronized (client) {  
                    client.session.getBasicRemote().sendText(msg);  
                }  
            } catch (IOException e) {   
                log.debug("Chat Error: Failed to send message to client", e);  
                connections.remove(client);  
                try {  
                    client.session.close();  
                } catch (IOException e1) {  
                    // Ignore  
                }  
                String message = String.format("* %s %s",  
                        client.nickname, "has been disconnected.");  
                broadcast(message);  
            }  
        }  
    }  
      
    /** 
     * ��ָ���û�������Ϣ  
     * @param msg 
     */  
    public static void sendUser(String msg){  
        ChatAnnotation c = (ChatAnnotation)connections.get("Guest0");  
        try {  
            c.session.getBasicRemote().sendText(msg);  
        } catch (IOException e) {  
            log.debug("Chat Error: Failed to send message to client", e);  
            connections.remove(c);  
            try {  
                c.session.close();  
            } catch (IOException e1) {  
                // Ignore  
            }  
            String message = String.format("* %s %s",  
                    c.nickname, "has been disconnected.");  
            broadcast(message);    
        }   
    } 
}
