package Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;

public class MatParse {
	public static double[][] parseVec(String s){
		int y=0;
		int z=0;
		ArrayList<Double> o = new ArrayList<Double>();
		y=allBr(s.substring(1)).length;
		for(String b : allBr(s.substring(1))) {
			if(b.equals(",")||b.equals("}")){
				y--;
				continue;
			}
			z=b.replace('{',' ').replace('}', ' ').trim().split(",").length;
			for(String c : b.replace('{',' ').replace('}', ' ').trim().split(",")){
				if(c.equals("")){
					z--;
					continue;
				}
				o.add(Double.parseDouble(c));
			}
		}
		double[][] res = new double[y][z];
		for(int j=0;j<y;j++){
			for(int k=0;k<z;k++){
				res[j][k]=o.get(0);
				o.remove(0);
			}
		}
		return res;
	}
	
	public static double[][][] parseM(String s){

		ArrayList<Double> o = new ArrayList<Double>();
		int x=0,y=0,z=0;
		x=allBr(s).length;
		for(String a : allBr(s)){
			y=allBr(a.substring(1)).length;
			for(String b : allBr(a.substring(1))) {
				if(b.equals(",")||b.equals("}")){
					y--;
					continue;
				}
				z=b.replace('{',' ').replace('}', ' ').trim().split(",").length;
				for(String c : b.replace('{',' ').replace('}', ' ').trim().split(",")){
					if(c.equals("")){
						z--;
						continue;
					}
					o.add(Double.parseDouble(c));
				}
			}
		}
		double[][][] res = new double[x][y][z];
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				for(int k=0;k<z;k++){
					res[i][j][k]=o.get(0);
					o.remove(0);
				}
			}
		}
		return res;
	}
	public static String[] allBr(String s){
		ArrayList<String> o = new ArrayList<String>();
		while(!s.equals("")){
			String[] tmp = getBr(s);
			o.add(tmp[0]);
			s=tmp[1];
		}
		return Arrays.copyOf(o.toArray(),o.size(),String[].class);
	}
	private static String[] getBr(String s){
		int i=0;
		String o="";
		for(char c:s.toCharArray()){
			if(c=='{') i++;
			if(c=='}') i--;
			o+=c;
			if(i==0) break;
		}
		return new String[]{o, s.substring(o.length())};
	}
}
