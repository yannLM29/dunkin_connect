package fr.yannlm29;

import java.util.Date;
public class ConnectedPlayer {
    private String mPseudo;
    private int mNumberOfKills;
    private int mNumberOfDeaths;
    private Date mSessionStart;

    public ConnectedPlayer(String inPseudo) {
        mPseudo = inPseudo;
        mNumberOfKills = 0;
        mNumberOfDeaths = 0;
        mSessionStart = new Date();
    }

    // -------------------- GET --------------------
    public String getPseudo() {
        return mPseudo;
    }
    
    public int getNumberOfKills() {
        return mNumberOfKills;
    }

    public int getNumberOfDeaths() {
        return mNumberOfDeaths;
    }

    public Date getSessionStart() {
        return mSessionStart;
    }

    public long getSessionDuration() {
        Date now = new Date();
        return now.getTime() - mSessionStart.getTime();
    }

    // -------------------- SET --------------------
    public void addKill() {
        mNumberOfKills++;
    }

    public void addDeath() {
        mNumberOfDeaths++;
    }
    
}
