/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asmr.dataAccess;

import asmr.Integration.JDBC.ConnectionBuilder;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class UtilisateurDAO extends SqlDAO<Utilisateur> {
    private static final String INSERT = "INSERT INTO UTILISATEUR (IDUTILISATEUR, NUMEROTELEPHONEUTILISATEUR, ADRESSEMAILUTILISATEUR) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE UTILISATEUR SET NUMEROTELEPHONEUTILISATEUR=?, ADRESSEMAILUTILISATEUR=?  WHERE IDUTILISATEUR=?";
    private static final String DELETE = "DELETE FROM UTILISATEUR WHERE IDUTILISATEUR=?";
    private static final String SELECT = "SELECT * FROM UTILISATEUR WHERE IDUTILISATEUR=?";
    private static final String SELECTALL = "SELECT * FROM UTILISATEUR";
    private static final String SELECTBYEMAIL = "SELECT * FROM UTILISATEUR WHERE ADRESSEMAILUTILISATEUR=?";


    public UtilisateurDAO() {
        super();
    }

    public boolean create(Utilisateur obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERT);
            ps.setString(1, obj.getIdPersonne());
            ps.setString(2, obj.getNumeroTelephone());
            ps.setString(3, obj.getAdresseMail());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Utilisateur : " + obj.getPrenom() + " " + obj.getNom() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;

    }

    @Override
    public Utilisateur read(String id) {
        Utilisateur utilisateur = null;
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                utilisateur = new Utilisateur();
                utilisateur.setIdPersonne(resultSet.getString("IDUTILISATEUR"));
                utilisateur.setNumeroTelephone(resultSet.getString("NUMEROTELEPHONEUTILISATEUR"));
                utilisateur.setAdresseMail(resultSet.getString("ADRESSEMAILUTILISATEUR"));
            }
            LogHelper.info("Utilisateur trouvé -> " + utilisateur.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return utilisateur;
    }

    public boolean update(Utilisateur obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(UPDATE);
            ps.setString(1, obj.getNumeroTelephone());
            ps.setString(2, obj.getAdresseMail());
            ps.setString(3, obj.getIdPersonne());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Utilisateur avec id : " + obj.getIdPersonne() + " a bien été mis à jour dans la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public boolean delete(Utilisateur obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(DELETE);
            ps.setString(1, obj.getIdPersonne());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Utilisateur avec id : " + obj.getIdPersonne() + " a bien été supprimé de la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public ArrayList<Utilisateur> getAll() {
        ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        try 
        {
            LogHelper.info("Recupération de l'ensemble des Utilisateurs en cours...");
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTALL);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setIdPersonne(resultSet.getString("IDUTILISATEUR"));
                utilisateur.setNumeroTelephone(resultSet.getString("NUMEROTELEPHONEUTILISATEUR"));
                utilisateur.setAdresseMail(resultSet.getString("ADRESSEMAILUTILISATEUR"));
                utilisateurs.add(utilisateur);
            }
            resultSet.close();
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        LogHelper.info("Récupération des Utilisateurs terminées");
        return utilisateurs;
    }

    public Utilisateur getByEmail(String email) {
        Utilisateur utilisateur = new Utilisateur();
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTBYEMAIL);
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                utilisateur = new Utilisateur();
                utilisateur.setIdPersonne(resultSet.getString("IDUTILISATEUR"));
                utilisateur.setNumeroTelephone(resultSet.getString("NUMEROTELEPHONEUTILISATEUR"));
                utilisateur.setAdresseMail(resultSet.getString("ADRESSEMAILUTILISATEUR"));
            }
            LogHelper.info("Utilisateur trouvé -> " + utilisateur.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return utilisateur;
    }

}