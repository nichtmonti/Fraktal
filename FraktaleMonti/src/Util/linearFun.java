package Util;

public class linearFun {
	private Matrix matrix;
	private vector2d vec;
	
	public linearFun (Matrix m, vector2d v){
		this.matrix=m;
		this.vec = v;
	}
	public linearFun (String m, String v){
		this(new Matrix(m), new vector2d(MatParse.parseVec(v)[0]));
	}
	public vector2d calc (vector2d x){
		return matrix.mult(x).add(vec);
	}
}
