import javax.naming.*;
import javax.jms.*;

public class Receiver {
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

		//Create Receiver
		QueueReceiver receiver =session.createReceiver(queue);
		
		//Receive Message
		TextMessage msg = (TextMessage) receiver.receive();
		
		//Print Message
		System.out.println("MESSAGE : "+msg.getText());

	} catch (Exception e) {
			System.out.println(e.getMessage());
	}
        
    }
}
