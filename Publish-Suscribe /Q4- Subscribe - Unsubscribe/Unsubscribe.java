import javax.naming.*;
import javax.jms.*;
                                                                           
public class Unsubscribe
{
    public static void main(String[] args) throws Exception
    {
		try{
				if(args.length>0){

		// get the initial context
		InitialContext ctx = new InitialContext();
																		
		// lookup the topic object
		Topic topic = (Topic) ctx.lookup("jms/TMessages");
																		
		// lookup the topic connection factory
		TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.
			lookup("jms/CFMessages");
																		
		// create a topic connection
		TopicConnection topicConn = connFactory.createTopicConnection();
		topicConn.setClientID(args[0]);
																		
		// create a topic session
		TopicSession topicSession = topicConn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
																		
		// unsubscribe the durable topic subscriber
		topicSession.unsubscribe(args[0]);
																		
		System.out.println("unsubscribed "+args[0]);
																		
		// close the topic connection
		topicConn.close();
		}else{
		System.out.println("USE : java Subscribe name");
		}
			} catch(Exception e){
		System.out.println(e.getMessage());
	}
    }
}
