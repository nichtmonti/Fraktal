package Fraktale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Util.linearFun;
import Util.vector2d;

public class Sierpinski extends Fraktal{
	List<vector2d> P = new ArrayList();
	linearFun lin1 = new linearFun("{{0.5,0},{0,0.5}}","{{5,0}}");
	linearFun lin2 = new linearFun("{{0.5,0},{0,0.5}}","{{-5,0}}");
	linearFun lin3 = new linearFun("{{0.5,0},{0,0.5}}","{{0,5}}");
	private int iteration = 13;
	public Sierpinski(double xmin, double xmax, double ymin, double ymax,long scale) {
		super(xmin, xmax, ymin, ymax, scale);
		P.add(new vector2d(0,-3000));
		// TODO Auto-generated constructor stub
	}
	
	
	public vector2d[] calc (vector2d x){
		return new vector2d[]{lin1.calc(x),lin2.calc(x),lin3.calc(x)};
	}
	public void iterate(){
		List<vector2d> temp = new ArrayList<vector2d>();
		for(int i=0;i<P.size();i++){
			//System.out.println("    "+i);
			temp.addAll(Arrays.asList(calc(P.get(i))));
		}
		P=temp;
	}
	public void update(){
		for(int i=0;i<iteration;i++){
			//System.out.println(i);
			iterate();
		}
		render();
	}
	public void render(){
		for(int i=0;i<pixels.length;i++)for(int j=0;j<pixels[i].length;j++)pixels[i][j]=0x000000;
		for(vector2d x : P){
			try{
				pixels[(int)((x.getR()-xmin)*scale)][(int)((x.getI()-ymin)*scale)]=0xffff00;
			}catch(Exception e){};
		}
	}
}
