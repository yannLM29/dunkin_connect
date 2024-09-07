package fr.yannlm29;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public ArrayList<String> getPlayerList() {
        ArrayList<String> players = new ArrayList<String>();

        try {
            Statement statement = mConnection.createStatement();
            String sql_get_all_players_query = "SELECT pseudo FROM players";

            ResultSet result = statement.executeQuery(sql_get_all_players_query);

            while(result.next()) {
                players.add(result.getString("pseudo"));
            }

            return players;

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }
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
}
