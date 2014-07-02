
import ij.*;
import ij.gui.GenericDialog;
import ij.plugin.PlugIn;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Change_Lang implements PlugIn {

    public void run(String arg) {
        Runtime run = Runtime.getRuntime();
        IJ.log("Total Memory " + run.totalMemory() / 1024);
        GenericDialog gd = new GenericDialog("Language settings");
        String[] lang = {"Français", "English", "Castellano", "Deutsch", "Nederlands", "Ελληνικά", "Italiano", "Svenska", "Română", "Português", "Polski"};
        String[] code = {"fr", "en", "es", "de", "nl", "el", "it", "sv", "ro", "pt", "pl"};
        gd.addChoice("Language", lang, lang[0]);
        gd.addNumericField("Memory", 1024, 0, 5, "m");
        gd.showDialog();
        if (gd.wasCanceled()) {
            return;
        }
        String is = code[gd.getNextChoiceIndex()];
        int mem = (int) gd.getNextNumber();
        if (mem > (int) (0.75 * run.totalMemory() / 1024)) {
            IJ.log("Too much memory");
            mem = (int) (0.75 * run.totalMemory() / 1024);
        }
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(IJ.getDirectory("home") + ".salsaj_lang"));
            bf.write(is);
            bf.close();
        } catch (IOException ex) {
            Logger.getLogger(ImageJ.class.getName()).log(Level.SEVERE, null, ex);
            IJ.log("Warning could not write the lang file in " + IJ.getDirectory("home"));
        }
        try {
            BufferedWriter bf2 = new BufferedWriter(new FileWriter(IJ.getDirectory("imagej") + "salsaj2.run"));
            //bf.write(is);
            bf2.write("java -Xmx" + mem + "m -jar SalsaJ2.jar");
            //bf.close();
            bf2.close();
        } catch (IOException ex) {
            Logger.getLogger(ImageJ.class.getName()).log(Level.SEVERE, null, ex);
            IJ.log("Warning could not write the startup file in " + IJ.getDirectory("imagej"));
        }
        System.exit(0);
    }
}
