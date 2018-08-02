import java.io.Serializable;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Football extends UnicastRemoteObject
             implements Football_Interface, Serializable {
	private static final long serialVersionUID = 1L;
	public Football () throws RemoteException {}
	public void start_match() throws RemoteException{
		//inicjalizacja scannera i list gdzie beda gromadzone zdarzenia pilkarskie.
		//team_1 - gospodarze.
		ArrayList<Player> team_1_players_list = new ArrayList<Player>();//lista graczy gospodarzy
		//ArrayList<Goal>   team_1_goal_list = new ArrayList<Goal>();
		//team_2 - goscie.
		ArrayList<Player> team_2_players_list = new ArrayList<Player>();
		Scanner s = new Scanner(System.in);
		// powitanie
		System.out.println("Witaj w systemie rejestracji meczu pilkarskiego!");
		System.out.println("Ile zawodnikow bedzie w druzynie");
		// skan liczby graczy w druzynie
		String number_of_players_str = s.next();
		int number_of_players = Integer.parseInt(number_of_players_str);
		s.nextLine();
		
		System.out.println("Podaj nazwe druzyny gospodarzy:");
		String team_1_name = s.next();
		s.nextLine();
		System.out.println("Druzyna gospodarzy "+ team_1_name);
		System.out.println("Podaj nazwe druzyny gosci");
		String team_2_name = s.next();
		s.nextLine();
		System.out.println("Druzyna gosciu "+ team_2_name);
		System.out.println("Prosze wprowadzic kolejno imie nazwisko oraz czy zadwodnik zdobyl 1 zolta w pop meczu");
		team_1_players_list = add_player(number_of_players);
		System.out.println("A teraz druzyna gosci: ");
		System.out.println("Potwierdz klikajac enter");
		team_2_players_list = add_player(number_of_players);
		print_players(team_1_players_list);
		print_players(team_2_players_list);
//		s.nextLine();
		
		

	}
	// metoda dodaj�ca graczy poszczegolnej druzyny 
	public ArrayList<Player> add_player(int number_of_players) {
		ArrayList<Player> temp_list = new ArrayList<Player>();
		Scanner s = new Scanner(System.in);
		for (int i = 0; i < number_of_players; i++) {
			System.out.println("Podaj Imie:");
			String temp_name = s.next();
			s.nextLine();
			System.out.println("Nazwisko");
			String temp_surname = s.next();
			s.nextLine();
			System.out.println("Ilosc posiadanych zoltych kartek");
			String temp_yellow_cards_str = s.next();
			s.nextLine();
			int temp_yellow_cards = Integer.parseInt(temp_yellow_cards_str);
			Player temp_player = new Player(temp_name, temp_surname, 0, 0, temp_yellow_cards, false);
			temp_list.add(temp_player);
		}
		return temp_list;
	}
	public void print_players(ArrayList<Player> list) {
		for (Player player : list) {
			System.out.println(player.toString());
		}
	}
	// pobieranie nazwy druzyny
	public String give_team_name() {
		Scanner s = new Scanner(System.in);
		String temp_team_name = s.nextLine();
		s.close();
		return temp_team_name;	
	}
	// wczytanie stringa
	public String get_string() {
		Scanner s = new Scanner(System.in);
		String temp_string = s.nextLine();
//		s.nextLine();
		s.close();
		
		
		return temp_string;
	}
	// wczytanie inta
	public int get_int() {
		Scanner s = new Scanner(System.in);
		String temp_string = s.nextLine();
		s.nextLine();
		s.close();
		int temp_int = Integer.parseInt(temp_string);
		return temp_int;
	}
}