package Util;

public class Number implements Expression {
    public complex val;
    
    public Number(complex c){
    	val=c;
    }
    
	public complex eval() {
		return new complex(val.getR(), val.getI());
	}

	public String toString(){
		if (val.getI()==0)
			return val.getR() + "";
		else if(val.getR()==0)
			return val.getI() + "i";
		return "+ " + val.getR() + " " + val.getI() + "i"; 
	}
	
	public void setVar(complex c, String name){}
}
