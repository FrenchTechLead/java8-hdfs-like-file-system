/*
 * TP MaxComputing
 * Date: 11/04/2017
 * Nom:  MECHERI
 * Prenoms: Mohammed Akram
 * email : mecheri.akram@gmail.com
 * Remarque:
 */
package dynamicCodeLoading;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ComputeInterface extends Remote {
        
    public <T> T executeTask(Task<T> t) throws RemoteException;
}
