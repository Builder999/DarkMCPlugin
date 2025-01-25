package com.bubbaj2016.darkmc;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyHandler {
    public static void registerPlayer(String playerUUID){
        try  {
            Connection conn = DriverManager.getConnection(Plugin.url);
            String sql = "INSERT INTO money VALUES(?,0);";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, playerUUID);
            pstm.executeUpdate();
            conn.close();
        } 
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void modMoneyPlayerMoney(String playerUUID, int modAmmount){
        try {
        Connection conn2 = DriverManager.getConnection(Plugin.url);
        String sql = "UPDATE money SET money = ? WHERE playerUUID = ?;";
        PreparedStatement pstm = conn2.prepareStatement(sql);
        pstm.setString(2, playerUUID);
        pstm.setInt(1, loadPlayerBalance(playerUUID)+ modAmmount);
        pstm.executeUpdate();
        conn2.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static int loadPlayerBalance(String playerID){
        int balance = -1;
        try {
            Connection conn2 = DriverManager.getConnection(Plugin.url);
            String sql = "SELECT money FROM money WHERE playerUUID=?;";
            PreparedStatement pstm = conn2.prepareStatement(sql);
            pstm.setString(1, playerID);
            ResultSet set = pstm.executeQuery();
            if (set.next()){
                balance = set.getInt(1);
            }
            conn2.close();
            return balance;
              
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }
}
