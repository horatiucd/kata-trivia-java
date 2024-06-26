package trivia;

import java.util.ArrayList;
import java.util.LinkedList;


// TODO refactor me
public class InitialTriviaGame implements Game {
  ArrayList players = new ArrayList();
  int[] places = new int[6];
  int[] purses = new int[6];
  boolean[] inPenaltyBox = new boolean[6];

  LinkedList popQuestions = new LinkedList();
  LinkedList scienceQuestions = new LinkedList();
  LinkedList sportsQuestions = new LinkedList();
  LinkedList rockQuestions = new LinkedList();

  int currentPlayer = 0;
  boolean isGettingOutOfPenaltyBox;

  public InitialTriviaGame() {
    for (int i = 0; i < 50; i++) {
      popQuestions.addLast("POP Question " + i);
      scienceQuestions.addLast(("SCIENCE Question " + i));
      sportsQuestions.addLast(("SPORTS Question " + i));
      rockQuestions.addLast(createRockQuestion(i));
    }
  }

  public String createRockQuestion(int index) {
    return "ROCK Question " + index;
  }

  public boolean isPlayable() {
    return (howManyPlayers() >= 2);
  }

  public void enrollPlayer(String name) {


    players.add(name);
    places[howManyPlayers()] = 0;
    purses[howManyPlayers()] = 0;
    inPenaltyBox[howManyPlayers()] = false;

    System.out.println(name + " was added");
    System.out.println("He / She is player number " + players.size());
  }

  public int howManyPlayers() {
    return players.size();
  }

  public void playTurn(int die) {
    System.out.println(players.get(currentPlayer) + " is the current player");
    System.out.println("He / She has rolled a " + die);

    if (inPenaltyBox[currentPlayer]) {
      if (die % 2 != 0) {
        isGettingOutOfPenaltyBox = true;

        System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
        inPenaltyBox[currentPlayer] = false;  // added this line to fix the bug
        places[currentPlayer] = places[currentPlayer] + die;
        if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

        System.out.println(players.get(currentPlayer)
                           + "'s new location is "
                           + places[currentPlayer]);
        System.out.println("The category is " + currentCategory());
        askQuestion();
      } else {
        System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
      }

    } else {

      places[currentPlayer] = places[currentPlayer] + die;
      if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

      System.out.println(players.get(currentPlayer)
                         + "'s new location is "
                         + places[currentPlayer]);
      System.out.println("The category is " + currentCategory());
      askQuestion();
    }

  }

  private void askQuestion() {
    if (currentCategory() == "POP")
      System.out.println(popQuestions.removeFirst());
    if (currentCategory() == "SCIENCE")
      System.out.println(scienceQuestions.removeFirst());
    if (currentCategory() == "SPORTS")
      System.out.println(sportsQuestions.removeFirst());
    if (currentCategory() == "ROCK")
      System.out.println(rockQuestions.removeFirst());
  }


  private String currentCategory() {
    if (places[currentPlayer] == 0) return "POP";
    if (places[currentPlayer] == 4) return "POP";
    if (places[currentPlayer] == 8) return "POP";
    if (places[currentPlayer] == 1) return "SCIENCE";
    if (places[currentPlayer] == 5) return "SCIENCE";
    if (places[currentPlayer] == 9) return "SCIENCE";
    if (places[currentPlayer] == 2) return "SPORTS";
    if (places[currentPlayer] == 6) return "SPORTS";
    if (places[currentPlayer] == 10) return "SPORTS";
    return "ROCK";
  }

  public boolean onCorrectAnswer() {
      System.out.println("Answer was correct!!!!");
      purses[currentPlayer]++;
      System.out.println(players.get(currentPlayer)
                         + " now has "
                         + purses[currentPlayer]
                         + " Gold Coins.");

      boolean winner = didPlayerWin();
      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;

      return winner;
  }

  public boolean onWrongAnswer() {
    System.out.println("Question was incorrectly answered");
    System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
    inPenaltyBox[currentPlayer] = true;

    currentPlayer++;
    if (currentPlayer == players.size()) currentPlayer = 0;
    return true;
  }


  private boolean didPlayerWin() {
    return !(purses[currentPlayer] == 6);
  }
}
