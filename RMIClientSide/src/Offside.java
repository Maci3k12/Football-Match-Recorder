import java.io.Serializable;

public class Offside implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Timee actual_time;
	public Player player_on_offside;
	public Offside(Timee actual_time, Player player_on_offside) {
		super();
		this.actual_time = actual_time;
		this.player_on_offside = player_on_offside;
	}
	public Timee getActual_time() {
		return actual_time;
	}
	public void setActual_time(Timee actual_time) {
		this.actual_time = actual_time;
	}
	public Player getPlayer_on_offside() {
		return player_on_offside;
	}
	public void setPlayer_on_offside(Player player_on_offside) {
		this.player_on_offside = player_on_offside;
	}
	@Override
	public String toString() {
		return "Spalony: Czas: "+ actual_time.view_time()+" Zawodnik na spalonym" + player_on_offside.name +" "+player_on_offside.surname;
	}
	
}
