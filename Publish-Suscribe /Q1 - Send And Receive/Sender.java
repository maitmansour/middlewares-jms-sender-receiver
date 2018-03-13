import javax.naming.*;
import javax.jms.*;

public class Sender {
    public static void main(String[] args) {
	
		try{
			
		TopicConnectionFactory connectionFactory = (TopicConnectionFactory) messaging.lookup("...");
		Topic topic = (Topic) messaging.lookup("...");
		TopicConnection connection = connectionFactory.createTopicConnection();
		TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		connection.start();



		TopicPublisher publisher =session.createPublisher(topic);






        TextMessage msg = session.createTextMessage();
        msg.setText("Hello");


        for(int i=1; i<5;i++){
			publisher.publish(msg);

        }
	} catch(Exception e){
		System.out.println(e.getMessage());
	}
        
    }
}
