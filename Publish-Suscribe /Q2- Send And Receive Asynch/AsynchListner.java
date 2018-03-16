import javax.jms.*;
import javax.naming.*;

public class AsynchListner implements MessageListener
    {
        public void onMessage(Message msg)
        {

            TextMessage tm = (TextMessage) msg;
            try {
							            Thread.sleep(5000);

                System.out.println("Message reçu  : " + tm.getText());
            } catch(Throwable t) {
                t.printStackTrace();
            }
        }
    }
