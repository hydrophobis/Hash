package RandomStuff.Hash;

import RandomStuff.Hash.Lib.Hash;
import RandomStuff.Hash.Lib.HashMaker;

public class HashCracker {

    public static String crackHash(String targetHash, int maxLength, int hashLength, String charset) {
        char[] currentAttempt = new char[maxLength];
        if (bruteForce(targetHash, charset, currentAttempt, 0, hashLength)) {
            return new String(currentAttempt).trim();
        }
        return null; // Return null if no match is found
    }

    private static boolean bruteForce(String targetHash, String charset, char[] attempt, int position, int length) {
        if (position == attempt.length) {
            // Generate hash using the current attempt
            Hash hashObj = new Hash(new String(attempt));
            if (hashObj.toString().equals(targetHash)) {
                return true;
            }
            return false;
        }

        for (char c : charset.toCharArray()) {
            attempt[position] = c;
            if (bruteForce(targetHash, charset, attempt, position + 1, length)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Example parameters
        String targetHash = HashMaker.linearSecureSeed("abc", 10).hash;
        int maxLength = 3;
        int hashLength = 10;
        int maxChar = 236;
        int minChar = 87;

        char[] charsetc = new char[maxChar - minChar];
        for (int i = 0; i < maxChar - minChar; i++) {
            charsetc[i] = (char) (i + minChar);
        }
        String charset = new String(charsetc); // Charset to use for input

        // Start timing
        long startTime = System.nanoTime();

        String crackedSeed = crackHash(targetHash, maxLength, hashLength, charset);

        // End timing
        long endTime = System.nanoTime();
        double elapsedTimeInSeconds = (endTime - startTime) / 1_000_000_000.0;

        if (crackedSeed != null) {
            System.out.println("Hash cracked! Seed: " + crackedSeed);
        } else {
            System.out.println("Failed to crack the hash.");
        }

        System.out.printf("Elapsed time: %.3f seconds%n", elapsedTimeInSeconds);
    }
}