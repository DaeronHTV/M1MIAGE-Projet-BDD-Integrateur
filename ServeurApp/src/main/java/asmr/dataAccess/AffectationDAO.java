package asmr.dataAccess;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import asmr.Integration.JDBC.ConnectionBuilder;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.Affectation;

import java.sql.ResultSet;

public class AffectationDAO extends SqlDAO<Affectation> {
    private static final String INSERT = "INSERT INTO AFFECTATION (IDAFFECTATION, IDPERSONNEL, IDUNITE, DATEDEBUT, DATEFIN) VALUES (SYS_GUID(),?,?,?,?)";
    private static final String UPDATE = "UPDATE AFFECTATION SET IDPERSONNEL=?, IDUNITE=?, DATEDEBUT=?, DATEFIN=? WHERE IDAFFECTATION=?";
    private static final String DELETE = "DELETE FROM AFFECTATION WHERE IDAFFECTATION=?";
    private static final String SELECT = "SELECT * FROM AFFECTATION WHERE IDAFFECTATION=?";

    public AffectationDAO() {
        super();
    }

    public boolean create(Affectation obj) {
        try {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERT);
            ps.setString(1, obj.getIdPersonnel());
            ps.setString(2, obj.getIdUnite());
            ps.setTimestamp(3, new Timestamp(obj.getDateDebut().getTime()));
            ps.setTimestamp(4, new Timestamp(obj.getDateFin().getTime()));
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Association Affectation personnel : " + obj.getIdPersonnel() + " à unité : " + obj.getIdUnite() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;

    }

    @Override
    public Affectation read(String id) {
        Affectation affectation = null;
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                affectation = new Affectation();
                affectation.setIdAffectation(resultSet.getString("IDAFFECTATION"));
                affectation.setIdPersonnel(resultSet.getString("IDPERSONNEL"));
                affectation.setIdUnite(resultSet.getString("IDUNITE"));
                affectation.setDateDebut(new Date(resultSet.getTimestamp("DATEDEBUT").getTime()));
                affectation.setDateFin(new Date(resultSet.getTimestamp("DATEFIN").getTime()));
            }
            LogHelper.info("Association Affectation trouvé -> " + affectation.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return affectation;
    }

    public boolean update(Affectation obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(UPDATE);
            ps.setString(1, obj.getIdUnite());
            ps.setTimestamp(2, new Timestamp(obj.getDateDebut().getTime()));
            ps.setTimestamp(3, new Timestamp(obj.getDateFin().getTime()));
            ps.setString(4, obj.getIdPersonnel());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Association Affectation personnel : " + obj.getIdPersonnel() + " à unité : " + obj.getIdUnite() + " a bien été mis à jour dans la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public boolean delete(Affectation obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(DELETE);
            ps.setString(1, obj.getIdAffectation());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Association Affectation personnel : " + obj.getIdPersonnel() + " à unité : " + obj.getIdUnite() + " a bien été supprimé de la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }
}
