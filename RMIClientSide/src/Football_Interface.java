import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
     
public interface Football_Interface extends Remote {
	public void start_match() throws RemoteException;
	public void serialize_a_match(Match match,String filename) throws RemoteException;
	public File[] find_matches() throws RemoteException;
	public String show_teams(String path) throws ClassNotFoundException, RemoteException;
	public ArrayList<Player> show_player_list(int choice,String path) throws ClassNotFoundException, RemoteException;
	public String show_team_name(int choice,String path) throws ClassNotFoundException, RemoteException;
	public String result_of_team_filtration(int team_choice,int type,String path)throws ClassNotFoundException, RemoteException;
	public String result_of_player_filtration(int team_choice,int player_choice,int type_of_filtration,String path ) throws ClassNotFoundException, RemoteException;
}