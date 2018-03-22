package sample;
import java.math.BigInteger;
import java.util.*;

/**
 *
 */

public class ThreadEncryptor  extends Thread{

    int  startIndex;
    int  endIndex;
    int  startIndexAlphabet;
    int  endIndexAlphabet;
    char[]  charArray;
    int[] genRelation;
    char[] alphabet;
    BigInteger[] publicKey;

    ArrayList <BigInteger> result=new ArrayList<BigInteger>();
    StringBuffer messageResult=new StringBuffer();

    public ThreadEncryptor(int startIndex, int endIndex,int startIndexAlphabet,int endIndexAlphabet,char[] charArray, int[] genRelation,char[] alphabet,BigInteger[] publicKey) {

        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.startIndexAlphabet=startIndexAlphabet;
        this.endIndexAlphabet=endIndexAlphabet;
        this.charArray=charArray;
        this.genRelation=genRelation;
        this.alphabet=alphabet;
        this.publicKey=publicKey;
    //   this.result=new BigInteger[charArray.length];
    }

    public ArrayList<BigInteger> getResult() {
        return result;
    }


    @Override
    public void run(){


        BigInteger e=BigInteger.valueOf(7);//publicKey[0];
        System.out.println(e);
        BigInteger N=BigInteger.valueOf(33);//publicKey[1];
        System.out.println("N1="+N);
        BigInteger iterator=BigInteger.valueOf(0);
        BigInteger mPowE=BigInteger.ONE;

        /*System.out.println(startIndex);
        System.out.println(endIndex);
        System.out.println(startIndexAlphabet);
        System.out.println(endIndexAlphabet);*/
        // System.out.println(genRelation[0]);
        for (int i = startIndex; i < endIndex; i++)
        {
            for (int j = 0; j < alphabet.length; j++)
            {//System.out.println(genRelation[j]);
                if (charArray[i] == alphabet[j])

                { //System.out.println("alphabetj[i]"+" "+ alphabet[j]+j);

                    while (iterator.compareTo(e)<0){
                        iterator=iterator.add(BigInteger.valueOf(1));
                        //System.out.println(iterator);
                        // System.out.println(j);
                        mPowE=mPowE.multiply(BigInteger.valueOf(genRelation[j]));
                        //System.out.println(mPowE);
                    }
                     iterator=BigInteger.ZERO;
                   // System.out.println(N);
                    // C[i]=BigInteger.valueOf(g
                    // enRelation[j]).pow(e)

                    result.add(mPowE.mod(N));
                  //  result.size();


                    System.out.println("mPowE "+mPowE);
                    //messageResult.append(result[i].toString());

                    System.out.println("*************");
                    mPowE=BigInteger.ONE;
                    // System.out.println(C[i]);
                    //System.out.println();
                }

            }
    }
}
}
