import java.util.Scanner;
import java.util.Arrays;

public class Main
{
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

  }				//end main

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

