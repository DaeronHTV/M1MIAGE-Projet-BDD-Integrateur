/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asmr.dataAccess;

import asmr.Integration.JDBC.ConnectionBuilder;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.Personnel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class PersonnelDAO extends SqlDAO<Personnel> {
    private static final String INSERT = "INSERT INTO PERSONNEL (IDPERSONNEL, FONCTION) VALUES (?,?)";
    private static final String UPDATE = "UPDATE PERSONNEL SET FONCTION=? WHERE IDPERSONNEL=?";
    private static final String DELETE = "DELETE FROM PERSONNEL WHERE IDPERSONNEL=?";
    private static final String SELECT = "SELECT * FROM PERSONNEL WHERE IDPERSONNEL=?";
    private static final String SELECTALL = "SELECT * FROM PERSONNEL";

    public PersonnelDAO() {
        super();
    }

    public boolean create(Personnel obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERT);
            ps.setString(1, obj.getIdPersonne());
            ps.setString(2, obj.getFonction());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Personnel : " + obj.getPrenom() + " " + obj.getNom() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Personnel read(String id) {
        Personnel personnel = null;
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                personnel = new Personnel();
                personnel.setIdPersonne(resultSet.getString("IDPERSONNEL"));
                personnel.setFonction(resultSet.getString("FONCTION"));
            }
            LogHelper.info("Personnel trouvé -> " + personnel.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return personnel;
    }

    public boolean update(Personnel obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(UPDATE);
            ps.setString(1, obj.getFonction());
            ps.setString(2, obj.getIdPersonne());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Personnel avec id : " + obj.getIdPersonne() + " a bien été mis à jour dans la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public boolean delete(Personnel obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(DELETE);
            ps.setString(1, obj.getIdPersonne());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Personnel avec id : " + obj.getIdPersonne() + " a bien été supprimé de la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public ArrayList<Personnel> getAll(){
        ArrayList<Personnel> personnels = new ArrayList<Personnel>();
        try 
        {
            LogHelper.info("Recupération de l'ensemble des Personnels en cours...");
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTALL);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Personnel personnel = new Personnel();
                personnel.setIdPersonne(resultSet.getString("IDPERSONNEL"));
                personnel.setFonction(resultSet.getString("FONCTION"));
                personnels.add(personnel);
            }
            resultSet.close();
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Recupération de l'ensemble des Personnels terminés !");
        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return personnels;
    }
}