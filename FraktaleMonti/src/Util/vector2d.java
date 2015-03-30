package Util;

import Main.SimpleDrawing;

public class vector2d {
	private double r,i;
	
	public vector2d(double r, double i){
		this.r=r;
		this.i=i;
	}
	public vector2d(complex c){
		r=c.getR();
		i=c.getI();
	}
	public vector2d(double[] ds) {
		this.r=ds[0];
		this.i=ds[1];
	}
	public double getI (){
		return i;
	}
	public double getR(){
		return r;
	}
	public vector2d mult(double[][] matrix){
		double xn,yn;
		xn=matrix[0][0]*this.r+matrix[0][1]*this.i;
		yn=matrix[1][0]*this.r+matrix[1][1]*this.i;
		this.r=xn;
		this.i=yn;
		return this;
	}
	public void print(){
		System.out.println(r+" | "+i);
	}
	public void setR(double r){
		this.r=r;
	}
	public void setI(double i){
		this.i=i;
	}
	public vector2d mult (double c1){
		r=this.getR()*c1;
		i=this.getI()*c1;
		return this;
	}
	public vector2d add (vector2d c){
		r=this.getR()+c.getR();
		i=this.getI()+c.getI();
		return this;
	}
	public vector2d sub (vector2d c){
		r=this.getR()-c.getR();
		i=this.getI()-c.getI();
		return this;
	}
	public double getAngle (){
		return Math.atan2(i,r);
	}
	public double getAbs(){
		return Math.sqrt(i*i+r*r);
	}
	public String toString(){
		return SimpleDrawing.round(r,5)+(i<0?"":"+")+SimpleDrawing.round(i,5)+"i";
	}
}
