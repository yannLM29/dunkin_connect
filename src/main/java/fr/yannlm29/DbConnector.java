package fr.yannlm29;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnector {

    Connection mConnection = null;
    Statement mStatement = null;
    ResultSet mResultSet = null;
    

    public void Connect(String inIp, int inPort, String inUsername, String inPassword) throws Exception, ClassNotFoundException, SQLException {
        if(inIp == null || inUsername == null || inPassword == null) {
            throw new Exception("Database config is wrong");
        }

        String url = "jdbc:mysql://" + inIp + ":" + inPassword + "/dunkin_connect_db";

        Class.forName("com.mysql.cj.jdbc.Driver");
        mConnection = DriverManager.getConnection(url, inUsername, inPassword);
    }
}
