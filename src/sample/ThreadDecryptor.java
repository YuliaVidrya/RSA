package sample;
import java.math.BigInteger;
import java.util.*;

public class ThreadDecryptor  extends Thread {

    int startIndex;
    int endIndex;
    char[] alphabet;
    BigInteger[] privateKey;
    List<BigInteger> messageEncrypt;

    ArrayList<BigInteger> result = new ArrayList<BigInteger>();
    StringBuffer messageResult = new StringBuffer();

    public ThreadDecryptor(int startIndex, int endIndex, List<BigInteger> messageEncrypt, char[] alphabet, BigInteger[] privateKey) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.alphabet = alphabet;
        this.privateKey = privateKey;
        //   this.result=new BigInteger[charArray.length];
        this.messageEncrypt = messageEncrypt;
    }

    public StringBuffer getResult() {
        return messageResult;
    }

    @Override
    public void run() {
        BigInteger d = BigInteger.valueOf(3);
        BigInteger N = BigInteger.valueOf(33);
        System.out.println("d="+d);
        System.out.println("N="+N);
        BigInteger pow = BigInteger.ONE;
        BigInteger[] M = new BigInteger[endIndex];
        BigInteger iterator = BigInteger.valueOf(0);

        //BigInteger mPowD=BigInteger.ONE;
        // System.out.println(genRelation[0]);
        for (int i = startIndex; i < endIndex; i++) {
            while (iterator.compareTo(d) < 0) {
                iterator = iterator.add(BigInteger.valueOf(1));
                //System.out.println(iterator);
                //System.out.println(d);
                pow = pow.multiply(messageEncrypt.get(i));
              //  System.out.println(messageEncrypt.get(i));
                // System.out.println(messageEncrypt[i]);
            }
            iterator = BigInteger.ZERO;
            System.out.println(pow);
            // C[i]=BigInteger.valueOf(genRelation[j]).pow(e)
            //System.out.println(N);
            M[i] = pow.mod(N);
            pow = BigInteger.ONE;
            System.out.println("M[i]"+M[i]);
        }

        for (int i = 0; i < M.length; i++) {
            int j = M[i].intValue();
            messageResult.append(alphabet[j]);
           //System.out.println(alphabet[j]);
        }

    }
}