package asmr.dataAccess;

import asmr.Integration.JDBC.ConnectionBuilder;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.InviteRendezVous;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class InviteRendezVousDAO extends SqlDAO<InviteRendezVous>{
    private static final String INSERT = "INSERT INTO INVITERENDEZVOUS (IDINVITE, IDRENDEZVOUS) VALUES (?,?)";
    private static final String UPDATE = "UPDATE INVITERENDEZVOUS SET IDRENDEZVOUS=? WHERE IDINVITE=?";
    private static final String DELETE = "DELETE FROM INVITERENDEZVOUS WHERE IDINVITE=?";
    private static final String SELECT = "SELECT * FROM INVITERENDEZVOUS WHERE IDINVITE=?";
    private static final String SELECTBYRENDEZVOUS = "SELECT * FROM INVITERENDEZVOUS WHERE IDRENDEZVOUS=?";

    public InviteRendezVousDAO() {
        super();
    }

    public boolean create(InviteRendezVous obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERT);
            ps.setString(1, obj.getIdInvite());
            ps.setString(2, obj.getIdRendezVous());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Association Invité " + obj.getIdInvite() + " avec RendezVous : " + obj.getIdRendezVous() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;

    }

    @Override
    public InviteRendezVous read(String id) {
        InviteRendezVous inviteRendezVous = null;
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                inviteRendezVous = new InviteRendezVous();
                inviteRendezVous.setIdRendezVous(resultSet.getString("IDRENDEZVOUS"));
                inviteRendezVous.setIdInvite(resultSet.getString("IDINVITE"));
            }
            LogHelper.info("Association Invité-RendezVous trouvé -> " + inviteRendezVous.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return inviteRendezVous;
    }

    public boolean update(InviteRendezVous obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(UPDATE);
            ps.setString(1, obj.getIdRendezVous());
            ps.setString(2, obj.getIdInvite());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Association Invité : " + obj.getIdInvite() + " avec RendezVous : " + obj.getIdRendezVous() + " a bien été mis à jour dans la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public boolean delete(InviteRendezVous obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(DELETE);
            ps.setString(1, obj.getIdInvite());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Association Invité : " + obj.getIdInvite() + " avec RendezVous : " + obj.getIdRendezVous() + " a bien été supprimé de la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public ArrayList<InviteRendezVous> getByRendezVous(String idRendezVous) {
        ArrayList<InviteRendezVous> lesInviteRendezVous = new ArrayList<>();
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTBYRENDEZVOUS);
            ps.setString(1, idRendezVous);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                InviteRendezVous inviteRendezVous = new InviteRendezVous();
                inviteRendezVous.setIdRendezVous(resultSet.getString("IDRENDEZVOUS"));
                inviteRendezVous.setIdInvite(resultSet.getString("IDINVITE"));
                lesInviteRendezVous.add(inviteRendezVous);
            }
            LogHelper.info("Association Invité-RendezVous trouvé -> " + lesInviteRendezVous.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return lesInviteRendezVous;
    }
}
