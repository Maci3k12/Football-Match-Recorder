import java.io.Serializable;

public class CornerKick implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Timee actual_time;
	public Player kicker;
	public String Team;
	public Timee getActual_time() {
		return actual_time;
	}
	public void setActual_time(Timee actual_time) {
		this.actual_time = actual_time;
	}
	public Player getKicker() {
		return kicker;
	}
	public void setKicker(Player kicker) {
		this.kicker = kicker;
	}
	public String getTeam() {
		return Team;
	}
	public void setTeam(String team) {
		Team = team;
	}
	public CornerKick(Timee actual_time, Player kicker, String team) {
		super();
		this.actual_time = actual_time;
		this.kicker = kicker;
		Team = team;
	}
	@Override
	public String toString() {
		return  "Rzut rozny " +actual_time.view_time() + " Rzut ro¿ny wykonany przez " + kicker.name + " " + kicker.surname;
	}
	
}
