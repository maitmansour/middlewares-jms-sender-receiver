package receiver;

import javax.naming.*;
import javax.jms.*;
import javax.ejb.*;

@MessageDriven(mappedName = "jms/QMessages", activationConfig =  { @ActivationConfigProperty(propertyName = "messageSelector",propertyValue = "receiver = 'q7receiver'")})
public class Receiver implements MessageListener {
    //@Resource
    private
    MessageDrivenContext    mdc;
    
    
    public void onMessage(Message msg) {
        TextMessage tmsg = null;
        try {
            if (msg instanceof TextMessage) {
                tmsg = (TextMessage) msg;
                System.out.println("Message reçu : " + tmsg.getText());
              
            } else System.out.println("Message pas de type texte");
       
       
        } catch (JMSException e) {
            e.printStackTrace();
            // mdc.setRollbackOnly();
        }
    }
}
