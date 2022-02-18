import java.util.Locale;
import java.util.Scanner;

public class WordleRunner
{
    public static boolean win = false;
    public static boolean lose = false;

    public static void welcome()
    {
        System.out.println("Welcome to Wordle!\nYou have six chances to correctly guess the randomly generated five letter word.");
        System.out.println("Green signifies correct letter and placement,\nYellow signifies correct letter, but incorrect placement, and gray signifies wrong letter.");
        play();
    }

    public static void play()
    {
        Scanner scan = new Scanner(System.in);
        Wordle wordle = new Wordle();

        while(!win && !lose)
        {
            wordle.printWordle();
            String input = "";
            boolean woo = true;
            while (woo)
            {
                System.out.println("Enter a valid word.");
                input = scan.nextLine();
                input = input.toLowerCase();
                if (input.length() > 5)
                {
                    System.out.println("Your words length exceeds 5 characters.");
                }
                else if (input.length() < 5)
                {
                    System.out.println("Your words is less than 5 characters.");
                }
                else
                {
                    woo = false;
                }
            }
            wordle.formatInput(input);
            wordle.checkAndModifyRows();
        }
        if(lose)
        {
            System.out.println("You lost!");
        }
        else
        {
            System.out.println("You win!!");
            System.out.print(wordle.currentGuess + "/" + "6");
        }
    }
}
