
import java.io.IOException;

public class mainServer {

  public static void main(String[] args) throws IOException {
    Thread l = new ServerListen(1234);
    l.run();                              
    

  }

}
