package asmr.dataAccess;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import asmr.Integration.JDBC.ConnectionBuilder;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.Etablissement;

import java.sql.ResultSet;

public class EtablissementDAO extends SqlDAO<Etablissement> {
    private static final String INSERT = "INSERT INTO ETABLISSEMENT (IDETABLISSEMENT, NOMETABLISSEMENT, NUMEROTELEPHONEETABLISSEMENT, ADRESSEMAILETABLISSEMENT, RUEETABLISSEMENT, CODEPOSTALETABLISSEMENT, VILLEETABLISSEMENT, PAYSETABLISSEMENT, NBPLACES, NBUNITESOFF) VALUES (SYS_GUID(),?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE ETABLISSEMENT SET NOMETABLISSEMENT=?, NUMEROTELEPHONEETABLISSEMENT=?, ADRESSEMAILETABLISSEMENT=?, RUEETABLISSEMENT=?, CODEPOSTALETABLISSEMENT=?, VILLEETABLISSEMENT=?, PAYSETABLISSEMENT=?, NBPLACES=?, NBUNITESOFF=? WHERE IDETABLISSEMENT=?";
    private static final String DELETE = "DELETE FROM ETABLISSEMENT WHERE IDETABLISSEMENT=?";
    private static final String SELECT = "SELECT * FROM ETABLISSEMENT WHERE IDETABLISSEMENT=?";
    private static final String SELECTALL = "SELECT * FROM ETABLISSEMENT";
    private static final String SELECTBYNOM = "SELECT * FROM ETABLISSEMENT WHERE NOMETABLISSEMENT=?";

    public EtablissementDAO() {
        super();
    }

    public boolean create(Etablissement obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERT);
            ps.setString(1, obj.getNom());
            ps.setString(2, obj.getNumeroTelephone());
            ps.setString(3, obj.getAdresseMail());
            ps.setString(4, obj.getRue());
            ps.setInt(5, obj.getCodePostal());
            ps.setString(6, obj.getVille());
            ps.setString(7, obj.getPays());
            ps.setInt(8, obj.getNbPlaces());
            ps.setInt(9, obj.getNbUnitesOff());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Etablissement : " + obj.getNom() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Etablissement read(String idEtablissement) {
        Etablissement etablissement = new Etablissement();
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, idEtablissement);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                etablissement = new Etablissement();
                etablissement.setId(resultSet.getString("IDETABLISSEMENT"));
                etablissement.setNom(resultSet.getString("NOMETABLISSEMENT"));
                etablissement.setNumeroTelephone(resultSet.getString("NUMEROTELEPHONEETABLISSEMENT"));
                etablissement.setAdresseMail(resultSet.getString("ADRESSEMAILETABLISSEMENT"));
                etablissement.setRue(resultSet.getString("RUEETABLISSEMENT"));
                etablissement.setCodePostal(Integer.parseInt(resultSet.getString("CODEPOSTALETABLISSEMENT")));
                etablissement.setVille(resultSet.getString("VILLEETABLISSEMENT"));
                etablissement.setPays(resultSet.getString("PAYSETABLISSEMENT"));
                etablissement.setNbPlaces(resultSet.getInt("NBPLACES"));
                etablissement.setNbUnitesOff(resultSet.getInt("NBUNITESOFF"));
            }
            LogHelper.info("Etablissement trouvée -> " + etablissement.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return etablissement;
    }

    public boolean update(Etablissement obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(UPDATE);
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getNom());
            ps.setString(3, obj.getNumeroTelephone());
            ps.setString(4, obj.getAdresseMail());
            ps.setString(5, obj.getRue());
            ps.setInt(6, obj.getCodePostal());
            ps.setString(7, obj.getVille());
            ps.setString(8, obj.getPays());
            ps.setInt(9, obj.getNbPlaces());
            ps.setInt(10, obj.getNbUnitesOff());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Etablissement avec id : " + obj.getId() + " a bien été mis à jour dans la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public boolean delete(Etablissement obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(DELETE);
            ps.setString(1, obj.getId());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Etablissement avec id : " + obj.getId() + " a bien été supprimé de la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public ArrayList<Etablissement> getAll() {
        ArrayList<Etablissement> etablissements = new ArrayList<Etablissement>();
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTALL);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Etablissement etablissement = new Etablissement();
                etablissement.setId(resultSet.getString("IDETABLISSEMENT"));
                etablissement.setNom(resultSet.getString("NOMETABLISSEMENT"));
                etablissement.setNumeroTelephone(resultSet.getString("NUMEROTELEPHONEETABLISSEMENT"));
                etablissement.setAdresseMail(resultSet.getString("ADRESSEMAILETABLISSEMENT"));
                etablissement.setRue(resultSet.getString("RUEETABLISSEMENT"));
                etablissement.setCodePostal(Integer.parseInt(resultSet.getString("CODEPOSTALETABLISSEMENT")));
                etablissement.setVille(resultSet.getString("VILLEETABLISSEMENT"));
                etablissement.setPays(resultSet.getString("PAYSETABLISSEMENT"));
                etablissement.setNbPlaces(resultSet.getInt("NBPLACES"));
                etablissement.setNbUnitesOff(resultSet.getInt("NBUNITESOFF"));   
                etablissements.add(etablissement);
            }
            resultSet.close();
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return etablissements;
    }

    public Etablissement getByName(String nomEtablissement) {
        Etablissement etablissement = new Etablissement();
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTBYNOM);
            ps.setString(1, nomEtablissement);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                etablissement = new Etablissement();
                etablissement.setId(resultSet.getString("IDETABLISSEMENT"));
                etablissement.setNom(resultSet.getString("NOMETABLISSEMENT"));
                etablissement.setNumeroTelephone(resultSet.getString("NUMEROTELEPHONEETABLISSEMENT"));
                etablissement.setAdresseMail(resultSet.getString("ADRESSEMAILETABLISSEMENT"));
                etablissement.setRue(resultSet.getString("RUEETABLISSEMENT"));
                etablissement.setCodePostal(Integer.parseInt(resultSet.getString("CODEPOSTALETABLISSEMENT")));
                etablissement.setVille(resultSet.getString("VILLEETABLISSEMENT"));
                etablissement.setPays(resultSet.getString("PAYSETABLISSEMENT"));
                etablissement.setNbPlaces(resultSet.getInt("NBPLACES"));
                etablissement.setNbUnitesOff(resultSet.getInt("NBUNITESOFF"));
            }
            LogHelper.info("Etablissement trouvée -> " + etablissement.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return etablissement;
    }
}
