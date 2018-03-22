package sample;
import java.math.BigInteger;

/**
 *
 */
public class ThreadCacl extends Thread {
    BigInteger startIndex;
    BigInteger endIndex;
    boolean  isTerminated=true;
    double result;

    public ThreadCacl(BigInteger startIndex, BigInteger endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public boolean getResult() {

        return isTerminated;
    }

    @Override
    public void run(){

        BigInteger i=BigInteger.valueOf(2);

        while ((i.compareTo(endIndex))<=0 && isTerminated==true){
            System.out.println(i);
            System.out.println(endIndex);
            if ((endIndex.mod(i)).equals(BigInteger.valueOf(0)))
            {
              isTerminated=false;
              break;
             }
             else{
                 isTerminated=true;
                i= i.add(BigInteger.valueOf(1));

             }
        }
    }
}
