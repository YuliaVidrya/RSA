package sample;
import java.math.BigInteger;
//import java.security.PublicKey;
import java.util.Objects;
import java.util.Random;
/**
 *
 */
public class GenerateKey {

    public BigInteger euler;
    PQGenerator PTest=new PQGenerator();
    public BigInteger p=PTest.generateP();
    public BigInteger q= PTest.generateQ();
    public BigInteger N=ModuleN();
    public BigInteger exponent;
    public  BigInteger d;

    public BigInteger ModuleN(){
        System.out.println(p+" "+q);
        return N=p.multiply(q);
    }

    public BigInteger Euler(){

        BigInteger fiN;
        return  fiN=(p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
    }


    public void PublicExponent(){

        Random rnd = new Random();
        exponent=BigInteger.valueOf(rnd.nextInt(5)+3);
        euler=Euler();
        if(exponent.compareTo(BigInteger.ONE)>0 && exponent.compareTo(euler)<0)
        {
            if (Mutually(euler,exponent)) {
               System.out.println("Exponent=" + exponent);
            }
            else {
                int t = 0;
                    PublicExponent();
            }
        }
    }



    public boolean Mutually(BigInteger a,BigInteger b){
        a = a.abs();
        b = b.abs();
        while (!Objects.equals(a, b)) {
            if (a.compareTo(b)>0) {
                a = a.subtract(b);
            } else {
                b = b.subtract(a);
            }
        }
        return a.equals(BigInteger.ONE);
    }





    public BigInteger  MutuallyPrimeNumber(BigInteger Ns,BigInteger exponent){

        BigInteger c;

        while (exponent.compareTo(BigInteger.ZERO) > 0) {
            c = Ns.remainder(exponent);
            Ns = exponent;
            exponent = c;
        }

        return Ns.abs();
    }

    public  BigInteger PrivateExponent(){
        PublicExponent();
        BigInteger exponentForPrivate=exponent;
        BigInteger b = euler, x = BigInteger.ZERO, d = BigInteger.ONE;
        System.out.println(euler);
        System.out.println(exponentForPrivate);
        while (exponentForPrivate.compareTo(BigInteger.ZERO)==1)
        {
            BigInteger q = b.divide(exponentForPrivate);
            BigInteger y = exponentForPrivate;
            exponentForPrivate = b.mod(exponentForPrivate);
            b = y;
            y = d;
            d = x.subtract(q.multiply(d));
            x = y;
        }
        x = x.mod(euler);
        if (x.compareTo(BigInteger.ZERO) == -1)//x<0
        {
            x = (x.add(euler)).mod(euler);
        }
        return x;

    }

     public  BigInteger[] PublicKey(){
        BigInteger[] PublicKey= {exponent,N};
        return PublicKey;
     }

    public  BigInteger[] PrivateKey(){
        d=PrivateExponent();
        BigInteger[] PrivateKey= {d,N};
        return  PrivateKey;
     }
}
