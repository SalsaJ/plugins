
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.plugin.filter.RankFilters;
import ij.process.ImageProcessor;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 **
 * /**
 * Copyright (C) 2008- 2012 Thomas Boudier and others
 *
 *
 *
 * This file is part of mcib3d
 *
 * mcib3d is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * @author thomas
 */
public class Filters_ implements PlugInFilter {

    ImagePlus imp;

    public int setup(String arg, ImagePlus imp) {
        this.imp = imp;

        return DOES_16 + DOES_8G + DOES_STACKS;
    }

    public void run(ImageProcessor ip) {
        RankFilters rk = new RankFilters();
        rk.setup("median", imp);
        rk.rank(ip, IJ.getNumber("Radius", 2), RankFilters.MEDIAN);

    }
}
