
package snailcompiler;
import java.io.IOException;
import java.util.ArrayList;

public class Semantique {
private int nbr_erreur;
	private static String code;
	private static ArrayList<String> int_var;
	private static ArrayList<String> real_var;
	private static ArrayList<String> set_var;

	public Semantique (int nbr_erreur, String code) {
		Semantique.code = code;
		this.nbr_erreur= nbr_erreur;
		int_var = new ArrayList<String>(); 
		real_var = new ArrayList<String>(); 
		set_var = new ArrayList<String>(); 
	}

	public String analyse_sem() throws IOException {
		int i =0, nbr_if=0, nbr_start= 0;int j =0;
		if(!start_close())	return "Une erreur dans Snl_start and Snl_Close";
		String resultat ="0 erreurs";
		if(nbr_erreur>0) {
			return "Une erreur a été detecté";}

		String[] line = divide(code);

		for(i=0; i<line.length;i++) {
			if(!line[j].trim().isEmpty()) {
				String[] word = line[i].trim().split("\\s+");

				/*	if ( word[j].isEmpty()) {
			j= 1;
		}*/

				switch(word[j]) {

				case "Snl_Int" :
					if( !snl_int(line[i]))
						nbr_erreur++;
					break;
				case "Snl_Real" : 
					if(!snl_real(line[i])) {
						nbr_erreur++;}
					break;
				case "Snl_Put" : 
					if(!put(line[i])){
						nbr_erreur++;}
					break;
				case "Set" : 
					if(!set(line[i])){
						nbr_erreur++;}
					break;
				case "Get" : 
					if(!get(line[i]))
						nbr_erreur++;
					break;
				case "If" : 
					nbr_if++;
					if( !condition(line[i]))
						nbr_erreur++;
					break;

				case "Else" : 
					if (nbr_if == 0) nbr_erreur++;
					if(!line[i-1].trim().equals("Finish")) {
						if(!line[i-2].trim().startsWith("If")) {
							nbr_erreur++;
						}
					}
					else nbr_if =0;
					break;
				case "Start" : 
					boolean t = false;
					String[] mot = line[i-1].split("\\s+");
					int k =0;
					if ( mot[k].isEmpty()) {
						k= 1;
					}
					if(!mot[k].trim().equals("If") && !mot[k].trim().equals("Else")) nbr_erreur++;
					else {
						for(int n = i ;n<line.length;n++) {
							if(line[n].trim().equals("Finish")) {
								t = true;
								n = line.length;
							}
						}
						if(t)nbr_start++;
						else  nbr_erreur++;
					}

					break;
				case "Finish" : 
					if(nbr_start==0)nbr_erreur++;
					else nbr_start= 0;
					break;	
				default : 

					break;
				}}}
		if(nbr_erreur>0) return "Nombre d'erreurs détectées: " + nbr_erreur;
		else {

			Traduction traduction = new Traduction(code);
			traduction.compile();
			return resultat;
		}
	}


	public static boolean condition(String line) {
		boolean bool = true;
		String[] word = line.split("\\s*,\\s*|\\s+|<=|>=|==|>|<|%");
		for(int i = 0; i<word.length;i++) {
			if(!word[i].isEmpty()) {
				if(ident(word[i])) {
					if(!int_var.contains(word[i]) && !real_var.contains(word[i]) ) return false;
				}}

		}


		return bool;

	}
	public static boolean get(String line) {
		boolean bool = true;
		int nbr_int = 0;
		int nbr_real = 0;
		String[] word = line.split("\\s*,\\s*|\\s+");
		for(int i = 0; i<word.length;i++) {
			if(!word[i].isEmpty()) {
				if(ident(word[i])) {
					if(int_var.contains(word[i])) 
					{if(set_var.contains(word[i])) 
						nbr_int++;
					}
					else if(real_var.contains(word[i])) 
					{if(set_var.contains(word[i])) 
						nbr_real++;
					}
				}}
		}
		if(nbr_int<2 && nbr_real<2)
			return false;


		return bool; 
	}
	public static boolean snl_int(String line) {
		boolean bool  = true;


		String[] word = line.split("\\s*,\\s*|\\s+");
		for(int i = 0; i<word.length;i++) {
			if(!word[i].isEmpty()) {
				if(ident(word[i])) {
					int_var.add(word[i]);
				}}
			for(String c : int_var) {
				if(real_var.contains(c))
					return false;}
		}
		return bool;
	}

