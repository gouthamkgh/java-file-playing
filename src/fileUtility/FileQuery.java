package fileUtility;

import ioPackage.FileOperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FileQuery {
	
	FileOperations fo = new FileOperations();
	private String fileName;
	private ArrayList<String> resultArray = new ArrayList<String>();
	
	public void printFiles(String directoryName, String fileName){
		this.fileName = fileName; 
		StringBuilder sb = new StringBuilder();
		File[] files = new File(directoryName).listFiles();
		try {
			for(File f : files){
				sb.append(f.getAbsolutePath());
				sb.append("|||\n");
				if(f.isDirectory()){
					printFiles(f.getAbsolutePath(), fileName);
				}
			}
			fo.writeToFile(fileName, sb.toString(), false);
			
		} catch (Exception e) {
			System.out.println("Specified directory does not exist");
		}
	}
	
	public void searchFile(String searchString, String fileName){
		BufferedReader br = null;
		try {
			String currentLine;
			br = new BufferedReader(new FileReader(fileName));
			while((currentLine = br.readLine()) != null){
				if(currentLine.toLowerCase().contains(searchString.toLowerCase())){
					resultArray.add(currentLine);
				}
			}
		} catch (Exception e) {
			System.out.println("Invalid search query");
		}
	}
	
	public void printResult(){
		for(String s : resultArray){
			System.out.println(s);
		}
	}
	
	public String getFileName(String directoryName){
		String temp = directoryName;
		String[] splits = temp.split("/");
		return splits[splits.length-1]+"_fileList.txt";
		
	}

}
