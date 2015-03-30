package Fraktale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Util.linearFun;
import Util.vector2d;

public class MatrixFrak extends Fraktal{
	List<vector2d> P = new ArrayList();
	ArrayList<linearFun> funs;
	private int iteration = 10;

	
	public MatrixFrak(List<linearFun> funs,double xmin, double xmax, double ymin, double ymax,long scale) {
		super(xmin, xmax, ymin, ymax, scale);
		this.funs=(ArrayList<linearFun>) funs;
		P.add(new vector2d(1,0));
		// TODO Auto-generated constructor stub
	}
	
	
	public List<vector2d> calc (vector2d x){
		ArrayList vecs = new ArrayList();
		for(linearFun l : funs)vecs.add(l.calc(x));
		return vecs;
	}
	public void iterate(){
		List<vector2d> temp = new ArrayList<vector2d>();
		for(int i=0;i<P.size();i++){
			System.out.println("    "+i);
			temp.addAll(calc(P.get(i)));
		}
		P=temp;
	}
	public void update(){
		for(int i=0;i<10;i++){
			System.out.println(i);
			iterate();
		}
		render();
	}
	public void render(){
		for(int i=0;i<pixels.length;i++)for(int j=0;j<pixels[i].length;j++)pixels[i][j]=0x000000;
		for(vector2d x : P){
			try{
				for(int i = 0;i<9;i++)pixels[(int)((x.getR()-xmin)*scale)][(int)((x.getI()-ymin)*scale)]=0xffff55;
			}catch(Exception e){};
		}
	}
}
