package sample;

//import com.sun.deploy.config.Platform;
import java.awt.*;
import  java.io.*;
import java.io.OutputStreamWriter;

import com.sun.org.apache.regexp.internal.RE;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import  javafx.scene.control.TextArea;
import  javafx.scene.control.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import javafx.application.Platform;
import  javafx.event.EventHandler;
import  javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javax.swing.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    @FXML
    TextField numberP;
    @FXML
    TextField numberQ;
    @FXML
    TextField modulusN;
    @FXML
    TextField openExponent;
    @FXML
    TextField closedExponent;
    @FXML
    MenuItem openFile;
    @FXML
    MenuItem saveFile;
    @FXML
    TextArea textMessage;
    @FXML
    TextArea textMessage11;
    @FXML
    RadioMenuItem rus;
    @FXML
    RadioMenuItem eng;
    @FXML
    RadioMenuItem mix;

    @FXML
    private void initialize() {
//        asd.setSelected(true);

      //  System.out.println("asdfgh");
      //  openFile.setOnAction(handler);
    }

    @FXML
    public void onOpenFile(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage =new Stage();
        stage.setTitle("File Chooser Sample");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFile(file);
        }

        // Platform.exit();
    }

    private Desktop desktop = Desktop.getDesktop();
    private void openFile(File file) {
            try{

                int c;
                StringBuffer st=new StringBuffer();
                FileReader fileReader =new FileReader(file);
                while((c=fileReader.read())!=-1){
                           st.append((char)c);
                    // System.out.print((char)c);
                }
                textMessage.setText(st.toString());
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }

    }
    @FXML
    public  void onSaveFile(){
        JFileChooser fc = new JFileChooser();
        String path="";
        String str[]=textMessage.getText().split("\n");
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
           for( String retval: fc.getSelectedFile().getPath().split(".txt")){
               path=retval;
               System.out.println(path);
           }
           File f=new File(path+".txt");
           try {
               if (!f.exists()) {
                   f.createNewFile();
                       try {
                           BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                                   new FileOutputStream(f), "UTF8"));
                           for(int i=0; i<str.length;i++) {
                               bw.write(str[i] + "\r\n");
                           }
                           bw.flush();

                       } catch (UnsupportedEncodingException exs) {
                           Logger.getLogger(Main.class.getName())
                                   .log(Level.SEVERE, null, exs);
                       } catch (IOException ex) {
                           Logger.getLogger(Main.class.getName())
                                   .log(Level.SEVERE, null, ex);
                       }

                   }

               else {

                   BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(f), "UTF-8"));
                String line;
                line = br.readLine();
               // if (!line.isEmpty()) {
                   for(int i=0; i<str.length;i++) {
                       update(f, str[i] + "\r\n");
                   }

              // }
           }
           }
           catch (FileNotFoundException exs) {
               Logger.getLogger(Main.class.getName())
                       .log(Level.SEVERE, null, exs);
           }
           catch (IOException ex){
           }
                /*try {
                    FileOutputStream fileStream = new FileOutputStream(fc.getSelectedFile());
                    ObjectOutputStream os = new ObjectOutputStream(fileStream);
                    String st = "qwertyuio";
                    st.getBytes("UTF-8");
                    os.writeObject(st);
                } catch (Exception e) {
                    System.out.println("Что-то пошло не так...");
                }*/
            }
    }

    public static void update(File nameFile, String newText) throws FileNotFoundException {
        exists(nameFile);
        StringBuilder sb = new StringBuilder();
        String oldFile = read(nameFile);
        /*if(!oldFile.isEmpty()){
        oldFile=oldFile;
        System.out.println(oldFile);
        }*/
        sb.append(oldFile);
        sb.append(newText);
        write(nameFile, sb.toString());
    }
    private static void exists(File file) throws FileNotFoundException {

        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }

    public static void write(File file, String text) {
        //Определяем файл

        try {
            //проверяем, что если файл не существует то создаем его
            if(!file.exists()){
                file.createNewFile();
            }

            //PrintWriter обеспечит возможности записи в файл
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                //Записываем текст у файл
                out.print(text);
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String read(File fileName) throws FileNotFoundException {
        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();

        exists(fileName);

        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader( fileName.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\r\n");
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        //Возвращаем полученный текст с файла
        return sb.toString();
    }

    public String engalphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRTSUVWXYZ1234567890.,!?; ";
    public String rusalphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ1234567890.,?!; ";
    public  String mixedalphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRTSUVWXYZ1234567890.,?!; ";
    private char[] alphabet;
    private Encryptor  controller=new Encryptor();
    private  GenerateKey Key=new GenerateKey();
    private BigInteger[] publicKey;
    private  BigInteger[] privateKey;
    private java.util.List<BigInteger> messageEncrypt;
    private StringBuffer messageEncryptWihout;

    public  void  onEncrypt() throws  InterruptedException{
        long timestart=System.currentTimeMillis() ;
         String input=textMessage.getText();
         char[] charArray = input.toCharArray();
        boolean checkAlphabet= controller.CheckAlphabet(charArray,alphabet);
        if(checkAlphabet) {
            int[] genRelation = controller.GenerateRelation(alphabet);
            privateKey=Key.PrivateKey();
            numberP.setText(Key.p.toString());
            numberQ.setText(Key.q.toString());
            publicKey=Key.PublicKey();
            modulusN.setText(privateKey[1].toString());
            openExponent.setText(publicKey[0].toString());
            closedExponent.setText(publicKey[0].toString());
            textMessage.clear();
            messageEncrypt=controller.EncryptPatralell(charArray,genRelation,alphabet,publicKey);
            textMessage.setText(messageEncrypt.toString());
            long timeend=System.currentTimeMillis() ;
            System.out.println("r= "+ (timeend-timestart));
        }
}

    public  void  onEncryptOne() throws  InterruptedException{
        long timestart=System.currentTimeMillis() ;
        String input=textMessage.getText();
        char[] charArray = input.toCharArray();
        boolean checkAlphabet= controller.CheckAlphabet(charArray,alphabet);
        if(checkAlphabet) {
            int[] genRelation = controller.GenerateRelation(alphabet);
            privateKey=Key.PrivateKey();
            numberP.setText(Key.p.toString());
            numberQ.setText(Key.q.toString());
            publicKey=Key.PublicKey();
            modulusN.setText(privateKey[1].toString());
            openExponent.setText(publicKey[0].toString());
            closedExponent.setText(publicKey[0].toString());
            textMessage.clear();
            messageEncryptWihout=controller.Encrypt(charArray,genRelation,alphabet,publicKey);
            textMessage11.setText(messageEncryptWihout.toString());
            long timeend=System.currentTimeMillis() ;
            System.out.println("r= "+ (timeend-timestart));

        }
    }

        public void onDecrypt() throws  InterruptedException{

          String   ResultParralel = controller.DecryptParallel(messageEncrypt, alphabet, privateKey);
            messageEncrypt.clear();
            textMessage.setText(ResultParralel);
    }

    public  char[] onSetRussian(){
        rus.setSelected(true);
        eng.setSelected(false);
        mix.setSelected(false);
    return alphabet=rusalphabet.toCharArray();
    }

    public char[] onSetEnglish(){
        eng.setSelected(true);
        rus.setSelected(false);
        mix.setSelected(false);
 return alphabet=engalphabet.toCharArray();
    }

    public  char[] onSetMixed(){
        mix.setSelected(true);
        rus.setSelected(false);
        eng.setSelected(false);
     return alphabet=mixedalphabet.toCharArray();
    }


}