	public static boolean snl_real(String line) {
		boolean bool =true;
		String[] word = line.split("\\s*,\\s*|\\s+");
		for(int i = 0; i<word.length;i++) {
			if(!word[i].isEmpty()) {
				if(ident(word[i])) {
					real_var.add(word[i]);
				}}
			for(String c : real_var) {
				if(int_var.contains(c))
					return false;
			}
		}
		return bool;
	}

	public static boolean set(String line) {
		boolean bool = true; 
		//boolean entier = false;
		//boolean real = false;
		String var = null;

		String[] word = line.split("\\s+");
		for(int i = 0; i<word.length;i++) {
			if (word[i].isEmpty()) {

			}
			else if(ident(word[i])) {
				var = word[i];
				set_var.add(var);

			}
			else if(integer(word[i])) {
				if(!int_var.contains(var)) return false;
				//	entier = true;
			}
			else if(real(word[i])) {
				if(!real_var.contains(var)) return false;
				//real = true;
			}

		}

		return bool;


	}
	public static boolean sinon(int nbr_if, int index) {

		if(nbr_if==0) return false;

		return false;

	}
	public static boolean put(String line) {
		boolean bool = true;
		boolean c = true;
		boolean d = true;
		@SuppressWarnings("unused")
		int nbr = 0;
		String put_var = "";
		for(int i =0; i<line.length();i++) {
			if (Character.isWhitespace(line.charAt(i))) {
			}

			else if(line.charAt(i)=='+') {
				c = true;
				i++;
				while(c ) {
					if(line.charAt(i)=='+' || line.charAt(i)=='%') {

						c = false;}
					else if (Character.isWhitespace(line.charAt(i))) {
						i++;}
					else if(line.charAt(i)=='"') {
						d=false;
						c= false;
					}

					else {
						d =true;
						put_var = put_var + line.charAt(i);
						i++;}
				}
				if(d) {
					if(!int_var.contains(put_var) && !real_var.contains(put_var) ) return false;
					put_var = "";}
			}
		}

		return bool;
	}
	public static boolean start_close() {
		int i =0;
		boolean bool = true;
		String[] line = divide(code);
		// chek the snl_start
		while(line[i].trim().isEmpty()){
			i++;
		}

		if(!line[i].trim().equals("Snl_Start")) {
			return false;
		}
		// check the snl_close
		int k = line.length-1;
		if(line[line.length-1].trim().isEmpty()) {
			k--;
		}
		if(!line[k].trim().equals("Snl_Close")) {

			return false;
		}
		return bool;

	}
	public static String[] divide(String code)
	{	

		String[] line = code.split("(\\r\\n)+");
		return line;

	}
	public static  boolean ident(String ident)
	{
		boolean bool = true; 

		switch(ident) {
		case "Snl_Start" : 
			return false; 
		case "Snl_Close" : 
			return false; 
		case "Snl_Int" :
			return false; 
		case "Snl_Real" : 
			return false; 
		case "Snl_Put" : 
			return false; 
		case "SnlSt" : 
			return false; 
		case "Set" : 
			return false; 
		case "Get" : 
			return false; 
		case "If" : 
			return false; 
		case "Else" : 
			return false; 
		case "Start" : 
			return false; 
		case "Finish" : 
			return false; 
		case "from" : 
			return false; 

		}


		if((Character.isLetter(ident.charAt(0))) || (Character.isUpperCase(ident.charAt(0)))){

		}
		else {
			return false;
		}
		for (int i =1; i< ident.length(); i++)
		{
			if((Character.isDigit(ident.charAt(i))) || (Character.isLetter(ident.charAt(i))) ||Character.isUpperCase(ident.charAt(i))) {

			}
			else if(ident.charAt(i) == '_')
			{
				if((Character.isDigit(ident.charAt(i+1))) || (Character.isLetter(ident.charAt(i+1))) ||Character.isUpperCase(ident.charAt(i+1))) {

				}
				else return false;
			}
			else 	return false;

		}
		return bool;
	}

	public static  boolean integer(String integer) {
		boolean bool =true;
		for (int i =0; i< integer.length(); i++)
		{
			if(Character.isDigit(integer.charAt(i))){
			}
			else return false;
		}	
		return bool;
	}

	public static boolean real(String real) {

		boolean bool =true;
		if(real.charAt(0) == '.') 
			return false;	
		for (int i =0; i< real.length(); i++)
		{
			if(Character.isDigit(real.charAt(i))){	
			}
			else if (real.charAt(i) == '.' ) {
				if(Character.isDigit(real.charAt(i+1))){		
				}
				else  return false;
			}
			else  return false;
		}
		return bool;
	}

}