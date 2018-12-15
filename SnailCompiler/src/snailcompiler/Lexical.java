package snailcompiler;
public class Lexical {
			
	public Lexical()
	{
		
	}
	
	public String analyse_lex( String code)
	{
		int i=0;
		String resultat = " ";
		
		String[] line = devide(code);
		while(line[i].isEmpty()) {
			i++;
		}
		int k = line.length;
		if(line[line.length-1].trim().isEmpty()) {
			k--;
		}
				 
		for(int j =i;j<k;j++) {
			if(!line[j].trim().isEmpty())
			resultat =  resultat +"\n"+ keyWord(line[j]);
			
		}
		return resultat;
	}
	
	public  boolean chaine(String chaine) {
		boolean bool =true;
		for (int i =0; i< chaine.length(); i++)
		{
			if((Character.isDigit(chaine.charAt(i))) || (Character.isLetter(chaine.charAt(i))) ||Character.isUpperCase(chaine.charAt(i)) || chaine.charAt(i) == '\'' ) {
				
			}
			
			else return false;
			}
		return bool;
		
	}
	
	public  boolean ident(String ident)
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
			
	public  boolean integer(String integer) {
		 boolean bool =true;
		for (int i =0; i< integer.length(); i++)
		{
			if(Character.isDigit(integer.charAt(i))){
				}
			else return false;
		}	
		return bool;
	}

