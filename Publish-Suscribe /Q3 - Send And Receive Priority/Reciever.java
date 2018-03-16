import javax.jms.*;
import javax.naming.*;

public class Reciever {
    public static void main(String[] args) {

        try {

        InitialContext messaging = new InitialContext();
		TopicConnectionFactory connectionFactory = (TopicConnectionFactory) messaging.lookup("jms/CFMessages");
		Topic topic = (Topic) messaging.lookup("jms/TMessages");
		TopicConnection connection = connectionFactory.createTopicConnection();
		TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		connection.start();


		TopicSubscriber subscriber = session.createSubscriber(topic);
		
        for(int i=0; i<5; i++){
        Message msg = subscriber.receive();
            System.out.println("msgt="+msg);
            Thread.sleep(1000);

		}

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
