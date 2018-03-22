/*
 *
 */
package sample;
import java.math.BigInteger;
import java.util.Random;

public class PQGenerator {

    public  BigInteger p;
    public BigInteger q;
    MillerRabin  millerRabin=new MillerRabin();
    public BigInteger generateP(){
        int bitLength=1024;
        Random rnd = new Random();
        int certaintyP = 5;
        int intP= rnd.nextInt(5)+4;
        p=BigInteger.valueOf(intP);
        MillerRabin test=new MillerRabin();
        if (!test.isPrime(p)){
             System.out.println(p);
             p=BigInteger.ZERO;
             generateP();
         }
         else{
             System.out.println(p);
         }
         return p;
     }

    public BigInteger generateQ(){
        int bitLength=1024;
        Random rnd = new Random();
        int certaintyQ=4;
        int intQ= rnd.nextInt(5)+4;
        q=BigInteger.valueOf(intQ);
        MillerRabin test=new MillerRabin();
        if (!test.isPrime(q)){
            generateQ();
        }
        else{
            if(q.equals(p)){
                generateQ();

            }
        }
        return q;
    }

}
