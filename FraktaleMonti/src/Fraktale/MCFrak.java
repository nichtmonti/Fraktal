package Fraktale;
import java.awt.Color;

import Util.complex;


public class MCFrak extends Fraktal implements Runnable{
	Thread[] threads;
	Fraktal[] fraks;
	
	public MCFrak(Fraktal frak) {
		super(frak);
		fraks=new Fraktal[4];
		threads=new Thread[4];
		if(frak instanceof Mandel){
			Mandel man = (Mandel)frak;
			for(int i=0;i<4;i++){
				double xmin=i%2==0?man.xmin:(man.xmin+man.xmax)/2;
				double xmax=i%2!=0?man.xmax:(man.xmax+man.xmin)/2;
				double ymin=i/2==0?man.ymin:(man.ymin+man.ymax)/2;
				double ymax=i/2!=0?man.ymax:(man.ymin+man.ymax)/2;
				fraks[i]=new Mandel(man.iteration,man.max,xmin,xmax, ymin,ymax,(long) man.scale);
				threads[i]=new Thread(fraks[i]);
			}
		}
		if(frak instanceof Julia){
			Julia man = (Julia)frak;
			for(int i=0;i<4;i++){
				double xmin=i%2==0?man.xmin:(man.xmin+man.xmax)/2;
				double xmax=i%2!=0?man.xmax:(man.xmax+man.xmin)/2;
				double ymin=i/2==0?man.ymin:(man.ymin+man.ymax)/2;
				double ymax=i/2!=0?man.ymax:(man.ymin+man.ymax)/2;
				fraks[i]=new Julia(man.iteration,man.max,xmin,xmax, ymin,ymax,(long) man.scale,man.c);
				threads[i]=new Thread(fraks[i]);
			}
		}
		if(frak instanceof JuliaFUN){
			JuliaFUN man = (JuliaFUN)frak;
			for(int i=0;i<4;i++){
				double xmin=i%2==0?man.xmin:(man.xmin+man.xmax)/2;
				double xmax=i%2!=0?man.xmax:(man.xmax+man.xmin)/2;
				double ymin=i/2==0?man.ymin:(man.ymin+man.ymax)/2;
				double ymax=i/2!=0?man.ymax:(man.ymin+man.ymax)/2;
				fraks[i]=new JuliaFUN(man.ex,man.iteration,man.max,xmin,xmax, ymin,ymax,(long) man.scale,man.c);
				threads[i]=new Thread(fraks[i]);
			}
		}
		if(frak instanceof JuliaEXP){
			JuliaEXP man = (JuliaEXP)frak;
			for(int i=0;i<4;i++){
				double xmin=i%2==0?man.xmin:(man.xmin+man.xmax)/2;
				double xmax=i%2!=0?man.xmax:(man.xmax+man.xmin)/2;
				double ymin=i/2==0?man.ymin:(man.ymin+man.ymax)/2;
				double ymax=i/2!=0?man.ymax:(man.ymin+man.ymax)/2;
				fraks[i]=new Julia(man.iteration,man.max,xmin,xmax, ymin,ymax,(long) man.scale,man.c);
				threads[i]=new Thread(fraks[i]);
			}
		}
		if(frak instanceof Buddhabrot){
			Buddhabrot man = (Buddhabrot)frak;
			for(int i=0;i<4;i++){
				double xmin=i%2==0?man.xmin:(man.xmin+man.xmax)/2;
				double xmax=i%2!=0?man.xmax:(man.xmax+man.xmin)/2;
				double ymin=i/2==0?man.ymin:(man.ymin+man.ymax)/2;
				double ymax=i/2!=0?man.ymax:(man.ymin+man.ymax)/2;
				fraks[i]=new Buddhabrot(man.nc/4,man.iteration,man.max,xmin,xmax, ymin,ymax,(long) man.scale);
				threads[i]=new Thread(fraks[i]);
			}
		}

		for(int i=0;i<threads.length;i++){
			if(threads[i]!=null)threads[i].start();
			else System.out.println(frak.getClass());
		}
	}
	public void update(){
		for(int i=0;i<fraks.length;i++){
			fraks[i].update();
			for(int y=0;y<fraks[i].pixels.length;y++){
				for(int x=0;x<fraks[i].pixels[y].length;x++){
					if(i==0)pixels[y][x]=fraks[i].pixels[y][x];
					if(i==1)pixels[y+fraks[0].pixels.length-1][x]=fraks[i].pixels[y][x];
					if(i==2)pixels[y][x+fraks[0].pixels[y].length-1]=fraks[i].pixels[y][x];
					if(i==3)pixels[y+fraks[0].pixels.length-1][x+fraks[0].pixels[y].length-1]=fraks[i].pixels[y][x];
				}
			}
		}
		for(int y=0;y<pixels.length;y++)for(int x=0;x<pixels[y].length;x++)if((Object)pixels[y][x]==null)pixels[y][x]=0xff00ff;
		
	}

}
