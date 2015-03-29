package Fraktale;

import Util.complex;

public class ArbeiterKlasse{
	
	public float calc(complex z, int iteration, double max, complex c){
		int itera=0;
		while(itera<iteration&&z.getAbs()<max){
			z.sqr().add(c);
			itera++;
		}
		if(itera!=iteration)return (float)(itera-Math.log(Math.log(z.getAbs())/Math.log(max))/Math.log(2));
		return itera;
	}
	public float calc(complex z, int iteration, double max){
		int itera=0;
		complex c = new complex(z);
		while(itera<iteration&&z.getAbs()<max){
			z.sqr().add(c);
			itera++;
		}
		if(itera!=iteration)return (float)(itera-Math.log(Math.log(z.getAbs())/Math.log(max))/Math.log(2));
		return itera;
	}

}
