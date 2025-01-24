package RandomStuff.Hash.Lib;

public abstract class HashMaker {

    public static Hash linearSetSeed(String toHash, int length, int seed, char id){
        
        // Var init
        MersenneTwister mt = new MersenneTwister(seed);
        byte[] byteArrayIn = new byte[length];

        // Check if toHash is longer than hash length
        try {
            byteArrayIn[toHash.length()] = byteArrayIn[toHash.length()];
        } catch (Exception e) {
            System.out.println("Input string is longer than specified length. Abandoning hash");
            System.exit(1);
        }

        // Set byteArrayIn to toHash as a char array
        // for(int i = 0; i < toHash.length(); i++){
        //     byteArrayIn[i] = bytemod((byte)toHash.toCharArray()[i], seed);
        // }

        // Add the MT values to the bytes
        for(int i = toHash.length(); i < length; i++){
            byteArrayIn[i] = (byte)((Math.abs(mt.nextInt() % 60)) + 65);
        }

        // Convert byteArrayIn to chars and set charArrayOut to those chars
        char[] charArrayOut = new char[length];
        for(int i = 0; i < length; i++){
            charArrayOut[i] = (char)byteArrayIn[i];
        }
        String outString = new String(charArrayOut);
        outString = outString.replace('\\', (char)93);
        return new Hash(id + "|" + outString); // Return charArrayOut as a String
    }

    public static Hash linearSecureSeed(String toHash, int length){
        // Sum bytes for the seed
        int seed = 0;
        for(char c : toHash.toCharArray()){
            seed += (int)c;
        }

        return linearSetSeed(toHash, length, seed, 'l');
    }

    public static Hash squareSecureSeed(String toHash, int length){
        // Product bytes for the seed
        int seed = 1;
        for(int i = 0; i < toHash.length(); i++){
            seed = seed * (int)toHash.toCharArray()[i];
        }
        return linearSetSeed(toHash, length, seed, 's');
    }

    public static Hash cubeSecureSeed(String toHash, int length){
        int seed = 2;
        for(int i = 0; i < toHash.length(); i++){
            seed = (int)Math.pow((double)seed, (double)toHash.toCharArray()[i]);
        }
        return linearSetSeed(toHash, length, seed, 'c');
    }
}
