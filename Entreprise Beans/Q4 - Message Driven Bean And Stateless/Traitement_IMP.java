import javax.ejb.Stateless;

@Stateless
public class Traitement_IMP implements Traitement_INT {
		public String traiter(){
			return "Traitement en cours "; 
		}
}
