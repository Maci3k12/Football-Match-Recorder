import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public ArrayList<Player> player_list = new ArrayList<Player>();

}
