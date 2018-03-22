package sample;
import java.math.BigInteger;
import java.security.SecureRandom;
/**
 *
 *
 */
public class MillerRabin {

   public BigInteger sqrt(BigInteger N)
   {
       BigInteger result=N.divide(BigInteger.valueOf(2));
       while(result.multiply(result).subtract(N).compareTo(BigInteger.ONE.divide(new BigInteger("100000000")))>0)
        result=result.add(N.divide(result)).divide(BigInteger.valueOf(2));
       return result;
   }


    public boolean isPrime(BigInteger n){
        for(BigInteger  i=BigInteger.valueOf(2);i.compareTo(sqrt(n))<=0; i=i.add(BigInteger.ONE))
        if((n.mod(i)).compareTo(BigInteger.ZERO)==0) {
            return false;
        }
        return true;
    }
}


