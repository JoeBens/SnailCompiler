package snailcompiler;

public class lexical {
	
	public lexical () { 
		
		
	}
	
	public String [] separerligne (String text) 
	{
		String[]ligne=text.split("(\\r\\n)+");
		return ligne; 
	
	}
	
	public String[] separervirgule (String ligne) 
	{
		
		String[]mot=ligne.split("\\s*,\\s*|\\s+");
		
		return mot ;  
	}
	
	public Boolean identificateur (String mot) {
		boolean bool = true; 
	
	if (Character.isLetter(mot.charAt(0)) || (Character.isUpperCase(mot.charAt(0))))
	{
		for (int i=1; i<mot.length();i++) 
		{
			if (Character.isLetter(mot.charAt(i)) || (Character.isUpperCase(mot.charAt(i))) ||(Character.isDigit((mot.charAt(i)))))   
		    {		     					
		    }
			else if (mot.charAt(i)=='_') 
			{
				if (Character.isLetter(mot.charAt(i+1)) || (Character.isUpperCase(mot.charAt(i+1))) ||(Character.isDigit((mot.charAt(i))))) 
				{	
				}
				else return false;
			     }
			}
	}
	
	else return false; 
	
	switch(mot) {
	case "Snl_Start" : 
	bool =false; 
	break;
	case "Snl_Int" :
	bool =false; 
	break;
	case "Snl_Real" : 
	bool =false; 
	break;
	case "Snl_Put" : 
	bool =false; 
	break;
	case "SnlSt" : 
	bool =false; 
	break;
	case "Set" : 
	bool =false; 
	break;
	case "Get" : 
	bool =false; 
	break;
	case "If" : 
	bool =false; 
	break;
	case "Else" : 
	bool =false; 
	break;
	case "Start" : 
	bool =false; 
	break;
	case "Finish" : 
	bool =false; 
	break;
	}

	return bool; 
	}
	public Boolean integer (String entier) {

		boolean bool = true;
		for (int i=1; i<entier.length() ;i++) 
		{
			if 	(Character.isDigit(entier.charAt(i))) { 
					
			}
			else return false ; 
		}
		return bool;
		}
		    
	public Boolean real (String reel) {
		boolean bool = true ; 
		for (int i=1; i<reel.length() ;i++) { 
		if(Character.isDigit(reel.charAt(i))) 
		{
		}
			if(reel.charAt(i)=='.') { 
			{
				if(Character.isDigit(reel.charAt(i+1))) 
				{ 
				} 
				else return false ; 
			} 	
		}	
		}
		return bool; 
	}
	public Boolean chaine (String string) {
		boolean bool = true ; 
		for (int i=1; i<string.length() ;i++) { 
		if 	(Character.isLetter(string.charAt(i)) || (Character.isUpperCase(string.charAt(i))) || (Character.isDigit((string.charAt(i)))) 
				|| (string.charAt(i)=='\'' ) )
		{
			
		}
		else return false ; 
		}
		
		return bool ; 
	}
	
	public String afficher (String message) 
	{
		if (identificateur (message)) 
		{
			message=message + "identificateurs" ;  
		}
		
		if (integer (message)) 
		{
			message=message + "entier" ;  
		}
		
		if (real (message)) 
		{
			message=message + "reel" ;  
			
		}
		
		if (chaine (message)) 
		{
			message=message + "message" ;  
			
		}
		
		switch(message) {
		case "Snl_Start" : 
		message=message + "debut " ;
		break;
		case "Snl_Int" :
			message=message + "d�claration d'un entier" ; 
		break;
		case "Snl_Real" : 
			message=message + "déclaration d'un reel" ;
		break;
		case "Snl_Put" : 
			message=message + "affichage" ; 
		break;
		case "SnlSt" : 
			message=message + "declaration d'une chaide de caract�re" ;
		break;
		case "Set" : 
			message=message + "mot reserv� pour affectation d'ine valeur" ;
		break;
		case "Get" : 
			message=message + "valeur a une variable" ;
		break;
		case "If" : 
			message=message + "condition" ;
		break;
		case "Else" : 
			message=message + "sinon" ;
		break;
		case "Start" : 
			message=message + "debut du programme" ;
		break;
		case "<" : 
			message=message + "opérateur de comparaison" ;
		break; 
		case ">" : 
			message=message + "opérateur de comparaison" ;
		break;
		case "Finish" : 
			message=message + "fin du programme" ; 
		break;
		case "%" : 
			message=message + "mot reservé pour fin d'une condition" ; 
		break; 
		case "%." : 
			message=message + "fin de ligne" ; 
		break; 
		
	}
		return message ; 
	} 
    
public String resultat (String text ) 
{
	String resultat="";
	String[] ligne = separerligne(text);
	 for(int i = 0; i<ligne.length; i++) {
		 String[] mot = separervirgule(ligne[i]);
		 for(int j = 0; i<mot.length; j++) {
			 resultat = resultat + "\n" + afficher(mot[j]);
			 
		 }
		 
	 }
	
	return afficher(text); 
	

}
		
}
	 
