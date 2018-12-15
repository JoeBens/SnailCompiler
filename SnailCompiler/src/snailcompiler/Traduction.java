package snailcompiler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Traduction {

	File fichier = new File("C:\\Users\\Joe\\Desktop\\Test.java") ; 
	private String code;
	private static String consoleOutput = "";
        private String sourceCode;
        public String xd;
	
	
	public Traduction (String code) {
		this.code = code ;
		
	}
	public void compile() throws IOException {
		String resultat = " ";
		
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fichier)) ){
		String[] line = divide();
		for(int i = 0 ; i<line.length; i++) {
		String[] word = line[i].split("\\s+");
		int j = 0;
		if ( word[j].isEmpty()) {
			j= 1;
		}
			//resultat =  resultat +"\n"+ keyWord(line[i]);
			switch(word[j]) {
			case "Snl_Start" : 
				bufferedWriter.write("public class Test{");
				bufferedWriter.newLine();
				bufferedWriter.write("public static void main(String args[]) {");
				bufferedWriter.newLine();
				
				break;
			case "Snl_Close" : 
				bufferedWriter.write("}}");
			
				break;
			case "Snl_Int" :
				String int_var = "";
				for(int k = 0; k<line[i].length(); k++) {
					
				 if (line[i].charAt(k)=='S') {
						k=k+7;
						do {
							int_var = int_var + line[i].charAt(k);
							k++;
						}while(line[i].charAt(k)!='%');
						
					}}
				bufferedWriter.write("int "+int_var+" ;" );
				bufferedWriter.newLine();
				break;
			case "Snl_Real" : 
				String real_var = "";
				for(int k = 0; k<line[i].length(); k++) {
					
				 if (line[i].charAt(k)=='S') {
						k=k+8;
						do {
							real_var = real_var + line[i].charAt(k);
							k++;
						}while(line[i].charAt(k)!='%');
						
					}}
				bufferedWriter.write("double "+real_var+" ;" );
				bufferedWriter.newLine();
				break;
			case "Snl_Put" : 
			String affichage ="";
			for(int k = 0; k<line[i].length(); k++) {
				
				 if (line[i].charAt(k)=='S') {
						k=k+7;
						do {
							affichage = affichage + line[i].charAt(k);
							k++;
						}while(line[i].charAt(k)!='%');
						}}
			
			bufferedWriter.write("System.out.println( "+affichage+") ;" );
			bufferedWriter.newLine();
				break;
		
			case "%.." : 
				String comment = "";
				for(int k = 4; k<line[i].trim().length(); k++) {
					
					comment = comment +line[i].trim().charAt(k);
				}
				bufferedWriter.write("//"+comment );
				bufferedWriter.newLine();
				break;
			
			case "Set" : 
				String var ="";
				String nbr ="";
				String[] w = line[i].split("\\s+");
				for(int k = 0; k<w.length;k++) {
					if (w[k].isEmpty()) {
						
					}
					else if(ident(w[k])) {
					var = w[k];
						}
					else if (integer(w[k]) || real(w[k]))	{
						nbr = w[k];
					}
				}
				bufferedWriter.write(var +"= "+nbr +" ;" );
				bufferedWriter.newLine();
				break;
			case "Get" : 
				var = "";
				String var1 = "";
				String[] w1 = line[i].split("\\s+");
				for(int k = 0; k<w1.length;k++) {
					if (w1[k].isEmpty()) {
						
					}
					else if(ident(w1[k])) {
					var = w1[k];
					var1  = w1[k+2];
					k=w1.length;
						}
					
				}
				bufferedWriter.write(var +"= "+var1 +" ;" );
				bufferedWriter.newLine();
				break;
			case "If" : 
				String if_var ="";
				int k = 0;	 
				while(line[i].charAt(k)!='%') {
					k++;
				}
				k++;
				while(line[i].charAt(k)!='%'){
					
					if_var = if_var +line[i].charAt(k);
					k++;
				} 
					 bufferedWriter.write("if("+if_var +" )" );
						bufferedWriter.newLine();
			
				break;
			case "If%" : 
				
			
				break;
			
			case "Else" : 
				bufferedWriter.write("else" );
				bufferedWriter.newLine();
			
				break;
			case "Start" : 
				bufferedWriter.write("{" );
				bufferedWriter.newLine();
				break;
			case "Finish" : 
				bufferedWriter.write("}" );
				bufferedWriter.newLine();
				break;	
				
			}
                        
		}
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		/*runProcess("javac file.java");
		Process p= Runtime.getRuntime().exec("cmd /c   javac Test.java");
			//p= Runtime.getRuntime().exec("cmd /c java Test");
		 String s;
		    System.out.println(p.getOutputStream());
		    BufferedReader stdInput = new BufferedReader(new 
		            InputStreamReader(p.getInputStream()));
		    while((s=stdInput.readLine())!=null){
		     System.out.println(s);*/
		     
		consoleOutput = "";
        /*compileAndRun();*/
				
	}
        public String getCode(){
            StringBuffer stringBuffer = null ;
		try {
			
			FileReader fileReader = new FileReader(fichier);
			 stringBuffer = new StringBuffer();
			int numCharsRead;
			char[] charArray = new char[1024];
			while ((numCharsRead = fileReader.read(charArray)) > 0) {
				stringBuffer.append(charArray, 0, numCharsRead);
				
			}
			fileReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
            
   
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

	
	public String[] divide()
	{	
		
		String[] line = code.split("(\\r\\n)+");
		return line;
		
	}

	 /*public String getConsoleOutput()
	    {
	        consoleOutput = "";
	        compileAndRun();
	        return consoleOutput;
	    }

	    private void compileAndRun()
	    {
	        String FILENAME = "C:\\Users\\Joe\\Desktop\\Test";
	        try
	        {
	            
	            runProcess("C:\\Users\\Joe\\Desktop\\Test.java");
	            consoleOutput = readStream(runProcess("java "+FILENAME));

	            
	        } catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }

	    private static String readStream(InputStream ins) throws Exception
	    {
	        String line = null;
	        String str = "";
	        BufferedReader in = new BufferedReader(new InputStreamReader(ins));
	        while ((line = in.readLine()) != null)
	            str += line + '\n';
	        System.out.println(str);
	        return str;
	    }

	    private static InputStream runProcess(String command) throws Exception
	    {
	        Process pro = Runtime.getRuntime().exec(command);
	        System.err.println(readStream(pro.getErrorStream()));
	        return pro.getInputStream();
	    }
*/







}
