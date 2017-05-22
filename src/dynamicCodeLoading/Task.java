/*
 * TP MaxComputing
 * Date: 11/04/2017
 * Nom:  MECHERI
 * Prenoms: Mohammed Akram
 * email : mecheri.akram@gmail.com
 * Remarque:
 */
package dynamicCodeLoading;

public interface Task<T> {
    public T execute(int dataNodeID);
}

