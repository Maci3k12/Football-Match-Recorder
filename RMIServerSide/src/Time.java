
public class Time {
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
	public Time(int minutes, int seconds) {
		super();
		this.minutes = minutes;
		this.seconds = seconds;
	}
	public String view_time() { 
		return minutes + ":" + seconds;
	}
	public int convert_to_seconds(int minutes,int seconds) {
		int temp = (60 * minutes) + seconds;
		return temp;
	}

}
