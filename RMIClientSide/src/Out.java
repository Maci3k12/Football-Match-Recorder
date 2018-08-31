import java.io.Serializable;

public class Out implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Timee actual_time;
	public Player performer;
	public String Team;
	public Timee getActual_time() {
		return actual_time;
	}
	public void setActual_time(Timee actual_time) {
		this.actual_time = actual_time;
	}
	public Player getPerformer() {
		return performer;
	}
	public void setPerformer(Player performer) {
		this.performer = performer;
	}
	public String getTeam() {
		return Team;
	}
	public void setTeam(String team) {
		Team = team;
	}
	public Out(Timee actual_time, Player performer, String team) {
		super();
		this.actual_time = actual_time;
		this.performer = performer;
		Team = team;
	}
	@Override
	public String toString() { 
		return "Aut: "+actual_time.view_time()+ " Aut wykonany przez: " +performer.name + " " + performer.surname ;
	}
	
	
}
