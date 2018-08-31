import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.print.attribute.Size2DSyntax;

import org.w3c.dom.css.Counter;  
public class FootballClient {

	public static void main (String[] args) {
    		Football_Interface hello;
    		try {
    			hello = (Football_Interface)Naming.lookup("rmi://localhost/ABC"); 
    			Date date = new Date();
    			System.out.println("Polaczono z serwerem");
    			boolean running = true;
    			while(running) {
    			System.out.println("Dokonaj wyboru\n[1]. Rejestracja przebiegu meczu\n[2]. Filtracja \n[3]. Zakonczenie programu");
    			Scanner scanner = new Scanner(System.in);
    			Integer choice = scanner.nextInt();

    			
    			scanner.nextLine();
    			switch (choice) {


				case 1:
	    			Match match = start_match();
	    			String file_name = toFilename(match.team_1_name, match.team_2_name, date);
	    			hello.serialize_a_match(match, file_name);
					break;
				case 2:
					boolean running_filtration = true;
					System.out.println("Wybrales opcje wyswietlania statystyk");
					File[] files = hello.find_matches();
					show_files(files);
					int temp_choice = scan_choice();
					String path = files[temp_choice-1].getName();
					while(running_filtration) {
					System.out.println("Wybierz rodzaj filtracji:\n[1]. Filtracja po druzynie\n[2]. Filtracja po zawodniku\n [3]. Wstecz\n");
					int choice_type = scan_choice();
					switch (choice_type) {
					case 1:
						System.out.println("Wybierz druzyne zawodnika:");
						System.out.println(hello.show_teams(path));
						int team_choice = scan_choice();
						System.out.println("Jakie statystyki druzyny "+hello.show_team_name(1, path)+" chcesz wyswietli� \n 1.Bramki\n 2.Faule\n 3.Spalone\n 4.Rzuty Karne\n 5.Auty\n 6.Rzuty rozne\n");
						int type_choice = scan_choice();
						System.out.println(hello.result_of_team_filtration(team_choice,type_choice, path));
						break;
					case 2:
						System.out.println("Wybierz druzyne zawodnika:"); 
						System.out.println(hello.show_teams(path));	
						int team_choice_2 = scan_choice();							
						ArrayList<Player> players_list = hello.show_player_list(team_choice_2,files[temp_choice-1].getName());
						System.out.println("Wybierz zawodnika:");
						print_team(players_list);
						int player_choice = scan_choice();
						System.out.println("Jakie statystyki gracza chcesz wyswietli�: \n 1.Bramki\n 2.Faule\n 3.Spalone\n 4.Rzuty Karne\n 5.Auty\n 6.Rzuty rozne ");
						int type_of_filtration = scan_choice();
						System.out.println(hello.result_of_player_filtration(team_choice_2, player_choice, type_of_filtration, path));
						break;
					case 3:
						System.out.println("Koniec filtracji");
						running_filtration = false;
					default:
						break;
					}
					}
					break;
				case 3:
					//koniec
					running = false;
					break;
				default:
					break;
    			}
				}

    			}
    		catch (Exception e) {
    				System.out.println("Blad klienta  " + e);
    		}
    		
    		}
	@SuppressWarnings("resource")
	public static Match start_match() throws  FileNotFoundException, RemoteException{
		//inicjalizacja scannera i list gdzie beda gromadzone zdarzenia pilkarskie.
		Scanner s = new Scanner(System.in);

		// Data rejestracji meczu, uzyta 
		Date date = new Date();
		
		//team_1 - gospodarze.
		ArrayList<Player> team_1_players_list = new ArrayList<Player>();//lista graczy gospodarzy
		ArrayList<Player> team_1_bench_warmers_list = new ArrayList<Player>();
		ArrayList<Goal>   team_1_goal_list = new ArrayList<Goal>();
		ArrayList<Foul>   team_1_foul_list = new ArrayList<Foul>();
		ArrayList<Offside> team_1_offside_list = new ArrayList<Offside>();
		ArrayList<PenaltyKick> team_1_penaltykick_list = new ArrayList<PenaltyKick>();
		ArrayList<CornerKick> team_1_cornerkick_list = new ArrayList<CornerKick>();
		ArrayList<Out> team_1_out_list = new ArrayList<Out>();
		
		//team_2 - goscie.
		ArrayList<Player> team_2_players_list = new ArrayList<Player>();
		ArrayList<Player> team_2_bench_warmers_list = new ArrayList<Player>();
		ArrayList<Goal>   team_2_goal_list = new ArrayList<Goal>();
		ArrayList<Foul>   team_2_foul_list = new ArrayList<Foul>();
		ArrayList<Offside> team_2_offside_list = new ArrayList<Offside>();
		ArrayList<PenaltyKick> team_2_penaltykick_list = new ArrayList<PenaltyKick>();
		ArrayList<CornerKick> team_2_cornerkick_list = new ArrayList<CornerKick>();
		ArrayList<Out> team_2_out_list = new ArrayList<Out>();
		
		//Glowna czesc rejestracji meczy


//			String team_1_name = "wisla4";
//			String team_2_name = "cracovia";
//			Player temp_player = new Player("Maciej", "Pacek", 0, 0, 0, false);
//			team_1_players_list.add(temp_player);	
//			Player temp_player1 = new Player("Maciej", "Maciej11zolta", 0, 0, 1, false);
//			team_1_players_list.add(temp_player1);
//			Player temp_player2 = new Player("Maciej", "Maciej2", 0, 0, 0, false);
//			team_2_players_list.add(temp_player2);	
//			Player temp_player3 = new Player("Maciej", "Maciej3", 0, 0, 0, false);
//			team_1_players_list.add(temp_player3);
//			Player temp_player4 = new Player("Maciej", "Maciej4", 0, 0, 0, false);
//			team_2_players_list.add(temp_player3);			
//			Player temp_player5 = new Player("Maciej", "Maciej5", 0, 0, 0, false);
//			team_1_players_list.add(temp_player3);			
//			Player temp_player6 = new Player("Maciej", "Maciej6", 0, 0, 0, false);
//			team_2_players_list.add(temp_player3);
//		// powitanie
//			String file_name = toFilename(team_1_name, team_2_name, date);
//			PrintWriter writer = new PrintWriter(file_name+".txt");
//			writer.flush();
//			writer.write("Start Meczu");
//			writer.println();
//			writer.flush();
		System.out.println("Witaj w systemie rejestracji meczu pilkarskiego!");
		System.out.println("Ile zawodnikow bedzie w druzynie");
		// skan liczby graczy w druzynie
		String number_of_players_str = s.next();
		int number_of_players = Integer.parseInt(number_of_players_str);
		s.nextLine();
		System.out.println("Ile bedzie zawodnikow rezerwowych: ");
		String number_of_bench_str = s.next();
		int number_of_bench = Integer.parseInt(number_of_bench_str);
		s.nextLine();
		System.out.println("Podaj nazwe druzyny gospodarzy:");
		String team_1_name = s.next();
		s.nextLine();
		System.out.println("Druzyna gospodarzy "+ team_1_name);
		System.out.println("Podaj nazwe druzyny gosci");
		String team_2_name = s.next();
		s.nextLine();
		System.out.println("Druzyna gosci "+ team_2_name);
		System.out.println("Prosze wprowadzic kolejno imie, nazwisko oraz czy zadwodnik posiada zolta kartke z poprzednich meczy");
		team_1_players_list = add_player(number_of_players);
		System.out.println("A teraz druzyna gosci: ");
		team_2_players_list = add_player(number_of_players);
		if(number_of_bench > 0 ) {
			System.out.println("Podaj zawodnikow rezerwowych , kolejno imie, nazwisko oraz czy zadwodnik posiada zolta kartke z poprzednich meczy");
			team_1_bench_warmers_list = add_player(number_of_bench);
			System.out.println("Teraz druzyna gosci: ");
			team_2_bench_warmers_list = add_player(number_of_bench);
		}
			String file_name = toFilename(team_1_name, team_2_name, date);
			PrintWriter writer = new PrintWriter(file_name+".txt");
			writer.flush();
			writer.write("Start Meczu");
			writer.println();
			writer.flush();
		boolean main_loop = true;
		Match match_to_save = new Match(team_1_name, team_2_name, team_1_players_list, team_1_goal_list, team_1_foul_list, team_1_offside_list, team_1_penaltykick_list, team_1_cornerkick_list, team_1_out_list, team_2_players_list, team_2_goal_list, team_2_foul_list, team_2_offside_list, team_2_penaltykick_list, team_2_cornerkick_list, team_2_out_list);
		while(main_loop) {// Glowne elementy dodawania zdarzen
			System.out.println(actual_score(team_1_name, team_2_name, team_1_goal_list, team_2_goal_list)+"Jakie zdarzenie chcesz zarejestrowa�:\n 1.Bramka\n 2.Faul\n 3.Spalony\n 4.Rzut Karny\n 5.Aut\n 6.Rzut rozny\n 7.Zmiana 8.Koniec pierwszej polowy\n 9.Koniec Meczu");
			String choice_str = s.next();
			int choice = Integer.parseInt(choice_str);
			s.nextLine();
//			System.out.println("Wybierz Druzyne:\n[1] "+team_1_name +"\n[2] "+team_2_name);
//			String team_choice_str = s.next();
//			s.nextLine();
			switch (choice) {
			case 1:
				System.out.println("Wybierz Druzyne:\n[1] "+team_1_name +"\n[2] "+team_2_name);
				String team_choice_str = s.next();
				s.nextLine();
				Timee temp_time = add_time();
				System.out.println("Wybierz gracza z duzyny " +team_choice_str);
				if (Integer.parseInt(team_choice_str) ==1) {
					print_team(team_1_players_list);
					String player_choice_str = s.next();
					int player_choice = Integer.parseInt(player_choice_str);
					add_goal(temp_time,team_1_goal_list, team_1_players_list, player_choice);	
					System.out.println("Bramka dodana!");
					writer.flush();
					System.out.println(team_1_goal_list.get(team_1_goal_list.size()-1).toString());
					writer.write(team_1_goal_list.get(team_1_goal_list.size()-1).toString());
					writer.println();
					writer.flush();
				}else if (Integer.parseInt(team_choice_str) == 2) {
					print_team(team_2_players_list);
					String player_choice_str = s.next();
					int player_choice = Integer.parseInt(player_choice_str);
					add_goal(temp_time,team_2_goal_list, team_2_players_list, player_choice);
					System.out.println("Bramka dodana!");
					writer.flush();
					System.out.println(team_2_goal_list.get(team_2_goal_list.size()-1).toString());
					writer.write(team_2_goal_list.get(team_2_goal_list.size()-1).toString());
					System.out.println(team_2_goal_list.size()-1);
					writer.println();
					writer.flush();
				}else {
					System.out.println("Zly wybor");
				}

				break;
			case 2:	
				System.out.println("Wybierz Druzyne:\n[1] "+team_1_name +"\n[2] "+team_2_name);
				String team_choice_str1 = s.next();
				s.nextLine();
				Timee temp_time1 = add_time();
				System.out.println("Wybierz gracza z duzyny "+team_choice_str1 +" kt�ry spowodowal faul");		
				if (Integer.parseInt(team_choice_str1) ==1) {
					print_team(team_1_players_list);
					String player_choice_str = s.next();
					int player_choice = Integer.parseInt(player_choice_str);
					add_foul(temp_time1,team_1_foul_list, team_1_players_list, player_choice);
					writer.flush();
					writer.write(team_1_foul_list.get(team_1_foul_list.size()-1).toString());
					writer.println();
					writer.flush();
					System.out.println("Faul dodany!");
				}else if (Integer.parseInt(team_choice_str1) == 2) {
					print_team(team_2_players_list);
					String player_choice_str = s.next();
					int player_choice = Integer.parseInt(player_choice_str);
					add_foul(temp_time1,team_2_foul_list, team_2_players_list, player_choice);
					writer.flush();
					writer.write(team_2_foul_list.get(team_2_foul_list.size()-1).toString());
					writer.println();
					writer.flush();
					System.out.println("Faul dodany!");
				}else {
					System.out.println("Zly wybor");
				}
				break;
			case 3:
				System.out.println("Wybierz Druzyne:\n[1] "+team_1_name +"\n[2] "+team_2_name);
				String team_choice_str2 = s.next();
				s.nextLine();
				System.out.println("Wybierz gracza z duzyny "+team_choice_str2 +" kt�ry by� na spalonym");
				if(Integer.parseInt(team_choice_str2) == 1) {
					print_team(team_1_players_list);
					String player_choice_str = s.next();
					int player_choice = Integer.parseInt(player_choice_str);
					add_offside(team_1_offside_list, team_1_players_list, player_choice);
					writer.flush();
					writer.write(team_1_offside_list.get(team_1_offside_list.size()-1).toString());
					writer.println();
					writer.flush();
					System.out.println("Spalony dodany!");
				}else if(Integer.parseInt(team_choice_str2) == 2) {
					print_team(team_2_players_list);
					String player_choice_str = s.next();
					int player_choice = Integer.parseInt(player_choice_str);
					add_offside(team_2_offside_list, team_2_players_list, player_choice);
					writer.flush();
					writer.write(team_2_offside_list.get(team_2_offside_list.size()-1).toString());
					writer.println();
					writer.flush();
					System.out.println("Spalony dodany!");
				}else {
					System.out.println("Zly wybor");
				}
				break;
			case 4:
				System.out.println("Wybierz druzyne ktora bedzie strzelac karnego:\n[1] "+team_1_name +"\n[2] "+team_2_name);
				String team_choice_str3 = s.next();
				s.nextLine();
				System.out.println("Wybierz gracza z duzyny "+team_choice_str3 +" kt�ry strzela karnego");
				if(Integer.parseInt(team_choice_str3)== 1 ) {
					print_team(team_1_players_list);
					String player_choice_str1 = s.next();
					int player_choice1 = Integer.parseInt(player_choice_str1);
					System.out.println("Wybierz gracza ktory spowodowal karnego");
					print_team(team_2_players_list);
					String player_choice_str2 = s.next();
					int player_choice2 = Integer.parseInt(player_choice_str2);
					add_penalty_kick(team_1_goal_list, team_1_penaltykick_list, team_2_foul_list, team_1_players_list, team_2_players_list, player_choice1, player_choice2);
					writer.flush();
					writer.write(team_1_penaltykick_list.get(team_1_penaltykick_list.size()-1).toString());
					writer.println();
					writer.flush();
				}else if(Integer.parseInt(team_choice_str3)== 2 ) {
					print_team(team_2_players_list);
					String player_choice_str1 = s.next();
					int player_choice1 = Integer.parseInt(player_choice_str1);
					System.out.println("Wybierz gracza ktory spowodowal karnego");
					print_team(team_1_players_list);
					String player_choice_str2 = s.next();
					int player_choice2 = Integer.parseInt(player_choice_str2);
					add_penalty_kick(team_2_goal_list, team_2_penaltykick_list, team_1_foul_list, team_2_players_list, team_1_players_list, player_choice1, player_choice2);
					writer.flush();
					writer.write(team_2_penaltykick_list.get(team_2_penaltykick_list.size()-1).toString());
					writer.println();
					writer.flush();
				}else {System.out.println("Zly wybor");
										break;}
				System.out.println("Rzut Karny odnotowany w systemie!");
				break;
			case 5:
				System.out.println("Wybierz druzyne ktora bedzie wykonywac aut:\n[1] "+team_1_name +"\n[2] "+team_2_name);
				String team_choice_str4 = s.next();
				s.nextLine();
				System.out.println("Wybierz gracza z duzyny "+team_choice_str4 +" bedzie wykonywal rzut z autu");
				if(Integer.parseInt(team_choice_str4)== 1 ) {
					print_team(team_1_players_list);
					String player_choice_str = s.next();
					int player_choice = Integer.parseInt(player_choice_str);
					add_out(team_1_out_list, team_1_players_list, team_1_name, player_choice);
					writer.flush();
					writer.write(team_1_out_list.get(team_1_out_list.size()-1).toString());
					writer.println();
					writer.flush();
				}
				else if(Integer.parseInt(team_choice_str4)== 2 ) {
					print_team(team_2_players_list);
					String player_choice_str = s.next();
					int player_choice = Integer.parseInt(player_choice_str);			
					add_out(team_2_out_list, team_2_players_list, team_2_name, player_choice);
					writer.flush();
					writer.write(team_2_out_list.get(team_2_out_list.size()-1).toString());
					writer.println();
					writer.flush();
				}
				System.out.println("Aut odntowoany!");
				break;
			case 6:
				System.out.println("Wybierz druzyne ktora bedzie wykonywac rozny:\n[1] "+team_1_name +"\n[2] "+team_2_name);
				String team_choice_str5 = s.next();
				s.nextLine();
				System.out.println("Wybierz gracza z duzyny "+team_choice_str5 +" bedzie wykonywal roznego");
				if(Integer.parseInt(team_choice_str5)== 1 ) {
						print_team(team_1_players_list);
						String player_choice_str = s.next();
						int player_choice = Integer.parseInt(player_choice_str);
						add_corner_kick(team_1_cornerkick_list, team_1_players_list, player_choice, team_1_name);
						writer.flush();
						writer.write(team_1_cornerkick_list.get(team_1_cornerkick_list.size()-1).toString());
						writer.println();
						writer.flush();
				}
				else if(Integer.parseInt(team_choice_str5)== 2 ) {
						print_team(team_2_players_list);
						String player_choice_str = s.next();
						int player_choice = Integer.parseInt(player_choice_str);
						add_corner_kick(team_2_cornerkick_list, team_2_players_list, player_choice, team_2_name);
						writer.flush();
						writer.write(team_2_cornerkick_list.get(team_2_cornerkick_list.size()-1).toString());
						writer.println();
						writer.flush();
						}
				System.out.println("Rozny dodany!");
				break;
			case 7:
				System.out.println("Wybierz druzyne w ktorej nastapila zmiana:\n[1] "+team_1_name +"\n[2] "+team_2_name);
				String team_choice_str6 = s.next();
				s.nextLine();
				if(Integer.parseInt(team_choice_str6)== 1 ) {
					if(team_1_bench_warmers_list.isEmpty()) {
						System.out.println("Brak zawodnikow rezerwowych");
					}
					else {
						change_player(s, team_1_players_list, team_1_bench_warmers_list,writer);
						System.out.println("\n Zmiana wykonana");
					}
				}
				else if(Integer.parseInt(team_choice_str6)== 2 ) {
					if(team_2_bench_warmers_list.isEmpty()) {
						System.out.println("Brak zawodnikow rezerwowych");
					}
					else {
						change_player(s, team_2_players_list, team_2_bench_warmers_list,writer);
						System.out.println("\n Zmiana wykonana");
					}
				}
				break;
			case 8:// koniec meczu wyjscie z break, serializacja przebiegu meczu do pliku
				temp_time = add_time();
				writer.flush();
				writer.write("Koniec pierwszej polowy " + temp_time.view_time() );
				writer.println();
				writer.flush();
				System.out.println("Koniec pierwszej polowy, rozpocznij drug� polowe");
				break;
			case 9:// koniec meczu wyjscie z break, serializacja przebiegu meczu do pliku
				temp_time = add_time();
//				match_to_save =  new Match(team_1_name, team_2_name, team_1_players_list, team_1_goal_list, team_1_foul_list, team_1_offside_list, team_1_penaltykick_list, team_1_cornerkick_list, team_1_out_list, team_2_players_list, team_2_goal_list, team_2_foul_list, team_2_offside_list, team_2_penaltykick_list, team_2_cornerkick_list, team_2_out_list);
//				serialize(match_to_save, toFilename(team_1_name, team_2_name, date)+".mth");
//				System.out.println("Mecz zostal zarhiwizowany pod nazwa pliku '"+toFilename(team_1_name, team_2_name, date)+".mth'");
				writer.flush();
				writer.write("Koniec meczu " + temp_time.view_time() );
				writer.println();
				writer.flush();
				main_loop = false;
				break;
			default:
				break;
			}
			match_to_save =  new Match(team_1_name, team_2_name, team_1_players_list, team_1_goal_list, team_1_foul_list, team_1_offside_list, team_1_penaltykick_list, team_1_cornerkick_list, team_1_out_list, team_2_players_list, team_2_goal_list, team_2_foul_list, team_2_offside_list, team_2_penaltykick_list, team_2_cornerkick_list, team_2_out_list);
		}
		
		System.out.println("Koniec rejestracji meczu");
		return match_to_save;

	}
	// metoda dodaj�ca graczy poszczegolnej druzyny 
	
