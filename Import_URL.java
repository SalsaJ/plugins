
import ij.*;
import ij.gui.GenericDialog;
import ij.plugin.PlugIn;

public class Import_URL implements PlugIn {

    public void run(String arg) {

        GenericDialog gd = new GenericDialog("Import URL");
        gd.addStringField("URL =", "http://www.euhou.net/images/docs/software/images_for_salsaj/imagesESO/ESO_CentaurusA.jpg", 64);
        gd.showDialog();
        String url = gd.getNextString();

        //IJ.log("Opening " + url);
        ImagePlus imp = new ImagePlus(url);
        imp.show();

    }
}
