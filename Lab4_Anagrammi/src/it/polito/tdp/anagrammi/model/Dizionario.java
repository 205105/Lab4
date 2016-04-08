package it.polito.tdp.anagrammi.model;

import it.polito.tdp.anagrammi.db.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Dizionario {

	
	DizionarioDAO dao=new DizionarioDAO();
	List<String> dizionario=new ArrayList<String>(dao.loadDictionary());
	
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
	
	public List<String> dividiTesto(String s){
		List<String> parole=new LinkedList<String>();
		StringTokenizer st=new StringTokenizer(s,"\n"); 
		while(st.hasMoreTokens()){
			parole.add(st.nextToken());
		}
		return parole;
		}
} 
	

