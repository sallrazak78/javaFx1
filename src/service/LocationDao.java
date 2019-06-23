package service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Client;
import model.Location;
import model.Vehicule;

public class LocationDao {
	public Client findClient(String piece) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM client WHERE numpiece=?";
		DataBaseHelper db=DataBaseHelper.getInstance();
		db.myPrepareStatement(sql);
		Object[] parameters = {piece};
		db.addParameters(parameters);
		ResultSet rs = db.myExecuteQuery();
		if(rs.next()) {
			Client c=new Client(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(2),rs.getDate(5).toLocalDate());
			c.toString();
			return c;
		}else
			return null;
	}
	public Vehicule findVehicule(String piece) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM vehicule WHERE matricule=?";
		DataBaseHelper db=DataBaseHelper.getInstance();
		db.myPrepareStatement(sql);
		Object[] parameters = {piece};
		db.addParameters(parameters);
		ResultSet rs = db.myExecuteQuery();
		if(rs.next()) {
			Vehicule v=new Vehicule(rs.getInt(1),rs.getInt(6),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			return v;
		}else
			return null;
	}
	public void addLocation(Location l) throws Exception{
		try {
			DataBaseHelper db=DataBaseHelper.getInstance();
			if(l.getClient().getId()==0) {
				Client c=l.getClient();
				String sql = "Insert into client values(null,?,?,?,?,?,?)";
				db.myPrepareStatement(sql);
				Object[] parameters = {c.getPiece(), c.getNom(), c.getPrenom() ,c.getDate().toString(), c.getAdresse(), c.getEmail()};
				db.addParameters(parameters);
				db.myExecuteUpdate();
				l.getClient().setId(db.getKey());
			}
			String sql = "INSERT INTO location VALUES (null, ?, ?, ?, ?, ?, ?)";
			db.myPrepareStatement(sql);
			Object[] parameters = {l.getDate().toString(), l.getJours(),l.getMontant(),l.getCommentaire(), l.getClient().getId(),l.getVehicule().getId()};
			
			db.addParameters(parameters);
			db.myExecuteUpdate();
		}catch (Exception e) {
			throw e;
		}
	}
	public List<Location> getAllRecords() throws Exception {

		try {
			String sql = "SELECT * FROM client c,vehicule v, location l where l.idClient=c.id and l.idVehicule=v.id	";
			DataBaseHelper db = DataBaseHelper.getInstance();
			db.myPrepareStatement(sql);
			ResultSet rs = db.myExecuteQuery();
			List<Location> vlist = getLocationObjects(rs);
			db.closeConnection();
			return vlist;
		} catch (Exception e) {
			throw e;
		}
	}
	private static List<Location> getLocationObjects(ResultSet rs) throws Exception {
		try {
			List<Location> llist = new ArrayList<Location>();
			while (rs.next()) {
				Client c=new Client();
				Vehicule v=new Vehicule();
				Location l= new Location();
				
				//recuperation client
				c.setId(rs.getInt(1));
				c.setPiece(rs.getString(2));
				c.setNom(rs.getString(3));
				c.setPrenom(rs.getString(4));
				c.setDate(rs.getDate(5).toLocalDate());
				c.setEmail(rs.getString(6));
				c.setAdresse(rs.getString(7));
				
				
				// recuperation de vehicule
				v.setId(rs.getInt(8));
				v.setMatricule(rs.getString(9));
				v.setMarque(rs.getString(10));
				v.setModele(rs.getString(11));
				v.setCouleur(rs.getString(12));
				v.setPrix(rs.getInt(13));
				//location
				l.setId(rs.getInt(14));
				l.setDate(rs.getDate(15).toLocalDate());
				l.setJours(rs.getInt(16));
				l.setMontant(rs.getInt(17));
				l.setCommentaire(rs.getString(18));
				l.setClient(c);
				l.setVehicule(v);
				
				llist.add(l);
			}
			rs.close();
			return llist;

		} catch (Exception e) {
			throw e;
		}

	}

}
