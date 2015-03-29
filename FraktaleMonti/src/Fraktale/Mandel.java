package Fraktale;
import java.awt.Color;

import Util.complex;


public class Mandel extends Fraktal implements Runnable{
public int iteration;
public double max,xsize,ysize;
private float[][] it;
private ArbeiterKlasse arbeiter = new ArbeiterKlasse();

	public Mandel(int it, double max, double xmin, double xmax, double ymin, double ymax,long scale) {
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
				it[(int)((x-xmin)*scale)][(int)((y-ymin)*scale)]=arbeiter.calc(t, iteration, max);
			}
		}
		render();
	}
	public void move (dir d, int pixel){
		if(d==dir.up){
			ymin+=pixel/scale;
			for(int x=0;x<xsize*scale;x++){
				for(int y=0;y<ysize*scale;y++){
					if(isthere(pixels,x,y+pixel))pixels[x][y]=pixels[x][y+pixel];
					else pixels[x][y]=col(arbeiter.calc(new complex(((double)x/scale+xmin),(double)y/scale+ymin), iteration, max));
				}
			}
		}
		if(d==dir.down){
			ymin-=pixel/scale;
			for(int x=0;x<xsize*scale;x++){
				for(int y=0;y<ysize*scale;y++){
					if(isthere(pixels,x,y-pixel))pixels[x][y]=pixels[x][y-pixel];
					else pixels[x][y]=col(arbeiter.calc(new complex(((double)x/scale+xmin),(double)y/scale+ymin), iteration, max));
				}
			}
		}
		if(d==dir.left){
			xmin-=pixel/scale;
			for(int x=0;x<xsize*scale;x++){
				for(int y=0;y<ysize*scale;y++){
					if(isthere(pixels,x-pixel,y))pixels[x][y]=pixels[x-pixel][y];
					else pixels[x][y]=col(arbeiter.calc(new complex(((double)x/scale+xmin),(double)y/scale+ymin), iteration, max));
				}
			}
		}
		if(d==dir.right){
			ymin+=pixel/scale;
			for(int x=0;x<xsize*scale;x++){
				for(int y=0;y<ysize*scale;y++){
					if(isthere(pixels,x+pixel,y))pixels[x][y]=pixels[x+pixel][y];
					else pixels[x][y]=col(arbeiter.calc(new complex(((double)x/scale+xmin),(double)y/scale+ymin), iteration, max));
				}
			}
		}
		/*for(int x=0;x<xsize*scale;x++){
			for(int y=0;y<ysize*scale;y++){
				if(d==dir.up){
					if(isthere(pixels,x,y+pixel))pixels[x][y]=pixels[x][y+pixel];
					else pixels[x][y]=col(arbeiter.calc(new complex((((double)x)/scale+xmin),(double)y/scale+ymin), iteration, max));
				}
				else if(d==dir.down){
					if(isthere(pixels,x,y-pixel))pixels[x][y]=pixels[x][y-pixel];
					else pixels[x][y]=col(arbeiter.calc(new complex((((double)x)/scale+xmin),(double)y/scale+ymin), iteration, max));
				}
				else if(d==dir.left){
					if(isthere(pixels,x+pixel,y))pixels[x][y]=pixels[x+pixel][y];	
					else pixels[x][y]=col(arbeiter.calc(new complex(((double)x/scale+xmin),((double)y)/scale+ymin), iteration, max));
				}
				else if(d==dir.right){
					if(isthere(pixels,x-pixel,y))pixels[x][y]=pixels[x-pixel][y];	
					else pixels[x][y]=col(arbeiter.calc(new complex(((double)x/scale+xmin),((double)y)/scale+ymin), iteration, max));
				}
			}
		}*/
	}
	private boolean isthere (int[][] a,int i, int j){
		try{
			int x=a[i][j];
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
	public int col(float x){
		if (x==this.iteration)return 0;
		return Color.HSBtoRGB((float)((float)x/(float)this.iteration), 1.0f, 0.8f);
		//return (x==iteration)?0xffffff:0x000000;
	}

	public void run() {
		update();
	}
}
