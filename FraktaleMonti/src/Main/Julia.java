package Main;
import java.awt.Color;

import Util.complex;


public class Julia extends Fraktal{
private int iteration;
private double max,xsize,ysize;
private int[][] it;
private complex c;
	protected Julia(int it, double max, double xmin, double xmax, double ymin, double ymax,int scale, complex c) {
		super(xmin, xmax, ymin, ymax, scale);
		this.max=max;
		this.iteration=it;
		xsize=xmax-xmin;
		ysize=ymax-ymin;
		this.c=c;
		this.it=new int[(int)(xsize*scale)+1][(int)(ysize*scale)+1];
	}
	
	private void render(){
		for(int x=0;x<xsize*scale;x++){
			for(int y=0;y<ysize*scale;y++)
			pixels[x][y]=col(it[x][y]);
		}
	}
	public void setC(complex c){
		this.c=c;
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
	public int col(int x){
		if (x==this.iteration)return 0;
		return Color.HSBtoRGB((float)((float)x/(float)this.iteration), 1.0f, 0.8f);
		//return (x==iteration)?0xffffff:0x000000;
	}
	public int calc(complex z){
		int itera=0;
		while(itera<this.iteration&&z.getAbs()<max){
			z.sqr();
			z.add(c);
			itera++;
		}
		return itera;
	}
}
