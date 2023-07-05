package java0913;

public class Linkedlist {
	
	Data head;
	public Linkedlist() {
		head = null;
	}
	
	Data findMiddleNode(int p) {
		Data m = head;
		 while (m.next != null) {
	         if(m.next.point<= p && p <= m.point) 
	        	 return m;
	         else
	        	 m = m.next;
	      } 
		return m;
	}
	
	Data findLastNode() {
		if (head == null) return null ; 
	    Data f = head ; 
	    while (f.next != null) {
	       f = f.next ; 
	       } 
	    return f ; 
	}  
	
	void insert (String n, int p) {
		Data d = head;
		Data f = findLastNode();
		Data i = new Data(n,p);
		if(head == null)
			head  = i;
		else if(head.next == null) {
			if(head.point <= p) {
				head = i;
				head.next = d;
			}
			else 
				head.next = i;
		}
		else {
			if(head.point <= p) {
				head = i;
				head.next = d;
			}
			else if(p <= f.point)
				f.next = i;
			else {
				Data m = findMiddleNode(p);
				i.next = m.next;
				m.next = i;
			   }
			}
		}  
	
	Data SearchString(String n) {
		Data s = head;
		while(s.next !=null){
			if(s.name.equals(n)) return s;
			else s = s.next;
		}
		if(!s.name.equals(n)) return null;
		return s;
	}
	
	Data deleteLastNode() {
	    Data f = head ;
	    Data t = head.next;
	    while (t.next != null) {
	       f=f.next; 
	       t=t.next;
	       }
	    f.next =null;
	    return f ; 
	}  
	
	void deleteSearchNode(String n) {
		Data s = SearchString(n);
		Data d = head;
		while(d.next!=null) {
			if(d.next.name.equals(s.name))
				break;
			else 
				d=d.next;
			}
			d.next = s.next;
			s.next=null;
			s=null;
	}
	
	void delete(String n) {
		Data d = head;
		Data f = findLastNode();
		Data s = SearchString(n);
		if(head == null) return;
		else if(n == null) {
			System.out.println("Wrong name");
			return;
		}
		else if(s == null) {
			System.out.println("Wrong name");
			return;
		}
		else if(head.next == null) {
			head = null;
			return;
		}
		else {
			if(d.name.equals(n)) head =d.next;
			else if(s == f) deleteLastNode();
			else {
				deleteSearchNode(n);
			}			
		}		
	}
	
	void printAllNodes ( ) {
	    Data d = head ; 
	    if (d == null) System.out.println("No Nodes ") ; 
	    else {
	       while(d!=null) {
	    	   	System.out.println("(" + d.name + ", " + d.point + ")") ;
	            d = d.next ; 
	          }
	       }
	}
}
