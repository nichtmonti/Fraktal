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
		if (val.getR()==0)
			return val.getI() + "i";
		else if(val.getI()==0)
			return val.getR() + "";
		return "+ " + val.getR() + " " + val.getI() + "i"; 
	}
	
	public void setVar(complex c, String name){}
}
