package IOServer.testframeworks;

import java.util.Timer;
import java.util.TimerTask;

public class UpdateHealthTest {
	private static double h1 = 20, regspeed1 = 20;
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				updateHealth();
				System.out.println((int) h1);
			}
		}, 0, 100);
	}

	public static void updateHealth(){
		if (h1 < 100){
			h1 =(h1 + 0.01* regspeed1);
		}
	}
}
