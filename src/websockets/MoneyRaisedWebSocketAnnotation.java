package websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.server.ServerEndpoint;

import model.Project;
import model.RMIBean;

import javax.websocket.OnOpen;
import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.Session;

@ServerEndpoint(value = "/websockets")
public class MoneyRaisedWebSocketAnnotation {
    private Session session;
    private RMIBean rmiBean;
    private static final Set<MoneyRaisedWebSocketAnnotation> users = new CopyOnWriteArraySet<>();

    public MoneyRaisedWebSocketAnnotation() {
    	
    }
    
    @OnOpen
    public void start(Session session) {
        this.session = session;
        this.rmiBean = new RMIBean();
        String message = "Current Projects com Websockets!";
        users.add(this);
        System.out.println(message);
        //sendMessageString(message);
        sendCurrentProjects(this.rmiBean.getCurrentProjects());
        
    }

    @OnClose
    public void end() {
    	// clean up once the WebSocket connection is closed
    	users.remove(this);
    }

    @OnMessage
    public void receiveMessage(String message) {
		// one should never trust the client, and sensitive HTML
        // characters should be replaced with &lt; &gt; &quot; &amp;
    	//String reversedMessage = new StringBuffer(message).reverse().toString();
    	//sendMessage(value);
    	if(message.charAt(0) == 'u')
    	{
    		sendCurrentProjects(this.rmiBean.getCurrentProjects());
    	}
    }
    
    @OnError
    public void handleError(Throwable t) {
    	t.printStackTrace();
    }
    
    private void sendMessageString(String text) {
    	// uses *this* object's session to call sendText()
    	try {
			for (MoneyRaisedWebSocketAnnotation webSocketAnnotation : users) {
				webSocketAnnotation.session.getBasicRemote().sendText(text);	
			}
			
		} catch (IOException e) {
			// clean up once the WebSocket connection is closed
			try {
				this.session.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
    }
    
    private void sendMessage(int value) {
    	// uses *this* object's session to call sendText()
    	try {
			for (MoneyRaisedWebSocketAnnotation webSocketAnnotation : users) {
				webSocketAnnotation.session.getBasicRemote().sendText(Integer.toString(value));	
			}
			
		} catch (IOException e) {
			// clean up once the WebSocket connection is closed
			try {
				this.session.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
    }
    
    private void sendCurrentProjects(ArrayList<Project> list)
    {
    	String aux;
    	// uses *this* object's session to call sendText()
    	try {
			for (MoneyRaisedWebSocketAnnotation webSocketAnnotation : users) {
				for(Project proj: list)
				{
					aux = proj.getProjectId()+" "+proj.getMoneyRaised()+" "+proj.getObjective();
					webSocketAnnotation.session.getBasicRemote().sendText(aux);
				}
			}
			
		} catch (IOException e) {
			// clean up once the WebSocket connection is closed
			try {
				this.session.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
    }
}
