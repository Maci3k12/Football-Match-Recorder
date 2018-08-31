import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.rmi.*;
import java.rmi.server.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.w3c.dom.css.Counter;

import java.util.Date;

//
//
//
public class Football extends UnicastRemoteObject
             implements Football_Interface, Serializable {
	private static final long serialVersionUID = 1L;
	public Football () throws RemoteException {}
	@SuppressWarnings("resource")
	public void start_match() throws RemoteException, FileNotFoundException{
		//inicjalizacja scannera i list gdzie beda gromadzone zdarzenia pilkarskie.
		Scanner s = new Scanner(System.in);

		// Data rejestracji meczu, uzyta 
		Date date = new Date();
		
		//team_1 - gospodarze.
		ArrayList<Player> team_1_players_list = new ArrayList<Player>();//lista graczy gospodarzy
		ArrayList<Goal>   team_1_goal_list = new ArrayList<Goal>();
		ArrayList<Foul>   team_1_foul_list = new ArrayList<Foul>();
		ArrayList<Offside> team_1_offside_list = new ArrayList<Offside>();
		ArrayList<PenaltyKick> team_1_penaltykick_list = new ArrayList<PenaltyKick>();
		ArrayList<CornerKick> team_1_cornerkick_list = new ArrayList<CornerKick>();
		ArrayList<Out> team_1_out_list = new ArrayList<Out>();
		
		//team_2 - goscie.
		ArrayList<Player> team_2_players_list = new ArrayList<Player>();
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
		// powitanie
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
		team_2_players_list = add_player(number_of_players);
			String file_name = toFilename(team_1_name, team_2_name, date);
			PrintWriter writer = new PrintWriter(file_name+".txt");
			writer.flush();
			writer.write("Start Meczu");
			writer.println();
			writer.flush();
		boolean main_loop = true;
		while(main_loop) {// Glowne elementy dodawania zdarzen
			System.out.println("Jakie zdarzenie chcesz zarejestrowa�:\n 1.Bramka\n 2.Faul\n 3.Spalony\n 4.Rzut Karny\n 5.Aut\n 6.Rzut rozny\n 7.Koniec pierwszej polowy\n 8.Koniec Meczu");
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
					writer.write(team_2_offside_list.get(team_2_offside_list.size()-1).toString());
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
			case 7:// koniec meczu wyjscie z break, serializacja przebiegu meczu do pliku
				temp_time = add_time();
				writer.flush();
				writer.write("Koniec pierwszej polowy " + temp_time.view_time() );
				writer.println();
				writer.flush();
				System.out.println("Koniec pierwszej polowy, rozpocznij drug� polowe");
				break;
			case 8:// koniec meczu wyjscie z break, serializacja przebiegu meczu do pliku
				temp_time = add_time();
				Match match_to_save = new Match(team_1_name, team_2_name, team_1_players_list, team_1_goal_list, team_1_foul_list, team_1_offside_list, team_1_penaltykick_list, team_1_cornerkick_list, team_1_out_list, team_2_players_list, team_2_goal_list, team_2_foul_list, team_2_offside_list, team_2_penaltykick_list, team_2_cornerkick_list, team_2_out_list);
//				serialize(match_to_save, toFilename(team_1_name, team_2_name, date)+".mth");
				System.out.println("Mecz zostal zarhiwizowany pod nazwa pliku '"+toFilename(team_1_name, team_2_name, date)+".mth'");
				writer.flush();
				writer.write("Koniec meczu " + temp_time.view_time() );
				writer.println();
				writer.flush();
				main_loop = false;
				break;
			default:
				break;
			}
		
		}
		
		System.out.println("Koniec rejestracji meczu");
		

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
	public void print_team(ArrayList<Player> list) {
		int counter = 0;
		for (Player player : list) {
			if(player.red_card == false) {
			System.out.println("["+(counter+1)+"]: "+ player.getName()+" "+player.getSurname());
			}
			counter = counter +1;
		}	
	}//Metoda dodajaca czas do zdarzenia
	public Timee add_time() {
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
	public void add_goal(Timee temp_timee,ArrayList<Goal> goals,ArrayList<Player> players_list,int number) {
//		Timee temp_timee = add_time();
		players_list.get(number-1).setNumber_of_gols(players_list.get(number-1).getNumber_of_gols()+1);
		Player temp_player = players_list.get(number-1);
		Goal temp_goal = new Goal(temp_timee, temp_player);
		goals.add(temp_goal);	
	}
	public void add_foul(Timee temp_timee,ArrayList<Foul> fouls, ArrayList<Player> players_list,int number) {

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
	public void add_offside(ArrayList<Offside> offsides,ArrayList<Player> players_list,int number) {
		Timee temp_timee = add_time();
		Offside temp_offside = new Offside(temp_timee, players_list.get(number-1));
		offsides.add(temp_offside);
	}
	//team_1 druzyna ktora strzela karnego
	//team_2 druzyna ktora spowodowala karnego
	public void add_penalty_kick(ArrayList<Goal>goals,ArrayList<PenaltyKick> penaltyKicks, ArrayList<Foul> fouls, ArrayList<Player> players_team1, ArrayList<Player> players_team2,int shooter,int couser) {
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
				}
			else if(is_scored == "n") {
				PenaltyKick temp = new PenaltyKick(temp_timee,players_team1.get(shooter-1),players_team2.get(couser-1),false);	
				penaltyKicks.add(temp);
			}
		else {System.out.println("zly wybor");}		
	}
}
	public void add_out(ArrayList<Out> outs,ArrayList<Player> players_list,String team,int number) {
		Timee temp_timee = add_time();
		Out temp_out = new Out(temp_timee, players_list.get(number-1), team);
		outs.add(temp_out);
	}
	public void add_corner_kick(ArrayList<CornerKick> cornerKicks,ArrayList<Player> players_list,int number,String team_name) {
		Timee temp_timee = add_time();
		CornerKick temp_kick = new CornerKick(temp_timee, players_list.get(number-1), team_name);
		cornerKicks.add(temp_kick);
	}
	// metody po zakonczeniu meczu, oraz stosowane do zapisu przebiegu.
	public String toFilename(String team1name,String team2name,Date date){
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
		return team1name +" - "+team2name + " " +ft.format(date);
	}
    public void serialize(Object obj, String path) throws FileNotFoundException, IOException {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(obj);
            out.close();
            System.out.println("Serialized data is saved in " + path);
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
    public void serialize_a_match(Match match,String filename) throws RemoteException{
    	Football football = new Football();
    	try {
			football.serialize(match, filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//    public static byte[] serialize(Object obj) {
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ObjectOutput out = null;
//        try {
//            out = new ObjectOutputStream(bos);
//            out.writeObject(obj);
//            out.flush();
//            return bos.toByteArray();
//        }
//        catch(IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        finally {
//            try {
//                bos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    // Metody, filtracja. 
    public File[] find_matches() throws RemoteException{
    	File dir = new File("C:\\Studia\\Projekt_2\\RMIServerSide");   

        File[] fileList = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".mth");
            }
        });
        return fileList;
        }
    public String show_teams(String path) throws ClassNotFoundException, RemoteException {
    	Match match =(Match) deserialize(path);
    	return "wybierz Druzyne:\n[1] "+match.team_1_name+"\n[2] "+match.team_2_name;   	
    }
    public ArrayList<Player> show_player_list(int choice,String path) throws ClassNotFoundException, RemoteException{
    	Match match =(Match) deserialize(path);
    	ArrayList<Player> temp = new ArrayList<Player>();
    	if(choice == 1) {
    		temp = match.team_1_players_list;
    	}else if(choice == 2) {
    		temp = match.team_2_players_list;
    	}
 
    	return temp;
    }
    public static Object deserialize(String path) throws ClassNotFoundException {
		Object obj = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
			obj = in.readObject();
			in.close();
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
			return obj;
		}
	}
    public String show_team_name(int choice,String path) throws ClassNotFoundException, RemoteException{
    	Match match =(Match) deserialize(path);
    	String team_name = "";
    	if(choice == 1){
    		team_name = match.team_1_name;
    	}else if(choice == 2) {
    		team_name = match.team_2_name;
    	}
    	return team_name;
    }
    public String result_of_team_filtration(int team_choice,int type,String path)throws ClassNotFoundException, RemoteException {
    	Match match =(Match) deserialize(path);
    	String msg_to_return = "";
    	//1.Bramki\n 2.Faule\n 3.Spalone\n 4.Rzuty Karne\n 5.Auty\n 6.Rzuty rozne
    	if(team_choice == 1) {// team_1_ - druzyna gospodarzy
    		switch (type) {
			case 1:
				msg_to_return = msg_to_return + "Bramki dla druzyny 1\n";
				if(match.team_1_goal_list.isEmpty()) {
					msg_to_return = "Druzyna nie zdobyla bramki";
				}
				else {
					for (Goal goal: match.team_1_goal_list) {
						msg_to_return = msg_to_return + goal.toString()+"\n";
					}
				}
				break;
			case 2:
				msg_to_return = msg_to_return + "Faule druzyny 1\n";
				if(match.team_1_foul_list.isEmpty()) {
					msg_to_return = "Druzyna nie posiada Fauli na swoim koncie";
				}
				else {
					for (Foul foul : match.team_1_foul_list) {
						msg_to_return = msg_to_return + foul.toString()+"\n";
					}
				}
				break;
			case 3:
				msg_to_return = msg_to_return + "Spalone druzyny 1\n";
				if(match.team_1_offside_list.isEmpty()) {
					msg_to_return = "Druzyna nie posiada spalonych na swoim koncie";
				}
				else {
					for (Offside out : match.team_1_offside_list) {
						msg_to_return = msg_to_return + out.toString()+"\n";
					}
				}
				break;
			case 4:
				msg_to_return = msg_to_return + "Rzuty karne druzyny 1\n";
				if(match.team_1_penaltykick_list.isEmpty()) {
					msg_to_return = "Druzyna nie posiada rzutow karnych na koncie";
				}
				else {
					for (PenaltyKick penaltyKick : match.team_1_penaltykick_list) {
						msg_to_return = msg_to_return + penaltyKick.toString()+"\n";
					}
				}
				break;
			case 5:
				msg_to_return = msg_to_return + "Auty druzyny 1\n";
				if(match.team_1_out_list.isEmpty()) {
					msg_to_return = "Druzyna nie posiada autow na swoim koncie";
				}
				else {
					for (Out out : match.team_1_out_list) {
						msg_to_return = msg_to_return + out.toString()+"\n";
					}
				}
				break;
			case 6:
				msg_to_return = msg_to_return + "Rzuty rozne druzyny 1\n";
				if(match.team_1_cornerkick_list.isEmpty()) {
					msg_to_return = "Druzyna nie posiada autow na swoim koncie";
				}
				else {
					for (CornerKick cornerKick : match.team_1_cornerkick_list) {
						msg_to_return = msg_to_return + cornerKick.toString()+"\n";
					}
				}
				break;
			default:
				msg_to_return = "Bledny wybor";
				break;
			}
    	}else if(team_choice == 2) {//druzyna gosci
    		switch (type) {
			case 1:
				msg_to_return = msg_to_return + "Bramki dla druzyny 2\n";
				if(match.team_2_goal_list.isEmpty()) {
					msg_to_return = "Druzyna nie zdobyla bramki";
				}
				else {
					for (Goal goal: match.team_2_goal_list) {
						msg_to_return = msg_to_return + goal.toString()+"\n";
					}
				}
				break;
			case 2:
				msg_to_return = msg_to_return + "Faule druzyny 2\n";
				if(match.team_2_foul_list.isEmpty()) {
					msg_to_return = "Druzyna nie posiada Fauli na swoim koncie";
				}
				else {
					for (Foul foul : match.team_2_foul_list) {
						msg_to_return = msg_to_return + foul.toString()+"\n";
					}
				}
				break;
			case 3:
				msg_to_return = msg_to_return + "Spalone druzyny 2\n";
				if(match.team_2_offside_list.isEmpty()) {
					msg_to_return = "Druzyna nie posiada spalonych na swoim koncie";
				}
				else {
					for (Offside out : match.team_2_offside_list) {
						msg_to_return = msg_to_return + out.toString()+"\n";
					}
				}
				break;
			case 4:
				msg_to_return = msg_to_return + "Rzuty karne druzyny 2\n";
				if(match.team_2_penaltykick_list.isEmpty()) {
					msg_to_return = "Druzyna nie posiada rzutow karnych na koncie";
				}
				else {
					for (PenaltyKick penaltyKick : match.team_2_penaltykick_list) {
						msg_to_return = msg_to_return + penaltyKick.toString()+"\n";
					}
				}
				break;
			case 5:
				msg_to_return = msg_to_return + "Auty druzyny 2\n";
				if(match.team_2_out_list.isEmpty()) {
					msg_to_return = "Druzyna nie posiada autow na swoim koncie";
				}
				else {
					for (Out out : match.team_2_out_list) {
						msg_to_return = msg_to_return + out.toString()+"\n";
					}
				}
				break;
			case 6:
				msg_to_return = msg_to_return + "Rzuty rozne druzyny 2\n";
				if(match.team_2_cornerkick_list.isEmpty()) {
					msg_to_return = "Druzyna nie posiada autow na swoim koncie";
				}
				else {
					for (CornerKick cornerKick : match.team_2_cornerkick_list) {
						msg_to_return = msg_to_return + cornerKick.toString()+"\n";
					}
				}
				break;
			default:
				msg_to_return = "Bledny wybor";
				break;
    	}
    }
		return msg_to_return;
    }
    
    
    public String result_of_player_filtration(int team_choice,int player_choice,int type_of_filtration,String path ) throws ClassNotFoundException, RemoteException{
    	//\n 1.Bramki\n 2.Faule\n 3.Spalone\n 4.Rzuty Karne\n 5.Auty\n 6.Rzuty rozne
    	Match match =(Match) deserialize(path);
    	String string_to_return = "";
    	if (team_choice == 1) {
			Player player_to_compare = match.team_1_players_list.get(player_choice-1);
			switch (type_of_filtration) {
			case 1:
				if(player_to_compare.number_of_gols == 0) {
					string_to_return = "zawodnik nie zdobyl bramki";
				}else {
				string_to_return = "\nBramki zawodnika: " + player_to_compare.getName()+" "+player_to_compare.getSurname()+"\n";
				for (Goal goals : match.team_1_goal_list) {
					if(goals.shooter.equals(player_to_compare)) {
						string_to_return = string_to_return +goals.toString()+"\n";
					}
				}
				}
				break;
			case 2:
				string_to_return = "Faule zawodnika: "+player_to_compare.getName()+" "+player_to_compare.getSurname()+"\n";
				if (player_to_compare.number_of_fauls == 0) {
					string_to_return = "Zawodnik nie posiada faulu na swoim koncie";
				} else {
					string_to_return = "Faule zawodnika"+ player_to_compare.getName()+" "+player_to_compare.getSurname()+"\n";
					for (Foul foul : match.team_1_foul_list) {
						
						if(foul.author.equals(player_to_compare)){
						string_to_return = string_to_return + foul.toString()+"\n";
						}
						
					} 
				}
				break;
			case 3:
				string_to_return = "\nSpalone zawodnika: "+player_to_compare.getName()+" "+player_to_compare.getSurname()+"\n";
				int counter = 0;
				for (Offside offside : match.team_1_offside_list) {
					if(offside.player_on_offside.equals(player_to_compare)) {
						string_to_return = string_to_return + offside.toString()+"\n";
						counter++;
					}
					
				}
				if(counter == 0) {string_to_return =string_to_return + "Zawodnik nie byl na pozycji spalonej przez caly czas trwania meczu";}
				break;
			case 4:
				string_to_return = "\nRzuty karne spowodowane przez "+player_to_compare.getName()+" "+player_to_compare.getSurname()+"\n";
				int counter_2 = 0;
				for (PenaltyKick penaltyKick : match.team_1_penaltykick_list) {
					if (penaltyKick.foul_couser.equals(player_to_compare)) {
						string_to_return = string_to_return + penaltyKick.toString()+"\n";
						counter_2++;
					}
					
				}
				if (counter_2 == 0) {
					string_to_return = string_to_return + "Zawodnik nie spowodowal karnego";
				}
				break;
			case 5:
				string_to_return = "\nAuty wykonane przez zawodnika: "+player_to_compare.getName()+" "+player_to_compare.getSurname()+"\n";
				int counter_3 =0;
				for (Out out : match.team_1_out_list) {
					if (out.performer.equals(player_to_compare)) {
						string_to_return = string_to_return + out.toString()+"\n";
						counter_3++;
					}
				}
				if (counter_3 == 0) {
					string_to_return = string_to_return +"Zawodnik nie wykonywal autu";
				}
				break;
			case 6:
				string_to_return = "\n Rzuty rozne wykonywane przez zawodnika: "+player_to_compare.getName()+" "+player_to_compare.getSurname()+"\n";
				int counter_4 = 0;
				for (CornerKick cornerKick : match.team_1_cornerkick_list) {
					if(cornerKick.kicker.equals(player_to_compare)) {
						string_to_return = string_to_return + cornerKick.toString()+"\n";
						counter_4++;
					}
				}
				if (counter_4 == 0) {
					string_to_return = string_to_return + "Zawodnik nie wykonywal rzutow roznych";
				}
				break;
			default:
				break;
			}
		} else if(team_choice == 2 ){
			Player player_to_compare = match.team_2_players_list.get(player_choice-1);
			switch (type_of_filtration) {
			case 1:
				if(player_to_compare.number_of_gols == 0) {
					string_to_return = "zawodnik nie zdobyl bramki";
				}else {
				string_to_return = "\nBramki zawodnika: " + player_to_compare.getName()+" "+player_to_compare.getSurname()+"\n";
				for (Goal goals : match.team_2_goal_list) {
					if(goals.shooter.equals(player_to_compare)) {
						string_to_return = string_to_return +goals.toString()+"\n";
					}
				}
				}
				break;
			case 2:
				string_to_return = "Faule zawodnika: "+player_to_compare.getName()+" "+player_to_compare.getSurname()+"\n";
				if (player_to_compare.number_of_fauls == 0) {
					string_to_return = "Zawodnik nie posiada faulu na swoim koncie";
				} else {
					string_to_return = "Faule zawodnika"+ player_to_compare.getName()+" "+player_to_compare.getSurname()+"\n";
					for (Foul foul : match.team_2_foul_list) {
						
						if(foul.author.equals(player_to_compare)){
						string_to_return = string_to_return + foul.toString()+"\n";
						}
						
					} 
				}
				break;
			case 3:
				string_to_return = "\nSpalone zawodnika: "+player_to_compare.getName()+" "+player_to_compare.getSurname()+"\n";
				int counter = 0;
				for (Offside offside : match.team_2_offside_list) {
					if(offside.player_on_offside.equals(player_to_compare)) {
						string_to_return = string_to_return + offside.toString()+"\n";
						counter++;
					}
					
				}
				if(counter == 0) {string_to_return =string_to_return + "Zawodnik nie byl na pozycji spalonej przez caly czas trwania meczu";}
				break;
			case 4:
				string_to_return = "\nRzuty karne spowodowane przez "+player_to_compare.getName()+" "+player_to_compare.getSurname()+"\n";
				int counter_2 = 0;
				for (PenaltyKick penaltyKick : match.team_2_penaltykick_list) {
					if (penaltyKick.foul_couser.equals(player_to_compare)) {
						string_to_return = string_to_return + penaltyKick.toString()+"\n";
						counter_2++;
					}
					
				}
				if (counter_2 == 0) {
					string_to_return = string_to_return + "Zawodnik nie spowodowal karnego";
				}
				break;
			case 5:
				string_to_return = "\nAuty wykonane przez zawodnika: "+player_to_compare.getName()+" "+player_to_compare.getSurname()+"\n";
				int counter_3 =0;
				for (Out out : match.team_2_out_list) {
					if (out.performer.equals(player_to_compare)) {
						string_to_return = string_to_return + out.toString()+"\n";
						counter_3++;
					}
				}
				if (counter_3 == 0) {
					string_to_return = string_to_return +"Zawodnik nie wykonywal autu";
				}
				break;
			case 6:
				string_to_return = "\n Rzuty rozne wykonywane przez zawodnika: "+player_to_compare.getName()+" "+player_to_compare.getSurname()+"\n";
				int counter_4 = 0;
				for (CornerKick cornerKick : match.team_2_cornerkick_list) {
					if(cornerKick.kicker.equals(player_to_compare)) {
						string_to_return = string_to_return + cornerKick.toString()+"\n";
						counter_4++;
					}
				}
				if (counter_4 == 0) {
					string_to_return = string_to_return + "Zawodnik nie wykonywal rzutu roznego";
				}
				break;
			default:
				break;
		}
		}else {
			 string_to_return = "Bledny wybor";
		}
		
    	return string_to_return;
    }




}


		

