import java.util.UUID;
/**
 * 
 * @author Lukas Hofmann, Patrick Waltermann
 * @version 0.0.2
 *
 */
public class ServerVerwaltung {
	private GameManager[] spielerListe = new GameManager[3];
	private int i = 0;
	private int k = 0; 
	public void sAttack(UUID id, int attackMode){
		
		//überprüfen ob möglich
		for(int i = 0; i >= spielerListe.length; i++){
			GameManager local = spielerListe[i];
			int y = sGetNumberID(id);
			local.cAttackg(y);
			//hit usw. muss man noch einfügen
			
		}
	}
	/**
	 * Initiert das Spiel
	 */
	public void sStartGame(){
		
	}
	/**
	 * 
	 * @param g Der Client der die Methode aufruft
	 * @return
	 */
	public boolean sLogin(GameManager g){
		if(i > 2) {return false;}
		spielerListe [i] = g;
		System.out.println("Yeah!");
		i++;
		if (k == 0){
		g.cSetBoss(true, k);
		k++;
		}else{
			g.cSetBoss(false, k);
			k++;
			
		}
		return true;
	}
	/**
	 * Überprüft die UUID mit der der Clients und liefert die interne ID zurück
	 * @param id UUID 
	 * @return Interne ID
	 */
	public int sGetNumberID(UUID id){
		int x = 5;//absichtlich falscher Int 
		for(int i = 0; i >= spielerListe.length; i++){
			GameManager local = spielerListe[i];
			if(local.cGetUUID() == id){
				return local.cGetNumberID();
			}
			else{
				
			}
			
		}
		return x; 
	}
	/**
	 * Methode die vom Client aufgerufen wird  um den austritt aus der Session zu signalisieren
	 * @param gameManager Client
	 */
	public void sLogout(GameManager gameManager) {
		
		
	}
	/**
	 * Berechnet den Jump der Spieler
	 */
	public void sJump(){
		//prüft ob möglich
	}
	/**
	 * Bewegt die Spieler
	 */
	public void sMove() {
		//prüft ob möglich
		
	}
	/**
	 * generelle Update Methode
	 * @param c Reduziertes Spielerobjekt
	 */
	public void sUpdate(ReducedPlayer c){
		
	}
}
