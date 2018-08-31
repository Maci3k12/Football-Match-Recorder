import java.io.Serializable;
import java.util.ArrayList;

public class Match implements Serializable {

	private static final long serialVersionUID = 1L;
	public String team_1_name;
	public String team_2_name;
	public ArrayList<Player> team_1_players_list;
	public ArrayList<Goal>   team_1_goal_list;
	public ArrayList<Foul>   team_1_foul_list;
	public ArrayList<Offside> team_1_offside_list;
	public ArrayList<PenaltyKick> team_1_penaltykick_list;
	public ArrayList<CornerKick> team_1_cornerkick_list; 
	public ArrayList<Out> team_1_out_list;
	public ArrayList<Player> team_2_players_list;
	public ArrayList<Goal>   team_2_goal_list;
	public ArrayList<Foul>   team_2_foul_list;
	public ArrayList<Offside> team_2_offside_list;
	public ArrayList<PenaltyKick> team_2_penaltykick_list;
	public ArrayList<CornerKick> team_2_cornerkick_list; 
	public ArrayList<Out> team_2_out_list;
	public Match(String team_1_name, String team_2_name, ArrayList<Player> team_1_players_list,
			ArrayList<Goal> team_1_goal_list, ArrayList<Foul> team_1_foul_list, ArrayList<Offside> team_1_offside_list,
			ArrayList<PenaltyKick> team_1_penaltykick_list, ArrayList<CornerKick> team_1_cornerkick_list,
			ArrayList<Out> team_1_out_list, ArrayList<Player> team_2_players_list, ArrayList<Goal> team_2_goal_list,
			ArrayList<Foul> team_2_foul_list, ArrayList<Offside> team_2_offside_list,
			ArrayList<PenaltyKick> team_2_penaltykick_list, ArrayList<CornerKick> team_2_cornerkick_list,
			ArrayList<Out> team_2_out_list) {
		super();
		this.team_1_name = team_1_name;
		this.team_2_name = team_2_name;
		this.team_1_players_list = team_1_players_list;
		this.team_1_goal_list = team_1_goal_list;
		this.team_1_foul_list = team_1_foul_list;
		this.team_1_offside_list = team_1_offside_list;
		this.team_1_penaltykick_list = team_1_penaltykick_list;
		this.team_1_cornerkick_list = team_1_cornerkick_list;
		this.team_1_out_list = team_1_out_list;
		this.team_2_players_list = team_2_players_list;
		this.team_2_goal_list = team_2_goal_list;
		this.team_2_foul_list = team_2_foul_list;
		this.team_2_offside_list = team_2_offside_list;
		this.team_2_penaltykick_list = team_2_penaltykick_list;
		this.team_2_cornerkick_list = team_2_cornerkick_list;
		this.team_2_out_list = team_2_out_list;
	}
}
