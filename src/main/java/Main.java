public class Main {
    public static void main(String args[]) {
        Decoder decoder = new Decoder(new ReadFile().retrieveResouceFileAsString("Cypher.txt").toUpperCase());
        //String cypher = new ReadFile().retrieveResouceFileAsString("Cypher.txt");
        //System.out.println(decoder.decipher(
        //        Friedman.estimateKeyLength(IndexOfCoincidence.calculateIndexOfCoincidence(cypher), cypher.length())
        //));
        System.out.println(decoder.cypher);
        System.out.println(decoder.decode("LAPINAS"));

    }
}
