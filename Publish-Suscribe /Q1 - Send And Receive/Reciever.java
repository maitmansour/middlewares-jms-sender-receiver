import javax.naming.*;
import javax.jms.*;

public class Reciever {
    public static void main(String[] args) {

        try {

            InitialContext messaging = new InitialContext();
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) messaging.lookup("jms/CFMessages");
            Queue queue = (Queue) messaging.lookup("jms/QMessages");
            QueueConnection connection = connectionFactory.createQueueConnection();
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
            TextMessage msg;
            QueueReceiver receiver;

			            if (args.length > 0) {
							for(int i=0; i<args.length;i++){
								System.out.println("ok 2");
									receiver = session.createReceiver(queue, "destinataire = '" + args[i] + "'");
									msg = (TextMessage) receiver.receive();
									System.out.println("JE SUIS  " + args[i] + " J'ai reçu : " + msg.getText());
							}

				
						} else {
							receiver = session.createReceiver(queue);
							msg = (TextMessage) receiver.receive();
							System.out.println("JE SUIS PERSONNE J'ai reçu : " + msg.getText());
						}

			



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
