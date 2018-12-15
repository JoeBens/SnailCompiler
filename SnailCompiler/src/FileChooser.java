//import java.awt.desktop.SystemEventListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {
	private File file ;
	//Declare Variable
	 
	 StringBuilder sb = new StringBuilder();
	
	  public void PickMe() throws Exception{ 
		  
		  JFileChooser fileChooser = new JFileChooser();
		  FileNameExtensionFilter filter = new FileNameExtensionFilter("SNL file", "snl");
	 
		 fileChooser.setDialogTitle("Choose a file");
		 fileChooser.setFileFilter(filter);
		 if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	
		 //get the file
	     file = fileChooser.getSelectedFile();
	
		
	    readFile();
	 
	    }
	
}
 
	  public String filePath() {
		  return file.getAbsolutePath();
	  }
	 
	 public String readFile()
	{StringBuffer stringBuffer = null ;
		try {
			
			FileReader fileReader = new FileReader(file);
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
	
	
	public String[] devide(String textFile)
	{
	
		String[] instruction  = textFile.split("(\\r\\n)+");
		return instruction;
	 
		
	}
	
	


}
