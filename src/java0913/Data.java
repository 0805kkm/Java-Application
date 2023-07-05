package java0913;

public class Data {
	   String name ; 
	   int point ; 
	   Data next ;
	   public Data (String n, int p) {
	      this.name = n ; this.point = p ; 
	      next = null ; 
	   } 
	   public Data () {
	      this( null, 0) ; 
	   } 
	} 
