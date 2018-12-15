package snailcompiler;
public class Syntaxique {

	private static int nbr_erreur = 0;
	
	public Syntaxique() {
		
	}
	
	public String analyse_syn( String code)
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
	
	public String keyWord(String line) {
		int i = 0;
		int nbr_erreur = 0;
		String resultat = " ";
		String[] word = line.split("\\s+");
		if ( word[i].isEmpty()) {
			i= 1;
		}
			//resultat =  resultat +"\n"+ keyWord(line[i]);
			switch(word[i]) {
			case "Snl_Start" : 
				if(snl_start(line)) {
					resultat = line + " :   debut de programe \n ";
				}
				else {
					nbr_erreur++;
					resultat = line + " :  erreur syntaxique \n";
				}
				break;
			case "Snl_Close" : 
				if(snl_close(line)) {
					resultat = line + " :   fin de programe \n ";
				}
				else {nbr_erreur++;
					resultat = line + " :  erreur syntaxique \n";
				}
				break;
			case "Snl_Int" :
				if(snl_int(line)) {
					resultat = line + " :   declaration d'un entier(s) ";
				}
				else {nbr_erreur++;
					resultat = line + " :  erreur syntaxique ";
				}
				break;
				
				
			case "Snl_Real" : 
				if(snl_real(line)) {
					resultat = line + " :   declaration d'un reel(s) ";
				}
				else {nbr_erreur++;
					resultat = line + " :  erreur syntaxique ";
				}
				break;
			case "Snl_Put" : 
				if(put(line)) {
					resultat = line +" :  affichage d'un message a l'�cran \n" ;
				}
				else {nbr_erreur++;
					resultat = line + " :  erreur syntaxique \n";
				}
				break;
		
			case "%.." : 
				resultat =  line + ":   un commentaire";
				break;
			
			case "Set" : 
				if(set(line)) {
					resultat = line +" :  mot reserver pour affection a d'une valeur a une variable \n" ;
				}
				else {nbr_erreur++;
					resultat = line + " :  erreur syntaxique ";
				}
				break;
			case "Get" : 
				if(get(line)) {
					resultat = line +" :  mot reserver pour affection a d'une variable a une variable \n" ;
				}
				else {nbr_erreur++;
					resultat = line + " :  erreur syntaxique ";
				}
				break;
			case "If" : 
				if(condition(line)) {
					resultat = line +" :  condition \n" ;
				}
				else {nbr_erreur++;
					resultat = line + " :  erreur syntaxique \n";
				}
				break;
			case "If%" : 
				if(condition(line)) {
					resultat = line +" :  condition \n" ;
				}
				else {nbr_erreur++;
					resultat = line + " :  erreur syntaxique \n";
				}
				break;
			
			case "Else" : 
				if(else_condition(line)) {
					resultat = line + " :   sinon \n ";
				}
				else {nbr_erreur++;
					resultat = line + " :  erreur syntaxique \n";
				}
				break;
			case "Start" : 
				if(start(line)) {
					resultat = line + " :   debut d'un block  \n ";
				}
				else {nbr_erreur++;
					resultat = line + " :  erreur syntaxique \n";
				}
				break;
			case "Finish" : 
				if(finish(line)) {
					resultat = line + " :   fin d'un block \n ";
				}
				else {nbr_erreur++;
					resultat = line + " :  erreur syntaxique \n";
				}
				break;	
				default : 
					nbr_erreur++;
					resultat =line  +  " :   erruer syntaxique \n" ;
					break;
			}
			
			
		return resultat;
				
	}
	
