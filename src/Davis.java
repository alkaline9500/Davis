/**
 * Davis Command Center
 * @author Nic Manoogian
 */
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.util.HashMap;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;

public class Davis
{
    // Exit command
    public static final String EXIT = "exit";
    // HashMap Matching Commands with url queries
    private HashMap<String, String> queryMap;

    /**
     * Constructor with a filename with the commands
     * @param filename name of the file containing matching commands and URLs
     */
    public Davis(String filename)
    {
        queryMap = new HashMap<String, String>();
        Scanner sc = null;
        try
        {
            sc = new Scanner(new File(filename));
        }
        catch (IOException e)
        {
            System.err.println("File Not Found");
        }

        while (sc.hasNext())
        {
            queryMap.put(sc.next(), sc.next());
        }
    }

    /**
     * Runs the infinite loop, waiting for commands and executing them
     * Breaks with the command "exit"
     */
    public void run()
    {
        Scanner input = new Scanner(System.in);
        String command = "";
        while (!command.equals(Davis.EXIT))
        {
            command = input.next();
            if (queryMap.containsKey(command))
            {
                try
                {
                    new URL("http://bluefile.org/light?l="+queryMap.get(command)).openStream().read();
                }
                catch (MalformedURLException e)
                {
                    System.err.println("Malformed URL");
                }
                catch (IOException ioe)
                {
                    System.err.println("IO Exception");
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Davis d = new Davis("commands.txt");
        d.run();
    }
}
