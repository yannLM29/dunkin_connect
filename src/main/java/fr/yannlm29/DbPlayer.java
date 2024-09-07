package fr.yannlm29;

public class DbPlayer {
    public String mPseudo;
    public int mNumberOfKills;
    public int mNumberOfDeaths;

    public DbPlayer() {

    }
    
    public DbPlayer(String inPseudo, int inNumberOfKills, int inNumberOfDeaths) {
        mPseudo = inPseudo;
        mNumberOfKills = inNumberOfKills;
        mNumberOfDeaths = inNumberOfDeaths;
    }
}
