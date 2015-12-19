package websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.server.ServerEndpoint;

import model.Project;
import model.RMIBean;

import javax.websocket.OnOpen;
import javax.json.JsonObject;
import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.Session;

@ServerEndpoint(value = "/websockets", configurator = GetHttpSessionConfigurator.class)
public class WebSocketServerEndPoint {
    private Session session;
    private HttpSession httpSession;
    private RMIBean rmiBean;
    private static final Set<WebSocketServerEndPoint> users = new CopyOnWriteArraySet<>();

    public WebSocketServerEndPoint() {
    	
    }
    
    @OnOpen
    public void start(Session session, EndpointConfig config) {
        this.session = session;
        if(config.getUserProperties().get(HttpSession.class.getName()) != null)
        {
	    	this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
	        this.rmiBean = (RMIBean) httpSession.getAttribute("rmiBean");
        }
        else
        {
        	this.rmiBean = new RMIBean();
        }
        //this.rmiBean = new RMIBean();
        String message = "Current Projects com Websockets!";
        users.add(this);
        System.out.println(message);
        //sendMessageString(message);
        sendCurrentProjectsOnLoad(this.rmiBean.getCurrentProjects());
        
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
    	System.out.println("Recebi no websocket "+message);
    	if(message.charAt(0) == 'p')
    	{
    		sendCurrentProjectsOnPledge(this.rmiBean.getCurrentProjects(),Integer.parseInt(message.split(" ")[1]));
    	}
    	else if((message.split(" ")[0]).equals("notify"))
    	{
    		System.out.println(message);
    		sendNotification(message);
    	}
    }
    
    @OnError
    public void handleError(Throwable t) {
    	t.printStackTrace();
    }
    
    private void sendMessageString(String text) {
    	// uses *this* object's session to call sendText()
    	try {
			for (WebSocketServerEndPoint webSocketAnnotation : users) {
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
			for (WebSocketServerEndPoint webSocketAnnotation : users) {
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
    
    private void sendCurrentProjectsOnLoad(ArrayList<Project> list)
    {
    	String aux;
    	// uses *this* object's session to call sendText()
    	try {
			for (WebSocketServerEndPoint webSocketAnnotation : users) {
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
    
    private void sendCurrentProjectsOnPledge(ArrayList<Project> list, int projectId)
    {
    	String aux;
    	// uses *this* object's session to call sendText()
    	try {
			for (WebSocketServerEndPoint webSocketAnnotation : users) {
				for(Project proj: list)
				{
					if(projectId==proj.getProjectId())
					{
						aux = proj.getProjectId()+" "+proj.getMoneyRaised()+" "+proj.getObjective();
						webSocketAnnotation.session.getBasicRemote().sendText(aux);
					}
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

    private void sendNotification(String text)
    {
    	// uses *this* object's session to call sendText()
    	ArrayList<String> admins = rmiBean.getAdministrators(Integer.parseInt(text.split(" ")[1]));
    	System.out.println(admins);
    	try {
    		for (WebSocketServerEndPoint webSocketAnnotation : users) {
    			for(String user : admins)
    			{
    				if(webSocketAnnotation.httpSession != null && webSocketAnnotation.httpSession.getAttribute("email").equals(user))
    				{
    	    			System.out.println("vou mandar: "+ text + " para "+webSocketAnnotation);
    	    			webSocketAnnotation.session.getBasicRemote().sendText(text);
    				}
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
