package snailcompiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import snailcompiler.VerificationId;

/**
 *
 * @author karim
 */
public class Syntaxique {
    private StringTokenizer st;
    private ArrayList stlignes=new ArrayList();
    private VerificationId id=new VerificationId();
    private int bool;
    private String aa="";
    
    Syntaxique(){        
    } 
    
    public String Verificationdeslignes(ArrayList lignes){
        if(lignes.get(0).equals("Snl_Star") && lignes.get(lignes.size()-1).equals("Snl_Close")) {  
            aa="ligne correct";
        
            for(int i=1 ;i<lignes.size() ;i++){
                st = new StringTokenizer((String) lignes.get(i));
                while (st.hasMoreTokens())
                    stlignes.add(st.nextToken());
                if(stlignes.get(0).equals("Snl_Int") || stlignes.get(0).equals("Snl_Real")){
                    for(int j=1 ;j<stlignes.size() ;j++){
                        if(stlignes.get(j).equals("%.")){
                            //bool=1;
                            break;
                        }
                        else if(id.VerificationDeId((String) stlignes.get(j))==1){
                             bool=1;
                        }
                        else if(stlignes.get(j).equals(","));
                        else {bool=0;break;}
                    }
                    if(bool==1)aa=aa+lignes.get(i)+" : ligne correct";
                    else if(bool==0) aa=aa+"ligne incorrect ";
                }
                if(stlignes.get(0).equals("Get")){
                    if(id.VerificationDeId((String) stlignes.get(1))==1){
                        if(stlignes.get(2).equals("from")){
                            if(id.VerificationDeId((String) stlignes.get(3))==1){
                                if(stlignes.get(4).equals("%.")){
                                    bool=1;
                                }
                            }    
                        }
                    }  
                    else bool=0;  
                    if(bool==1)aa=aa+lignes.get(i)+" : ligne correct";
                    else if(bool==0) aa=aa+"ligne incorrect";
                }
                if(stlignes.get(0).equals("Snl_Put")){
                    if(stlignes.get(1).equals("\"")){
                        if(stlignes.get(stlignes.size()-2).equals("\"")){
                            if(stlignes.get(stlignes.size()-1).equals("%.")){
                                bool=1;
                            }
                        }
                    }
                    else if (id.VerificationDeId((String) stlignes.get(1))==1){
                        if(stlignes.get(stlignes.size()-1).equals("%.")){
                                bool=1;
                            }
                    }
                    else bool=0;
                    if(bool==1)aa=aa+lignes.get(i)+" : ligne correct";
                    else if(bool==0) aa=aa+"ligne incorrect";
                }
                if(stlignes.get(0).equals("%..")){
                    bool=1;
                    aa=aa+lignes.get(i)+" : ligne correct";
                }
                if(stlignes.get(0).equals("If")){
                    if(stlignes.get(1).equals("%")){
                        if(id.VerificationDeId((String) stlignes.get(2))==1){
                            if(stlignes.get(3).equals("<") || stlignes.get(3).equals(">")){
                                if(id.VerificationDeId((String) stlignes.get(4))==1){
                                    if(stlignes.get(3).equals("%")){
                                        if(stlignes.get(3).equals("do")){
                                            bool=1;
                                        }
                                    }
                                }
                            }    
                        }
                    }  
                    else bool=0;  
                    if(bool==1)aa=aa+lignes.get(i)+" : ligne correct";
                    else if(bool==0) aa=aa+"ligne incorrect";
                }
                if(stlignes.get(0).equals("Set")){
                    if(id.VerificationDeId((String) stlignes.get(1))==1){
                        //if(id.chekID((String) stlineList.get(3))==1)
                            if(stlignes.get(stlignes.size()-1).equals("%.")){
                                bool=1;
                            }
                            
                    }  
                    else bool=0;  
                    if(bool==1)aa=aa+lignes.get(i)+" : ligne correct";
                    else if(bool==0) aa=aa+"ligne incorrect";
                }
                if(stlignes.get(0).equals("Else") || stlignes.get(0).equals("Start") || stlignes.get(0).equals("Finish")){
                    bool=1;
                    aa=aa+lignes.get(i)+" : ligne correct";
                }
                aa=aa+"\n";
                stlignes.clear();
            }
        }
        return aa;
    }
}
