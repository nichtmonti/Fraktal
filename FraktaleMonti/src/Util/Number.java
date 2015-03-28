package Util;

public class Number implements Expression {
    public complex val;
    
    public Number(complex c){
    	val=c;
    }
    
	public complex eval() {
		return val;
	}

	public String toString(){
		return "+ " + val.getR() + " " + val.getI() + "i"; 
	}
	
	public void setVar(complex c, String name){}
}
