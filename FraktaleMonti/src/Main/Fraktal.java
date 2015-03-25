package Main;

public class Fraktal {

	public int[][] pixels;
	protected double xmin,xmax,ymax,ymin,scale;
	
	protected Fraktal (double xmin, double xmax, double ymin, double ymax,int scale){
		this.xmin=xmin;
		this.xmax=xmax;
		this.ymin=ymin;
		this.ymax=ymax;
		this.scale=scale;

		this.pixels = new int[(int)((xmax-xmin)*scale)+1][(int)((ymax-ymin)*scale)+1];
	}
	public void update(){};
	
}