	public static ArrayList<Player> add_player(int number_of_players) {
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
	public static void change_player(Scanner s,ArrayList<Player> players_list,ArrayList<Player> bench_list,PrintWriter writer) {
		Timee tmp_time = add_time();
		System.out.println("Wybierz zawodnika schodzacego z boiska: \n");
		print_team(players_list);
		String player_choice_str = s.next();
		int player_choice = Integer.parseInt(player_choice_str);
		System.out.println("Wybierz zawodnika wchodzacego na boisko: \n");
		print_team(bench_list);
		String player_choice_str2 = s.next();
		int player_choice2 = Integer.parseInt(player_choice_str2);
		Player temp_player1 = bench_list.get(player_choice2-1);
		Player temp_player2 = players_list.get(player_choice-1);
		bench_list.set(player_choice2-1, players_list.get(player_choice-1));
		players_list.set(player_choice-1, temp_player1);
		System.out.println("Zamiana wykonana");
		writer.flush();
		writer.write("Zmiana nastapila Czas : " +tmp_time.view_time()+ " Schodzi zawodnik : "+temp_player1.name+" "+temp_player1.surname +" Wchodzi zawodnik" +temp_player2.name+" "+temp_player2.surname );
		writer.println();
		writer.flush();
	
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
	public static void print_team(ArrayList<Player> list) {
		int counter = 0;
		for (Player player : list) {
			if(player.red_card == false) {
			System.out.println("["+(counter+1)+"]: "+ player.getName()+" "+player.getSurname());
			}
			counter = counter +1;
		}	
	}//Metoda dodajaca czas do zdarzenia
	public static Timee add_time() {
		Scanner s = new Scanner(System.in);
		System.out.println("Podaj czas zdarzenia:\n Minuta meczu:");
		String minute_str = s.next();
		s.nextLine();
		System.out.println("Sekunda Meczu: ");
		String second_str = s.next();
		int minute =  Integer.parseInt(minute_str);
		int second =  Integer.parseInt(second_str);
		s.nextLine();
		Timee temp_time = new Timee(minute, second);
		return temp_time;
	}
	public static void add_goal(Timee temp_timee,ArrayList<Goal> goals,ArrayList<Player> players_list,int number) {
//		Timee temp_timee = add_time();
		players_list.get(number-1).setNumber_of_gols(players_list.get(number-1).getNumber_of_gols()+1);
		Player temp_player = players_list.get(number-1);
		Goal temp_goal = new Goal(temp_timee, temp_player);
		goals.add(temp_goal);	
	}
	public static void add_foul(Timee temp_timee,ArrayList<Foul> fouls, ArrayList<Player> players_list,int number) {

		Scanner s = new Scanner(System.in);
//		Timee temp_timee = add_time();
		System.out.println("Podaj typ kartki:\n[1]: Zolta\n[2]: Czerwona\n[3]: Bez kartki");
		String type_of_card_str = s.next();
		s.nextLine();
		int type_of_card = Integer.parseInt(type_of_card_str);
		if(type_of_card == 1) {
			if(players_list.get(number-1).getNumber_of_yell_cards() == 1 ) {
				System.out.println("Zawodnik posiada juz jedna zolta kartke, otrzymuje czerwona kartke.");
				players_list.get(number-1).setRed_card(true);
				players_list.get(number-1).setNumber_of_yell_cards(players_list.get(number-1).number_of_yell_cards+1);
				players_list.get(number-1).setNumber_of_fauls(players_list.get(number-1).number_of_fauls+1);
				Foul temp_foul = new Foul(players_list.get(number-1), temp_timee, true, true);
				fouls.add(temp_foul);
				
			}else if (players_list.get(number-1).getNumber_of_yell_cards() == 0) {
				players_list.get(number-1).setNumber_of_yell_cards(players_list.get(number-1).number_of_yell_cards+1);
				players_list.get(number-1).setNumber_of_fauls(players_list.get(number-1).number_of_fauls+1);
				Foul temp_foul = new Foul(players_list.get(number-1), temp_timee, true, false);
				fouls.add(temp_foul);
			}
		}else if (type_of_card == 2) {
			players_list.get(number-1).setRed_card(true);
			players_list.get(number-1).setNumber_of_fauls(players_list.get(number-1).number_of_fauls+1);
			Foul temp_foul = new Foul(players_list.get(number-1), temp_timee, false, true);
			fouls.add(temp_foul);
		}else if(type_of_card == 3) {
			players_list.get(number-1).setNumber_of_fauls(players_list.get(number-1).number_of_fauls+1);
			Foul temp_foul = new Foul(players_list.get(number-1), temp_timee, false, false);
			fouls.add(temp_foul);
		}
	}
	public static void add_offside(ArrayList<Offside> offsides,ArrayList<Player> players_list,int number) {
		Timee temp_timee = add_time();
		Offside temp_offside = new Offside(temp_timee, players_list.get(number-1));
		offsides.add(temp_offside);
	}
	//team_1 druzyna ktora strzela karnego
	//team_2 druzyna ktora spowodowala karnego
	public static void add_penalty_kick(ArrayList<Goal>goals,ArrayList<PenaltyKick> penaltyKicks, ArrayList<Foul> fouls, ArrayList<Player> players_team1, ArrayList<Player> players_team2,int shooter,int couser) {
		Scanner s = new Scanner(System.in);
		Timee temp_timee = add_time();
		System.out.println("Podaj typ zdarzenia ktore spowodowalo karny:\n[1]. Faul\n[2].Zdarzenie inne np. reka");
		String type_of_couse_str = s.next();
		s.nextLine();
		System.out.println("Czy padla bramka po karnym?T-Tak/N-Nie");
		String is_scored=s.next().toLowerCase();
		s.nextLine();
		int type_of_couse = Integer.parseInt(type_of_couse_str);
		if(type_of_couse == 1 ) {
			add_foul(temp_timee,fouls, players_team2, couser);
			if(is_scored == "t") {
				PenaltyKick temp = new PenaltyKick(temp_timee,players_team1.get(shooter-1),players_team2.get(couser-1),true);
				add_goal(temp_timee,goals, players_team1, shooter);
				penaltyKicks.add(temp);
			}
			else if(is_scored == "n") {
				PenaltyKick temp = new PenaltyKick(temp_timee,players_team1.get(shooter-1),players_team2.get(couser-1),false);	
				penaltyKicks.add(temp);
			}
		}else if(type_of_couse == 2){
			if(is_scored == "t") {
				PenaltyKick temp = new PenaltyKick(temp_timee,players_team1.get(shooter-1),players_team2.get(couser-1),true);
				add_goal(temp_timee,goals, players_team1, shooter);
				penaltyKicks.add(temp);
				}
			else if(is_scored == "n") {
				PenaltyKick temp = new PenaltyKick(temp_timee,players_team1.get(shooter-1),players_team2.get(couser-1),false);	
				penaltyKicks.add(temp);
			}
		else {System.out.println("zly wybor");}		
	}
}
	public static void add_out(ArrayList<Out> outs,ArrayList<Player> players_list,String team,int number) {
		Timee temp_timee = add_time();
		Out temp_out = new Out(temp_timee, players_list.get(number-1), team);
		outs.add(temp_out);
	}
	public static void add_corner_kick(ArrayList<CornerKick> cornerKicks,ArrayList<Player> players_list,int number,String team_name) {
		Timee temp_timee = add_time();
		CornerKick temp_kick = new CornerKick(temp_timee, players_list.get(number-1), team_name);
		cornerKicks.add(temp_kick);
	}
	// metody po zakonczeniu meczu, oraz stosowane do zapisu przebiegu.
	public static String toFilename(String team1name,String team2name,Date date){
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
		return team1name +" - "+team2name + "-" +ft.format(date)+".mth";
	}
    public static File[] getFileList(String dirPath) {
        File dir = new File(dirPath);   

        File[] fileList = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".mth");
            }
        });
        return fileList; 
    }
    // filtracja
    
    
    public static void show_files(File[] file_list) {
    	System.out.println("Dostepne mecze:\nDokonaj wyboru ");
    	int counter = 1 ;
    	for (File file : file_list) {
			String[] splited_name = file.getName().split("-");
			String[] date = splited_name[2].split(".mth");
			System.out.println("["+counter+"]"+" Mecz ktory odbyl sie: "+date[0]+" Pomiedzy druzynami "+splited_name[0]+"a"+splited_name[1]);
			counter++;
		}
    } 
    public static int scan_choice() {
    	@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		String temp_str = s.next();
		int temp= Integer.parseInt(temp_str);
		s.nextLine();
		//s.close();
    	return temp;
    }
    public static String actual_score(String team1,String team2,ArrayList<Goal> goals ,ArrayList<Goal> goals2) {;	
    	return team1 +" : " +team2 +" " + goals.size() +" : " +goals2.size()+" \n" ;
    }
    }
