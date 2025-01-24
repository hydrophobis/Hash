package RandomStuff.Hash.Lib;

public class Hash {
    public String hash;

    public Hash(String hash){
        this.hash = hash;
    }

    public Hash(char[] hash){
        this.hash = new String(hash);
    }

    public Hash(){
        this.hash = new String();
    }

    @Override
    public String toString(){
        return this.hash;
    }

    public boolean equals(Hash h2){
        return this.toString().equals(h2.toString());
    }
}
