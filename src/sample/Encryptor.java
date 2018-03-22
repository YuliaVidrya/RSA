package sample;

import org.apache.commons.collections4.ListUtils;
import sample.Controller;
import sample.ThreadEncryptor;
import java.math.BigInteger;
import java.util.*;
import javax.swing.JOptionPane;

public class Encryptor {

    public int NUNMBER_JOBS = 1;
    public  List<BigInteger> parallelEncryptResult =new ArrayList<BigInteger>();
    public StringBuffer parallelDecryptResult=new StringBuffer();

    //public  String mixedalphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRTSUVWXYZ1234567890.,?!; ";
   // public  String mixedalphabet = "ABCDEFGHIJKLMNOPQRTSUVWXYZ";
    public boolean CheckAlphabet(char[] input,char[] alphabet){

        boolean result=false;

        for (int i = 0; i < input.length; i++)
        {
            result = false;
            for (int j = 0; j < alphabet.length; j++)
            {
                if (input[i] == alphabet[j])
                {
                    result = true;

                }
            }
            if (!result)
            {
                JOptionPane.showMessageDialog(null, "Check the alphabet", "Error:Alphabet", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
        return result;
    }


    public  int[] GenerateRelation(char[] alphabet) {

       int[] genRelation=new int[alphabet.length];

       // Random rand = new Random();

        for (int i = 0; i < alphabet.length; i++) {
            genRelation[i]=i;
        }


     /*   for (int i = 0; i < charArray.length; i++) {

            genRelation[i] = rand.nextInt(charArray.length) + 1;;


            }
*/
        return genRelation;
    }



//без паралельности
    public StringBuffer  Encrypt(char[] charArray,int[] genRelation,char[] alphabet,BigInteger[] publicKey){

        BigInteger[] C=new BigInteger[charArray.length];
        StringBuffer  messageResult=new StringBuffer();
                BigInteger e=publicKey[0];//BigInteger.valueOf(7);//publicKey[0];
          BigInteger N=publicKey[1];//BigInteger.valueOf(33);//publicKey[1];

          BigInteger iterator=BigInteger.valueOf(0);

          BigInteger mPowE=BigInteger.ONE;
         // System.out.println(genRelation[0]);
         for (int i = 0; i < charArray.length; i++)
         {
             for (int j = 0; j < alphabet.length; j++)
             {//System.out.println(genRelation[j]);
                 if (charArray[i] == alphabet[j])
                 { //System.out.println(publicKey[0]);
                     while (iterator.compareTo(e)<0){
                         iterator=iterator.add(BigInteger.valueOf(1));
                         //System.out.println(iterator);
                        // System.out.println(j);
                         mPowE=mPowE.multiply(BigInteger.valueOf(genRelation[j]));
                         //System.out.println(mPowE);
                 }

                 iterator=BigInteger.ZERO;
                     // C[i]=BigInteger.valueOf(genRelation[j]).pow(e)
                     C[i]=mPowE.mod(N);
                    // System.out.print(mPowE+" "+C[i]);
                   //  System.out.println();
                     mPowE=BigInteger.ONE;
                // System.out.println(C[i]);
                     //System.out.println();



             }

         }
         }
         for (int i=0;i<C.length;i++){


          messageResult.append(C[i].toString(16));
          System.out.print(C[i]);
          System.out.println();
         }

   return messageResult;
 }


    public  List<BigInteger> EncryptPatralell(char[] charArray,int[] genRelation,char[] alphabet,BigInteger[] publicKey)  throws InterruptedException {

        ThreadEncryptor TreadArrray[] = new ThreadEncryptor[NUNMBER_JOBS];

        for (int i = 0; i < NUNMBER_JOBS; i++) {
            TreadArrray[i] = new ThreadEncryptor(
                    charArray.length / NUNMBER_JOBS * i,
                    i == NUNMBER_JOBS - 1 ? charArray.length : charArray.length / NUNMBER_JOBS * (i + 1),
                    genRelation.length / 2 * i,
                    i == NUNMBER_JOBS - 1 ? alphabet.length : alphabet.length / NUNMBER_JOBS * (i + 1),
                    charArray, genRelation, alphabet, publicKey);
            TreadArrray[i].start();
            // System.out.println("Syper");
        }

    for (int i = 0; i < NUNMBER_JOBS; i++) {
        TreadArrray[i].join();
    }
    for (int i = 0; i < NUNMBER_JOBS; i++) {

           parallelEncryptResult = ListUtils.union(parallelEncryptResult, TreadArrray[i].getResult());
       // System.out.println(TreadArrray[i].getResult());
           // System.out.println(parallelEncryptResult.get(i));
    }
        return parallelEncryptResult;

    }



    public String DecryptParallel(List<BigInteger> messageEncrypt,char[] alphabet,BigInteger[] privateKey) throws  InterruptedException
    { parallelDecryptResult.setLength(0);
        ThreadDecryptor TreadArrray[] = new ThreadDecryptor[NUNMBER_JOBS];

        for (int i = 0; i < NUNMBER_JOBS; i++) {
            TreadArrray[i] = new ThreadDecryptor(
                    messageEncrypt.size() / NUNMBER_JOBS * i,
                    i == NUNMBER_JOBS - 1 ? messageEncrypt.size(): messageEncrypt.size() / NUNMBER_JOBS * (i + 1),
                  messageEncrypt,alphabet, privateKey);
            TreadArrray[i].start();
        }

        for (int i = 0; i < NUNMBER_JOBS; i++) {
            TreadArrray[i].join();
        }
        for (int i = 0; i < NUNMBER_JOBS; i++) {
            parallelDecryptResult = parallelDecryptResult.append(TreadArrray[i].getResult());
            // System.out.println(TreadArrray[i].getResult());
          // System.out.println(parallelDecryptResult.toString());

        }
        return parallelDecryptResult.toString();


    }

    // без паралельности расшифровка
  /*public String Decrypt(BigInteger[] messageEncrypt,char[] alphabet,BigInteger[] privateKey)
  {
      StringBuffer messageResult=new StringBuffer();
      BigInteger d=privateKey[0];//BigInteger.valueOf(3);//
      BigInteger N=privateKey[1];//BigInteger.valueOf(33);//
      BigInteger pow=BigInteger.ONE;
      BigInteger[] M=new BigInteger[messageEncrypt.length];
      BigInteger iterator=BigInteger.valueOf(0);

      //BigInteger mPowD=BigInteger.ONE;
      // System.out.println(genRelation[0]);
      for (int i = 0; i < messageEncrypt.length; i++) {

          while (iterator.compareTo(d) < 0) {
              iterator = iterator.add(BigInteger.valueOf(1));
              //System.out.println(iterator);
              //System.out.println(d);
              pow = pow.multiply(messageEncrypt[i]);
              //System.out.println(messageEncrypt[i]);
             // System.out.println(messageEncrypt[i]);

          }
          iterator = BigInteger.ZERO;

          System.out.println(pow);
          // C[i]=BigInteger.valueOf(genRelation[j]).pow(e)
        //System.out.println(N);
          M[i] = pow.mod(N);
          pow=BigInteger.ONE;
          //System.out.println(M[i]);
      }
          for (int i=0;i<M.length;i++){
                int j=M[i].intValue();
                messageResult.append(alphabet[j]);
             //  System.out.println(M[i]);
          }
      return messageResult.toString();
  }*/

   /*public  void EncryptMessage (String input) throws InterruptedException {
        Controller controller=new Controller();
        GenerateKey generateKey=new GenerateKey();
        char[] charArray = input.toCharArray();
        char[] alphabet= mixedalphabet.toCharArray();
        GenerateKey Key=new GenerateKey();
        boolean checkAlphabet= CheckAlphabet(charArray,alphabet);
        if(checkAlphabet) {
            int[] genRelation = GenerateRelation(alphabet);

            BigInteger[] privateKey=Key.PrivateKey();
           controller.numberP.setText(privateKey[1].toString());
            BigInteger[] publicKey=Key.PublicKey();
           // System.out.println("e="+publicKey[0]);
           // System.out.println("d="+privateKey[0]);
          //  System.out.println("N="+privateKey[1]);
            //System.out.println(publicKey[0]);
           // BigInteger[] messageEncrypt=Encrypt(charArray,genRelation,alphabet,publicKey);
           // BigInteger[] messageEncrypt=Encrypt(charArray,genRelation,alphabet,publicKey);

            //String result=Decrypt(messageEncrypt,alphabet,privateKey);
          // System.out.println(result);
           List<BigInteger> messageEncrypt=EncryptPatralell(charArray,genRelation,alphabet,publicKey);
            for (int i=0;i<messageEncrypt.size();i++) {
            System.out.println("EncryptMessage=" + messageEncrypt.get(i));
            }
            //System.out.println(messageEncrypt.size());
            String ResultParralel=DecryptParallel(messageEncrypt,alphabet,privateKey);
            System.out.println(ResultParralel);
        }
    }*/
}