	 public static boolean snl_int(String line) {
		  int nbr = 1;
		
			boolean bool = true;
			boolean a = true;
			boolean boucle = true;
			for(int i = 0; i<line.length(); i++) {
				
					if (Character.isWhitespace(line.charAt(i))) {
						
					}
					else if (line.charAt(i)=='S') {
						i=i+7;
						do {
							if (Character.isWhitespace(line.charAt(i))) {
								i++;}
							
							
							else if((Character.isLetter(line.charAt(i))) || (Character.isUpperCase(line.charAt(i)))){
								i++;
								
								while(a) {
									 if (i>= line.length()) {
										return false; }
									 else if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
									if (nbr ==2) {
										if((Character.isDigit(line.charAt(i))) || line.charAt(i) == '_') {
											return false; }
										else if((Character.isLetter(line.charAt(i))) || (Character.isUpperCase(line.charAt(i)))) {
											nbr =1;}
									}
										i++;}
									
									
									
									else if(line.charAt(i) == '_'){
										if((Character.isDigit(line.charAt(i+1))) || (Character.isLetter(line.charAt(i+1))) || Character.isUpperCase(line.charAt(i+1))) {
											i++;}
											
										else {
										return false;}}
		
									else if (line.charAt(i)==',') {
										i++;
										nbr++;	}

									
									else if (Character.isWhitespace(line.charAt(i))) {
										boolean b = true;
										while(b) {   
											 if (i>= line.length()) {
												return false; }
											 else if(Character.isWhitespace(line.charAt(i))) {
												// if (i>= line.length()) {
												//	return false; }
												 
												i++;}
											
											
											else if (line.charAt(i)==',') {
												if(nbr == 2) {
													return false;}
												else {
												nbr++;}
												b=false;}
											
											else if((Character.isLetter(line.charAt(i))) || (Character.isUpperCase(line.charAt(i)))) {
												if(nbr==2) {
													nbr = 1;}
												else {
													return false;}
												i++;
												b = false;}
											
											else if (line.charAt(i) == '%' &&  line.charAt(i+1) == '.') {
												if(nbr==2) {
													return false;
												}
												for( i = i+2; i<line.length(); i++) {
													 if (Character.isWhitespace(line.charAt(i))) {
														  }
													 else {
														 return false;}
												}
												b = false;
												a = false;
												boucle=false;}
											
											else if (i>= line.length()) {
												return false; }
											
											else {
												return false;}}}
									
									
									
									else if (line.charAt(i) == '%' &&  line.charAt(i+1) == '.') {
										for( i = i+2; i<line.length(); i++) {
											 if (Character.isWhitespace(line.charAt(i))) {
												  }
											 else {
												 return false;}
										}
										a = false;
										boucle=false;}
									
									else if (i>= line.length()) {
										return false; }
									
									else {
										return false;}
									
								}
								
							}
							
							else { 
								return false;}
							
							
							
						}while(boucle);
					}
			
		} 
			
			
			
