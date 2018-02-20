
import java.io.IOException;

public class main {

  public static void main(String[] args) throws IOException {
    Thread l = new ServerListen(1234);
    l.run();                              
    

  }

}
