/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asmr.Integration.JDBC;

import java.sql.DriverManager;
import java.sql.SQLException;

import asmr.dataUtilities.log.LogHelper;

import java.sql.Connection;

/**
 * Classe permettant la connexion à la base de données
 * Oracle de l'établissement
 * @author Mohammad Oubari
 */
public class ConnectionBuilder {

    private static final String URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
    private static final String USER = "avanzina";
    private static final String PASSWD = "Sangoku07";

    private static Connection connect;

    /**
     * Constructeur par defaut de la classe
     * @throws ClassNotFoundException Si le cast ne s'effectue pas correctement
     * @throws SQLException if a database access error occurs or the url is null
     */
    private ConnectionBuilder() throws ClassNotFoundException, SQLException {
        try {
            connect = (Connection) DriverManager.getConnection(URL, USER, PASSWD);

        } catch (SQLException e) {
            LogHelper.info("Erreur lors de la connexion à la base de données", e);
            System.out.println(e);
        }
    }

    /**
     * Créer si necessaire et renvoie l'instance de la 
     * classe Connection
     * @return L'instance de connexion
     */
    public static Connection getInstance() {
        if (connect == null) {
            try {
                connect = (Connection) DriverManager.getConnection(URL, USER, PASSWD);
            } catch (SQLException e) {
                LogHelper.error("Erreur lors de la connexion à la base de données", e);
            }
        }
        return connect;
    }
}
