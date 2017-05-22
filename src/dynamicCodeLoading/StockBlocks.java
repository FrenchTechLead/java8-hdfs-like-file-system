/*
 * TP MaxComputing
 * Date: 11/04/2017
 * Nom:  MECHERI
 * Prenoms: Mohammed Akram
 * email : mecheri.akram@gmail.com
 * Remarque:
 */
package dynamicCodeLoading;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.List;

public class StockBlocks implements Task<Boolean>, Serializable {

	private static final long serialVersionUID = 39429L;

	private final List<String> blocks;
	private final String blockName;
	
	public StockBlocks(List<String> blocks, String blockName) {
		this.blocks = blocks;
		this.blockName = blockName;
	}

	public Boolean execute(int dataNodeID) {
		System.out.println("Block: "+this.blockName+" received, size: "+this.blocks.size()+"\n");
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("dataNode"+dataNodeID+"/"+this.blockName+".txt"), "utf-8"))) {
			for(String line : this.blocks)
				writer.write(line+"\n");
			writer.close();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
