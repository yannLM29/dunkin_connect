package fr.yannlm29;

public class ConnectedPlayer {
    private String mPseudo;
    private int mNumberOfKills;
    private int mNumberOfDeaths;

    public ConnectedPlayer(String inPseudo) {
        mPseudo = inPseudo;
        mNumberOfKills = 0;
        mNumberOfDeaths = 0;
    }

    // -------------------- GET --------------------
    public int getNumberOfKills() {
        return mNumberOfKills;
    }

    public int getNumberOfDeaths() {
        return mNumberOfDeaths;
    }

    // -------------------- SET --------------------
    public void addKill() {
        mNumberOfKills++;
    }

    public void addDeath() {
        mNumberOfDeaths++;
    }
}
