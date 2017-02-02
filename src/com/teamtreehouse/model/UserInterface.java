package com.teamtreehouse.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Ted on 1/31/2017.
 */
public class UserInterface {

  private SortedSet<Team> league;
  private BufferedReader reader;
  private Map<String, String> mainMenu;
  private Player[] players;
  private Set<Player> alphaPlayers = new TreeSet<>();
  private List<Player> alphaList;

  public UserInterface(){

    league = new TreeSet<>();
    reader = new BufferedReader(new InputStreamReader(System.in));
    mainMenu = new LinkedHashMap<>();
    mainMenu.put("1", "Create a new team");
    mainMenu.put("2", "Add a player to a team");
    mainMenu.put("3", "Remove a player from a team");
    mainMenu.put("4", "View a report of players grouped by height");
    mainMenu.put("5", "View a report of players grouped by experience level");
    mainMenu.put("6", "View a team roster");
    mainMenu.put("7", "Exit the program");
  }

  public String mainPrompt() throws IOException {

    System.out.println("\nYour available options are listed below:\n");
    for (Map.Entry<String, String> menuItem : mainMenu.entrySet()) {
      System.out.printf("\t%s) %s%n", menuItem.getKey(), menuItem.getValue());
    }
    System.out.print("\nEnter the number for your selection: ");
    String selection = reader.readLine();
    return selection.trim();
  }

  public void run() {

    players = Players.load();
    alphaPlayers = alphabetizePlayers(players);
    alphaList = new ArrayList<>(alphaPlayers);

    String selection = "";
    System.out.printf("There are currently %d registered players.%n", alphaList.size());
    do {
      try {
        selection = mainPrompt();

        switch(selection) {
          case "1":
            // create a new team
            displayTeamList();
            createTeamPrompt();
            pauseProgram();
            break;
          case "2":
            // add a player to a team
            displayPlayerList(alphaList);
            Player selectedPlayer = getPlayerPrompt();
            displayTeamList();
            Team selectedTeam = getTeamPrompt();
            selectedTeam.addPlayerToTeam(selectedPlayer);
            alphaList.remove(selectedPlayer);
            pauseProgram();
            break;
          case "3":
            // remove a player from a team
            displayTeamList();
            Team thisTeam = getTeamPrompt();
            displayPlayerList(thisTeam.getTeamRoster());
            getPlayerPrompt();
            break;
          case "4":
            // view a height report

            break;
          case "5":
            // view an experience report

            break;
          case "6":
            // view a team roster
            displayTeamList();
            Team theTeam = getTeamPrompt();
            displayPlayerList(theTeam.getTeamRoster());
            pauseProgram();
            break;
          case "7":
            // exit the program

            break;
          default:
            System.out.println("Unknown entry. Please try again.");
        }
      } catch(IOException ioe) {
          System.err.println("Invalid entry. Please enter a number between 1 and 7");
      }
    } while (!selection.equals("7"));
  }

  public Set<Player> alphabetizePlayers(Player[] players){
    Collections.addAll(alphaPlayers, players);
    return alphaPlayers;
  }

  public void pauseProgram() throws IOException {

    System.out.print("Press the <Enter> key to continue...");
    reader.readLine();
  }

  public void displayTeamList() {

    System.out.printf("%n%-20s %-20s %-8s%n%n", "Team Name", "Coach Name", "Players");
    if (!league.isEmpty()) {
      int count = 1;
      for (Team team : league) {
        System.out.printf("%d) %s%n", count, String.valueOf(team));
        count++;
      }
    } else {
      System.out.println("No teams currently in the league");
    }
  }

  public Team getTeamPrompt() throws IOException{

    System.out.print("\nEnter the number for the team you would like to select: ");
    int teamNumber = Integer.parseInt(reader.readLine());
    Team[] teamArray = league.toArray(new Team[0]);
    return teamArray[teamNumber - 1];
  }

  public void createTeamPrompt() throws IOException {

    System.out.print("\nEnter the new team name: ");
    String newTeamName = reader.readLine();

    System.out.print("Enter the new team coach's name: ");
    String coachName = reader.readLine();

    Team newTeam = new Team(newTeamName, coachName);
    league.add(newTeam);

    System.out.printf("%nTeam: %s coached by %s successfully created and added to the league%n%n",
            newTeam.getTeamName(), newTeam.getCoachName());

  }

  public void displayPlayerList(Collection<Player> players) {

    System.out.printf("%n%-24s %-10s %-6s%n%n", "Player Name", "Height", "Experience");
    int count = 1;
    for (Player player : players) {
        System.out.printf("%2d) %s%n", count, player);
        count++;
    }
  }

  public Player getPlayerPrompt() throws IOException {

    System.out.print("\nEnter the number for the player you would like to select: ");
    int playerNumber = Integer.parseInt(reader.readLine());
    return alphaList.get(playerNumber - 1);
  }
}
