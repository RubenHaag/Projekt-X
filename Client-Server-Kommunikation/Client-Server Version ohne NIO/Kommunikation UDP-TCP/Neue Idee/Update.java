import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/*
   * @author Oskar Moritz
   * @version 1.0 
   * Update
   */
public class Update{
  private int a;
  private String b;
  private Long c;

  public byte[] getbyte() throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(baos);
      out.writeInt(a);
        out.writeChars(b);
        out.writeLong(c);
        byte[] data = baos.toByteArray();
        return data;      
    }
    
    public void awaybyte(byte[] data) throws IOException {
      ByteArrayInputStream bais = new ByteArrayInputStream(data);
      DataInputStream in = new DataInputStream(bais);
        a = in.readInt();
        b = in.readUTF();
        c = in.readLong();
    }
  
}
