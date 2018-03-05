package grafik;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;


/**
 * Stellt Methoden zum Abspielen von Sound-Dateien bereit
 * @author Fabian Scherer
 *
 */
class SoundManager extends Thread{  // muss vllt ein eigener Thread sein
    private static Clip clip = null;

    /**
     * Spielt die �bergebene Sound-Datei ab
     * @param dateiname Datei-Pfad der Sound-Datei
     */
    public static void playSound(String dateiname){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(dateiname));
            AudioFormat af = audioInputStream.getFormat();
            int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
            byte[] audio = new byte[size];
            DataLine.Info info = new DataLine.Info(Clip.class, af, size);
            audioInputStream.read(audio, 0, size);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(af, audio, 0, size);
            clip.start();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    
    public void run(){
    	try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Assets/Sound/Menu Music.wav"));
            AudioFormat af = audioInputStream.getFormat();
            int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
            byte[] audio = new byte[size];
            DataLine.Info info = new DataLine.Info(Clip.class, af, size);
            audioInputStream.read(audio, 0, size);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(af, audio, 0, size);
            clip.start();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
        while(!isInterrupted()) {
        	if(!clip.isRunning()) {
        		try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Assets/Sound/Menu Music.wav"));
                    AudioFormat af = audioInputStream.getFormat();
                    int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
                    byte[] audio = new byte[size];
                    DataLine.Info info = new DataLine.Info(Clip.class, af, size);
                    audioInputStream.read(audio, 0, size);
                    clip = (Clip) AudioSystem.getLine(info);
                    clip.open(af, audio, 0, size);
                    clip.start();
            		} catch(Exception e) {
            			e.printStackTrace();
            		}
        	}
        	try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

}

