import java.io.Serializable;

public class Foul implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Player author;
	public Timee actual_time;
	public boolean yellow_card;
	public boolean red_card;
	public Foul(Player author, Timee actual_time, boolean yellow_card, boolean red_card) {
		super();
		this.author = author;
		this.actual_time = actual_time;
		this.yellow_card = yellow_card;
		this.red_card = red_card;
	}
	public Player getAuthor() {
		return author;
	}
	public void setAuthor(Player author) {
		this.author = author;
	}
	public Timee getActual_time() {
		return actual_time;
	}
	public void setActual_time(Timee actual_time) {
		this.actual_time = actual_time;
	}
	public boolean isYellow_card() {
		return yellow_card;
	}
	public void setYellow_card(boolean yellow_card) {
		this.yellow_card = yellow_card;
	}
	public boolean isRed_card() {
		return red_card;
	}
	public void setRed_card(boolean red_card) {
		this.red_card = red_card;
	}

	@Override
	public String toString() {
		String temp_strign = "";
		if(red_card == true) {
			temp_strign = "Faul: Czas mm/ss: "+actual_time.view_time()+" Faul dokonany przez: "+ author.name + " "+ author.surname +" otrzyma³ czerwona kartke";
		}else if(yellow_card == true && red_card == true) {
			temp_strign = "Faul: Czas mm/ss: "+actual_time.view_time()+" Faul dokonany przez: "+ author.name + " "+ author.surname +"otrzyma³ zolta kartke a w nastepstwie czerwon¹ kartke";
		}
		else if(yellow_card == true) {
			temp_strign = "Faul: Czas mm/ss: "+actual_time.view_time()+" Faul dokonany przez: "+ author.name + " "+ author.surname +"otrzyma³ zolta kartke";
		}else {
			temp_strign = "Faul: Czas mm/ss: "+actual_time.view_time()+" Faul dokonany przez: "+ author.name + " "+ author.surname;
		}
		return temp_strign;
	}
	
}
