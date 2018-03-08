package src.ioserver;

import java.io.*;

public class SettingsManager {
    private static File file = new File("settings.game");

    public static void saveSettings(Settings settings) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeObject(settings);
            output.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Settings loadSettings() {
        Settings settings = null;
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            Object read = input.readObject();
            if (!(read instanceof Settings)) {
                throw new IllegalStateException("Wrong Settings file!");
            }
            settings = (Settings) read;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return settings;
    }
}
