import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Decoder {
    private Alphabet alphabetMap = new Alphabet();
    private String alphabet = "AĄBCČDEĘĖFGHIĮYJKLMNOPRSŠTUŲŪVZŽ";
    private String mostLikelyKey;
    private int mostLikelyKeyMatches;

    public List<String> cypher;

    public Decoder(String cypher){
        this.cypher = Arrays.stream(cypher.split(" ")).toList();
    }

    private List<String> lithuanianWords = Arrays.stream(new ReadFile().retrieveResouceFileAsString("lithuanian-words-list.txt").toUpperCase().split("\n"))
            .map(Alphabet::sanitize)
            .filter(word -> word.length() > 0)
            .toList();
    public String decipher(int length){;
        return attempt(length);
    }

    private String attempt(int length) {
        System.out.println("Total words: " + lithuanianWords.size());
        for (String word : lithuanianWords) {
            if (lithuanianWords.indexOf(word) % 10 == 0) {
                System.out.println(String.format("%d/%d", lithuanianWords.indexOf(word), lithuanianWords.size()));
            }

            if(!characterFromWrongAlphabetExists(word)) {
                int cypherMatch = 0;
                if (word.length() == 7) {
                    System.out.println(word);
                    List<String> decodeAttempt = decode(word);
                    for (String cypherFragment : decodeAttempt) {
                        if (lithuanianWords.contains((cypherFragment))) {
                            cypherMatch++;
                        }
                    }
                }
                if (cypherMatch > 0) {
                    if (mostLikelyKey == null || mostLikelyKeyMatches < cypherMatch) {
                        mostLikelyKey = word;
                        mostLikelyKeyMatches = cypherMatch;
                    }
                }
            }
        }
        System.out.println(mostLikelyKey + " with matches " + mostLikelyKeyMatches);
        return mostLikelyKey;
    }

    public List<String> decode(String key){
        List<String> decipheredWithKey = new ArrayList<>();
        List<Integer> keyNumericValues = this.getKeyNumericValues(key);
        int keyLengthIndex = 0;
        for(String word : this.cypher){
            StringBuilder decodedString = new StringBuilder();
            for(char character: word.toCharArray()) {
                if (this.isCharacterInAlphabet(character)) {
                    if (keyLengthIndex == key.length()) {
                        keyLengthIndex = 0;
                    }
                    int realValue = alphabetMap.getValue(character) - keyNumericValues.get(keyLengthIndex);
                    keyLengthIndex = keyLengthIndex + 1;
                    if (realValue < 0) {
                        realValue = realValue + 32;
                    }
                    decodedString.append(alphabetMap.getCharacter(realValue));
                }
            }
            decipheredWithKey.add(decodedString.toString());
        }
        return decipheredWithKey;
    }

    private boolean isCharacterInAlphabet(char character){

        return this.alphabet.indexOf(character)!=-1 ?
                true : false;
    }

    private boolean characterFromWrongAlphabetExists(String word){
        if(word.contains("W") || word.contains("X") || word.contains("Q")){
            return true;
        }
        else return false;
    }

    private List<Integer> getKeyNumericValues(String key){
        List<Integer> keyNumericValues = new ArrayList<>();
        for (char character : key.toCharArray()){
            keyNumericValues.add(alphabetMap.getValue(character));
        }
        return keyNumericValues;
    }

}
