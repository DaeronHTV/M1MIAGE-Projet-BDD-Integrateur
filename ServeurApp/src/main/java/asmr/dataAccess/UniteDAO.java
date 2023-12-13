package asmr.dataAccess;

import asmr.Integration.JDBC.ConnectionBuilder;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.Unite;
import asmr.visiopad.enumeration.EtatUnite;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.ResultSet;

public class UniteDAO extends SqlDAO<Unite> {
    private static final String INSERT = "INSERT INTO UNITE (IDUNITE, IDETABLISSEMENT, NOMUNITE, ETATUNITE) VALUES (SYS_GUID(),?,?,?)";
    private static final String UPDATE = "UPDATE UNITE SET IDETABLISSEMENT=?, NOMUNITE=?, ETATUNITE=? WHERE IDUNITE=?";
    private static final String DELETE = "DELETE FROM UNITE WHERE IDUNITE=?";
    private static final String SELECT = "SELECT * FROM UNITE WHERE IDUNITE=?";
    private static final String SELECTALL = "SELECT * FROM UNITE";
    private static final String SELECTALLBYETB = "SELECT * FROM UNITE WHERE IDETABLISSEMENT=?";

    public UniteDAO() {
        super();
    }

    public boolean create(Unite obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERT);
            ps.setString(1, obj.getIdEtablissement());
            ps.setString(2, obj.getNomUnite());
            ps.setString(3, obj.getEtatUnite().toString());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Unité : " + obj.getNomUnite() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Unite read(String idUnite) {
        Unite unite = null;
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, idUnite);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                unite = new Unite();
                unite.setIdUnite(resultSet.getString("IDUNITE"));
                unite.setIdEtablissement(resultSet.getString("IDETABLISSEMENT"));
                unite.setNomUnite(resultSet.getString("NOMUNITE"));
                unite.setEtatUnite(EtatUnite.fromValue(resultSet.getString("ETATUNITE")));
            }
            LogHelper.info("Unité trouvée -> " + unite.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return unite;
    }

    public boolean update(Unite obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(UPDATE);
            ps.setString(1, obj.getIdEtablissement());
            ps.setString(2, obj.getNomUnite());
            ps.setString(3, obj.getEtatUnite().toString());
            ps.setString(4, obj.getIdUnite());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Unité avec id : " + obj.getNomUnite() + " a bien été mis à jour dans la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public boolean delete(Unite obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(DELETE);
            ps.setString(1, obj.getIdUnite());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Unité avec id : " + obj.getIdUnite() + " a bien été supprimé de la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public ArrayList<Unite> getAll(){
        ArrayList<Unite> unites = new ArrayList<Unite>();
        try
        {
            LogHelper.info("Recupération de l'ensemble des Unite en cours...");
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTALL);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Unite unite = new Unite();
                unite.setIdUnite(resultSet.getString("IDUNITE"));
                unite.setIdEtablissement(resultSet.getString("IDETABLISSEMENT"));
                unite.setNomUnite(resultSet.getString("NOMUNITE"));
                unite.setEtatUnite(EtatUnite.fromValue(resultSet.getString("ETATUNITE")));
                unites.add(unite);
            }
            resultSet.close();
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e){
            LogHelper.error(e);
        }
        LogHelper.info("Recupération de l'ensemble des Unites terminé !");
        return unites;
    }

    public ArrayList<Unite> getAllByEtablissement(String idEtablissement){
        ArrayList<Unite> unites = new ArrayList<Unite>();
        try
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTALLBYETB);
            ps.setString(1, idEtablissement);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Unite unite = new Unite();
                unite.setIdUnite(resultSet.getString("IDUNITE"));
                unite.setIdEtablissement(resultSet.getString("IDETABLISSEMENT"));
                unite.setNomUnite(resultSet.getString("NOMUNITE"));
                unite.setEtatUnite(EtatUnite.fromValue(resultSet.getString("ETATUNITE")));
                unites.add(unite);
            }
            resultSet.close();
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e){
            LogHelper.error(e);
        }
        LogHelper.info("Recupération de l'ensemble des Unites terminé !");
        return unites;
    }
}
