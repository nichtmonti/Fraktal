package Util;

public class Matrix {
	private final double[][] mat;
	
	private static double[][] parseArray (String s){
		return MatParse.parseM(s)[0];
	}
	
	public Matrix (String s){
		this(parseArray(s));
	}
	public Matrix (double[][] s){
		mat=s;
	}
	public vector2d mult (vector2d v){
		return new vector2d(mat[0][0]*v.getR()+mat[1][0]*v.getI(),mat[0][1]*v.getR()+mat[1][1]*v.getI());		
	}
}
