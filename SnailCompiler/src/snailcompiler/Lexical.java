/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snailcompiler;

import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author karim
 */
public class Lexical {
    private String token="";
    private Vector tokens =new Vector();
    
    
    
    Lexical(){
        
    }

    public String verifierLesTokens(String code){
        
        StringTokenizer st = new StringTokenizer(code);
        
        while (st.hasMoreTokens())
            tokens.add(st.nextToken());
        
        for(int i=0 ;i<tokens.size() ; i++){
            if(tokens.get(i).equals("Snl_Start")) 
                token=tokens.get(i)+" : Mot résevé pour debut du programme\n";
            else if(tokens.get(i).equals("Snl_Close")) 
                token=token+tokens.get(i)+" : Mot réservé pour fin du programme\n";
            else if(tokens.get(i).equals("Snl_Int")) {
                token=token+tokens.get(i)+" : Mot resevé pour déclaration d'un entier\n"+tokens.get(i+1)+" : identificateur\n";
            }
            else if(tokens.get(i).equals(",")) {
                token=token+tokens.get(i+1)+" : identificateur\n";
            }
            else if(tokens.get(i).equals("Snl_Real")) {
                token=token+tokens.get(i)+" : Mot resevé pour declaration d'un réel\n"+tokens.get(i+1)+" : identificateur\n";
            }
            else if(tokens.get(i).equals("%.")) 
                token=token+tokens.get(i)+" : Mot resevé pour fin d'instruction\n";
            else if(tokens.get(i).equals("Set")) 
                token=token+tokens.get(i)+" : Mot resevé pour une affectaion d'une valeur à un id\n";
            else if(tokens.get(i).equals("If")) 
                token=token+tokens.get(i)+" : Mot resevé pour  condition\n";
            else if(tokens.get(i).equals("Else")) 
                token=token+tokens.get(i)+" : Mot resevé pour une 2eme condition\n";
            else if(tokens.get(i).equals("<")) 
                token=token+tokens.get(i)+" : Operateur de comparaison\n";
            else if(tokens.get(i).equals(">")) 
                token=token+tokens.get(i)+" : Operateur de comparaison\n";
            else if(tokens.get(i).equals("%")) 
                token=token+tokens.get(i)+" : debut ou fin de condition\n";
            else if(tokens.get(i).equals("Start")) 
                token=token+tokens.get(i)+" : Mot réservé pour un debut d'un block\n";
            else if(tokens.get(i).equals("Finish")) 
                token=token+tokens.get(i)+" : Mot resevé pour une fin d'un block`n";
            else if(tokens.get(i).equals("Snl_Put"))
                token=token+tokens.get(i)+" : Mot réservé pour printf\n";
            else if(tokens.get(i).equals("%.."))
                token=token+tokens.get(i)+" : Mot resevé pour commentaire\n";
        }
        return token;
        
    }
}
