import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Wordle
{
    //row variables
    private String[] row1 = {"   ", "   ", "   ", "   ", "   "};
    private String[] row2 = {"   ", "   ", "   ", "   ", "   "};
    private String[] row3 = {"   ", "   ", "   ", "   ", "   "};
    private String[] row4 = {"   ", "   ", "   ", "   ", "   "};
    private String[] row5 = {"   ", "   ", "   ", "   ", "   "};
    private String[] row6 = {"   ", "   ", "   ", "   ", "   "};
    public int currentGuess = 0;
    private ArrayList<String[]> board = new ArrayList<>(Arrays.asList(row1, row2, row3, row4, row5, row6));

    //THE WORD && options arraylist
    private String answer = "";
    public final static ArrayList<String> options = loadWordsInto();
    private int currentRight = 0;

    //color ansi escape codes
    public static final String GREEN_BG = "\u001B[42m";
    public static final String YELLOW_BG = "\u001B[43m";
    public static final String RESET = "\u001B[0m";

    //CONSTRUCTOR
    public Wordle()
    {
        int random = (int) (Math.random() * 430) + 1;
        answer = options.get(random);
        System.out.println(answer);
    }

    //formats always valid input into row arrays
    public void formatInput(String input)
    {
        for(int i = 0; i < board.get(currentGuess).length; i++)
        {
            board.get(currentGuess)[i] = input.substring(i, i + 1);
        }
    }

    //checks and modifies row arrays
    public void checkAndModifyRows()
    {
        String currentFormatted = new String("");
        for(int i = 0; i < board.get(currentGuess).length; i++)
        {
            currentFormatted += (board.get(currentGuess)[i]);
        }
        System.out.println(currentFormatted);
        for(int i = 0; i < board.get(currentGuess).length; i++)
        {
            if(board.get(currentGuess)[i].equals(answer.substring(i, i + 1) ))
            {
                board.get(currentGuess)[i] = GREEN_BG + " " + board.get(currentGuess)[i] + " ";
            }
            else if(answer.contains(board.get(currentGuess)[i]))
            {
                board.get(currentGuess)[i] = YELLOW_BG + " " + board.get(currentGuess)[i] + " ";
            }
            else
            {
                board.get(currentGuess)[i] = " " + board.get(currentGuess)[i] + " ";
            }
        }
        currentGuess++;
        if(currentGuess == 6 && !currentFormatted.equals(answer))
        {
            WordleRunner.lose = true;
            printWordle();
        }
        if(currentGuess == 6 && currentFormatted.equals(answer))
        {
            WordleRunner.win = true;
            printWordle();
        }
        else if(currentFormatted.equals(answer))
        {
            WordleRunner.win = true;
            printWordle();
        }
    }

    //prints the wordle
    public void printWordle()
    {
        for(String[] row : board)
        {
            int i = 0;
            System.out.println("_____________________");
            System.out.print("|");
            System.out.print(row[i]);
            i++;
            System.out.print(RESET);
            System.out.print("|");
            System.out.print(row[i]);
            i++;
            System.out.print(RESET);
            System.out.print("|");
            System.out.print(row[i]);
            i++;
            System.out.print(RESET);
            System.out.print("|");
            System.out.print(row[i]);
            i++;
            System.out.print(RESET);
            System.out.print("|");
            System.out.print(row[i]);
            i++;
            System.out.print(RESET);
            System.out.println("|");
        }
        System.out.println("_____________________");
    }

    public static boolean checkWordsAgainst(String input)
    {
        for (int j = 0; j < options.size() - 1; j++)
        {
            int minIndex = j;
            for (int k = j + 1; k < options.size(); k++)
            {
                if (options.get(k).compareTo(options.get(minIndex)) < 0)
                {
                    minIndex = k;
                }
            }
            String temp = options.get(j);
            options.set(j, options.get(minIndex));
            options.set(minIndex, temp);
        }
        int left = 0;
        int right = options.size() - 1;
        int numchecks = 0;
        while(left <= right)
        {
            numchecks++;
            int middle = (left + right) / 2;
            if(input.compareTo(options.get(middle)) < 0)
            {
                right = middle - 1;
            }
            else if(input.compareTo(options.get(middle)) > 0)
            {
                left = middle - 1;
            }
            else
            {
                return true;
            }
        }
        return false; // STUB
    }

    // helper method to load words from dict.txt (taken from my boy mr miller and modified for use)
    private static ArrayList<String> loadWordsInto()
    {
        ArrayList<String> wordList = new ArrayList<>();
        try {
            Scanner input = new Scanner(new File("src\\dict.txt"));
            while (input.hasNext()) {
                String word = input.next();
                wordList.add(word.toLowerCase());
            }
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return wordList;
    }

}
