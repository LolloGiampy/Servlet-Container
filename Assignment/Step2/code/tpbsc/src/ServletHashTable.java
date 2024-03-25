
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServlet;

//contenitore per memorizzare associazioni tra nomi di servlet e oggetti di classe httpservlet
public class ServletHashTable {
	//le chiavi sono di tipo string 
	static Hashtable<String, HttpServlet> ht;
	//costruttore di hashtable inizializza ht
	ServletHashTable() {
		ht = new Hashtable<String, HttpServlet>();
	}
	//inserisce un'associazione tra una stringa (nome del servlet) e un servlet
	static void put (String s, HttpServlet h){
		ht.put(s,  h);
	}
	//verifica se contiene una chiave (nome del servlet)
	static boolean contains (String s){
		return ht.containsKey(s);
	}
	//restituisce l'oggetto servlet associato al nome inserito
	static HttpServlet get(String s) {
		return ht.get(s);
	}
	//rimuove il servlet associato al nome 
	static void remove(String s) {
		ht.remove(s);
	}
	static void list() {
		Enumeration<String> servletNames = ht.keys();
		while(servletNames.hasMoreElements()){
			String servletName = servletNames.nextElement();
			HttpServlet servlet = ht.get(servletName);
			System.out.println(servlet.getClass().getName());
		}
	}
}

