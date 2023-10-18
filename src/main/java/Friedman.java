public class Friedman {
    public static int estimateKeyLength(double ic, int textLength) {
        double estimatedKeyLength = (0.067 * textLength) / ((ic - 0.0385) * textLength + 0.065);
        return (int) Math.round(estimatedKeyLength);
    }

}
