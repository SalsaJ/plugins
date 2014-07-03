
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author thomas
 */
public class Log_ implements PlugInFilter {

    ImagePlus plus;

    public int setup(String arg, ImagePlus imp) {
        plus = imp;

        return DOES_8G + DOES_16 + DOES_32;
    }

    public void run(ImageProcessor ip) {
        ImageProcessor ip2 = ip.convertToFloat().duplicate();
        ip2.log();
        ip2.resetMinAndMax();
        new ImagePlus("Log Image",ip2).show();
    }
}
