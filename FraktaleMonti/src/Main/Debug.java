package Main;
import Fraktale.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import Util.*;

public class Debug {
	static double xscroll=-1.402;
	static double yscroll=0.0000005;
	static int zoomP=3;
	static int zoomF=1;
	
	static int swidth=2500;
	static int sheight=(int)Math.ceil(9.0f/16.0f*swidth);
	
	
	static int scale=(int)Math.round(zoomF*Math.pow(10,zoomP));
	static double w=(1.0*swidth)/(2.0*scale);
	static double h=(1.0*sheight)/(2.0*scale);
	
	static Fraktal frak = new Mandel(10000,100,xscroll-w,xscroll+w,yscroll-h,yscroll+h,scale);
	public static void main(String[] args) {
		frak.update();
		Image img = getImageFromArray(frak.pixels,swidth,sheight);
		
		try {
		    BufferedImage bi = (BufferedImage) img;
		    File outputfile = new File("saved.png");
		    ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {}
	}
	public static Image getImageFromArray(int[][] pixels, int width, int height) {
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //System.out.println(pixels.length + " " + pixels[0].length);
        for(int i=0;i<height;i++){
        	for(int j=0;j<width;j++){
        		
        		image.setRGB(j,i,pixels[j][i]);
        	}
        }
        return image;
    }
}
