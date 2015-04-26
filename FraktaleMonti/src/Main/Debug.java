package Main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Fraktale.Buddhabrot;
import Fraktale.Fraktal;
import Fraktale.MCFrak;
import Fraktale.linearTransFrak;
import Util.linearFun;
import Util.vector2d;


public class Debug {
	static double xscroll=0.4;
	static double yscroll=-0.15;
	static int zoomP=3;
	static int zoomF=1;
	
	static int swidth=1700;
	static int sheight=(int)(Math.ceil(swidth)*9.0/12.0);
	
	
	static int scale=(int)Math.round(zoomF*Math.pow(10,zoomP));
	static double w=(1.0*swidth)/(2.0*scale);
	static double h=(1.0*sheight)/(2.0*scale);
	
	/*static Fraktal frak = new Buddhabrot(1000000,10000,100,xscroll-w,xscroll+w,yscroll-h,yscroll+h,scale);
	static Fraktal frak2 = new MCFrak(frak);
	static linearFun lin = new linearFun("{{0.5,0},{0,0.5}}","{{5,0}}");*/
	
	public static void main(String[] args) {
		List<String[]> funs = new ArrayList<String[]>();
		/*String[] fun1 = {"{{0.5,-0.288675},{0.288675,0.5}}" , "{{0,0}}"};
		String[] fun2 = {"{{0.3333333,0},{0,0.33333}}","{{0.577350,0.3333333}}"};
		String[] fun3 = {"{{0.33333,0},{0,0.33333}}","{{0,0.66667}}"};
		String[] fun4 = {"{{0.3333333,0},{0,0.3333333}}","{{-0.577350,0.3333333}}"};
		String[] fun5 = {"{{0.3333333,0},{0,0.3333333}}","{{-0.577350,-0.3333333}}"};
		String[] fun6 = {"{{0.3333333,0},{0,0.3333333}}","{{0,-0.6666667}}"};
		String[] fun7 = {"{{0.3333333,0},{0,0.3333333}}","{{0.577350,-0.3333333}}"};*/
		String[] fun1 = {"{{0.5,-0.5},{0.5,0.5}}" , "{{ 0.0, 0.0}}"};
		String[] fun2 = {"{{-0.5,-0.5},{0.5,-0.5}}" , "{{ 1, 0}}"};
		/*String[] fun3 = {"{{-1,0},{0,-1}}" , "{{-0.1, -0.1}}"};
		String[] fun4 = {"{{0.2,0},{0,-0.2}}" , "{{0, 0.1}}"};	*/	

		
		funs.add(fun1);
		funs.add(fun2);
		/*funs.add(fun3);
		funs.add(fun4);
		funs.add(fun5);
		funs.add(fun6);
		funs.add(fun7);*/
		
		linearTransFrak linFrak = new linearTransFrak(xscroll-w, xscroll+w, yscroll-h, yscroll+h, 2000, funs);
		linFrak.setItera(23);
		linFrak.setStartPoint(new vector2d(0,1));
		linFrak.update();
		File outputfile = new File("test.png");
	    try {
			ImageIO.write(getImageFromArray(linFrak.pixels), "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static BufferedImage getImageFromArray(int[][] pixels) {
        BufferedImage image = new BufferedImage(pixels.length,pixels[0].length,BufferedImage.TYPE_INT_RGB);
        //System.out.println(pixels.length + " " + pixels[0].length);
        for(int i=0;i<pixels.length;i++){
        	for(int j=0;j<pixels[i].length;j++){
        		image.setRGB(i,j,pixels[i][j]);
        	}
        }
        return image;
    }
}
