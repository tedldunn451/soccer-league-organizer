import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

public class LeagueManager {

  public static void main(String[] args) {
    Player[] players = Players.load();
    System.out.printf("There are currently %d registered players.%n", players.length);
    // Your code here!

/*
    public void displayPlayerWithStats(Player[] players) {
      int count = 1;
      for (Player player : players) {
      System.out.printf("%2d) %s%n", count, player);
      count++;
      }
    }
*/

/*    Team newTeam = new Team("The Bears", "Ted Dunn");

    System.out.print(newTeam);*/
  }
}
