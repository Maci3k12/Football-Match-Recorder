import java.rmi.*;  
public class FootballClient {

	public static void main (String[] args) {
    		Football_Interface hello;
    		try {
    			hello = (Football_Interface)Naming.lookup("rmi://localhost/ABC"); 
    			System.out.println("Polaczono z serwerem");
    			hello.start_match();
    			}
    		catch (Exception e) {
    				System.out.println("HelloClient exception: " + e);
    		}
    		}
    }	
