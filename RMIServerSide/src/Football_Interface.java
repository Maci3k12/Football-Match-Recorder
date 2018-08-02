import java.rmi.*;
     
public interface Football_Interface extends Remote {
	public void start_match() throws RemoteException;
}