	public  boolean real(String real) {
		
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

	public  boolean ArithmeticOp(String arithm) {
		boolean bool =true;
		if (arithm.length()>2) {
			bool =false;
		}
		
			if(arithm=="<"|| arithm ==">"|| arithm =="<=" || arithm ==">="  || arithm =="==") {
				
			}
			else {
				bool =false;
			}
		
		
		return bool;
		
	}
	
	public  String keyWord(String line) {
		int i = 0;
		String resultat = "";
		String[] word = line.split("\\s+");
		if ( word[i].isEmpty()) {
			i= 1;
		}
		
		switch(word[i]) {
		case "Snl_Start" : 
			resultat =   word[i] +  ":   mot reserver de debut de programer";
			break;
		case "Snl_Close" : 
			resultat =   word[i] +  ":   mot reserver de fin de programer";
			break;
		case "Snl_Int" :
			resultat = word[i] + ":   mot reserver pour declaration d'un entier \n" +  lex(line);
			break;
		case "Snl_Real" : 
			resultat =word[i] + ":   mot reserver pourdeclaration d'un reel  \n" + lex(line);
			break;
		case "Snl_Put" : 
			resultat =word[i] + ":    mot reserver pour  affichage \n" + lex(line);
			break;
		//case "SnlSt" : 
			//return "mot reserver chiane de caracter";
			
		case "%." : 
			resultat = word[i] + ":   mot reserver pour fin d'instruction";
			break;
		case "%.." : 
			resultat =  word[i] + ":   mot reserver pour debut de commentaire";
			break;
			
		case "%" : 
			resultat =  " :   fin de condition";
			break;
			
		case "Set" : 
			resultat = word[i] + " :  mot reserver pour affection a d'une valeur a une variable \n" +	lex(line);
			break;
		case "Get" : 
			resultat = word[i] + "    mot reserver pour affectation d'une valeur entre variable \n"+	lex(line);
			break;
		case "If" : 
			resultat =word[i] + "  : mot reserver pour une condition " +  snl_if(line);
			break;
		
		case "Else" : 
			resultat =  word[i] +" :  le sinon de la condition ";
			break;
		case "Start" : 
			resultat =word[i] +  " :   mot reserve pour debut de bloc";
			break;
		case "Finish" : 
			resultat =word[i] +  " :   mot reserve pour fin de bloc";
			break;	
			default : 
				resultat =word[i] +  " :   erruer mot non reconu" +	lex(line);
				break;
		}
		return resultat;
	}
	
 	public  String lex(String line) {
		// TODO Auto-generated method stub
 		int nbr = 0;
 		int i = 0;
 		String resultat = " ";
 		String chaine_cara =" ";
 		
 		String[] word = line.split("\\s*,\\s*|\\s+");
 		if ( word[i].isEmpty()) {
			i= 1;
		}
 		for( i = i+1	; i<word.length;i++)
 		{
 			
 			if(word[i].equals("from")) {
 				word[i] = word[i] + "  :  mot reserver du langage pour affectation ";
 			}
 			else if(ident(word[i])) {
 				word[i] = word[i] + " :  identificateur";
 			}
 			else if (integer(word[i])) {
 				word[i] = word[i] + "  :  un nombre entier";
 			}
 			else if(ArithmeticOp(word[i])) {
 				word[i] = word[i] + "  :  operation arithmitique";
 			}
 			else if (real(word[i])) {
 				word[i] = word[i] + "  :  un nombre real";
 			}
 			else if(word[i].equals("%.")) {
 				word[i] = word[i] + "  :  fin d'instruction";
 			}
 		
 			else  if(word[i].charAt(0) =='"'  &&  word[i].length()>1) {
 				nbr ++ ;
 				boolean t = true;
 				for(int j = 1; j<word[i].length(); j++ ) {
 						if ((Character.isDigit(word[i].charAt(j))) || (Character.isLetter(word[i].charAt(j))) ||Character.isUpperCase(word[i].charAt(j)) || word[i].charAt(j) == '\'' ) {
 							chaine_cara = word[i]; 	
 						}
 						else {
 						//	System.out.println("i'm not fine");
 							//chaine_cara =  word[i] + "  :  erreur ..mot non reconnue  \n";
 							t = false;
 							 j=word[i].length() +1;
 						}}
 			   //********************************************************************************************************
 			
 			   //see if the other word until we get a "  *****************************************************
 			   for(int k  = i +1 ; k<word.length; k++) {
 				   if(chaine(word[k])) {
 					   //System.out.println("the first if ");
 					   chaine_cara = chaine_cara +"  "   + word[k];
 				   }
 				   else if (word[k] .equals("\"")) {
 					   nbr++;
 					   chaine_cara = chaine_cara +"  "   + word[k];
 					   i = k ++; 
 					 //  System.out.println("the second if ");
 					   k=word.length;
 				   } 
 				   // if the last word contain a "  in the end of the word ******************************
 				   else if(word[k].charAt(word[k].length()-1) =='"'  &&  word[k].length()>1) {
 					   nbr++;
 					 //  System.out.println("the third if ");
 					  boolean c = true;
 					   // see if the word is correct *************************
 						for(int j = 1; j<word[k].length()-1; j++ ) {
 							if ((Character.isDigit(word[k].charAt(j))) || (Character.isLetter(word[k].charAt(j))) ||Character.isUpperCase(word[k].charAt(j)) || word[k].charAt(j) == '\'' ) {	
 							}
 							else {
 								t=false;
 								//chaine_cara =  chaine_cara +"  " +word[k] + "  :  erreur ..mot non reconnue  \n";
 								 j=word[k].length() ;
 							}}
 						if(c){
 							chaine_cara =  chaine_cara +"  " +word[k] ;
 						}
 						i = k++; 
 						 k=word.length;
 				   }
 				   else {
 					  i=word.length  -1; 
 					 System.out.println("the if  where the word is wrong");
 					   chaine_cara =  chaine_cara +"  " +word[k]  ;
 					   t=false;
 				   }
 			   }
 		   

 		if(t) { 
 			chaine_cara = chaine_cara + "   : chaine de caractere ";
 		}
 		else {
 			chaine_cara = chaine_cara + "   :   chaine de caractere non reconue , erreur  ";
 		}
 		resultat = resultat + "\n" +  chaine_cara;
 			}
 			
 			else if (word[i].equals("\"")){
 				chaine_cara = word[i]; 
 				nbr ++ ;
 				boolean t = true;
 				for(int k  = i +1 ; k<word.length; k++) {
  				   if(chaine(word[k])) {
  					   //System.out.println("the first if ");
  					   chaine_cara = chaine_cara +"  "   + word[k];
  				   }
  				   else if (word[k] .equals("\"")) {
  					   nbr++;
  					   chaine_cara = chaine_cara +"  "   + word[k];
  					   i = k++ ; 
  					 //  System.out.println("the second if ");
  					   k=word.length;
  				   } 
  				   // if the last word contain a "  in the end of the word ******************************
  				   else if(word[k].charAt(word[k].length()-1) =='"'  &&  word[k].length()>1) {
  					   nbr++;
  					 //  System.out.println("the third if ");
  					  boolean c = true;
  					   // see if the word is correct *************************
  						for(int j = 1; j<word[k].length()-1; j++ ) {
  							if ((Character.isDigit(word[k].charAt(j))) || (Character.isLetter(word[k].charAt(j))) ||Character.isUpperCase(word[k].charAt(j)) || word[k].charAt(j) == '\'' ) {
  								
  							}
  							else {
  								t=false;
  								
  								//chaine_cara =  chaine_cara +"  " +word[k] + "  :  erreur ..mot non reconnue  \n";
  								 j=word[k].length() ;
  							}}
  						if(c){
  							chaine_cara =  chaine_cara +"  " +word[k] ;
  						}
  						i = k++; 
  						 k=word.length;
  				   }
  				   else {
  					  i=word.length -1 ;
  					// System.out.println("the if  where the word is wrong");
  					   chaine_cara =  chaine_cara +" " +word[k]  ;
  					   t=false;
  				   }
  			   }
  		   

  		if(t) { 
  			chaine_cara = chaine_cara + "   : chaine de caractere ";
  		}
  		else {
  			chaine_cara = chaine_cara + "   :   chaine de caractere non reconue , erreur  ";
  		}
  		resultat = resultat + "\n" +  chaine_cara;
 			}
 			
 			else {
 				word[i] = word[i] + "  :  ne reconnait pas ce mot, erreur syntaxique";
 			}
 			resultat = resultat + "\n" + word[i];
 			
 		}
 		return resultat;
	}
 	
 	public  String snl_if(String line) {
 		int i = 0;
 		String resultat = " ";
 		String[] word = line.split("\\s+"); 
 		int nbr = 1;
 		if ( word[i].isEmpty()) {
			i= 1;
		}
 		word[i] = word[i] + "  :  une condition if ";
 		for( i = i+1; i<word.length;i++) {
 			
 			if(ident(word[i])) {
 				word[i] = word[i] + " :  identificateur";
 			}
 			else if (integer(word[i])) {
 				word[i] = word[i] + "  :  un nombre entier";
 			}
 			else if(ArithmeticOp(word[i])) {
 				word[i] = word[i] + "  :  operation arithmitique";
 			}
 			else if (real(word[i])) {
 				word[i] = word[i] + "  :  un nombre real";
 			}
 			else if(word[i].equals("%.")) {
 				word[i] = word[i] + "  :  fin d'instruction";
 			}
 			else if(word[i].equals("%") && nbr == 1) {
 				
 				word[i] = word[i] + "  : mot reserver pour debut d'un condtion  une condition ";
 				nbr = 2;	
 			}
 			else if(word[i].equals("%") && nbr == 2) {
 				
 				word[i] = word[i] + "  : mot reserver pour fin d'un condtion  une condition ";
 				
 			}
 			else {
 				 if(word[i].contains("<=")) {
						String[] sub_word = word[i].split("\\s*<=\\s*");
	 					for(int j =0; j< sub_word.length;j++) {
	 						if(sub_word[j].isEmpty()) {
	 							
	 						}
	 						else if(ident(sub_word[j])) {
	 						word[i] = word[i] +"    " + sub_word[j] + " :  identificateur \n";
	 					    }
	 						else if(integer(sub_word[j])) {
	 	 						word[i] = word[i] +"     " +sub_word[j] + "  :  un nombre entier \n";
	 	 					}
	 						else if(real(sub_word[j])) {
	 	 						word[i] = word[i] +"        " +sub_word[j] + "  :  un nombre re�l \n";
	 	 					}
	 						else {
	 							word[i] = word[i] +"    " +sub_word[j] + "  :   erreur , mot non reconnue \n";
	 						}
	 						
	 						}
	 					word[i] = word[i] + " <=   operation arithmiqtique ";
					}
 				else if(word[i].contains(">=")) {
						String[] sub_word = word[i].split("\\s*>=\\s*");
	 					for(int j =0; j<sub_word.length;j++) {
	 						if(ident(sub_word[j])) {
	 						word[i] = word[i] +"    " + sub_word[j] + " :  identificateur \n";
	 					    }
	 						else if(integer(sub_word[j])) {
	 	 						word[i] = word[i] +"     " +sub_word[j] + "  :  un nombre entier \n";
	 	 					}
	 						else if(real(sub_word[j])) {
	 	 						word[i] = word[i] +"        " +sub_word[j] + "  :  un nombre re�l \n";
	 	 					}
	 						else {
	 							word[i] = word[i] +"    " +sub_word[j] + "  :   erreur , mot non reconnue \n";
	 						}
	 						
	 					}
	 					word[i] = word[i] + " >= operation arithmiqtique ";
					}
					else if(word[i].contains("==")) {
						String[] sub_word = word[i].split("\\s*==\\s*");
	 					for(int j =0; j<sub_word.length;j++) {
	 						if(ident(sub_word[j])) {
	 						word[i] = word[i] +"    " + sub_word[j] + " :  identificateur \n";
	 					    }
	 						else if(integer(sub_word[j])) {
	 	 						word[i] = word[i] +"     " +sub_word[j] + "  :  un nombre entier \n";
	 	 					}
	 						else if(real(sub_word[j])) {
	 	 						word[i] = word[i] +"        " +sub_word[j] + "  :  un nombre re�l \n";
	 	 					}
	 						else {
	 							word[i] = word[i] +"    " +sub_word[j] + "  :   erreur , mot non reconnue \n";
	 						}
	 						
					}word[i] = word[i] + " ==   operation arithmiqtique ";
				}
 				 else if(word[i].contains("<")) {
 						
 					String[] sub_word = word[i].split("\\s*<\\s*");
 					for(int j =0; j<sub_word.length;j++) {
 						if(ident(sub_word[j])) {
 						word[i] = word[i] +"    " + sub_word[j] + " :  identificateur \n";
 					    }
 						else if(integer(sub_word[j])) {
 	 						word[i] = word[i] +"     " +sub_word[j] + "  :  un nombre entier \n";
 	 					}
 						else if(real(sub_word[j])) {
 	 						word[i] = word[i] +"        " +sub_word[j] + "  :  un nombre re�l \n";
 	 					}
 						else {
 							word[i] = word[i] +"    " +sub_word[j] + "  :   erreur , mot non reconnue \n";
 						}
 						
 						
 					}
 					word[i] = word[i] + "  <   operation arithmiqtique ";
 					}
 					else if(word[i].contains(">")) {
 						String[] sub_word = word[i].split("\\s*>\\s*");
 	 					for(int j =0; j<sub_word.length;j++) {
 	 						if(ident(sub_word[j])) {
 	 						word[i] = word[i] +"    " + sub_word[j] + " :  identificateur  \n";
 	 					    }
 	 						else if(integer(sub_word[j])) {
 	 	 						word[i] = word[i] +"     " +sub_word[j] + "  :  un nombre entier \n";
 	 	 					}
 	 						else if(real(sub_word[j])) {
 	 	 						word[i] = word[i] +"        " +sub_word[j] + "  :  un nombre re�l  \n";
 	 	 					}
 	 						else {
 	 							word[i] = word[i] +"    " +sub_word[j] + "  :   erreur , mot non reconnue \n";
 	 						}
 	 						word[i] = word[i] + " > operation arithmiqtique ";
 	 					}
 	 					
 					}
 				
 					
 					else {
 						word[i] = word[i] + "  :   erreur , mot non reconnue";
 						
 					}
 			}	
 			resultat = resultat + "\n" + word[i];
 		}
 	
 		
		return resultat;

 	}
 	
	public String[] devide(String code)
	{	
		
		String[] line = code.split("(\\r\\n)+");
		return line;
		
	}

	
}
	
