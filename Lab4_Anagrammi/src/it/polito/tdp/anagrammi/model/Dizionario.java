package it.polito.tdp.anagrammi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dizionario {

protected List<String> dizionario;
private String jdbcURL="jdbc:mysql://localhost/dizionario?user=root";

	
	public Dizionario(){
		this.dizionario=new ArrayList<String>();
	}
	
	public void loadDictionary(){
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			Statement st=conn.createStatement();
			String sql="select nome from parola"; 
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
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){ //ricerca "normale"
	List<RichWord> listaAnalizzata=new ArrayList<RichWord>();
	RichWord t=null;
	for(String s: inputTextList){
		boolean flag=false;
			if(dizionario.contains(s)){
				flag=true;
		}
		if(flag){
			 t=new RichWord(s, true);
		} else {
			 t=new RichWord(s, false);
		}
		listaAnalizzata.add(t);
	}
	return listaAnalizzata;
}
} 
	

