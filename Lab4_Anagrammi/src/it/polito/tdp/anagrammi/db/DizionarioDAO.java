package it.polito.tdp.anagrammi.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DizionarioDAO {

	private List<String> dizionario=new LinkedList<String>();

	public List<String> loadDictionary(){
		
		Connection conn= DBConnect.getConnection();
		PreparedStatement st;
		
		try {
			String sql="select nome from parola";
			st=conn.prepareStatement(sql);
			ResultSet res=st.executeQuery(sql); 
			while(res.next()){
			dizionario.add(res.getString("nome"));
			}
			res.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dizionario;
	}
}
