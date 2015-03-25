package Main;
import java.awt.Color;

import Util.complex;


public class Mandel extends Fraktal{
private int iteration;
private double max,xsize,ysize;
private float[][] it;
	protected Mandel(int it, double max, double xmin, double xmax, double ymin, double ymax,int scale) {
		super(xmin, xmax, ymin, ymax, scale);
		this.max=max;
		this.iteration=it;
		xsize=xmax-xmin;
		ysize=ymax-ymin;
		this.it=new float[(int)(xsize*scale)+1][(int)(ysize*scale)+1];
	}
	
	private void render(){
		for(int x=0;x<xsize*scale;x++){
			for(int y=0;y<ysize*scale;y++)
			pixels[x][y]=col(it[x][y]);
		}
	}
	
	public void update(){
		for (double y=ymin;y<ymax;y+=1.0/(double)scale){
			for(double x=xmin;x<xmax;x+=1.0/(double)scale){
				complex t = new complex(x,y);
				it[(int)((x-xmin)*scale)][(int)((y-ymin)*scale)]=calc(t);
			}
		}
		render();
	}
	public int col(float x){
		if (x==this.iteration)return 0;
		return Color.HSBtoRGB((float)((float)x/(float)this.iteration), 1.0f, 0.8f);
		//return (x==iteration)?0xffffff:0x000000;
	}
	public float calc(complex c){
		int itera=0;
		complex c0=new complex(c.getR(),c.getI());
		while(itera<this.iteration&&c.getAbs()<max){
			c.sqr();
			c.add(c0);
			itera++;
		}
		if(itera!=this.iteration)
			return (float)(itera-Math.log(Math.log(c.getAbs())/Math.log(max))/Math.log(2));
		return itera;
	}
}
