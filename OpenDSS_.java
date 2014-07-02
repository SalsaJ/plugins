
import ij.*;
import ij.gui.GenericDialog;
import ij.plugin.PlugIn;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.event.ItemEvent;
import java.util.*;
import java.io.*;
import java.io.IOException;


public class OpenDSS_ implements PlugIn {

    private ResourceBundle myBundle;
    private Locale lang;

    String[] stars = {"Alma telescope at Chajnantor","Centaurus A black Hole","Centaurus A","Chandra deep field with many galaxies","Christmas Tree NGC2264","Crab Nebula M1","Dumbbell Nebula","Eagle Nebula","Eta Carinae","Flame Nebula","Galactic Center","Helix Nebula","Horsehead Nebula","Jewel Box cluster NGC4755","Milky Way","New planetary system Gliese 581","Omega Centauri","Orion Nebula","Pair of Galaxies NGC1532","Pluto artiste view","Sombrero Galaxy","Spiral Galaxy Messier 83","Spiral Galaxy NGC1232","Spiral Galaxy NGC253","Spiral Galaxy NGC300","Spiral Galaxy NGC4945","Starburst Region NGC3603","Star R Coronae Australis","Stellar cluster NGC2467","Stellar Nursery","The Moon","Trifid Nebula","VLT Paranal platform","VLT Paranal with laser"};
    String[] astars = {"ESO_Alma-Chajnantor.jpg","ESO_CentaurusA_black_Hole.jpg","ESO_CentaurusA.jpg","ESO_Chandra_deep_field_many_galaxies.jpg","ESO_Christmas_Tree_NGC2264.jpg","ESO_Crab_Nebula_M1.jpg","ESO_Dumbbell_Nebula.jpg","ESO_Eagle_Nebula.jpg","ESO_Eta_Carinae.jpg","ESO_Flame_Nebula.jpg","ESO_Galactic_Center.jpg","ESO_Helix_Nebula.jpg","ESO_Horsehead_Nebula.jpg","ESO_Jewel_Box_cluster_NGC4755.jpg","ESO_Milky_Way.jpg","ESO_new_planetary_system_Gliese_581.jpg","ESO_Omega_Centauri.jpg","ESO_Orion_Nebula.jpg","ESO_pair_of_Galaxies_NGC1532.jpg","ESO_Pluto_artiste_view.jpg","ESO_Sombrero_Galaxy.jpg","ESO_Spiral_Galaxy_Messier_83.jpg","ESO_Spiral_Galaxy_NGC1232.jpg","ESO_Spiral_Galaxy_NGC253.jpg","ESO_Spiral_Galaxy_NGC300.jpg","ESO_Spiral_Galaxy_NGC4945.jpg","ESO_Starburst_Region_NGC3603.jpg","ESO_star_R_Coronae_Australis.jpg","ESO_Stellar_cluster_NGC2467.jpg","ESO_Stellar_Nursery.jpg","ESO_the_Moon.jpg","ESO_Trifid_Nebula.jpg","ESO_VLT_Paranal_platform.jpg","ESO_VLT_Paranal_with_laser.jpg"};
    int is = 0;
  		

    public void run(String arg) {

        lang = Locale.getDefault();
        String ist = null;
        try {
            BufferedReader bf = new BufferedReader(new FileReader(".salsaj_lang"));
            ist = bf.readLine();
            bf.close();
        } catch (IOException ex) {
        }
        if (ist != null) {
            lang = new Locale(ist);
        }
		if (lang == null) {
			lang = Locale.ENGLISH;
		}
		myBundle = ResourceBundle.getBundle("OpenDSSBundle", lang);
        
       // StarsDialog sd = new StarsDialog("Select an image");
        StarsDialog sd = new StarsDialog(myBundle.getString("Selection"));
        if (sd.wasOKed()) {
            sd.processInfoFromDialog();
        }
    }

    class StarsDialog extends GenericDialog {

        public StarsDialog(String arg) {
            super(arg);
            //this.addChoice("Image", stars, stars[is]);
            this.addChoice(myBundle.getString("Image"), stars, stars[is]);
            //this.addStringField("File", astars[is], 12);
            this.addStringField(myBundle.getString("File"), astars[is], 3);
            this.showDialog();
        }

        private void updateDialog() {
            TextField tf1;
            Vector v = this.getStringFields();
            tf1 = (TextField) v.get(0);
            Vector vn = this.getNumericFields();      
            tf1.setText(astars[is]);
           
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource() instanceof Choice) {
                Choice src = (Choice) e.getSource();
                is = src.getSelectedIndex();
                updateDialog();
            }
        }

        private void processInfoFromDialog() {
            String r;      
            is = this.getNextChoiceIndex();
            // get values
            r = this.getNextString();
     
                String url = "http://www.euhou.net/images/docs/software/images_for_salsaj/imagesESO/" + r ;
                IJ.showStatus("Opening " + url);
                //IJ.log(url);
                ImagePlus imp = new ImagePlus(url);
                imp.show();
            
        }
    }
}
