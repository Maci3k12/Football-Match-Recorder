import java.sql.Time;

public class Goal {
	public Time actual_time;
	public Player shooter;
	public Goal(Time actual_time, Player shooter) {
		super();
		this.actual_time = actual_time;
		this.shooter = shooter;
	}
	public Time getActual_time() {
		return actual_time;
	}
	public void setActual_time(Time actual_time) {
		this.actual_time = actual_time;
	}
	public Player getShooter() {
		return shooter;
	}
	public void setShooter(Player shooter) {
		this.shooter = shooter;
	}
}
