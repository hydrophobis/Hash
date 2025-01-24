package RandomStuff.Hash.Hash;

import RandomStuff.Hash.Lib.Hash;
import RandomStuff.Hash.Lib.HashMaker;

public class HashClient {
    public static void main(String[] args) {
        int len = 32;
        Hash h1 = HashMaker.linearSecureSeed("test", len);
        Hash h2 = HashMaker.squareSecureSeed("test", len);
        Hash h3 = HashMaker.cubeSecureSeed  ("test", len);

        System.out.println(h1);
        System.out.println(h2);
        System.out.println(h3);
    }
}
