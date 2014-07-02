
import ij.ImagePlus;
import ij.gui.Roi;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;
import ij.process.Photometer;
import java.awt.Rectangle;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author thomas
 */
public class Stack_Photometer implements PlugInFilter {

    int x = -1, y = -1;
    Photometer photo;
    ImagePlus plus;

    public int setup(String arg, ImagePlus imp) {
        plus = imp;
        photo = Photometer.getInstance();
        if (photo == null) {
            photo = new Photometer();
        }

        Roi roi = imp.getRoi();
        if (roi != null) {
            Rectangle re = roi.getBounds();
            x = (int) (re.x + 0.5 * re.width);
            y = (int) (re.y + 0.5 * re.height);
        }

        return DOES_8G + DOES_16 + DOES_32;
    }

    public void run(ImageProcessor ip) {
        if (x == -1) {
            return;
        }
        for (int s = 1; s <= plus.getNSlices(); s++) {
            plus.setSlice(s);
            photo.computePhotometry(plus, x, y);
        }
    }
}
