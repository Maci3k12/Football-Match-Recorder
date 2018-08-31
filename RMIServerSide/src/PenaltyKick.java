import java.io.Serializable;

public class PenaltyKick implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Timee actual_time;
	public Player shooter;
	public Player foul_couser;
	public boolean is_scored;
	public PenaltyKick(Timee actual_time, Player shooter, Player foul_couser, boolean is_scored) {
		super();
		this.actual_time = actual_time;
		this.shooter = shooter;
		this.foul_couser = foul_couser;
		this.is_scored = is_scored;
	}
	public Timee getActual_time() {
		return actual_time;
	}
	public void setActual_time(Timee actual_time) {
		this.actual_time = actual_time;
	}
	public Player getShooter() {
		return shooter;
	}
	public void setShooter(Player shooter) {
		this.shooter = shooter;
	}
	public Player getFoul_couser() {
		return foul_couser;
	}
	public void setFoul_couser(Player foul_shooter) {
		this.foul_couser = foul_shooter;
	}
	public boolean isIs_scored() {
		return is_scored;
	}
	public void setIs_scored(boolean is_scored) {
		this.is_scored = is_scored;
	}
	@Override
	public String toString() {
		String temp_string = "";
		if(is_scored == true) {
			temp_string = "Rzut Karny Czas: "+actual_time.view_time()+" Rzut karny po spowodowany przez: " +foul_couser.name + " " +foul_couser.surname + " Karnego strzalal i zdobyl bramke " + shooter.name + " " +shooter.surname;  
		}else {
			temp_string = "Rzut Karny Czas: "+actual_time.view_time()+" Rzut karny po spowodowany przez: " +foul_couser.name + " " +foul_couser.surname + " Karnego strzalal " + shooter.name + " " +shooter.surname;
		}
		return temp_string;
	}
}
