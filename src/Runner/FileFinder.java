package Runner;

import java.util.ArrayList;

import ioPackage.FileOperations;
import fileUtility.FileQuery;

public class FileFinder {
	
	private static String fileName;
	private static ArrayList<String> dirNames;
	
	public static void workFlow(String[] args){
		int i = 0;
		int length = args.length;
		String command;
		FileOperations fo = new FileOperations();
		
		if(length == 0){
			System.out.println("Specify at least two arguments");
			return;
		}
		else{
			command = args[0];
			FileQuery fq = new FileQuery();
			if(command.equals("--load")){
				dirNames = new ArrayList<String>();
				StringBuilder sb = new StringBuilder();
				for(i = 1; i < length; i++){
					dirNames.add(fq.getFileName(args[i]));
					fileName = fq.getFileName(args[i]);
					fq.printFiles(args[i], fileName);
				}
				for(String s : dirNames){
					sb.append(s);
					sb.append("|||\n");
				}
				fo.writeToFile("Directory_Names.txt", sb.toString(), true);
				
			}else if(command.equals("--find")){
				ArrayList<String> dirNames = fo.readFile("Directory_Names.txt");
				for(String s : dirNames){
					fq.searchFile(args[1], s);
				}
				fq.printResult();
			}else{
				System.out.println("Wrong command.");
			}
		}
		
	}

	public static void main(String[] args) {
		workFlow(args);
	}
}
