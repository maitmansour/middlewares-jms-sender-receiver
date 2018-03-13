import javax.naming.*;
import javax.jms.*;

public class Sender {
    public static void main(String[] args) {
	
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


		//Create Sender
        QueueSender sender = session.createSender(queue);
        TextMessage msg = session.createTextMessage();
        
        //Init Message Text
        msg.setText("This Is A Message");
        
        //Send Message
        sender.send(msg);
        
        
	} catch(Exception e){
		System.out.println(e.getMessage());
	}
        
    }
}
