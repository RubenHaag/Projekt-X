import java.nio.ByteBuffer;

public class FirstBuffer {
	public static void main(String[]args) {
		
		ByteBuffer bb = ByteBuffer.allocate(1024); //new buffer with 1024 bytes space
		bb.putInt(1273659238); //UUID
		bb.putInt(200); //XPos
		bb.putInt(300); //YPos
		bb.putDouble(-7.89); //velocity
		bb.flip(); //Ready to be read
		
		System.out.println("Limit= "+bb.limit()); //how much space is needed
		byte[] sd = new byte[bb.limit()];
		System.out.println(bb.getInt());
		System.out.println(bb.getInt());
		System.out.println(bb.getInt());
		System.out.println(bb.getDouble());
	}
}
