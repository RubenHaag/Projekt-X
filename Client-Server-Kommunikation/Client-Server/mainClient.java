import java.io.IOException;

public class mainClient {

  public static void main(String[] args) throws IOException {
    Spieler s1= new Spieler();
    Thread c = new Client(11, s1);
    c.run(); 

  }

}
