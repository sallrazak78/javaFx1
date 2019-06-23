package service;

import java.sql.ResultSet;

import model.Utilisateur;

public class UserDao {
	public static Utilisateur getUser(String login,String pwd) {
		Utilisateur u=null;
	try {
		String sql="SELECT * FROM utilisateur WHERE login=? AND password=?";
		DataBaseHelper db=DataBaseHelper.getInstance();
		db.myPrepareStatement(sql);
		Object[] params= {login,pwd};
		db.addParameters(params);
		ResultSet rs=db.myExecuteQuery();
		if(rs.next()) {
			u=new Utilisateur();
			u.setId(rs.getInt(1));
			u.setLogin(rs.getString(2));
			u.setPwd(rs.getString(3));
			u.setNom(rs.getString(4));
			u.setPrenom(rs.getString(5));
			u.setProfil(rs.getString(6));
		}else {
			u=null;
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return u;
	}
	
	
}
