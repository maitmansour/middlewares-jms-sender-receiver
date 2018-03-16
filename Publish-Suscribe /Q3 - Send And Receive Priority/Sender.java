import javax.naming.*;
import javax.jms.*;

public class Sender {
    public static void main(String[] args) {
	
		try{
			
        InitialContext messaging = new InitialContext();
		TopicConnectionFactory connectionFactory = (TopicConnectionFactory) messaging.lookup("jms/CFMessages");
		Topic topic = (Topic) messaging.lookup("jms/TMessages");
		TopicConnection connection = connectionFactory.createTopicConnection();
		TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		connection.start();



		TopicPublisher publisher =session.createPublisher(topic);






        TextMessage msg = session.createTextMessage();


        for(int i=0; i<5;i++){
			 msg.setText("Hello "+i);
			 System.out.println("Hello "+i);
			publisher.publish(msg);

        }
        			 msg.setText("CECI EST UN MSG PRIORITAIRE");
        			 publisher.setPriority(9);
			publisher.publish(msg);

	} catch(Exception e){
		System.out.println(e.getMessage());
	}
        
    }
}
