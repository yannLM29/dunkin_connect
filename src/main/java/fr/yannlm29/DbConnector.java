package fr.yannlm29;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.Date;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
public class DbConnector {

    private static final Logger LOGGER=Logger.getLogger("dunkin_connect");
    Connection mConnection = null;

    public void Connect(String inIp, int inPort, String inUsername, String inPassword) throws Exception, ClassNotFoundException, SQLException {
        if(inIp == null || inUsername == null || inPassword == null) {
            throw new Exception("Database config is wrong");
        }
        
        String url = "jdbc:mysql://" + inIp + ":" + inPort + "/dunkin_connect_db";

        Class.forName("com.mysql.cj.jdbc.Driver");
        mConnection = DriverManager.getConnection(url, inUsername, inPassword);
    }

    // -------------------- GET --------------------
    public ArrayList<DbPlayer> getPlayerList() {
        ArrayList<DbPlayer> players = new ArrayList<DbPlayer>();

        try {
            Statement statement = mConnection.createStatement();
            String sql_get_all_players_query = "SELECT pseudo, nb_of_kills, nb_of_deaths FROM players";

            ResultSet result = statement.executeQuery(sql_get_all_players_query);

            while(result.next()) {
                players.add(new DbPlayer(result.getString("pseudo"), result.getInt("nb_of_kills"), result.getInt("nb_of_deaths")));
            }

            return players;

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    public DbPlayer getPlayerInfo(String inPseudo) {
        if(!isPlayerInDb(inPseudo)) {
            LOGGER.info("Player not found: " + inPseudo);
            return null;
        }

        String sql_get_player_query = "SELECT pseudo, nb_of_kills, nb_of_deaths FROM players WHERE pseudo = ?";
        try {
            PreparedStatement prepared_statement = mConnection.prepareStatement(sql_get_player_query);
            prepared_statement.setString(1, inPseudo);

            ResultSet result = prepared_statement.executeQuery();

            if(result.next()) {
                DbPlayer p = new DbPlayer();

                p.mPseudo = result.getString("pseudo");
                p.mNumberOfKills = result.getInt("nb_of_kills");
                p.mNumberOfDeaths = result.getInt("nb_of_deaths");

                return p;
            }

        } catch (Exception e) {
            LOGGER.info("error getPlayerInfo: " + e.getMessage());
        }

        return null;
    }
    
    public Boolean isPlayerInDb(String inPseudo) {
        String sql_get_player_query = "SELECT id, pseudo FROM players WHERE pseudo = ?";
        try {
            PreparedStatement prepared_statement = mConnection.prepareStatement(sql_get_player_query);
            prepared_statement.setString(1, inPseudo);

            ResultSet result = prepared_statement.executeQuery();

            return result.next();

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    // -------------------- SET --------------------
    public Boolean addPlayer(String inPseudo) {
        String sql_get_player_query = "INSERT INTO players (pseudo) VALUES (?)";
        try {
            PreparedStatement prepared_statement = mConnection.prepareStatement(sql_get_player_query);
            prepared_statement.setString(1, inPseudo);

            int result = prepared_statement.executeUpdate();
            
            // LOGGER.info("res=" + );
            return true;

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    public Boolean updatePlayer(String inPseudo, int inNbOfKills, int inNbOfDeath) {
        
        String sql_get_player_query = "UPDATE players SET nb_of_kills = ?, nb_of_deaths = ? WHERE pseudo = ?";

        try {
            PreparedStatement prepared_statement = mConnection.prepareStatement(sql_get_player_query);
            prepared_statement.setInt(1, inNbOfKills);
            prepared_statement.setInt(2, inNbOfDeath);
            prepared_statement.setString(3, inPseudo);

            int result = prepared_statement.executeUpdate();
            return true;

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    public Boolean addSession(String inPseudo, Date inStart, long inDuration) {
        String sql_get_player_query = "INSERT INTO session (pseudo, start, duration) VALUES (?, ?, ?)";
        
        LOGGER.info("addSession with " + inStart + " " + inDuration);
        try {
            PreparedStatement prepared_statement = mConnection.prepareStatement(sql_get_player_query);
            prepared_statement.setString(1, inPseudo);
            prepared_statement.setTimestamp(2, new Timestamp(inStart.getTime()));

            int hours = (int) (inDuration / 3600000);
            int minutes = (int) ((inDuration % 3600000) / 60000);
            int seconds = (int) ((inDuration % 60000) / 1000);
            prepared_statement.setTime(3, new Time(hours, minutes, seconds));   //TODO: Needs rework

            int result = prepared_statement.executeUpdate();
            return true;

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }
}
