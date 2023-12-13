package asmr.dataAccess;

import asmr.Integration.JDBC.ConnectionBuilder;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.RendezVous;
import asmr.visiopad.enumeration.EtatRDV;
import asmr.visiopad.enumeration.StatusRDV;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;

public class RendezVousDAO extends SqlDAO<RendezVous> {
    private static final String INSERT = "INSERT INTO RENDEZVOUS (IDRENDEZVOUS, IDRESIDENT, IDCRENEAU, IDCONTACT, IDTABLETTE, IDREMPLACANT, DATECRENEAU, HEUREDEBUTRDV, HEUREFINRDV, STATUTRENDEZVOUS, ETATRENDEZVOUS, AVISNBETOILE, COMMENTAIRE) VALUES (SYS_GUID(),?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE RENDEZVOUS SET STATUTRENDEZVOUS=?, ETATRENDEZVOUS=? WHERE IDRENDEZVOUS=?";
    private static final String DELETE = "DELETE FROM RENDEZVOUS WHERE IDRENDEZVOUS=?";
    private static final String SELECT = "SELECT * FROM RENDEZVOUS WHERE IDRENDEZVOUS=?";
    private static final String SELECTBYCRENEAU = "SELECT * FROM RENDEZVOUS WHERE IDCRENEAU=?";
    private static final String SELECTBYCONTACT = "SELECT * FROM RENDEZVOUS WHERE IDCONTACT=?";

    public RendezVousDAO() {
        super();
    }

