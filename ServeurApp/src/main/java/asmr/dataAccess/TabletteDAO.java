package asmr.dataAccess;

import asmr.Integration.JDBC.ConnectionBuilder;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.Tablette;
import asmr.visiopad.enumeration.EtatTablette;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class TabletteDAO extends SqlDAO<Tablette> {
    private static final String INSERT = "INSERT INTO TABLETTE (IDTABLETTE, ETATTABLETTE) VALUES (SYS_GUID(),?)";
    private static final String UPDATE = "UPDATE TABLETTE SET ETATTABLETTE=? WHERE IDTABLETTE=?";
    private static final String DELETE = "DELETE FROM TABLETTE WHERE IDTABLETTE=?";
    private static final String SELECT = "SELECT * FROM TABLETTE WHERE IDTABLETTE=?";
    private static final String SELECTALL = "SELECT * FROM TABLETTE";

    public TabletteDAO() {
        super();
    }

    public boolean create(Tablette obj) {
        try {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERT);
            ps.setString(1, obj.getEtatTablette().toString());
            ps.executeUpdate();
            ps.close();
            System.out.println("Tablette : " + obj.toString() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Tablette read(String id) {
        Tablette tablette = null;
        try {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                tablette = new Tablette();
                tablette.setIdTablette(resultSet.getString("IDTABLETTE"));
                tablette.setEtatTablette(EtatTablette.fromValue(resultSet.getString("ETATTABLETTE")));
            }
            LogHelper.info("Tablette trouvée -> " + tablette.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return tablette;
    }

    public boolean update(Tablette obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(UPDATE);
            ps.setString(1, obj.getEtatTablette().toString());
            ps.setString(2, obj.getIdTablette());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Tablette avec id : " + obj.getIdTablette() + " a bien été mis à jour dans la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public boolean delete(Tablette obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(DELETE);
            ps.setString(1, obj.getIdTablette());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Tablette avec id : " + obj.getIdTablette() + " a bien été supprimé de la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public ArrayList<Tablette> getAll() {
        ArrayList<Tablette> tablettes = new ArrayList<Tablette>();
        try {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTALL);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Tablette tablette = new Tablette();
                tablette.setIdTablette(resultSet.getString("IDTABLETTE"));
                tablette.setEtatTablette(EtatTablette.fromValue(resultSet.getString("ETATTABLETTE")));
                tablettes.add(tablette);
            }
            LogHelper.info("Tablette trouvée -> " + tablettes.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return tablettes;
    }
}
