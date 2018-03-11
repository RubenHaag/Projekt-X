import java.io.IOException;

public class MapBuilderTest {
    public static void main(String[] args){
        try{
            MapBuilder.BildZuRechteck("./MapComputer.jpg");
            MapBuilder.BildZuPortal("./MapComputer.jpg");
            System.out.println("b");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}

