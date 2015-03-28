package Util;

public class Fun implements Expression {
	enum FunType {
		exp, neg,sqr,abs,getr,geti
	}
	public FunType fun;
	public Expression expr;
  
	public Fun(String name, Expression e){
		if (name.equals("exp")) {
			fun=FunType.exp;
		}
		else if(name.equals("sqr")){
			fun=FunType.sqr;
		}
		else if(name.equals("abs")){
			fun=FunType.abs;
		}
		else if(name.equals("getr")){
			fun=FunType.getr;
		}
		else if(name.equals("geti")){
			fun=FunType.geti;
		}
		else if(name.equals("~")){
			fun=FunType.neg;
		}
		else {
			System.out.println("Error");
		}
		expr = e;
	}
	
	public String toString(){
		if (fun==FunType.neg) return "~" + " " + expr.toString();
		return fun.toString()+" "+expr.toString();
	}
  
	public complex eval(){
		switch (fun) {
	 		case sqr:
	 			return  (expr.eval().sqr());
	 		case exp:
	 			return (expr.eval().exp());  
	 		case abs:
	 			return (new complex(expr.eval().getAbs(),0));  
	 		case getr:
	 			return (new complex(expr.eval().getR(),0));  
	 		case geti:
	 			return (new complex(expr.eval().getI(),0));  
	 		case neg:
	 			return (new complex(0,0).sub(expr.eval()));
	 		default:
	 			System.out.println("Error");
	 			return new complex(0,0);
		}
	}

	public void setVar(complex x, String name) {
		expr.setVar(x,name);
	}
	
}
