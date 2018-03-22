package sample;
import java.math.BigInteger;

/**
 *
 */
public class isPrime {
    public  BigInteger size;
    public int NUNMBER_JOBS = 2;
    boolean parallelResult=true;
    public BigInteger p;//= new BigInteger("22");
    public BigInteger q;//= new BigInteger("11");;

    public isPrime(String p, String q) {
        this.p =new BigInteger(p);
        this.q = new BigInteger(q);
    }

    public BigInteger Size(BigInteger p, BigInteger q){
    return  (p.multiply(q)).pow(1/2);

    }

    final BigInteger TWO = new BigInteger("2");



    public  void isPrimeMethod() throws  InterruptedException {

       ThreadCacl TreadArrray[] = new ThreadCacl[NUNMBER_JOBS];

            for (int i = 0; i < NUNMBER_JOBS; i++) {
                TreadArrray[i] = new ThreadCacl(
                        Size(p, q).divide(BigInteger.valueOf(NUNMBER_JOBS)).multiply(BigInteger.valueOf(i)),
                        i == NUNMBER_JOBS - 1 ? Size(p, q) : (Size(p, q).divide(BigInteger.valueOf(NUNMBER_JOBS))).multiply(BigInteger.valueOf(i + 1)));
                TreadArrray[i].start();
            }

            for (int i = 0; i < NUNMBER_JOBS; i++) {
                TreadArrray[i].join();
            }
            for (int i = 0; i < NUNMBER_JOBS; i++) {
                parallelResult = TreadArrray[i].getResult();
            }
            //System.out.println(parallelEncryptResult);
       // BigInteger res=Size(p, q);
      // System.out.println("sdfghj"+ res);


}
}
