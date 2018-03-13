import javax.naming.*;
import javax.jms.*;
import javax.ejb.*;
import javax.naming.InitialContext;


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
				InitialContext initcont = new InitialContext();
			   Traitement_INT traitement= (Traitement_INT) initcont.lookup("Traitement_INT");
			   System.out.println(traitement.traiter());
            } else System.out.println("Message pas de type texte");
       
       
        } catch (Exception e) {
            e.printStackTrace();
            // mdc.setRollbackOnly();
        }
    }
}
