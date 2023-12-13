package asmr.dataAccess;

import asmr.Integration.JDBC.ConnectionBuilder;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.Resident;
import asmr.visiopad.enumeration.StatutResident;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.ResultSet;

public class ResidentDAO extends SqlDAO<Resident> {
    private static final String INSERT = "INSERT INTO RESIDENT (IDRESIDENT, IDUNITE, IDPERSONNEL, DATENAISSANCERESIDENT, STATUTRESIDENT) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE RESIDENT SET IDUNITE=?, IDPERSONNEL=?, DATENAISSANCERESIDENT=?, STATUTRESIDENT=? WHERE IDRESIDENT=?";
    private static final String DELETE = "DELETE FROM RESIDENT WHERE IDRESIDENT=?";
    private static final String SELECT = "SELECT * FROM RESIDENT WHERE IDRESIDENT=?";
    private static final String SELECTALL = "SELECT * FROM RESIDENT";
    private static final String SELECTWITHBIRTHDAY = "SELECT * FROM RESIDENT WHERE DATENAISSANCERESIDENT=?";

    public ResidentDAO() {
        super();
    }

    public boolean create(Resident obj) {
        try {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERT);
            ps.setString(1, obj.getIdPersonne());
            ps.setString(2, obj.getIdUnite());
            ps.setString(3, obj.getIdPersonnel());
            ps.setTimestamp(4, new Timestamp(obj.getDateNaissanceResident().getTime()));
            ps.setString(5, obj.getStatutResident().toString());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Résident : " + obj.getPrenom() + " " + obj.getNom() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Resident read(String id) {
        Resident resident = null;
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                resident = new Resident();
                resident.setIdPersonne(resultSet.getString("IDRESIDENT"));
                resident.setIdUnite(resultSet.getString("IDUNITE"));
                resident.setIdPersonnel(resultSet.getString("IDPERSONNEL"));
                resident.setDateNaissanceResident(resultSet.getDate("DATENAISSANCERESIDENT"));
                resident.setStatutResident(StatutResident.fromValue(resultSet.getString("STATUTRESIDENT")));
            }
            LogHelper.info("Résident trouvé -> " + resident.toString());
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return resident;
    }

    public boolean update(Resident obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(UPDATE);
            ps.setString(1, obj.getIdUnite());
            ps.setString(2, obj.getIdPersonnel());
            ps.setTimestamp(3, new Timestamp(obj.getDateNaissanceResident().getTime()));
            ps.setString(4, obj.getStatutResident().toString());
            ps.setString(5, obj.getIdPersonne());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Résident avec id : " + obj.getIdPersonne() + " a bien été mis à jour dans la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public boolean delete(Resident obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(DELETE);
            ps.setString(1, obj.getIdPersonne());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Résident avec id : " + obj.getIdPersonne() + " a bien été supprimé de la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public ArrayList<Resident> getAll() {
        ArrayList<Resident> residents = new ArrayList<>();
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTALL);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Resident resident = new Resident();
                resident.setIdPersonne(resultSet.getString("IDRESIDENT"));
                resident.setIdUnite(resultSet.getString("IDUNITE"));
                resident.setIdPersonnel(resultSet.getString("IDPERSONNEL"));
                resident.setDateNaissanceResident(resultSet.getDate("DATENAISSANCERESIDENT"));
                resident.setStatutResident(StatutResident.fromValue(resultSet.getString("STATUTRESIDENT")));
                residents.add(resident);
            }
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return residents;
    }

    public ArrayList<Resident> getResidentWithBirthday(Resident obj) {
        ArrayList<Resident> residents = new ArrayList<>();
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTWITHBIRTHDAY);
            ps.setDate(1, obj.getDateNaissanceResident());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Resident resident = new Resident();
                resident.setIdPersonne(resultSet.getString("IDRESIDENT"));
                resident.setIdUnite(resultSet.getString("IDUNITE"));
                resident.setIdPersonnel(resultSet.getString("IDPERSONNEL"));
                resident.setDateNaissanceResident(resultSet.getDate("DATENAISSANCERESIDENT"));
                resident.setStatutResident(StatutResident.fromValue(resultSet.getString("STATUTRESIDENT")));
                residents.add(resident);
            }
            LogHelper.info("Résidents trouvé -> " + residents.toString());
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return residents;
    }
    
}
