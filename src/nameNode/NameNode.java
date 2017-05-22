package nameNode;


import java.util.List;
import java.util.Scanner;


public class NameNode {

	private static final int RF=3,N=5;

 public static void main(String[] args) {
	System.setProperty("java.rmi.server.codebase",
		NameNode.class.getProtectionDomain().getCodeSource().getLocation().toString());

	System.setProperty("java.security.policy", "allow_all.policy");
	
	try{
			
		while(true){
			System.out.println("\nPlease select one option:");
			System.out.println("*****************************");
			List<String> choices = NameNodeFunctions.getChoices();
		    for(int i = 0; i<choices.size() ; i++)
		    	System.out.println("- "+ i +" "+choices.get(i));
		    System.out.println("*****************************");
		    Integer selection = new Scanner(System.in).nextInt();
		    
		    switch (selection) {
		        case 0:
		            System.out.println("Generating the file...");
		            NameNodeFunctions.writeRandomFile("F.txt");
		            break;
		            
		        case 1:
		            System.out.println("Sending blocks to dataNodes...");
		            NameNodeFunctions.sendBlocksToDataNodes("F.txt",NameNodeFunctions.getDataNodesList(), RF, N);            
		            System.out.println("--Blocks sent OK--");
		            break;

		        default:
		        	new Exception("Invalid choice").printStackTrace();
		   }
		}	

	} catch (Exception e) {
		System.err.println("Client exception:");
		e.printStackTrace();
	}
	
  }
}
