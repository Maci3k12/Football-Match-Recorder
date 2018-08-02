import java.rmi.Remote;
import java.rmi.RemoteException;
     
public interface Football_Interface extends Remote {
	public void start_match() throws RemoteException;
}