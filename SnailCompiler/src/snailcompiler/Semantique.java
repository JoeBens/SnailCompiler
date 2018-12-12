/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snailcompiler;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author karim
 */
public class Semantique {
    private ArrayList stlignes =new ArrayList();
    private StringTokenizer st;
    
    
    Semantique(){
        
    }
    
    public String SemantiqueMethod(ArrayList lignes){
        
        String aa="";
        
        for(int i=1; i<lignes.size() ;i++){
            st = new StringTokenizer((String) lignes.get(i));
            
            while (st.hasMoreTokens())
                stlignes.add(st.nextToken());   
            
            if(stlignes.get(0).equals("Snl_Int")){
                aa=aa+"int "+stlignes.get(1)+" ,"+stlignes.get(3)+" ,"+stlignes.get(5)+" ,"+stlignes.get(7)+";"+"\n";
            }
            if(stlignes.get(0).equals("Snl_Real")){
                aa=aa+"Float "+stlignes.get(1)+";"+"\n";
            }
            if(stlignes.get(0).equals("Set")){
                aa=aa+stlignes.get(1)+" = "+stlignes.get(2)+";"+"\n";
            }
            if(stlignes.get(0).equals("If")){
                aa=aa+"if("+stlignes.get(2)+stlignes.get(3)+stlignes.get(4)+")\n";
            }
            if(stlignes.get(0).equals("Else")){
                aa=aa+"else"+"\n";
            }
            if(stlignes.get(0).equals("Start")){
                aa=aa+"{"+"\n";
            }
            if(stlignes.get(0).equals("Finish")){
                aa=aa+"}"+"\n";
            }
            if(stlignes.get(0).equals("Get")){
                aa=aa+stlignes.get(1)+" = "+stlignes.get(3)+";"+"\n";
            }
            if(stlignes.get(0).equals("Snl_Put")){
                aa=aa+"System.out.print(\"";
                if(stlignes.get(1).equals("\"")){
                    for(int j=2 ;j<stlignes.size()-1 ;j++)
                        aa=aa+" "+stlignes.get(j);
                    aa=aa+");";
                }
                else{
                    aa=aa+stlignes.get(1)+"\");";
                }
                aa=aa+"\n";
            }
            
            
        stlignes.clear();
        }
        return aa;
    }
    
} 
