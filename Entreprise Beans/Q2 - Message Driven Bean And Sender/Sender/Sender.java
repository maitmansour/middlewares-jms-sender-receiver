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
		
		//Create Queue Sender
        QueueSender sender = session.createSender(queue);
        TextMessage msg = session.createTextMessage();
        
		//Get Msg text From ARGS
        msg.setText(args[0]);
        
        //Get Receivers From ARGS
        for(int i=1; i<args.length;i++){
			msg.setStringProperty("receiver", args[i]);
         sender.send(msg);

        }
	} catch(Exception e){
		System.out.println(e.getMessage());
	}
        
    }
}
