package com.teamtreehouse.model;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Ted on 1/31/2017.
 */
public class Team implements Comparable{

  private int numberOfPlayers;
  private String teamName;
  private String coachName;
  private SortedSet<Player> teamRoster;
  public static final int MAX_PLAYERS = 11;

  public Team(String teamName, String coachName) {

    this.teamName = teamName;
    this.coachName = coachName;
    this.teamRoster = new TreeSet<>();
    this.numberOfPlayers = 0;
  }

  public int getNumberOfPlayers() {
    return numberOfPlayers;
  }

  public String getTeamName() {
    return teamName;
  }

  public String getCoachName() {
    return coachName;
  }

  public SortedSet<Player> getTeamRoster() {
    return teamRoster;
  }

  public void addPlayerToTeam(Player player) {

    if (this.getNumberOfPlayers() < Team.MAX_PLAYERS) {
      this.teamRoster.add(player);
      this.numberOfPlayers++;
      player.setAvailable(false);
    } else {
        System.out.print("Error. Cannot add player as this would exceed the " +
                          "maximum number of players allowed per team.");
    }
  }

  public void removePlayerFromTeam(Player player) {
    teamRoster.remove(player);
  }

  @Override
  public String toString(){

    return  String.format("%-20s %-20s %-4d",
            teamName,
            coachName,
            numberOfPlayers);
  }

  @Override
  public int compareTo(Object obj) {

    Team other = (Team) obj;
    if (equals(other)) {
      return 0;
    }
    return teamName.compareTo(other.teamName);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Team)) return false;

    Team team = (Team) o;
    return coachName.equals(team.coachName) && teamName.equals(team.teamName);
  }

  @Override
  public int hashCode() {
    int result = teamName.hashCode();
    result = 31 * result + coachName.hashCode();
    return result;
  }

}
