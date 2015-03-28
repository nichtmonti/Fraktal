package Util;

public interface Expression {
  public complex eval();
  public void setVar(complex x, String name);
  public String toString(); 
}
