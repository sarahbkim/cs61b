import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by sarahbkim on 2/11/15.
 */

public class Nuke2 {

    public static void main (String[] arg) throws Exception {
        BufferedReader keyboard;
        String word;

        keyboard = new BufferedReader(new InputStreamReader(System.in));

        System.out.flush();
        word = keyboard.readLine();

        System.out.println(word.substring(0, 1) + word.substring(2, word.length()));
    }

}