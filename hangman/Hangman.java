/*
This is a single playthrough of the game hang man the word list is hard coded as an array.
the user has 7 chances to guess the word before the man on the gallow is drawn.
If the user guess wrong 7 times the game ends and the correct word is displayed.
If the user guesses correctly then a congratulations is displayed and the game ends.
*/

import java.util.Scanner;
import java.util.Arrays;

public class Main
{
//list of word user can guess
  static String[] words = {
    "java", "javascript", "compiler", "variable", "keyboard"
  };

  static char[] alphabet = {
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
    'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
  };

  static String word = words[(int) (Math.random () * words.length)];

  static char[] answer = new char[word.length ()];

  static int wrongCount = 0;

  public static void main (String[]args)
  {

    System.out.println ("Welcome to my Hangman game. :)\n");

    System.out.println ("Your word has " + word.length () +
			" letters in it.");

    answer = explode (word);

    playGame (answer);

  }//end main

  //turning word in to char array
  public static char[] explode (String word)
  {
    char[] brokenWord = word.toCharArray ();

    return brokenWord;
  }

  public static void playGame (char[]answer)
  {
    char[] hiddenAnswer = new char[answer.length];

    for (int i = 0; i < answer.length; i++)
      {
	hiddenAnswer[i] = '_';
      }

    while (wrongCount != 7)
      {
	gallows (answer, hiddenAnswer);

	guess (answer, hiddenAnswer);

	endGame (answer, hiddenAnswer);
      }

  }

//This function checks the user input and compares it to the hidden answer.
//If user input is not found we add 1 to the wrong count.
//We then remove the users guess from our alphabet list.
  public static void guess (char[]answer, char[]hiddenAnswer)
  {
    Scanner input = new Scanner (System.in);

    System.out.println ("\nplease enter guess a-z:");
    char userInput = input.next ().charAt (0);

    boolean found = false;

    for (int i = 0; i < answer.length; i++)
      {
	if (answer[i] == userInput)
	  {
	    found = true;
	    hiddenAnswer[i] = userInput;
	  }
      }

    if (!found)
      {
	wrongCount++;
      }

    for (int k = 0; k < alphabet.length; k++)
      {
	if (userInput == alphabet[k])
	  {
	    alphabet[k] = '_';
	  }
      }
  }

  public static void endGame (char[]answer, char[]hiddenAnswer)
  {
    boolean result = Arrays.equals (answer, hiddenAnswer);

    if (result == true)
      {
	gallows (answer, hiddenAnswer);
	System.out.println ("\nCongratulations you got the correct word!!!");
	System.exit (0);
      }

  }
	
  public static void gallows (char[]answer, char[]hiddenAnswer)
  {
    switch (wrongCount)
      {
      case 0:
	System.out.println ("**********************");
	for (int j = 0; j < alphabet.length; j++)
	  {
	    System.out.print (alphabet[j] + " ");
	  }
	System.out.println ("\n------");
	System.out.println ("|    |");
	System.out.println ("|");
	System.out.println ("|");
	System.out.println ("|");
	System.out.println ("|");

	for (int j = 0; j < answer.length; j++)
	  {
	    System.out.print (hiddenAnswer[j] + " ");
	  }
	break;

      case 1:
	System.out.println ("\n**********************");

	for (int j = 0; j < alphabet.length; j++)
	  {
	    System.out.print (alphabet[j] + " ");
	  }

	System.out.println ("\n------");
	System.out.println ("|    |");
	System.out.println ("|    0");
	System.out.println ("|");
	System.out.println ("|");
	System.out.println ("|");

	for (int j = 0; j < answer.length; j++)
	  {
	    System.out.print (hiddenAnswer[j] + " ");
	  }
	break;

      case 2:
	System.out.println ("\n**********************");

	for (int j = 0; j < alphabet.length; j++)
	  {
	    System.out.print (alphabet[j] + " ");
	  }

	System.out.println ("\n------");
	System.out.println ("|    |");
	System.out.println ("|    0");
	System.out.println ("|    |");
	System.out.println ("|");
	System.out.println ("|");

	for (int j = 0; j < answer.length; j++)
	  {
	    System.out.print (hiddenAnswer[j] + " ");
	  }
	break;

      case 3:
	System.out.println ("\n**********************");

	for (int j = 0; j < alphabet.length; j++)
	  {
	    System.out.print (alphabet[j] + " ");
	  }

	System.out.println ("\n------");
	System.out.println ("|    |");
	System.out.println ("|    0");
	System.out.println ("|   /|");
	System.out.println ("|");
	System.out.println ("|");

	for (int j = 0; j < answer.length; j++)
	  {
	    System.out.print (hiddenAnswer[j] + " ");
	  }
	break;

      case 4:
	System.out.println ("\n**********************");

	for (int j = 0; j < alphabet.length; j++)
	  {
	    System.out.print (alphabet[j] + " ");
	  }

	System.out.println ("\n------");
	System.out.println ("|    |");
	System.out.println ("|    0");
	System.out.println ("|   /|\\");
	System.out.println ("|");
	System.out.println ("|");

	for (int j = 0; j < answer.length; j++)
	  {
	    System.out.print (hiddenAnswer[j] + " ");
	  }
	break;

      case 5:
	System.out.println ("\n**********************");

	for (int j = 0; j < alphabet.length; j++)
	  {
	    System.out.print (alphabet[j] + " ");
	  }

	System.out.println ("\n------");
	System.out.println ("|    |");
	System.out.println ("|    0");
	System.out.println ("|   /|\\");
	System.out.println ("|   /");
	System.out.println ("|");

	for (int j = 0; j < answer.length; j++)
	  {
	    System.out.print (hiddenAnswer[j] + " ");
	  }
	break;

      case 6:
	System.out.println ("\n**********************");

	for (int j = 0; j < alphabet.length; j++)
	  {
	    System.out.print (alphabet[j] + " ");
	  }

	System.out.println ("\n------");
	System.out.println ("|    |");
	System.out.println ("|    0");
	System.out.println ("|   /|\\");
	System.out.println ("|   / \\");
	System.out.println ("|");

	for (int j = 0; j < answer.length; j++)
	  {
	    System.out.print (hiddenAnswer[j] + " ");
	  }
	System.out.println ("\nSorry to many wrong guesses.\n" + word +
			    " was the correct word.");

	System.exit (0);
      }

  }
}				//end class
