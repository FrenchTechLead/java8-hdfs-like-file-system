/*
 * TP MaxComputing
 * Date: 11/04/2017
 * Nom:  MECHERI
 * Prenoms: Mohammed Akram
 * email : mecheri.akram@gmail.com
 * Remarque:
 */
package dynamicCodeLoading;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ComputeEngine4 extends UnicastRemoteObject implements ComputeInterface {

	private static final long serialVersionUID = 4L;

	public ComputeEngine4() throws RemoteException {
		super();
	}

	public <T> T executeTask(Task<T> t) throws RemoteException {

		System.out.println("dataNode 4 has got a task: " + t);

		return t.execute(4);
	}
}

