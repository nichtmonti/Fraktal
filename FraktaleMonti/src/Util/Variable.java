package Util;

public class Variable implements Expression {
    public String name;
    public complex value;
	
    public Variable(complex x, String n){
    	name=n;
    	value=x;
    }
    
    public String toString(){
    	return name;
    }
    
	public complex eval() {
		return value;
	}

	public void setVar(complex x, String n) {
		if (name.equals(n)){
			value=x;
		}
	}

}
