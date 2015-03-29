package Util;

public class Op implements Expression{

	enum OpType{
		Add,Sub,Mul,Div,Pow;
		public String toString() {
			if (this==Add) return "+";
			else if (this==Sub) return "-";
			else if (this==Mul) return "*";
			else if (this==Div) return "/";
			else return "^";
		}
	}
	OpType op;
	Expression a;
	Expression b;
	
	public Op(Expression a,Expression b, String op) {
		this.a=a;
		this.b=b;
		if(op.equals("+")){
			this.op=OpType.Add;
		}
		else if(op.equals("-")){
			this.op=OpType.Sub;
		}
		else if(op.equals("*")){
			this.op=OpType.Mul;
		}
		else if(op.equals("/")){
			this.op=OpType.Div;
		}
		else if(op.equals("^")){
			this.op=OpType.Pow;
		}
	}
	
	public String toString(){
		return op.toString() + " " + a.toString() + " " + b.toString();
	}
	
	public complex eval() {
		switch (op){
			case Add:
				return a.eval().add(b.eval());
			case Sub:
				return a.eval().sub(b.eval());
			case Mul:
				return a.eval().mult(b.eval());
			case Div:
				return a.eval().div(b.eval());
			case Pow:
				return a.eval().pow((int)Math.round(b.eval().getAbs()));
			default:
				System.out.println("Error");
				return new complex(0,0);
		}
	}

	public void setVar(complex x, String name) {
		a.setVar(x, name);
		b.setVar(x, name);
	}

}
