    import java.rmi.*;   
     
    public class FootballServer {
    	   public static void main (String[] argv) {
    		   try {
    			   Football Hello = new Football();			   		   
    			   Naming.rebind("rmi://localhost/ABC", Hello);
    			   System.out.println("Serwer jest gotowy");
    			   }catch (Exception e) {
    				   System.out.println("Blad serwera:  " + e);
    				}
    		   }
    }