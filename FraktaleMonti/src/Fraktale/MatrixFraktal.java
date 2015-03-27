package Fraktale;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Util.vector2d;

public class MatrixFraktal extends Fraktal{
	private double[][][] Matrizen;
	private int iteration;
	private double max,xsize,ysize;
	private boolean[][] it;
	private vector2d[] c;
	private List<vector2d> points = new ArrayList<vector2d>();
		public MatrixFraktal(int it, double max, double xmin, double xmax, double ymin, double ymax,long scale, vector2d[] transfvec, double[][][] matrices, List<vector2d> p) {
			super(xmin, xmax, ymin, ymax, scale);
			this.max=max;
			this.Matrizen=matrices;
			this.iteration=it;
			xsize=xmax-xmin;
			ysize=ymax-ymin;
			this.c=transfvec;
			points.addAll(p);
		}
		public MatrixFraktal(int it, double max, double xmin, double xmax, double ymin, double ymax,long scale, vector2d c, double[][] m, vector2d[] p){
			super(0,0,0,0,0);
			new MatrixFraktal(it,max,xmin,xmax,ymin,ymax,scale,c, m, p);
		}
		private void render(){
			for(int x=0;x<xsize*scale;x++){
				for(int y=0;y<ysize*scale;y++)
					pixels[x][y]=(it[x][y])?0x000000:0xffffff;
			}
		}
		public void setTransformationVectors(vector2d[] c){
			this.c=c;
		}
		public void update(){
			for(int i=0;i<points.size();i++){
				try{it[(int)((points.get(i).getR()-xmin)*scale)][(int)((points.get(i).getI()-ymin)*scale)]=true;}catch(Exception e){e.printStackTrace();}
			}
			render();
		}
		public int col(float x){
			if (x==this.iteration)return 0;
			return Color.HSBtoRGB((float)((float)x/(float)this.iteration), 1.0f, 0.8f);
			//return (x==iteration)?0xffffff:0x000000;
		}
		public List<vector2d> calc(){
			int itera=0;
			while(itera<this.iteration){
				List<vector2d> nps = new ArrayList<vector2d>();
				for(int i=0;i<Matrizen.length&&i<c.length;i++){
					for(int j=0;j<points.size();j++){
						nps.add(points.get(j).mult(Matrizen[i]).add(c[i]));
					}
				}
				points=nps;
				itera++;
			}
			return points;
		}
}
