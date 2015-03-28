package Util;

public class FnParse {
	private static boolean isNum(String s){
		try{
			Double.parseDouble(s);
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}
    public static ParseState parseFn(String s) throws Exception {
    	if(s.isEmpty())
    		return new ParseState(new Number(new complex(0,0)),"");
    	else if(isNum(s.split(" ")[0])){
    		return new ParseState(new Number(new complex(Double.parseDouble(s.split(" ")[0]),0)), s.substring(s.split(" ")[0].length()).trim());
    	}
    	else if(isNum(s.split(" ")[0].substring(0, s.split(" ")[0].length()-1))){
    		return new ParseState(new Number(new complex(0,Double.parseDouble(s.split(" ")[0].substring(0, s.split(" ")[0].length()-2)))), s.substring(s.split(" ")[0].length()).trim());
    	}
    	else if(s.split(" ")[0].matches("^[+*/^-]$")){
    		ParseState p = parseFn(s.substring(1).trim());
    		ParseState p2= parseFn(p.rest);
    		return new ParseState(new Op(p.e, p2.e,s.split(" ")[0]), p2.rest);
    	}
    	else if(s.split(" ")[0].matches("^(exp|sqr|abs|getr|geti|[~])$")){
    		ParseState p = parseFn(s.substring(s.split(" ")[0].length()).trim());
    		return new ParseState(new Fun(s.split(" ")[0],p.e),p.rest);
    	}
    	else if(s.split(" ")[0].matches("^[a-zA-Z]+$")){
    		return new ParseState(new Variable(new complex(0,0),s.split(" ")[0]), s.substring(s.split(" ")[0].length()).trim()); 	
    	}
    	else {
           throw new Exception("Error while parsing function: " + s);
    	}
    }
}
