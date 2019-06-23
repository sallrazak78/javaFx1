package service;

import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Vehicule;

public class VehiculeDao {
	public static void addVehicule(Vehicule v) throws Exception {
		try {
			DataBaseHelper db = DataBaseHelper.getInstance();
			String sql = "Insert into vehicule values(null,?,?,?,?,?)";
			db.myPrepareStatement(sql);
			Object[] parameters = { v.getMatricule(), v.getMarque(), v.getModele(), v.getCouleur(), v.getPrix() };
			db.addParameters(parameters);
			db.myExecuteUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public static void supprimerVehicule(int id) throws Exception {
		try {
			DataBaseHelper db = DataBaseHelper.getInstance();
			String sql = "DELETE FROM vehicule WHERE id=?";
			db.myPrepareStatement(sql);
			Object[] parameters = { id };
			db.addParameters(parameters);
			db.myExecuteUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public static void modifyVehicule(Vehicule v) throws Exception {
		try {
			DataBaseHelper db = DataBaseHelper.getInstance();
			String sql = "UPDATE vehicule SET matricule=?,marque=?,modele=?,couleur=? ,prix=? WHERE id=?";
			db.myPrepareStatement(sql);
			Object[] parameters = { v.getMatricule(), v.getMarque(), v.getModele(), v.getCouleur(), v.getPrix(),v.getId() };
			db.addParameters(parameters);
			int s=db.myExecuteUpdate();
			if(s==1) {
				System.out.println("Il a modification");
			}else {
				System.out.println("il y a pas modification");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public static ObservableList<Vehicule> getAllRecords() throws Exception {

		try {
			String sql = "SELECT * FROM vehicule";
			DataBaseHelper db = DataBaseHelper.getInstance();
			db.myPrepareStatement(sql);
			ResultSet rs = db.myExecuteQuery();
			ObservableList<Vehicule> vlist = getVoitureObjects(rs);
			db.closeConnection();
			return vlist;
		} catch (Exception e) {
			throw e;
		}
	}

	private static ObservableList<Vehicule> getVoitureObjects(ResultSet rs) throws Exception {
		try {
			ObservableList<Vehicule> vlist = FXCollections.observableArrayList();
			while (rs.next()) {
				Vehicule v = new Vehicule();
				v.setId(rs.getInt("id"));
				v.setMatricule(rs.getString("matricule"));
				v.setMarque(rs.getString("marque"));
				v.setModele(rs.getString("modele"));
				v.setCouleur(rs.getString("couleur"));
				v.setPrix(rs.getInt("prix"));
				vlist.add(v);
			}
			return vlist;

		} catch (Exception e) {
			throw e;
		}

	}

	
}
