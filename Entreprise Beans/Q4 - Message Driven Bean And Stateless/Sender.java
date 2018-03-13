import javax.naming.*;
import javax.jms.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Sender extends HttpServlet {
  public void doPost(HttpServletRequest request,  HttpServletResponse response)   throws ServletException, IOException {
  	 String fname=request.getParameter("firstname");   	 
  	 String message=request.getParameter("message"); 
    PrintWriter out = response.getWriter();    
    
		try{
		//Init Context
		InitialContext messaging = new InitialContext();
		
		//Find Connection Factory
		QueueConnectionFactory connectionFactory = (QueueConnectionFactory) messaging.lookup("jms/CFMessages");
		
		//Find Queue
		Queue queue = (Queue) messaging.lookup("jms/QMessages");
		
		//Create Queue Connection
        QueueConnection connection = connectionFactory.createQueueConnection();
        
        //Config Queue
        QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
        
        //Start Connection
		connection.start();
		
		//Create Queue Sender
        QueueSender sender = session.createSender(queue);
        TextMessage msg = session.createTextMessage();
        			msg.setStringProperty("receiver", "q7receiver");

        msg.setText(message);
        
         sender.send(msg);
	} catch(Exception e){
		System.out.println(e.getMessage());
	}
        
    
  }
}
