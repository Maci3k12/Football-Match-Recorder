import java.io.Serializable;

public class Timee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int minutes;
	public int seconds;
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	public Timee(int minutes, int seconds) {
		super();
		this.minutes = minutes;
		this.seconds = seconds;
	}
	public String view_time() {
		String temp_string = "";
		if(minutes<10 && seconds <= 10) {
		temp_string = "Czas : "+"0"+minutes + ":" + seconds;}
		else if(minutes<10 && seconds<10) {temp_string ="Czas : "+"0"+minutes + ":" +"0" +seconds; }
		else if(minutes<=10 && seconds<10) {temp_string ="Czas : "+ minutes + ":" + "0"+seconds;}
		else {temp_string ="Czas : "+ minutes + ":" + seconds;}
		return temp_string;
	}
	public int convert_to_seconds(int minutes,int seconds) {
		int temp = (60 * minutes) + seconds;
		return temp;
	}

}
