import java.util.HashMap;
import java.util.Map;

public class Alphabet {
    private Map<Character, Integer> values = new HashMap<Character, Integer>();
    private Map<Integer, Character> reversedValues = new HashMap<Integer, Character>();
    private static String alphabet = "AĄBCČDEĘĖFGHIĮYJKLMNOPRSŠTUŲŪVZŽ";
    public Alphabet(){
        for(int i=0; i<this.alphabet.length(); i++){
            this.values.put(alphabet.charAt(i), i);
        }
        for(int i=0; i<this.alphabet.length(); i++){
            this.reversedValues.put(i, alphabet.charAt(i));
        }
    }

    public int getValue(char character){
        return this.values.get(character);
    }

    public char getCharacter(int input){
        return this.reversedValues.get(input);
    }

    public static String sanitize(String word) {
        StringBuilder builder = new StringBuilder();
        for (char character : word.toCharArray()){
            if (alphabet.contains("" + character)) {
                builder.append(character);
            }
        }
        return builder.toString();
    }

}
