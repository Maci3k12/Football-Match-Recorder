import java.io.Serializable;

public class Goal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Timee actual_time;
	public Player shooter;
	public Goal(Timee actual_time, Player shooter) {
		super();
		this.actual_time = actual_time;
		this.shooter = shooter;
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
	@Override
	public String toString() {
		return "Gol : " +actual_time.view_time() + " Bramke zdobyl gracz: "+shooter.name+" "+shooter.surname;
	}
}
