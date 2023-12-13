package asmr.dataAccess;

import asmr.Integration.JDBC.ConnectionBuilder;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.CreneauVisio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class CreneauVisioDAO extends SqlDAO<CreneauVisio> {
    private static final String INSERT = "INSERT INTO CRENEAUVISIO (IDCRENEAU, IDPERSONNEL, DATECRENEAU, DISPONIBLE, DUREECRENEAU) VALUES (SYS_GUID(),?,?,?,?)";
    private static final String UPDATE = "UPDATE CRENEAUVISIO SET IDPERSONNEL=?, DATECRENEAU=?, DISPONIBLE=?, DUREECRENEAU=? WHERE IDCRENEAU=?";
    private static final String DELETE = "DELETE FROM CRENEAUVISIO WHERE IDCRENEAU=?";
    private static final String SELECT = "SELECT * FROM CRENEAUVISIO WHERE IDCRENEAU=?";
    private static final String SELECTOFPERSONNEL = "SELECT * FROM CRENEAUVISIO WHERE IDPERSONNEL=?";

    public CreneauVisioDAO() {
        super();
    }

    public boolean create(CreneauVisio obj) {
        try {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERT);
            ps.setString(1, obj.getIdPersonnel());
            ps.setTimestamp(2, new Timestamp(obj.getDateCreneau().getTime()));
            ps.setInt(3, obj.getDisponisble());
            ps.setInt(4, obj.getDureeCreneau());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Créneau : " + obj.toString() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    @Override
    public CreneauVisio read(String id) {
        CreneauVisio creneau = null;
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                creneau = new CreneauVisio();
                creneau.setIdCreneau(resultSet.getString("IDCRENEAU"));
                creneau.setIdPersonnel(resultSet.getString("IDPERSONNEL"));
                creneau.setDateCreneau(new Date(resultSet.getTimestamp("DATECRENEAU").getTime()));
                creneau.setDureeCreneau(resultSet.getInt("DUREECRENEAU"));
                creneau.setDisponisble(resultSet.getInt("DISPONIBLE"));
            }
            LogHelper.info("Créneau trouvé -> " + creneau.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return creneau;
    }

    public boolean update(CreneauVisio obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(UPDATE);
            ps.setString(1, obj.getIdPersonnel());
            ps.setTimestamp(2, new Timestamp(obj.getDateCreneau().getTime()));
            ps.setInt(3, obj.getDisponisble());
            ps.setInt(4, obj.getDureeCreneau());
            ps.setString(5, obj.getIdCreneau());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Créneau avec id : " + obj.getIdCreneau() + " a bien été mis à jour dans la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public boolean delete(CreneauVisio obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(DELETE);
            ps.setString(1, obj.getIdCreneau());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Créneau avec id : " + obj.getIdCreneau() + " a bien été supprimé de la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public ArrayList<CreneauVisio> getAllOfPersonnel(String idPersonnel) {
        ArrayList<CreneauVisio> creneauVisioList = new ArrayList<>(); 
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTOFPERSONNEL);
            ps.setString(1, idPersonnel);
            ResultSet resultSet = ps.executeQuery();
            CreneauVisio creneau = null;
            while (resultSet.next()) {
                creneau = new CreneauVisio();
                creneau.setIdCreneau(resultSet.getString("IDCRENEAU"));
                creneau.setIdPersonnel(resultSet.getString("IDPERSONNEL"));
                creneau.setDateCreneau(new Date(resultSet.getTimestamp("DATECRENEAU").getTime()));
                creneau.setDureeCreneau(resultSet.getInt("DUREECRENEAU"));
                creneau.setDisponisble(resultSet.getInt("DISPONIBLE"));
                creneauVisioList.add(creneau);
            }
            System.out.println("Créneau Personnel trouvé -> " + creneau.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return creneauVisioList;
    }
}
