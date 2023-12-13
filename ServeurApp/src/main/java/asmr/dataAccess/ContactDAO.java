package asmr.dataAccess;

import asmr.Integration.JDBC.ConnectionBuilder;
import asmr.visiopad.Contact;
import asmr.dataUtilities.log.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class ContactDAO extends SqlDAO<Contact>{
    private static final String INSERT = "INSERT INTO CONTACT (IDCONTACT, COMPTEVALIDE) VALUES (?,?)";
    private static final String UPDATE = "UPDATE CONTACT SET COMPTEVALIDE=? WHERE IDCONTACT=?";
    private static final String DELETE = "DELETE FROM CONTACT WHERE IDCONTACT=?";
    private static final String SELECT = "SELECT * FROM CONTACT WHERE IDCONTACT=?";
    private static final String SELECTALL = "SELECT * FROM CONTACT";
    
    public ContactDAO() {
        super();
    }

    public boolean create(Contact obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERT);
            ps.setString(1, obj.getIdPersonne());
            ps.setInt(2, obj.getCompteValide());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Contact : " + obj.toString() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;

    }

    @Override
    public Contact read(String idContact) {
        Contact contact = null;
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, idContact);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                contact = new Contact();
                contact.setIdPersonne(resultSet.getString("IDCONTACT"));
                contact.setCompteValide(resultSet.getInt("COMPTEVALIDE"));
            }
            LogHelper.info("Contact trouvé -> " + contact.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return contact;
    }

    public boolean update(Contact obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(UPDATE);
            ps.setInt(1, obj.getCompteValide());
            ps.setString(2, obj.getIdPersonne());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Contact avec id : " + obj.getIdPersonne() + " a bien été mis à jour dans la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public boolean delete(Contact obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(DELETE);
            ps.setString(1, obj.getIdPersonne());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Contact avec id : " + obj.getIdPersonne() + " a bien été supprimé de la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public ArrayList<Contact> getAll() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTALL);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setIdPersonne(resultSet.getString("IDCONTACT"));
                contact.setCompteValide(resultSet.getInt("COMPTEVALIDE"));
                contacts.add(contact);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return contacts;
    }
}
