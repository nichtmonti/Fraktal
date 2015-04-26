package Fraktale;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Fraktale.Fraktal;
import Util.linearFun;
import Util.vector2d;

public class linearTransFrak extends Fraktal{
	
	List<vector2d> P = new ArrayList<vector2d>();	
	List<linearFun> funs = new ArrayList<linearFun>();
	
	private int iteration = 6;
	
	public linearTransFrak(double xmin, double xmax, double ymin, double ymax,long scale, List<String[]> funs) {
		super(xmin, xmax, ymin, ymax, scale);
		P.add(new vector2d(0,0));
		for(String[] fun : funs)this.funs.add(new linearFun(fun[0],fun[1]));
		// TODO Auto-generated constructor stub
	}
	
	public void setStartPoint (vector2d p)
	{
		P.clear();
		P.add(p);
	}
	public void addStartPoint (vector2d p)
	{
		P.add(p);
	}
	public List<vector2d> calc (vector2d x){
		List<vector2d> vecs = new ArrayList<vector2d>();
		for(linearFun lin : funs)vecs.add(lin.calc(x));
		return vecs;
	}
	public void iterate(){
		List<vector2d> temp = new ArrayList<vector2d>();
		for(int i=0;i<P.size();i++){
			//System.out.println("    "+i);
			temp.addAll(calc(P.get(i)));
		}
		P=temp;
	}
	public void setItera(int it){
		iteration = it;
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
				pixels[(int)((x.getR()-xmin)*scale)][(int)((x.getI()-ymin)*scale)]=0xffffff;
			}catch(Exception e){};
		}
	}
}