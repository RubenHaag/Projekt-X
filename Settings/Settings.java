import java.io.FileNotFoundException;
import java.io.IOException;


public class Settings{
  private String[][] ausgabe;
  private String pfad;
  private char tastaurLinks;
  private char tastaurRechts;
  private char tastaurJump;
  private char tastaurAttack1;
  private char tastaurAttack2;
  private char tastaurAttack3;
  private boolean mausBenutzen;
  private int aufloesungX;
  private int aufloesungY;
  private boolean sound;
  private boolean musik;
    
    /**
     * Dieser Konstruktor legt die benutzte Settingsdatei als Settings.txt fest.
     * @throws IOException Falls in der angegebenen Datei irgendwelche Fehler sind, oder sie nicht Existiert.
     */
  Settings()throws IOException, NumberFormatException{
    pfad = "Settings.txt";
    
    leseParams();
  }
    /**
     * Dieser Konstruktor legt die beutzte Settingsdatei auf den �bergegebenen Wert Fest
     * @param pfad Der Pfad der Settings Datei.
     * @throws IOException Falls in der angegebenen Datei irgendwelche Fehler sind, oder sie nicht Existiert.
     */
  Settings(String pfad)throws IOException{
    this.pfad = pfad;
    leseParams();
  }

    /**
     * Diese Funktion updatet alle Settings Variablen
     * @return True, wenn alle Variablen in in der Textdatei vorhanden Sind
     * @throws IOException Falls in der angegebenen Datei irgendwelche Fehler sind, oder sie nicht Existiert.
     */
  public void leseParams() throws IOException{
    ausgabe = DateienReadWrite.read(pfad);
    //For-each-Schleife geht alle Elemente in dem Array für i einmal durch
    for (String[] i: ausgabe) {
      switch (i[0]) {
        
        //für die String Werte
        case "tastaturLinks":
          tastaurLinks = i[1].charAt(0);
          break;
        case "tastaturRechts":
          tastaurRechts = i[1].charAt(0);
          break;
        case "tastaurJump": 
          tastaurJump = i[1].charAt(0);
          break;
        case "tastaurAttack1":
          tastaurAttack1 = i[1].charAt(0); 
          break;         
        case "tastaurAttack2":
          tastaurAttack2 = i[1].charAt(0);
          break;
        case "tastaurAttack3":
          tastaurAttack3 = i[1].charAt(0);
          break;
          //für die Integerwerte
        case "aufloesungX":
          aufloesungX = Integer.parseInt(i[1]);
          break;
        case "aufloesungY":
          aufloesungY = Integer.parseInt(i[1]);
          break;
          //für die Booleanwerte
        case "mausBenutzen":
          mausBenutzen = Boolean.parseBoolean(i[1]);
          break;
        case "sound":
          sound = Boolean.parseBoolean(i[1]);
          break;
        case "musik":
          musik = Boolean.parseBoolean(i[1]);
          break;
      }
    }
  }

    /**
     * Diese Methode schreibt alle Einstellungen in die Speicherdatei. Anders werden die Einstellungen nicht geschreiben.
     * @throws IOException Falls in der angegebenen Datei irgendwelche Fehler sind, oder sie nicht Existiert.
     */
  public void writeParams() throws IOException{
    for (String i[]: ausgabe) {
        System.out.println(i[0] + "\t" + i[1]);      
    } // end of for
    DateienReadWrite.write(pfad, ausgabe);
  }

    /**
     * Diese Methode lässt einen die für eine Linksbewegung zu benutzende Taste Bearbeiten
     * @param tastaurLinks Die Taste, die für eine Linksbewegung des Spielers gewünscht ist.
     * @return Gibt eine aktualisierte Version des Settings Objektes, in dem diese Methode aufgrufen wurde zurück.
     */
  public Settings setTastaurLinks(char tastaurLinks) {
    this.tastaurLinks = tastaurLinks;
    for(int i = 0; i<ausgabe.length; i++){
      if(ausgabe[i][0].equals("tastaurLinks")){
        ausgabe[i][1] = String.valueOf(tastaurLinks);
      }
    }
    return this;
  }

