package src.ioserver;

import java.io.Serializable;

public class Settings implements Serializable {
    private final long serialVersionUID = 1712787743039716508L;
    private char tastaurLinks;
    private char tastaurRechts;
    private char tastaurJump;
    private boolean sound;
    private boolean musik;

    public Settings(char tastaurLinks, char tastaurRechts, char tastaurJump, boolean sound, boolean musik) {
        this.tastaurLinks = tastaurLinks;
        this.tastaurRechts = tastaurRechts;
        this.tastaurJump = tastaurJump;
        this.sound = sound;
        this.musik = musik;
    }

    public SettingMovement getMovement(char c) {
        if (c==tastaurJump) {
            return SettingMovement.JUMP;
        } else if (c==tastaurLinks) {
            return SettingMovement.LEFT;
        } else if (c==tastaurRechts) {
            return SettingMovement.RIGHT;
        } else {
            throw new IllegalStateException("Wrong config state!");
        }
    }

    public void printOut() {
        System.out.println("Links laufen: " + tastaurLinks);
        System.out.println("Rechts laufen: " + tastaurRechts);
        System.out.println("Springen: " + tastaurJump);
        System.out.println("Sound an: " + (sound ? "Ja" : "Nein"));
        System.out.println("Musik an: " + (musik? "Ja" : "Nein"));
    }

    public char getTastaurLinks() {
        return tastaurLinks;
    }

    public char getTastaurRechts() {
        return tastaurRechts;
    }

    public char getTastaurJump() {
        return tastaurJump;
    }

    public boolean isSound() {
        return sound;
    }

    public boolean isMusik() {
        return musik;
    }

    public void setTastaurLinks(char tastaurLinks) {
        this.tastaurLinks = tastaurLinks;
    }

    public void setTastaurRechts(char tastaurRechts) {
        this.tastaurRechts = tastaurRechts;
    }

    public void setTastaurJump(char tastaurJump) {
        this.tastaurJump = tastaurJump;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public void setMusik(boolean musik) {
        this.musik = musik;
    }
}
