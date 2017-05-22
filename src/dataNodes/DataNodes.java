package dataNodes;

import java.util.HashMap;

import dynamicCodeLoading.ComputeEngine0;
import dynamicCodeLoading.ComputeEngine1;
import dynamicCodeLoading.ComputeEngine2;
import dynamicCodeLoading.ComputeEngine3;
import dynamicCodeLoading.ComputeEngine4;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class DataNodes {

    /*TODO les dataNodes sont les clients HDFS mais ils sont des serveurs RMI, */
    public static void main(String[] argv) {



        try {
            HashMap<String,Remote> dataNodes = new HashMap<>() ;
            dataNodes.put("dataNode0", new ComputeEngine0());
            dataNodes.put("dataNode1", new ComputeEngine1());
            dataNodes.put("dataNode2", new ComputeEngine2());
            dataNodes.put("dataNode3", new ComputeEngine3());
            dataNodes.put("dataNode4", new ComputeEngine4());
            
            Registry registry = LocateRegistry.createRegistry(1099);

            // regestring dataNodes in the registry
            for (String key : dataNodes.keySet())
                registry.rebind(key, dataNodes.get(key));
            
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
