package asmr.dataAccess;

import asmr.Integration.JDBC.ConnectionBuilder;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.Invite;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class InviteDAO extends SqlDAO<Invite> {
    private static final String INSERT = "INSERT INTO INVITE (IDINVITE, ADRESSEMAILINVITE) VALUES (?,?)";
    private static final String UPDATE = "UPDATE INVITE SET ADRESSEMAILINVITE=? WHERE IDINVITE=?";
    private static final String DELETE = "DELETE FROM INVITE WHERE IDINVITE=?";
    private static final String SELECT = "SELECT * FROM INVITE WHERE IDINVITE=?";
    private static final String SELECTBYMAIL = "SELECT * FROM INVITE WHERE ADRESSEMAILINVITE=?";


    public InviteDAO() {
        super();
    }

    public boolean create(Invite obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERT);
            ps.setString(1, obj.getIdPersonne());
            ps.setString(2, obj.getAdresseMailInvite());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Invité : " + obj.getPrenom() + " " + obj.getNom() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;

    }

    @Override
    public Invite read(String id) {
        Invite invite = null;
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                invite = new Invite();
                invite.setIdPersonne(resultSet.getString("IDINVITE"));
                invite.setAdresseMailInvite(resultSet.getString("ADRESSEMAILINVITE"));
            }
            LogHelper.info("Invité trouvé -> " + invite.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return invite;
    }

    public boolean update(Invite obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(UPDATE);
            ps.setString(1, obj.getAdresseMailInvite());
            ps.setString(2, obj.getIdPersonne());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Invité avec id : " + obj.getIdPersonne() + " a bien été mis à jour dans la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public boolean delete(Invite obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(DELETE);
            ps.setString(1, obj.getIdPersonne());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Invité avec id : " + obj.getIdPersonne() + " a bien été supprimé de la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public Invite getByEmail(String mail) {
        Invite invite = null;
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTBYMAIL);
            ps.setString(1, mail);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                invite = new Invite();
                invite.setIdPersonne(resultSet.getString("IDINVITE"));
                invite.setAdresseMailInvite(resultSet.getString("ADRESSEMAILINVITE"));
            }
            LogHelper.info("Invité trouvé -> " + invite.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return invite;
    }
}
