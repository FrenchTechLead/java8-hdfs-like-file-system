package nameNode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dynamicCodeLoading.ComputeInterface;
import dynamicCodeLoading.StockBlocks;

public class NameNodeFunctions {

	// fonction qui affiche les choix que peux rentrer un utilisateur
	public static List<String> getChoices() {
		List<String> choices = new ArrayList<String>();
		choices.add("Generate random file");
		choices.add("Send Blocks to dataNodes");
		return choices;
	}

	
	// fonction qui genere un fichier random
	public static void writeRandomFile(String fileName) {
		Random rand = new Random();
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"))) {
			for(int i = 0 ; i< 4094 ; i++)
				writer.write(rand.nextInt()+"\n");
			writer.close();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--File generation OK--");
	}
	
	
	// fonction qui envoie les blocks aux dataNodes
	//@param dataNodesList : une liste qui contient les dataNodes de destination obtenue du registre
	public static void sendBlocksToDataNodes(String fileName, List<ComputeInterface> dataNodesList, int RF, int N) throws Exception{
		ArrayList<String> fileList = fileToList(fileName);
        int currentDataNode = 0;
        int currentBlock = 1;
        int lastBlockIndex = 1;
        
        for(int i = 1 ;i<=fileList.size(); i++){
        	if(i%64 == 0){
        		lastBlockIndex =i;
        		List<String> toSend = new ArrayList<String>(fileList.subList(i-64,i));
        		String name = currentBlock<10 ? "B_0"+currentBlock : "B_"+currentBlock;
        		StockBlocks task = new StockBlocks(toSend,name);
        		for(int j = 0; j<RF ; j++){
            		ComputeInterface dataNode = dataNodesList.get(currentDataNode%N);	
            		dataNode.executeTask(task);
            		currentDataNode++;
	            }
        		currentBlock++;
        	}if(i == fileList.size() && i != lastBlockIndex){
        		List<String> toSend = new ArrayList<String>(fileList.subList(lastBlockIndex,i));
        		String name = currentBlock<10 ? "B_0"+currentBlock : "B_"+currentBlock;
        		StockBlocks task = new StockBlocks(toSend,name);
        		for(int j = 0; j<RF ; j++){
            		ComputeInterface dataNode = dataNodesList.get(currentDataNode%N);	
            		dataNode.executeTask(task);
            		currentDataNode++;
	            }
        	}
        }
	}
	
	// converts a file into a list containing its lines  
	public static ArrayList<String> fileToList(String fileName) throws Exception{
		ArrayList<String> list= new ArrayList<String>();
		File file = new File(fileName);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       list.add(line);
		    }
		}
		System.out.println("The file's initial length "+list.size());
		return list;
	}
	
	
	public static List<ComputeInterface> getDataNodesList() throws Exception{
		ComputeInterface compute0,compute1,compute2,compute3,compute4 =null;
			Registry registry = LocateRegistry.getRegistry(1099);

			compute0 = (ComputeInterface) registry.lookup("dataNode0");
			compute1 = (ComputeInterface) registry.lookup("dataNode1");
			compute2 = (ComputeInterface) registry.lookup("dataNode2");
			compute3 = (ComputeInterface) registry.lookup("dataNode3");
			compute4 = (ComputeInterface) registry.lookup("dataNode4");
			
			List<ComputeInterface> dataNodesList = new ArrayList<ComputeInterface>();
			dataNodesList.add(compute0);
			dataNodesList.add(compute1);
			dataNodesList.add(compute2);
			dataNodesList.add(compute3);
			dataNodesList.add(compute4);
			return dataNodesList;
	}
	
}
