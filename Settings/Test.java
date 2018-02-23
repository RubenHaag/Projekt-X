import java.io.IOException;

public class Test {

  static Settings s;
  public static void main(String[] args){
    try {
      s = new Settings("Settings.txt");
    }
    catch (IOException e){
      e.printStackTrace();
    }
    
    s = s.setMausBenutzen(true);
    s = s.setTastaurLinks('1');
    try{
      s.writeParams();
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
}
