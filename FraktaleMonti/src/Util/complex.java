package Util;

public class complex {
	private double r,i;
	
	public complex(double r, double i){
		this.r=r;
		this.i=i;
	}
	public complex(complex c){
		r=c.getR();
		i=c.getI();
	}
	public double getI (){
		return i;
	}
	public double getR(){
		return r;
	}
	public void print(){
		System.out.println(r+(i<0?"":"+")+i+"i");
	}
	public void setR(double r){
		this.r=r;
	}
	public void setI(double i){
		this.i=i;
	}
	public complex mult (complex c1){
		r=this.getR()*c1.getR()-this.getI()*c1.getI();
		i=this.getR()*c1.getI()+this.getI()*c1.getR();
		return this;
	}
	public complex add (complex c){
		r=this.getR()+c.getR();
		i=this.getI()+c.getI();
		return this;
	}
	public complex sub (complex c){
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
	/*public complex pow(complex c){
		double abs=Math.pow(this.getAbs(),c.getAbs());
		double angle=this.getAngle()+c.getAngle();
		this.r=Math.cos(angle)*abs;
		this.i=Math.sin(angle)*abs;
		return this;
	}
	public complex pow(double j) {
		double abs=Math.pow(this.getAbs(),j);
		double angle=this.getAngle()*j;
		this.r=Math.cos(angle)*abs;
		this.i=Math.sin(angle)*abs;
		return this;
	}*/
	public complex sqr(){
		double r0=r;
		double i0=i;
		r=r0*r0-i0*i0;
		i=2*r0*i0;
		return this;
	}
}
