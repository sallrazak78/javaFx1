package service;

import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;
public class ClientDao {
	public static void addClient(Client c) throws Exception {
		try {
			DataBaseHelper db = DataBaseHelper.getInstance();
			String sql = "Insert into client values(null,?,?,?,?,?,?)";
			db.myPrepareStatement(sql);
			Object[] parameters = {c.getPiece(), c.getNom(), c.getPrenom() ,c.getDate().toString(), c.getAdresse(), c.getEmail()};
			db.addParameters(parameters);
			db.myExecuteUpdate();
			db.closeConnection();
		} catch (Exception e) {
			throw e;
		}
	}
	public static void supprimerClient(int id) throws Exception {
		try {
			DataBaseHelper db = DataBaseHelper.getInstance();
			String sql = "DELETE FROM client WHERE id=?";
			db.myPrepareStatement(sql);
			Object[] parameters = { id };
			db.addParameters(parameters);
			db.myExecuteUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public static void modifyClient(Client v) throws Exception {
		try {
			DataBaseHelper db = DataBaseHelper.getInstance();
			String sql = "UPDATE client SET numpiece=?,nom=?,prenom=?,dateNaiss=? ,email=?,adresse=? WHERE id=?";
			db.myPrepareStatement(sql);
			Object[] parameters = { v.getPiece(), v.getNom(), v.getPrenom(), v.getDate().toString(), v.getEmail(),v.getAdresse(),v.getId() };
			db.addParameters(parameters);
			db.myExecuteUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public static ObservableList<Client> getAllRecords() throws Exception {

		try {
			String sql = "SELECT * FROM client";
			DataBaseHelper db = DataBaseHelper.getInstance();
			db.myPrepareStatement(sql);
			ResultSet rs = db.myExecuteQuery();
			ObservableList<Client> vlist = getVoitureObjects(rs);
			db.closeConnection();
			return vlist;
		} catch (Exception e) {
			throw e;
		}
	}

	private static ObservableList<Client> getVoitureObjects(ResultSet rs) throws Exception {
		try {
			ObservableList<Client> vlist = FXCollections.observableArrayList();
			while (rs.next()) {
				Client v = new Client(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(2),rs.getDate(5).toLocalDate());
				vlist.add(v);
			}
			return vlist;

		} catch (Exception e) {
			throw e;
		}

	}
	
	
}
