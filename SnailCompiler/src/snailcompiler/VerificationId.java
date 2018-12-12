/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snailcompiler;

/**
 *
 * @author karim
 */
public class VerificationId {
     VerificationId( ){    }
    
    public int VerificationDeId(String id){
        int bool=1 ;
	
	switch(id) {
	case "Snl_Start" : 
		bool =0; 
		break;
	case "Snl_Int" :
		bool =0; 
		break;
	case "Snl_Real" : 
		bool =0; 
		break;
	case "Snl_Put" : 
		bool =0; 
		break;
	case "SnlSt" : 
		bool =0; 
		break;
	case "Set" : 
		bool =0; 
		break;
	case "Get" : 
		bool =0; 
		break;
	case "If" : 
		bool =0; 
		break;
	case "Else" : 
		bool =0; 
		break;
	case "Start" : 
		bool =0; 
		break;
	case "Finish" : 
		bool =0; 
		break;
		
	}
		
	if((Character.isLetter(id.charAt(0))) || (Character.isUpperCase(id.charAt(0)))){ 
	
            for (int i =1; (i< id.length()) && (bool==1) ; i++){
                
                if((Character.isDigit(id.charAt(i))) || (Character.isLetter(id.charAt(i))) ||Character.isUpperCase(id.charAt(i))) {
                    bool=1 ;
	        }
                
                else if(id.charAt(i) == '_'){
                    if((Character.isDigit(id.charAt(i+1))) || (Character.isLetter(id.charAt(i+1))) || Character.isUpperCase(id.charAt(i+1))) {
		         bool=1;
                    }
                }
                
	        else bool = 0;
		                    
            }
            
        }
        else bool = 0;
        
        //if(bool==1)System.out.println("valide");
        //else if(bool==0)System.out.println("non valide");
        
        return bool;
    }
}
