/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asmr.dataAccess;

import asmr.Integration.JDBC.ConnectionBuilder;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.Personne;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class PersonneDAO extends SqlDAO<Personne> {
    private static final String INSERT = "INSERT INTO PERSONNE (IDPERSONNE, NOMPERSONNE, PRENOMPERSONNE) VALUES (?,?,?)";
    private static final String INSERTAUTOGUID = "INSERT INTO PERSONNE (IDPERSONNE, NOMPERSONNE, PRENOMPERSONNE) VALUES (SYS_GUID(),?,?)";
    private static final String UPDATE = "UPDATE PERSONNE SET NOMPERSONNE=?, PRENOMPERSONNE=? WHERE IDPERSONNE=?";
    private static final String DELETE = "DELETE FROM PERSONNE WHERE IDPERSONNE=?";
    private static final String SELECT = "SELECT * FROM PERSONNE WHERE IDPERSONNE=?";
    private static final String SELECTALL = "SELECT * FROM PERSONNE";

    public PersonneDAO() {
        super();
    }

    public boolean create(Personne obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERT);
            ps.setString(1, obj.getIdPersonne());
            ps.setString(2, obj.getNom());
            ps.setString(3, obj.getPrenom());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Personne : " + obj.getPrenom() + " " + obj.getNom() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Personne read(String idPersonne) {
        Personne personne = null;
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, idPersonne);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                personne = new Personne();
                personne.setIdPersonne(resultSet.getString("IDPERSONNE"));
                personne.setNom(resultSet.getString("NOMPERSONNE"));
                personne.setPrenom(resultSet.getString("PRENOMPERSONNE"));
            }
            LogHelper.info("Personne trouvée -> " + personne.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return personne;
    }

    public boolean update(Personne obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(UPDATE);
            ps.setString(1, obj.getNom());
            ps.setString(2, obj.getPrenom());
            ps.setString(3, obj.getIdPersonne());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Personne avec id : " + obj.getIdPersonne() + " a bien été mis à jour dans la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public boolean delete(Personne obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(DELETE);
            ps.setString(1, obj.getIdPersonne());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Personne avec id : " + obj.getIdPersonne() + " a bien été supprimé de la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public ArrayList<Personne> getAll(){
        ArrayList<Personne> personnes = new ArrayList<Personne>();
        try
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTALL);
            ResultSet resultSet = ps.executeQuery();
            LogHelper.info("Recupération de l'ensemble des Personnes en cours...");
            while(resultSet.next()){
                Personne personne = new Personne();
                personne.setIdPersonne(resultSet.getString("IDPERSONNE"));
                personne.setNom(resultSet.getString("NOMPERSONNE"));
                personne.setPrenom(resultSet.getString("PRENOMPERSONNE"));
                personnes.add(personne);
            }
            resultSet.close();
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e){
            LogHelper.error(e);
        }
        LogHelper.info("Récupération des Personnes terminées");
        return personnes;
    }
    
    public String createPersonneAutoGUID(Personne obj) {
        String guid = null;
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERTAUTOGUID, new String[]{"IDPERSONNE"});
            ps.setString(1, obj.getNom());
            ps.setString(2, obj.getPrenom());
            int i = ps.executeUpdate();
            if (i > 0) 
            {
                ResultSet rs = ps.getGeneratedKeys();
                while(rs.next())
                {
                    guid = rs.getString(1); 
                    System.out.println(guid);
                }
            }
            ps.close();
            LogHelper.info("Personne : " + obj.getPrenom() + " " + obj.getNom() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return null;
        }
        return guid;
    }
}