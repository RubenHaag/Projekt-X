import java.io.IOException;

public class main2 {

  public static void main(String[] args) throws IOException {
    Spieler s1= new Spieler();
    Thread c = new Client(11, s1);
    c.run(); 

  }

}
