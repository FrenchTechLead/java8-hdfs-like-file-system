package tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import nameNode.NameNodeFunctions;

public class BlocksDistributionTests {
 
	
	
	// Make sure that the dataNodes are running before testing this
	// si la distribution respecte bien le Replication factor = 3 on est cens� avoir 3 fois plus de data 
	// dans les datanode que dans le namenode
	@Test
	public void testSizeCoherence() throws Exception {
		NameNodeFunctions.writeRandomFile("F.txt");
		NameNodeFunctions.sendBlocksToDataNodes("F.txt",NameNodeFunctions.getDataNodesList(), 3, 5);
		File fileF =new File("F.txt");
		double initialSize = fileF.length();
		double blocksSize = 	folderSize(new File("dataNode0"))+
							folderSize(new File("dataNode1"))+
							folderSize(new File("dataNode2"))+
							folderSize(new File("dataNode3"))+
							folderSize(new File("dataNode4"));
		
		System.out.println(blocksSize);
		assertEquals(initialSize*3, blocksSize, 0.0);
		
	}

	public static long folderSize(File directory) {
	    long length = 0;
	    for (File file : directory.listFiles()) {
	        if (file.isFile())
	            length += file.length();
	        else
	            length += folderSize(file);
	    }
	    return length;
	}
}