    /**
     * Diese Methode lässt einen die für eine Rechtsbewegung zu benutzende Taste Bearbeiten
     * @param tastaurRechts Die Taste, die für eine Linksbewegung des Spielers gewünscht ist.
     * @return Gibt eine aktualisierte Version des Settings Objektes, in dem diese Methode aufgrufen wurde zurück.     *
     */
  public Settings setTastaurRechts(char tastaurRechts) {
    this.tastaurRechts = tastaurRechts;
    for(int i = 0; i<ausgabe.length; i++){
      if(ausgabe[i][0].equals("tastaurRechts")){
        ausgabe[i][1] = String.valueOf(tastaurRechts);
      }
    }
    return this;
  }
    /**
     * Diese Methode lässt einen die für einen Sprung zu benutzende Taste Bearbeiten
     * @param tastaurJump Die Taste, die für einen Sprung des Spielers gewünscht ist.
     * @return Gibt eine aktualisierte Version des Settings Objektes, in dem diese Methode aufgrufen wurde zurück.     *
     */
  public Settings setTastaurJump(char tastaurJump) {
    this.tastaurJump = tastaurJump;
    for(int i = 0; i<ausgabe.length; i++){
      if(ausgabe[i][0].equals("tastaurJump")){
        ausgabe[i][1] = String.valueOf(tastaurJump);
      }
    }
    return this;
  }
    /**
     * Diese Methode lässt einen die für die erste Attacke zu benutzende Taste Bearbeiten
     * @param tastaurAttack1 Die Taste, die für die erste Attacke des Spielers gewünscht ist.
     * @return Gibt eine aktualisierte Version des Settings Objektes, in dem diese Methode aufgrufen wurde zurück.     *
     */
  public Settings setTastaurAttack1(char tastaurAttack1) {
    this.tastaurAttack1 = tastaurAttack1;
    for(int i = 0; i<ausgabe.length; i++){
      if(ausgabe[i][0].equals("tastaurAttack1")){
        ausgabe[i][1] = String.valueOf(tastaurAttack1);
      }
    }
    return this;
  }

    /**
     *
     * @param tastaurAttack2
     * @return
     */
  public Settings setTastaurAttack2(char tastaurAttack2) {
    this.tastaurAttack2 = tastaurAttack2;
    for(int i = 0; i<ausgabe.length; i++){
      if(ausgabe[i][0].equals("tastaurAttack2")){
        ausgabe[i][1] = String.valueOf(tastaurAttack2);
      }
    }
    return this;
  }

  public Settings setTastaurAttack3(char tastaurAttack3) {
    this.tastaurAttack3 = tastaurAttack3;
    for(int i = 0; i<ausgabe.length; i++){
      if(ausgabe[i][0].equals("tastaurAttack3")){
        ausgabe[i][1] = String.valueOf(tastaurAttack3);
      }
    }
    return this;
  }

  public Settings setMausBenutzen(boolean mausBenutzen) {
    this.mausBenutzen = mausBenutzen;
    for(int i = 0; i<ausgabe.length; i++){
      if(ausgabe[i][0].equals("mausBenutzen")){
        ausgabe[i][1] = String.valueOf(mausBenutzen);
      }
    }
    return this;
  }

  public Settings setAufloesungX(int aufloesungX) {
    this.aufloesungX = aufloesungX;
    for(int i = 0; i<ausgabe.length; i++){
      if(ausgabe[i][0].equals("aufloesungX")){
        ausgabe[i][1] = String.valueOf(aufloesungX);
      }
    }
    return this;
  }

  public Settings setAufloesungY(int aufloesungY) {
    this.aufloesungY = aufloesungY;
    for(int i = 0; i<ausgabe.length; i++){
      if(ausgabe[i][0].equals("aufloesungY")){
        ausgabe[i][1] = String.valueOf(aufloesungY);
      }
    }
    
    return this;
  }

  public Settings setSound(boolean sound) {
    this.sound = sound;
    for(int i = 0; i<ausgabe.length; i++){
      if(ausgabe[i][0].equals("sound")){
        ausgabe[i][1] = String.valueOf(sound);
      }
    }
    
    return this;
  }

    /**
     *
     * @param musik
     * @return
     */
  public Settings setMusik(boolean musik) {
    this.musik = musik;
    for(int i = 0; i<ausgabe.length; i++){
      if(ausgabe[i][0].equals("musik")){
        ausgabe[i][1] = String.valueOf(musik);
      }
    }
    return this;
  }

  public char getTastaurLinks() {
    return tastaurLinks;
  }

  public char getTastaurRechts() {
    return tastaurRechts;
  }

  public char getTastaurJump() {
    return tastaurJump;
  }

  public char getTastaurAttack1() {
    return tastaurAttack1;
  }

  public char getTastaurAttack2() {
    return tastaurAttack2;
  }

  public char getTastaurAttack3() {
    return tastaurAttack3;
  }

  public boolean isMausBenutzen() {
    return mausBenutzen;
  }

  public int getAufloesungX() {
    return aufloesungX;
  }

  public int getAufloesungY() {
    return aufloesungY;
  }

  public boolean issound() {
    return sound;
  }

  public boolean isMusik() {
    return musik;
  }

}
