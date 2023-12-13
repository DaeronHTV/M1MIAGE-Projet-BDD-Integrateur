package asmr.dataAccess;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import asmr.Integration.JDBC.ConnectionBuilder;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.PersonnelResident;

import java.sql.ResultSet;

public class PersonnelResidentDAO extends SqlDAO<PersonnelResident>{
    private static final String INSERT = "INSERT INTO PERSONNELRESIDENT (IDPERSONNELRESIDENT, IDRESIDENT, IDPERSONNEL, DATEDEBUT, DATEFIN) VALUES (SYS_GUID(),?,?,?,?)";
    private static final String UPDATE = "UPDATE PERSONNELRESIDENT SET IDRESIDENT, IDPERSONNEL=?, DATEDEBUT=?, DATEFIN=? WHERE IDPERSONNELRESIDENT=?";
    private static final String DELETE = "DELETE FROM PERSONNELRESIDENT WHERE IDPERSONNELRESIDENT=?";
    private static final String SELECT = "SELECT * FROM PERSONNELRESIDENT WHERE IDPERSONNELRESIDENT=?";
    private static final String SELECTBYPERSONNEL = "SELECT * FROM PERSONNELRESIDENT WHERE IDPERSONNEL=?";
    private static final String SELECTBYRESIDENT = "SELECT * FROM PERSONNELRESIDENT WHERE IDRESIDENT=?";

    public PersonnelResidentDAO() {
        super();
    }

    public boolean create(PersonnelResident obj) {
        try {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERT);
            ps.setString(1, obj.getIdResident());
            ps.setString(2, obj.getIdPersonnel());
            ps.setTimestamp(3, new Timestamp(obj.getDateDebut().getTime()));
            ps.setTimestamp(4, new Timestamp(obj.getDateFin().getTime()));
            ps.executeUpdate();
            ps.close();
            System.out.println("Association Personnel : " + obj.getIdPersonnel() + " Resident : " + obj.getIdResident() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;

    }

    @Override
    public PersonnelResident read(String id) {
        PersonnelResident personnelResident = null;
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                personnelResident = new PersonnelResident();
                personnelResident.setIdPersonnelResident(resultSet.getString("IDPERSONNELRESIDENT"));
                personnelResident.setIdPersonnel(resultSet.getString("IDPERSONNEL"));
                personnelResident.setIdResident(resultSet.getString("IDRESIDENT"));
                personnelResident.setDateDebut(new Date(resultSet.getTimestamp("DATEDEBUT").getTime()));
                personnelResident.setDateFin(new Date(resultSet.getTimestamp("DATEFIN").getTime()));
            }
            LogHelper.info("Personnel-Resident trouvé -> " + personnelResident.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return personnelResident;
    }

    public boolean update(PersonnelResident obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(UPDATE);
            ps.setString(1, obj.getIdResident());
            ps.setString(2, obj.getIdPersonnel());
            ps.setTimestamp(3, new Timestamp(obj.getDateDebut().getTime()));
            ps.setTimestamp(4, new Timestamp(obj.getDateFin().getTime()));
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Association Personnel : " + obj.getIdPersonnel() + " Resident : " + obj.getIdResident() + " a bien été mis à jour dans la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public boolean delete(PersonnelResident obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(DELETE);
            ps.setString(1, obj.getIdPersonnelResident());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Association Personnel : " + obj.getIdPersonnel() + " Resident : " + obj.getIdResident() + " a bien été supprimé de la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public ArrayList<PersonnelResident> getByPersonnel(String idPersonnel) {
        ArrayList<PersonnelResident> personnelResidents = new ArrayList<>();
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTBYPERSONNEL);
            ps.setString(1, idPersonnel);
            ResultSet resultSet = ps.executeQuery();
            LogHelper.info(resultSet.toString());
            while (resultSet.next()) {
                PersonnelResident personnelResident = new PersonnelResident();
                personnelResident.setIdPersonnelResident(resultSet.getString("IDPERSONNELRESIDENT"));
                personnelResident.setIdPersonnel(resultSet.getString("IDPERSONNEL"));
                personnelResident.setIdResident(resultSet.getString("IDRESIDENT"));
                if (resultSet.getTimestamp("DATEDEBUT") != null){
                    personnelResident.setDateDebut(new Date(resultSet.getTimestamp("DATEDEBUT").getTime()));
                }
                if (resultSet.getTimestamp("DATEFIN") != null){
                    personnelResident.setDateFin(new Date(resultSet.getTimestamp("DATEFIN").getTime()));
                }
                personnelResidents.add(personnelResident);
            }
            LogHelper.info("Personnel-Resident trouvé -> " + personnelResidents.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return personnelResidents;
    }

    public ArrayList<PersonnelResident> getByResident(String idResident) {
        ArrayList<PersonnelResident> personnelResidents = new ArrayList<>();
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTBYRESIDENT);
            ps.setString(1, idResident);
            ResultSet resultSet = ps.executeQuery();
            LogHelper.info(resultSet.toString());
            while (resultSet.next()) {
                PersonnelResident personnelResidento = new PersonnelResident();
                personnelResidento.setIdPersonnelResident(resultSet.getString("IDPERSONNELRESIDENT"));
                personnelResidento.setIdPersonnel(resultSet.getString("IDPERSONNEL"));
                personnelResidento.setIdResident(resultSet.getString("IDRESIDENT"));
                if (resultSet.getTimestamp("DATEDEBUT") != null){
                    personnelResidento.setDateDebut(new Date(resultSet.getTimestamp("DATEDEBUT").getTime()));
                }
                if (resultSet.getTimestamp("DATEFIN") != null){
                    personnelResidento.setDateFin(new Date(resultSet.getTimestamp("DATEFIN").getTime()));
                }
                personnelResidents.add(personnelResidento);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return personnelResidents;
    }
}