		return bool;
			
	  
	}
	
	 public static boolean snl_real(String line) {
		  int nbr = 1;
		
			boolean bool = true;
			boolean a = true;
			boolean boucle = true;
			for(int i = 0; i<line.length(); i++) {
				
					if (Character.isWhitespace(line.charAt(i))) {
						
					}
					else if (line.charAt(i)=='S') {
						i=i+8;
						do {
							if (Character.isWhitespace(line.charAt(i))) {
								i++;}
							
							
							else if((Character.isLetter(line.charAt(i))) || (Character.isUpperCase(line.charAt(i)))){
								i++;
								
								while(a) {
									 if (i>= line.length()) {
										return false; }
									 else if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
									if (nbr ==2) {
										if((Character.isDigit(line.charAt(i))) || line.charAt(i) == '_') {
											return false; }
										else if((Character.isLetter(line.charAt(i))) || (Character.isUpperCase(line.charAt(i)))) {
											nbr =1;}
									}
										i++;}
									
									
									
									else if(line.charAt(i) == '_'){
										if((Character.isDigit(line.charAt(i+1))) || (Character.isLetter(line.charAt(i+1))) || Character.isUpperCase(line.charAt(i+1))) {
											i++;}
											
										else {
										return false;}}
									
									
									
									else if (line.charAt(i)==',') {
										i++;
										nbr++;	}
									
									
									
									else if (Character.isWhitespace(line.charAt(i))) {
										boolean b = true;
										while(b) {   
											 if (i>= line.length()) {
												return false; }
											 else if(Character.isWhitespace(line.charAt(i))) {
												// if (i>= line.length()) {
												//	return false; }
												 
												i++;}
											
											
											else if (line.charAt(i)==',') {
												if(nbr == 2) {
													return false;}
												else {
												nbr++;}
												b=false;}
											
											
											else if((Character.isLetter(line.charAt(i))) || (Character.isUpperCase(line.charAt(i)))) {
												if(nbr==2) {
													nbr = 1;}
												else {
													return false;}
												i++;
												b = false;}
											
											
											else if (line.charAt(i) == '%' &&  line.charAt(i+1) == '.') {
												if(nbr==2) {
													return false;
												}
												for( i = i+2; i<line.length(); i++) {
													 if (Character.isWhitespace(line.charAt(i))) {
														  }
													 else {
														 return false;}
												}
												b = false;
												a = false;
												boucle=false;}
											
											else if (i>= line.length()) {
												return false; }
											
											else {
												return false;}}}
									
									
									
									else if (line.charAt(i) == '%' &&  line.charAt(i+1) == '.') {
										for( i = i+2; i<line.length(); i++) {
											 if (Character.isWhitespace(line.charAt(i))) {
												  }
											 else {
												 return false;}
										}
										a = false;
										boucle=false;}
									
									else if (i>= line.length()) {
										return false; }
									
									else {
										return false;}
									
								}
								
							}
							
							else { 
								return false;}
							
							
							
						}while(boucle);
					}
			
		} 
			
			
			
		return bool;
			
	  
	}
	  
	 public static boolean set(String line) {
		  int nbr = 1;
		
			boolean bool = true;
			boolean a = true;
			boolean boucle = true;
			for(int i = 0; i<line.length(); i++) {
			
			if (Character.isWhitespace(line.charAt(i))) {
				
			}
			else if (line.charAt(i)=='S') {
				i=i+3;
				do {
					if (Character.isWhitespace(line.charAt(i))) {
						i++;}
					
					
					else if((Character.isLetter(line.charAt(i))) || (Character.isUpperCase(line.charAt(i)))){
						
						i++;
						
						while(a) {
							 if (i>= line.length()) {
								return false; }
							 
							 
							 else  if (Character.isWhitespace(line.charAt(i))) {
								 nbr++;
									i++;}
						
							 else if((Character.isDigit(line.charAt(i))) && (nbr ==2)) {
								 i++;
								 boolean b = true;
								 int point = 1;
								 while(b) {//-----------------------------------------------------------------------------------------------------------------------
									 if (i>= line.length()) {
											return false; }
									 
									 else if(Character.isDigit(line.charAt(i))){
										 i++;
									 }
									 
									 else if(line.charAt(i)=='.') { 
										 if(point >=2 ) {
											 return false;
										 }
										 else if (Character.isWhitespace(line.charAt(i+1))) {
											 return false;
										 }
										 else {
										point++;}
										 i++;
										}
									 
									 
									 else if (Character.isWhitespace(line.charAt(i))) {
										 i++;
										 boolean c = true; 
										 while(c) {
											 if (i>= line.length()) {
													return false; }
										 else  if (Character.isWhitespace(line.charAt(i))) {
													i++;}
												else if (line.charAt(i) == '%' &&  line.charAt(i+1) == '.') {
													
													for( i = i+2; i<line.length(); i++) {
														 if (Character.isWhitespace(line.charAt(i))) {
															  }
														 else {
															 return false;}
													}
													c = false;
													b = false;
													a = false;
													boucle=false;}
												else {
													return false; 
												}
											 
										 }
										 
									 }
									 
									 else {
										 return false;
									 }
								 }
								 
							 }
							 
							 else if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
								 if(nbr == 2) {
									 return false;
								 }
								 i++;
							 }
							 
								else if(line.charAt(i) == '_'){
									
									if((Character.isDigit(line.charAt(i+1))) || (Character.isLetter(line.charAt(i+1))) || Character.isUpperCase(line.charAt(i+1))) {
										i++;}
									else {
										return false;
									}}
							 
								
							 else {
									return false;}
							 
						}
						
					}
					
					
					else {
						return false;
					}
					
					
					
				}while(boucle);
				
			}
			
			}
			
			
			
			return bool;
	 
	 }

	 public static boolean get(String line) {
		  int nbr = 1;
		
			boolean bool = true;
			boolean a = true;
			boolean boucle = true;
			for(int i = 0; i<line.length(); i++) {
			
			if (Character.isWhitespace(line.charAt(i))) {
				
			}
			else if (line.charAt(i)=='G') {
				i=i+3;
				do {
					if (Character.isWhitespace(line.charAt(i))) {
						i++;}
					
					
					else if((Character.isLetter(line.charAt(i))) || (Character.isUpperCase(line.charAt(i)))){
						a = true;
						i++;
						
						while(a) {
							 if (i>= line.length()) {
								return false; }
							 
							 
							 else  if (Character.isWhitespace(line.charAt(i))) {
								 i++;
								boolean b =  true;
								while(b) {
									 if (i>= line.length()) {
											return false; }
									 else if (Character.isWhitespace(line.charAt(i))) {
										 i++;}
									
									else if (line.charAt(i)=='f' && nbr ==1) {
										 if (line.charAt(i+1)=='r') {
										 if (line.charAt(i+2)=='o') {
										 if (line.charAt(i+3)=='m') {
											 if (Character.isWhitespace(line.charAt(i+4))) {
												 nbr ++;
											 } else {return false;}
											 i = i+5;
											 a =false;
											 b=false;}
										 else {return false;}}else {return false;}
										 }else {return false;}
										 }
									
									else if (line.charAt(i) == '%' &&  line.charAt(i+1) == '.') {
										
										for( i = i+2; i<line.length(); i++) {
											 if (Character.isWhitespace(line.charAt(i))) {
												  }
											 else {
												 return false;}
										}
										
										b = false;
										a = false;
										boucle=false;}
									
									else {return false;}
									}
									}
						
							 
							 
							 else if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
								 i++;
							 }
							 
								else if(line.charAt(i) == '_'){
									if((Character.isDigit(line.charAt(i+1))) || (Character.isLetter(line.charAt(i+1))) || Character.isUpperCase(line.charAt(i+1))) {
										i++;}
									else {
										return false;
									}}
							 
								
							 else {
									return false;}
							 
						}
						
					}
					
					
					else {
						return false;
					}
					
					
					
				}while(boucle);
				
			}
			
			}
			
			
			
			return bool;
	 
	 }
	 
	 public static boolean condition(String line) {
		  	int nbr = 0;
		  	int op = 0;
			boolean bool = true;
			boolean a = true;
			boolean boucle = true;
			for(int i = 0; i<line.length(); i++) {
			
			if (Character.isWhitespace(line.charAt(i))) {
				}
			
			
			else if (line.charAt(i)=='I') {
				i=i+2;
				do {
					 if (i>= line.length()) {
							return false; }
					 
					 else if (Character.isWhitespace(line.charAt(i))) {
						i++;}
					
					else	if(line.charAt(i)=='%') {
						nbr++;
						i++;
					while(a){
						 if (i>= line.length()) {
								return false; }
						 //----------------------------------------------------------------------------------
						 else if (Character.isWhitespace(line.charAt(i))) {
							i++;}
				//--------------------------------------------------------------------------------------------------------------------
						else if((Character.isLetter(line.charAt(i))) || (Character.isUpperCase(line.charAt(i)))){
							boolean b = true;
							i++;
							while(b) {
								 if (i>= line.length()) {
										return false; }
								 //-------------------------------------whte spaeceeeeeeeee-------------------------------------------------------------------------------------
							else if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
									 i++;
								 }
								 //------------------------------------------------------------------------------------------------------------
									else if(line.charAt(i) == '_'){
										if((Character.isDigit(line.charAt(i+1))) || (Character.isLetter(line.charAt(i+1))) || Character.isUpperCase(line.charAt(i+1))) {
											i++;}
										else {
											return false;
										}}
								 
								 //-------------------------------------------------------------------------------------------------------------------------------------
									else if (line.charAt(i)=='<'){
										if (op>=1) {
											return false;
										}
										i++;
										op++;
										if (line.charAt(i)=='=') {	
											i++; b=false;
										}
										else if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
											b =false;
										}
										else if (Character.isWhitespace(line.charAt(i))) {
											b = false;
										}
										else {
											return false;
										}
										}
									 else if (line.charAt(i)=='>'){
											if (op>=1) {
												return false;
											}
											i++;
											op++;
											if (line.charAt(i)=='=') {	
												i++; b=false;
											}
											else  if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
												b =false; 
											}
											else if (Character.isWhitespace(line.charAt(i))) {
												b = false; 
											}
											else {
												return false;
											}
											}
									 else if (line.charAt(i)=='='){
											if (op>=1) {
												return false;
											}
											if (line.charAt(i+1)!='=') {	
												return false;
											}
											i++;
											op++;
											if (line.charAt(i)=='=') {	
												i++; b=false;
											}
											
											else if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
												b =false; 
											}
											else if (Character.isWhitespace(line.charAt(i))) {
												b = false; 
											}
											else {
												return false;
											}
											}
								  //---------------------------------------------------------------------------------------------------------------------------------------
									else	if(line.charAt(i)=='%') {
										if (nbr ==0) {
											return false;
										}
										for( i = i+2; i<line.length(); i++) {
											 if (Character.isWhitespace(line.charAt(i))) {
												  }
											 else {
												 return false;}
										}
										if (nbr == 1 ) {
											a= false; 
											b = false;
											boucle = false;
										}
										else {
											return false;
										}
									}
						
									else  if (Character.isWhitespace(line.charAt(i))) {
										boolean c = true;
										while (c) {
											 if (i>= line.length()) {
													return false; }
											 //------------------------------------
											 else  if (Character.isWhitespace(line.charAt(i))) {i++;}
											 
											 //----------------------------------------------------------
											 
											 else if (line.charAt(i)=='<'){
													if (op>=1) {
														return false;
													}
													i++;
													op++;
													if (line.charAt(i)=='=') {	
														i++; b=false;c=false;//**********************************************************************************************************************************************************
													}
													else if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
														b =false; c=false;
													}
													else if (Character.isWhitespace(line.charAt(i))) {
														b = false; c=false;
													}
													else {
														System.out.println("the erro is here");
														return false;
													}}
											 else if (line.charAt(i)=='>'){
													if (op>=1) {
														return false;
													}
													i++;
													op++;
													if (line.charAt(i)=='=') {	
														i++; b=false;c=false;
													}
													else  if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
														b =false; c=false;
													}
													else if (Character.isWhitespace(line.charAt(i))) {
														b = false; c=false;
													}
													else {
														return false;
													}
													}
											 else if (line.charAt(i)=='='){
													if (op>=1) {
														return false;
													}
													if (line.charAt(i+1)!='=') {	
														return false;
													}
													i++;
													op++;
													if (line.charAt(i)=='=') {	
														i++; b=false;c=false;
													}
													
													else if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
														b =false; c=false;
													}
													else if (Character.isWhitespace(line.charAt(i))) {
														b = false; c=false;
													}
													else {
														return false;
													}
													}
											 //------------------------------------------
											 else	if(line.charAt(i)=='%') {
											if (nbr ==0) {
												return false;
											}
											for( i = i+2; i<line.length(); i++) {
												 if (Character.isWhitespace(line.charAt(i))) {
													  }
												 else {
													 return false;}
											}
											if (nbr == 1 ) {
												c = false;
												a= false; 
												b = false;
												boucle = false;
											}
											else {
												return false;
											}}
											 //-------------------------------------------------------
										else {
											return false;
										}}}
									else {
										return false;
									}}}
						 //---------------------------------------------------------if it is a digit and not a t------------------------
						 
						 
						else if(Character.isDigit(line.charAt(i))) {
							
							int point = 0;
							boolean b = true;
							i++;
							while(b) {
								 boolean c;
								if (i>= line.length()) {
										return false; }
								 //------------------------------------
							else if(Character.isDigit(line.charAt(i))) {
									 i++;
								 }
								 //------------------------------------------------------------------------------------------------------------
							else if (line.charAt(i) == '.') {
								if (point != 0) {
									return false;
								}
								if(Character.isDigit(line.charAt(i+1))) {
									 i++;point++;
								 }
								else {
									return false;
								}
								
							}
								 //-------------------------------------------------------------------------------------------------------------------------------------
							else if (line.charAt(i)=='<'){
								if (op>=1) {
									return false;
								}
								i++;
								op++;
								if (line.charAt(i)=='=') {	
									i++; b=false;
								}
								else if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
									b =false;
								}
								else if (Character.isWhitespace(line.charAt(i))) {
									b = false;
								}
								else {
									return false;
								}
								}
							 else if (line.charAt(i)=='>'){
									if (op>=1) {
										return false;
									}
									i++;
									op++;
									if (line.charAt(i)=='=') {	
										i++; b=false;
									}
									else  if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
										b =false; 
									}
									else if (Character.isWhitespace(line.charAt(i))) {
										b = false; 
									}
									else {
										return false;
									}
									}
							 else if (line.charAt(i)=='='){
									if (op>=1) {
										return false;
									}
									if (line.charAt(i+1)!='=') {	
										return false;
									}
									i++;
									op++;
									if (line.charAt(i)=='=') {	
										i++; b=false;
									}
									
									else if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
										b =false; 
									}
									else if (Character.isWhitespace(line.charAt(i))) {
										b = false; 
									}
									else {
										return false;
									}
									}
								  //---------------------------------------------------------------------------------------------------------------------------------------
									else	if(line.charAt(i)=='%') {
										if (nbr ==0) {
											return false;
										}
										for( i = i+2; i<line.length(); i++) {
											 if (Character.isWhitespace(line.charAt(i))) {
												  }
											 else {
												 return false;}
										}
										if (nbr == 1 ) {
											a= false; 
											b = false;
											boucle = false;
										}
										else {
											return false;
										}
									}
						
									else  if (Character.isWhitespace(line.charAt(i))) {
										 c = true;
										while (c) {
											 if (i>= line.length()) {
													return false; }
											 //------------------------------------
											 else  if (Character.isWhitespace(line.charAt(i))) {i++;}
											 //MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
											 //----------------------------------------------------------
											 
											 else if (line.charAt(i)=='<'){
													if (op>=1) {
														return false;
													}
													i++;
													op++;
													if (line.charAt(i)=='=') {	
														i++; b=false;c=false;//**********************************************************************************************************************************************************
													}
													else if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
														b =false; c=false;
													}
													else if (Character.isWhitespace(line.charAt(i))) {
														b = false; c=false;
													}
													else {
														System.out.println("the erro is here");
														return false;
													}}
											 else if (line.charAt(i)=='>'){
													if (op>=1) {
														return false;
													}
													i++;
													op++;
													if (line.charAt(i)=='=') {	
														i++; b=false;c=false;
													}
													else  if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
														b =false; c=false;
													}
													else if (Character.isWhitespace(line.charAt(i))) {
														b = false; c=false;
													}
													else {
														return false;
													}
													}
											 else if (line.charAt(i)=='='){
													if (op>=1) {
														return false;
													}
													if (line.charAt(i+1)!='=') {	
														return false;
													}
													i++;
													op++;
													if (line.charAt(i)=='=') {	
														i++; b=false;c=false;
													}
													
													else if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
														b =false; c=false;
													}
													else if (Character.isWhitespace(line.charAt(i))) {
														b = false; c=false;
													}
													else {
														return false;
													}
													}
											 //------------------------------------------
											 else	if(line.charAt(i)=='%') {
											if (nbr ==0) {
												return false;
											}
											for( i = i+2; i<line.length(); i++) {
												 if (Character.isWhitespace(line.charAt(i))) {
													  }
												 else {
													 return false;}
											}
											if (nbr == 1 ) {
												c = false;
												a= false; 
												b = false;
												boucle = false;
											}
											else {
												return false;
											}}
											 //-------------------------------------------------------
										else {
											return false;
										}}
										//------------------------------	
								  }
									else {
										return false;
									}}}	
						else {
							return false;}}}		
					else {
						return false;
					}
				}while(boucle);
				}}
			return bool;
}

	 public static boolean put(String line) {
		  int nbr = 0;
	
			boolean bool = true;
			boolean a = true;
			boolean boucle = true;
			for(int i = 0; i<line.length(); i++) {
			
			if (Character.isWhitespace(line.charAt(i))) {
				}
			
			
			else if (line.charAt(i)=='S') {
				i=i+7;
				do {
					 if (i>= line.length()) {
							return false; }
					 
					 else if (Character.isWhitespace(line.charAt(i))) {
						i++;}
					 
					 //---------------------------------------------------------------------------------------------------------------
					 else if (line.charAt(i)=='"') {
						 a = true;
						 nbr ++;
						 i++;
						 do {
							 if (i>= line.length()) {
									return false; }
							 else if (Character.isWhitespace(line.charAt(i))) {
									i++;}
							 else if ((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) ||Character.isUpperCase(line.charAt(i)) || line.charAt(i)== '\'' ) {
	  								i++;
	  								}
							 else if (line.charAt(i)=='"') {
								 if (i>= line.length()) {
										return false;}
								 if (nbr==1) {
									 nbr--;
									 i++;
									 boolean b = true;
									 while(b) {
										 if (Character.isWhitespace(line.charAt(i))) {
											 i++;
												}
										 else if (line.charAt(i) == '%' &&  line.charAt(i+1) == '.') {
											
												
												for( i = i+2; i<line.length(); i++) {
													 if (Character.isWhitespace(line.charAt(i))) {
														  }
													 else {
														 return false;}
												}
												b = false;
												a = false;
												boucle=false;}
										 
										 else  if(line.charAt(i) == '+'){
											  i++;
											  b = false;
											  a= false; }
										 
										 
										 else {
											 return false;}
									 }
									 } else {
										 return false;}
								 }
							 
							 
							 
							 else  {
								 return false;}
							 
							 
							 
						 }while(a);
					 }
				 //-----------------------------------------------------------------------------------------------------------------------
					
					 else if((Character.isLetter(line.charAt(i))) || (Character.isUpperCase(line.charAt(i)))){
						 a = true;
						 do {
							 
							  if((Character.isDigit(line.charAt(i))) || (Character.isLetter(line.charAt(i))) || Character.isUpperCase(line.charAt(i))) {
								  i++;
							 }
							  else if(line.charAt(i) == '_'){
									
									if((Character.isDigit(line.charAt(i+1))) || (Character.isLetter(line.charAt(i+1))) || Character.isUpperCase(line.charAt(i+1))) {
										i++;}
									else {
										return false;
									}}
							  
							  else  if (Character.isWhitespace(line.charAt(i))) {
								 boolean  b = true;
								 while(b) {
									 if (i>= line.length()) {
											return false; }
									 else  if (Character.isWhitespace(line.charAt(i))) { i++;}
									 else  if(line.charAt(i) == '+'){
								  i++;
								  b=false;
								  a= false; }
									 
									 
									 else if (line.charAt(i) == '%' &&  line.charAt(i+1) == '.') {
											
											
											for( i = i+2; i<line.length(); i++) {
												 if (Character.isWhitespace(line.charAt(i))) {
													  }
												 else {
													 return false;}
											}
											b = false;
											a = false;
											boucle=false;}
									 
									 else {
										 return false;
									 }
							  }
								  }
							  
								 else  if(line.charAt(i) == '+'){
									  i++;
									  a= false; }
								 else if (line.charAt(i) == '%' &&  line.charAt(i+1) == '.') {
										
										
										for( i = i+2; i<line.length(); i++) {
											 if (Character.isWhitespace(line.charAt(i))) {
												  }
											 else {
												 return false;}
										}
									
										a = false;
										boucle=false;}
								 else {
									 return false;
								 }
							  
							 	 
						 }while(a);
						 
						 
					 
					 }
				
					
					 
					 
					else {
						return false;
					}
					
					
					
				}while(boucle);
				}
			
			}
			
			
			
			return bool;
	 
	 }

	 public static boolean snl_start(String line) {
			
			boolean bool = true;
			boolean boucle = true;
			for(int i = 0; i<line.length(); i++) {
			
			if (Character.isWhitespace(line.charAt(i))) {
				}
			
			
			else if (line.charAt(i)=='S') {
				i=i+9;
				do {	
					 if (i>= line.length()) {
							return true; }
					 else if (Character.isWhitespace(line.charAt(i))) {
						i++;
					}
				
				
					else {
						return false;
					}
				
					
					
				}while(boucle);
				}
			
			}
			
			
			
			return bool;
	 
	 } 
	 
	 public static boolean start(String line) {
			
			boolean bool = true;
			boolean boucle = true;
			for(int i = 0; i<line.length(); i++) {
			
			if (Character.isWhitespace(line.charAt(i))) {
				}
			
			
			else if (line.charAt(i)=='S') {
				i=i+5;
				do {	
					 if (i>= line.length()) {
							return true; }
				
					 else if (Character.isWhitespace(line.charAt(i))) {
						i++;
					}
				
				
					else {
						return false;
					}
				
					
					
				}while(boucle);
				}
			
			}
			
			
			
			return bool;
	 
	 }
	
	 public static boolean else_condition(String line) {
			
			boolean bool = true;
			boolean boucle = true;
			for(int i = 0; i<line.length(); i++) {
			
			if (Character.isWhitespace(line.charAt(i))) {
				}
			
			
			else if (line.charAt(i)=='E') {
				i=i+4;
				do {
					if (i>= line.length()) {
							return true; }
					
					else if (Character.isWhitespace(line.charAt(i))) {
						i++;
					}
				
					
					else {
						return false;
					}
				
					
					
				}while(boucle);
				}
			
			}
			
			
			
			return bool;
	 
	 }
	 
	 public static boolean finish(String line) {
			
			boolean bool = true;
			boolean boucle = true;
			for(int i = 0; i<line.length(); i++) {
			
			if (Character.isWhitespace(line.charAt(i))) {
				}
			
			
			else if (line.charAt(i)=='F') {
				i=i+6;
				do {
					if (i>= line.length()) {
						boucle = false;
							return true; }
				
				
				else	if (Character.isWhitespace(line.charAt(i))) {
						i++;}
				
					
					
					else {
						boucle = false;
						return false;
					}
				
					
					
				}while(boucle);
				}
			
			}
			
			
			
			return bool;
	 
	 }

	 public static boolean snl_close(String line) {
			
			boolean bool = true;
			boolean boucle = true;
			for(int i = 0; i<line.length(); i++) {
			
			if (Character.isWhitespace(line.charAt(i))) {
				}
			
			
			else if (line.charAt(i)=='F') {
				i=i+9;
				do {
					if (i>= line.length()) {
						
							return true; }
				
				
				else	if (Character.isWhitespace(line.charAt(i))) {
						i++;}
				
					
					
					else {
						boucle = false;
						return false;
					}
				
					
					
				}while(boucle);
				}
			
			}
			
			
			
			return bool;
	 
	 }
	 
	 public static int erreur() {
		 
		return nbr_erreur;
		 
	 }
	 public String[] devide(String code)
	{	
		
		String[] line = code.split("(\\r\\n)+");
		return line;
		
	}

}








