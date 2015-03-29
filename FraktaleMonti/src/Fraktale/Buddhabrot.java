package Fraktale;

import java.awt.Color;
import java.util.ArrayList;

import Util.complex;

public class Buddhabrot extends Fraktal{
	public int iteration;
	public double max,xsize,ysize;
	private int[][] it;
	private int mn=0;
	int nc;
		public Buddhabrot(int nc, int it, double max, double xmin, double xmax, double ymin, double ymax,long scale) {
			super(xmin, xmax, ymin, ymax, scale);
			this.max=max;
			this.iteration=it;
			xsize=xmax-xmin;
			ysize=ymax-ymin;
			this.it=new int[(int)(xsize*scale)+1][(int)(ysize*scale)+1];
			this.nc=nc;
		}
		
		private void render(){
			for(int x=0;x<xsize*scale;x++){
				for(int y=0;y<ysize*scale;y++)
				pixels[x][y]=col(it[x][y]);
			}
		}
		
		public void update(){
			for (int i=0; i<xsize*scale;i++)
				for(int j=0; j<ysize*scale;j++)
					it[i][j]=0;
		
			
			for(int i=0; i<nc; i++){
				ArrayList<complex> tmp = iterate(new complex(Math.random()*3.0-2.0,Math.random()*2.0-1.0));
				for(complex a : tmp){
					if (a.getR()<xmax && a.getR()>xmin && a.getI()<ymax && a.getI()>ymin){
						try{it[(int)((a.getR()-xmin)*scale)][(int)((a.getI()-ymin)*scale)]++;}catch(Exception e){}
					}
				}
			}
			
			for (int i=0; i<xsize*scale;i++)
				for(int j=0; j<ysize*scale;j++)
					if(it[i][j]>mn) mn=it[i][j];
			render();
		}

		private ArrayList<complex> iterate(complex c){
			ArrayList<complex> out = new ArrayList<complex>();
			complex z = new complex(c.getR(), c.getI());
			for (int j = 0; j <iteration;j++){
				z.sqr().add(c);
				out.add(new complex(z.getR(), z.getI()));
				if(z.getAbs()>max) return out;
			}
			return new ArrayList<complex>();
		}
		
		public int col(int x){
			if (x==this.iteration)return 0;
			return Color.HSBtoRGB((float)((float)x/(float)this.mn), 1.0f, 0.8f);
			//return (x==iteration)?0xffffff:0x000000;
		}
}
