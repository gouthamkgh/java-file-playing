package ioPackage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileOperations { 
	
	public void writeToFile(String fileName, String content, boolean directory){
		File file = null;
		if(directory){
			file = new File("Directory_Names.txt");
		} else {
			file = new File(fileName);
		}
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
				BufferedWriter bw = new BufferedWriter(fw);
				StringTokenizer st = new StringTokenizer(content, "|||\n");
				while (st.hasMoreTokens()) {
					String s = st.nextToken().trim();
					bw.write(s + "\n");
				}
				bw.close();
		} catch (Exception e) {
			System.out.println("Invalid file name");
		}
	}
	
	public ArrayList<String> readFile(String fileName){
		BufferedReader br = null;
		ArrayList<String> dirNamesArray = new ArrayList<String>();
		try {
			String currentLine;
			br = new BufferedReader(new FileReader(fileName));
			while((currentLine = br.readLine()) != null){
				dirNamesArray.add(currentLine.trim());
			}
		} catch (Exception e) {
			System.out.println("Error reading file");
		}
		return dirNamesArray;
	}
}
