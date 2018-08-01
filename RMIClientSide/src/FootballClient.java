import java.rmi.*;
import java.util.Scanner;    
public class FootballClient {

	public static void main (String[] args) {
    		Football_Interface hello;
    		try {
    			hello = (Football_Interface)Naming.lookup("rmi://localhost/ABC"); 
    			System.out.println("Polaczono z serwerem");
    			}
    		catch (Exception e) {
    				System.out.println("HelloClient exception: " + e);
    		}
    		}
    }