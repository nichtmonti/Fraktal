package Util;

public class ParseState {
	public Expression e;
	public String rest;
	public ParseState(){}
	public ParseState(Expression e, String rest){
		this.e=e;
		this.rest=rest;
	}
}
