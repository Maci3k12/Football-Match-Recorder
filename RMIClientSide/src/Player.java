import java.io.Serializable;

public class Player implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public String surname;
	public int number_of_gols;
	public int number_of_fauls;
	public int number_of_yell_cards;
	public boolean red_card;
	
	public Player(String name, String surname, int number_of_gols, int number_of_fauls, int number_of_yell_cards,
			boolean red_card) {
		super();
		this.name = name;
		this.surname = surname;
		this.number_of_gols = number_of_gols;
		this.number_of_fauls = number_of_fauls;
		this.number_of_yell_cards = number_of_yell_cards;
		this.red_card = red_card;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getNumber_of_gols() {
		return number_of_gols;
	}

	@Override
	public String toString() {
		return "Player [Player= " + name + " " + surname + ", Goals=" + number_of_gols
				+ ", Faulss= " + number_of_fauls + ", Yellow Cards=" + number_of_yell_cards
				+ ", red_card=" + red_card + "]";
	}

	public void setNumber_of_gols(int number_of_gols) {
		this.number_of_gols = number_of_gols;
	}

	public int getNumber_of_fauls() {
		return number_of_fauls;
	}

	public void setNumber_of_fauls(int number_of_fauls) {
		this.number_of_fauls = number_of_fauls;
	}

	public int getNumber_of_yell_cards() {
		return number_of_yell_cards;
	}

	public void setNumber_of_yell_cards(int number_of_yell_cards) {
		this.number_of_yell_cards = number_of_yell_cards;
	}

	public boolean isRed_card() {
		return red_card;
	}

	public void setRed_card(boolean red_card) {
		this.red_card = red_card;
	}
}
