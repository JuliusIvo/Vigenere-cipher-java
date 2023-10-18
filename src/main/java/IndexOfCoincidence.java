import java.util.HashMap;
import java.util.Map;
public class IndexOfCoincidence {

    public static String alphabet = "AĄBCČDEĘĖFGHIĮYJKLMNOPRSŠTUŲŪVZŽaąbcčdeęėfghiįyjklmnoptrsštuųūvzž";
    public static double calculateIndexOfCoincidence(String cypher) {
        cypher = cypher.replaceAll("[^" + alphabet + "]", "");

        Map<Character, Integer> letterFrequency = new HashMap<>();
        int totalLetters = cypher.length();
        for (char c : cypher.toCharArray()) {
            letterFrequency.put(c, letterFrequency.getOrDefault(c, 0) + 1);
        }
        double indexOfCoincidence = 0.0;
        for (char c : letterFrequency.keySet()) {
            int frequency = letterFrequency.get(c);
            indexOfCoincidence += (frequency * (frequency - 1));
        }
        indexOfCoincidence /= (totalLetters * (totalLetters - 1));
        return indexOfCoincidence;
        }
    }
