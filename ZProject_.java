/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thomas
 */
import ij.*;
import ij.process.*;
import ij.plugin.filter.*;
import ij.plugin.*;

public class ZProject_ implements PlugInFilter {
	ImagePlus imp;

	public int setup(String arg, ImagePlus imp) {
		this.imp = imp;
		return DOES_ALL;
	}

	public void run(ImageProcessor ip) {
		ZProjector ZP=new ZProjector(imp);
		ZP.setMethod(ZProjector.AVG_METHOD);
		ZP.doProjection();
		ImagePlus proj=ZP.getProjection();
		proj.show();

	}

}

