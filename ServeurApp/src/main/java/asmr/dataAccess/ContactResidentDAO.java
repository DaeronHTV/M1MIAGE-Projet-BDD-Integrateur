package asmr.dataAccess;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import asmr.Integration.JDBC.ConnectionBuilder;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.ContactResident;

import java.sql.ResultSet;

public class ContactResidentDAO extends SqlDAO<ContactResident>{
    private static final String INSERT = "INSERT INTO CONTACTRESIDENT (IDRESIDENT, IDCONTACT) VALUES (?,?)";
    private static final String UPDATE = "UPDATE CONTACTRESIDENT SET IDRESIDENT=? WHERE IDCONTACT=?";
    private static final String DELETE = "DELETE FROM CONTACTRESIDENT WHERE IDCONTACT=?";
    private static final String SELECT = "SELECT * FROM CONTACTRESIDENT WHERE IDCONTACT=?";

    public ContactResidentDAO() {
        super();
    }

    public boolean create(ContactResident obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERT);
            ps.setString(1, obj.getIdResident());
            ps.setString(2, obj.getIdContact());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Association Contact " + obj.getIdContact() + " avec Résident : " + obj.getIdResident() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;

    }

    @Override
    public ContactResident read(String idContact) {
        ContactResident contactResident = null;
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, idContact);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                contactResident = new ContactResident();
                contactResident.setIdContact(resultSet.getString("IDCONTACT"));
                contactResident.setIdResident(resultSet.getString("IDRESIDENT"));
            }
            LogHelper.info("Association Invité-RendezVous trouvé -> " + contactResident.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return contactResident;
    }

    public boolean update(ContactResident obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(UPDATE);
            ps.setString(1, obj.getIdResident());
            ps.setString(2, obj.getIdContact());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Association Contact " + obj.getIdContact() + " avec Résident : " + obj.getIdResident() + " a bien été mis à jour dans la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public boolean delete(ContactResident obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(DELETE);
            ps.setString(1, obj.getIdContact());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Association Contact " + obj.getIdContact() + " avec Résident : " + obj.getIdResident() + " a bien été supprimé de la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public ArrayList<ContactResident> getAllOfContact(String idContact) {
        ArrayList<ContactResident> contactResidentList = new ArrayList<>(); 
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, idContact);
            ResultSet resultSet = ps.executeQuery();
            ContactResident contactResident = null;
            while (resultSet.next()) {
                contactResident = new ContactResident();
                contactResident.setIdContact(resultSet.getString("IDCONTACT"));
                contactResident.setIdResident(resultSet.getString("IDRESIDENT"));
                contactResidentList.add(contactResident);
            }
            LogHelper.info("Association Contact-Resident trouvé -> " + contactResident.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return contactResidentList;
    }
}