    public boolean create(RendezVous obj) {
        try {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(INSERT);
            ps.setString(1, obj.getIdResident()); System.out.println(obj.getIdResident());
            ps.setString(2, obj.getIdCreneau()); System.out.println(obj.getIdCreneau());
            ps.setString(3, obj.getIdContact());System.out.println(obj.getIdContact());
            ps.setString(4, obj.getIdTablette());System.out.println(obj.getIdTablette());
            ps.setString(5, obj.getIdRemplacant());System.out.println( obj.getIdRemplacant());
            ps.setTimestamp(6, new Timestamp(obj.getDateCreneau().getTime()));System.out.println( new Timestamp(obj.getDateCreneau().getTime()));
            ps.setString(7, obj.getHeureDebutRDV());System.out.println( obj.getHeureDebutRDV());
            ps.setString(8, obj.getHeureFinRDV());System.out.println(obj.getHeureFinRDV());
            ps.setString(9, obj.getStatutRendezVous().toString());System.out.println(obj.getStatutRendezVous().toString());
            ps.setString(10, obj.getEtatRendezVous().toString());System.out.println(obj.getEtatRendezVous().toString());
            ps.setInt(11, obj.getAvisNbEtoile());System.out.println(obj.getAvisNbEtoile());
            ps.setString(12, obj.getCommentaire());System.out.println(obj.getCommentaire());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Rendez-Vous : " + obj.toString() + " a bien été ajouté à la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    @Override
    public RendezVous read(String id) {
        RendezVous rendezVous = null;
        try {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECT);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                rendezVous = new RendezVous();
                rendezVous.setIdRendezVous(resultSet.getString("IDRENDEZVOUS"));
                rendezVous.setIdResident(resultSet.getString("IDRESIDENT"));
                rendezVous.setIdCreneau(resultSet.getString("IDCRENEAU"));
                rendezVous.setIdContact(resultSet.getString("IDCONTACT"));
                rendezVous.setIdTablette(resultSet.getString("IDTABLETTE"));
                rendezVous.setIdRemplacant(resultSet.getString("IDREMPLACANT"));
                rendezVous.setDateCreneau(new Date(resultSet.getTimestamp("DATECRENEAU").getTime()));
                rendezVous.setHeureDebutRDV(resultSet.getString("HEUREDEBUTRDV"));
                rendezVous.setHeureFinRDV(resultSet.getString("HEUREFINRDV"));
                rendezVous.setStatutRendezVous(StatusRDV.fromValue(resultSet.getString("STATUTRENDEZVOUS")));
                rendezVous.setEtatRendezVous(EtatRDV.fromValue(resultSet.getString("ETATRENDEZVOUS")));
                rendezVous.setAvisNbEtoile(resultSet.getInt("AVISNBETOILE"));
                rendezVous.setCommentaire(resultSet.getString("COMMENTAIRE"));
            }
            LogHelper.info("RendezVous trouvé -> " + rendezVous.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return rendezVous;
    }

    public boolean update(RendezVous obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(UPDATE);
            ps.setString(1, obj.getStatutRendezVous().toString());
            ps.setString(2, obj.getEtatRendezVous().toString());
            ps.setString(3, obj.getIdRendezVous());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Rendez-Vous avec id : " + obj.getIdRendezVous() + " a bien été mis à jour dans la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public boolean delete(RendezVous obj) {
        try 
        {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(DELETE);
            ps.setString(1, obj.getIdRendezVous());
            ps.executeUpdate();
            ps.close();
            LogHelper.info("Personnel avec id : " + obj.getIdRendezVous() + " a bien été supprimé de la BD.");
        } catch (SQLException e) {
            LogHelper.error(e);
            return false;
        }
        return true;
    }

    public RendezVous getByCreneau(String id) {
        RendezVous rendezVous = new RendezVous();
        try {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTBYCRENEAU);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                rendezVous = new RendezVous();
                rendezVous.setIdRendezVous(resultSet.getString("IDRENDEZVOUS"));
                rendezVous.setIdResident(resultSet.getString("IDRESIDENT"));
                rendezVous.setIdCreneau(resultSet.getString("IDCRENEAU"));
                rendezVous.setIdContact(resultSet.getString("IDCONTACT"));
                rendezVous.setIdTablette(resultSet.getString("IDTABLETTE"));
                rendezVous.setIdRemplacant(resultSet.getString("IDREMPLACANT"));
                rendezVous.setDateCreneau(new Date(resultSet.getTimestamp("DATECRENEAU").getTime()));
                rendezVous.setHeureDebutRDV(resultSet.getString("HEUREDEBUTRDV"));
                rendezVous.setHeureFinRDV(resultSet.getString("HEUREFINRDV"));
                rendezVous.setStatutRendezVous(StatusRDV.fromValue(resultSet.getString("STATUTRENDEZVOUS")));
                rendezVous.setEtatRendezVous(EtatRDV.fromValue(resultSet.getString("ETATRENDEZVOUS")));
                rendezVous.setAvisNbEtoile(resultSet.getInt("AVISNBETOILE"));
                rendezVous.setCommentaire(resultSet.getString("COMMENTAIRE"));
            }
            LogHelper.info("RendezVous trouvé -> " + rendezVous.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return rendezVous;
    }

    public ArrayList<RendezVous> getByContact(String idContact) {
        ArrayList<RendezVous> lesRendezVous = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectionBuilder.getInstance().prepareStatement(SELECTBYCONTACT);
            ps.setString(1, idContact);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                RendezVous rendezVous = new RendezVous();
                rendezVous.setIdRendezVous(resultSet.getString("IDRENDEZVOUS"));
                rendezVous.setIdResident(resultSet.getString("IDRESIDENT"));
                rendezVous.setIdCreneau(resultSet.getString("IDCRENEAU"));
                rendezVous.setIdContact(resultSet.getString("IDCONTACT"));
                rendezVous.setIdTablette(resultSet.getString("IDTABLETTE"));
                rendezVous.setIdRemplacant(resultSet.getString("IDREMPLACANT"));
                rendezVous.setDateCreneau(new Date(resultSet.getTimestamp("DATECRENEAU").getTime()));
                rendezVous.setHeureDebutRDV(resultSet.getString("HEUREDEBUTRDV"));
                rendezVous.setHeureFinRDV(resultSet.getString("HEUREFINRDV"));
                rendezVous.setStatutRendezVous(StatusRDV.fromValue(resultSet.getString("STATUTRENDEZVOUS")));
                rendezVous.setEtatRendezVous(EtatRDV.fromValue(resultSet.getString("ETATRENDEZVOUS")));
                rendezVous.setAvisNbEtoile(resultSet.getInt("AVISNBETOILE"));
                rendezVous.setCommentaire(resultSet.getString("COMMENTAIRE"));
                lesRendezVous.add(rendezVous);
            }
            LogHelper.info("RendezVous trouvé -> " + lesRendezVous.toString());
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            LogHelper.error(e);
        }
        return lesRendezVous;
    }
}
