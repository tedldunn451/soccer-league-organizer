package com.teamtreehouse.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Ted on 1/31/2017.
 */
public class UserInterface {

  private Set<Team> league;
  private BufferedReader reader;
  private Map<String, String> mainMenu;

  public UserInterface(){

    league = new TreeSet<>();
    reader = new BufferedReader(new InputStreamReader(System.in));
    mainMenu = new HashMap<>();
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
    System.out.print("\nPlease enter the number for your selection: ");
    String selection = reader.readLine();
    return selection.trim();
  }

  public void run() {

    String selection = "";
    do {
      try {
        selection = mainPrompt();

        switch(selection) {
          case "1":
            // create a new team
            displayTeamHeader();
            createTeamPrompt();
            pauseProgram();
            break;
          case "2":
            // add a player to a team

            break;
          case "3":
            // remove a player from a team

            break;
          case "4":
            // view a height report

            break;
          case "5":
            // view an experience report

            break;
          case "6":
            // view a team roster

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

  public void pauseProgram() throws IOException{

    System.out.print("Press the <Enter> key to continue...");
    reader.readLine();
  }

  public void displayTeamHeader() {

    System.out.printf("%n%-20s %-20s %-8s%n%n", "Team Name", "Coach Name", "Players");
    if (!league.isEmpty()) {
      for (Team team : league) {
        System.out.printf("%s%n",String.valueOf(team));
      }
    } else {
      System.out.println("No teams currently in the league");
    }
  }

  public Team createTeamPrompt() throws IOException {

    System.out.print("\nPlease enter the new team name: ");
    String newTeamName = reader.readLine();

    System.out.print("Please enter the new team coach's name: ");
    String coachName = reader.readLine();

    Team newTeam = new Team(newTeamName, coachName);
    league.add(newTeam);

    System.out.printf("%nTeam: %s coached by %s successfully created and added to the league%n%n",
            newTeam.getTeamName(), newTeam.getCoachName());

    return newTeam;
  }
}
