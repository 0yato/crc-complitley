package com.example.crccct;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Random;

public class controller {
    @FXML
    private TextArea message;
    @FXML
    private Text messagetype;
    @FXML
    private Button send;
    @FXML
    private Button file;
    @FXML
    private TextField message2;
    @FXML
    private TextField errorDedect;
    @FXML
    private TextField pattern;
     @FXML
     private TextArea bitsSent;
    @FXML
    private TextArea crcsecnt;
    @FXML
    private TextArea recevedMessage;
    @FXML
    private TextField errorRate;
    @FXML
    private TextField errorPercentage;
    @FXML
    private TextField errormsg;
    @FXML
    private TextArea correctedMessageBit;
    @FXML
    private TextField correctedMessage;
    @FXML
    private TextField fcs;
    @FXML
    private TextField reminder;


public int arraya;
public  boolean filee=false;



    static int exorOperation(int x, int y) {

        if(x == y) {
            return 0;
        }
        return 1;
    }
    static int[] divideDataWithDivisor(int oldData[], int divisor[]) {
        int rem[] = new int[divisor.length];
        int i;
        int data[] = new int[oldData.length + divisor.length];
        System.arraycopy(oldData, 0, data, 0, oldData.length);
        System.arraycopy(data, 0, rem, 0, divisor.length);
        for(i = 0; i < oldData.length; i++) {


            if(rem[0] == 1) {

                for(int j = 1; j < divisor.length; j++) {
                    rem[j-1] = exorOperation(rem[j], divisor[j]);

                }
            }
            else {

                for(int j = 1; j < divisor.length; j++) {
                    rem[j-1] = exorOperation(rem[j], 0);

                }
            }

            rem[divisor.length-1] = data[i+divisor.length];

        }
        return rem;
    }

    /*to convert to binary*/
    public static String stringToBinary(String input) {
        StringBuilder binaryStringBuilder = new StringBuilder();

        for (char character : input.toCharArray()) {
            // Convert each character to binary representation and append to the StringBuilder
            String binaryValue = String.format("%8s", Integer.toBinaryString(character)).replace(' ', '0');
            binaryStringBuilder.append(binaryValue);
        }

        return binaryStringBuilder.toString();
    }
    public static String binaryToString(String binaryString) {
        StringBuilder message = new StringBuilder();
        int length = binaryString.length();

        if (length % 8 != 0) {

            return "";
        }

        for (int i = 0; i < length; i += 8) {
            String binaryChar = binaryString.substring(i, i + 8);
            int charCode = Integer.parseInt(binaryChar, 2);
            char character = (char) charCode;
            message.append(character);
        }

        return message.toString();
    }

////////////////////////////////////////////////////////////////


public void noErrors(ActionEvent e){
        if(pattern.getText().equals("")||message.getText().equals("")){

            JOptionPane.showMessageDialog(null, "You should fill both the pattern and the message.", "Error", JOptionPane.ERROR_MESSAGE);

        }
        else{
            String sentMessageInBinary;
            if(filee){
                sentMessageInBinary = message.getText();

            }
            else {
                sentMessageInBinary = stringToBinary(message.getText());
            }
            bitsSent.setText(sentMessageInBinary);
            String sentMessage=binaryToString(sentMessageInBinary);
            message2.setText(sentMessage);
            recevedMessage.setText(sentMessageInBinary);
            errorRate.setText("0/"+ sentMessageInBinary.length());
            errorDedect.setText("no Errors dedected !");
            errorPercentage.setText((0/sentMessageInBinary.length())+"%");
            errormsg.setText("no errors occurs");
            correctedMessageBit.setText(sentMessageInBinary);
            correctedMessage.setText(binaryToString(sentMessageInBinary));
            int size = bitsSent.getText().length();

            int dataarray[]=new int[size];
             for (int i=0;i<size;i++){
                 if(sentMessageInBinary.charAt(i)=='1'){
                  dataarray[i]=1;

                 }
                 else {
                     dataarray[i]=0;

                 }
             }

             int size2=pattern.getText().length();
             String s=pattern.getText();
             int divarray[]=new int[size2];
             for (int i=0;i<size2;i++){
                 if(s.charAt(i)=='1'){
                     divarray[i]=1;

                 }
                 else {
                     divarray[i]=0;

                 }
             }
             String remend="";
            int rem[] = divideDataWithDivisor(dataarray, divarray);

            for(int i = 0; i < rem.length-1; i++) {

                remend+=rem[i]+"";
            }
            fcs.setText(remend);
            String Fullsmessage=sentMessageInBinary+remend;
            crcsecnt.setText(Fullsmessage);
            recevedMessage.setText(crcsecnt.getText());
              int size3=Fullsmessage.length();
            int []full=new int[size+size2];
            for (int i=0;i<size3;i++){
                if(Fullsmessage.charAt(i)=='1'){
                    full[i]=1;

                }
                else {
                    full[i]=0;

                }
            }

            String rrem="";
            int[] remm = divideDataWithDivisor(full, divarray);

            for (int i=0;i<remm.length;i++){
                rrem+=remm[i];
            }
            reminder.setText(rrem);

        }




}

public void withError(ActionEvent e){
if(message.getText().equals("")||pattern.getText().equals("")){
    JOptionPane.showMessageDialog(null, "You should fill both the pattern and the message.", "Error", JOptionPane.ERROR_MESSAGE);

}
    else{
    String sentMessageInBinary;
    if(filee){
        sentMessageInBinary = message.getText();

    }
    else {
        sentMessageInBinary = stringToBinary(message.getText());
    }
        String msg=message.getText();
        String patt=pattern.getText();
        message2.setText(msg);

    correctedMessageBit.setText(sentMessageInBinary);
    correctedMessage.setText(binaryToString(sentMessageInBinary));
    bitsSent.setText(sentMessageInBinary);
    double ee=((double)1/sentMessageInBinary.length())*100;
    errorPercentage.setText(ee+"%");
    errorRate.setText("1/"+sentMessageInBinary.length());

    int size = bitsSent.getText().length();

    int dataarray[]=new int[size];
    for (int i=0;i<size;i++){
        if(sentMessageInBinary.charAt(i)=='1'){
            dataarray[i]=1;

        }
        else {
            dataarray[i]=0;

        }
    }

    int size2=pattern.getText().length();
    String s=pattern.getText();
    int divarray[]=new int[size2];
    for (int i=0;i<size2;i++){
        if(s.charAt(i)=='1'){
            divarray[i]=1;

        }
        else {
            divarray[i]=0;

        }
    }
    String remend="";
    int rem[] = divideDataWithDivisor(dataarray, divarray);

    for(int i = 0; i < rem.length-1; i++) {

        remend+=rem[i]+"";
    }
    fcs.setText(remend);
    String Fullsmessage=sentMessageInBinary+remend;
    crcsecnt.setText(Fullsmessage);
    recevedMessage.setText(crcsecnt.getText());
    int size3=Fullsmessage.length();
    int []full=new int[size+size2];
    for (int i=0;i<size3;i++){
        if(Fullsmessage.charAt(i)=='1'){
            full[i]=1;

        }
        else {
            full[i]=0;

        }
    }

    Random random = new Random();


    int randomNumber = random.nextInt(sentMessageInBinary.length());

    errorDedect.setText("Error at bit "+(randomNumber+1));
    StringBuilder stringBuilder = new StringBuilder(sentMessageInBinary);


    char currentChar = sentMessageInBinary.charAt(randomNumber);
    char flippedChar = (currentChar == '0') ? '1' : '0';
    stringBuilder.setCharAt(randomNumber, flippedChar);


    sentMessageInBinary = stringBuilder.toString();
    errormsg.setText(binaryToString( sentMessageInBinary));
    for(int i=0;i<fcs.getText().length();i++){
        sentMessageInBinary+=fcs.getText().charAt(i);
    }
    recevedMessage.setText(sentMessageInBinary);



    int []earray=new int[recevedMessage.getText().length()];
    StringBuilder rm=new  StringBuilder(recevedMessage.getText());

    for (int i=0;i<recevedMessage.getText().length();i++){
     if(rm.charAt(i)=='1'){
         earray[i]=1;

     }
     else{
         earray[i]=0;

     }
    }
    String rrem="";
    int[] remm = divideDataWithDivisor(earray, divarray);

    for (int i=0;i<remm.length;i++){
        rrem+=remm[i];
    }
    reminder.setText(rrem);

    }
}
public void openfile(ActionEvent e){
        filee=true;
    FileChooser fileChooser = new FileChooser();
    File sf=fileChooser.showOpenDialog(null);

     // replace with your file path
    try {
        byte[] fileContent = Files.readAllBytes(sf.toPath());
        StringBuilder binaryRepresentation = new StringBuilder();

        for (byte b : fileContent) {
            // Convert the byte to a binary string
            String binaryString = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            binaryRepresentation.append(binaryString);
            binaryRepresentation.append(" "); // Space for separating binary values of bytes
        }
        String ss=binaryRepresentation.toString();
        message.setText(ss);

    } catch (IOException ee) {
        ee.printStackTrace();
    }

}
public  void burstError(ActionEvent e){
    {
        if(message.getText().equals("")||pattern.getText().equals("")){
            JOptionPane.showMessageDialog(null, "You should fill both the pattern and the message.", "Error", JOptionPane.ERROR_MESSAGE);

        }
        else{
            String sentMessageInBinary;
            if(filee){
                sentMessageInBinary = message.getText();

            }
            else {
                sentMessageInBinary = stringToBinary(message.getText());
            }
            String msg=message.getText();
            String patt=pattern.getText();
            message2.setText(msg);

            correctedMessageBit.setText(sentMessageInBinary);
            correctedMessage.setText(binaryToString(sentMessageInBinary));
            bitsSent.setText(sentMessageInBinary);


            int size = bitsSent.getText().length();

            int dataarray[]=new int[size];
            for (int i=0;i<size;i++){
                if(sentMessageInBinary.charAt(i)=='1'){
                    dataarray[i]=1;

                }
                else {
                    dataarray[i]=0;

                }
            }

            int size2=pattern.getText().length();
            String s=pattern.getText();
            int divarray[]=new int[size2];
            for (int i=0;i<size2;i++){
                if(s.charAt(i)=='1'){
                    divarray[i]=1;

                }
                else {
                    divarray[i]=0;

                }
            }
            String remend="";
            int rem[] = divideDataWithDivisor(dataarray, divarray);

            for(int i = 0; i < rem.length-1; i++) {

                remend+=rem[i]+"";
            }
            fcs.setText(remend);
            String Fullsmessage=sentMessageInBinary+remend;
            crcsecnt.setText(Fullsmessage);
            recevedMessage.setText(crcsecnt.getText());
            int size3=Fullsmessage.length();
            int []full=new int[size+size2];
            for (int i=0;i<size3;i++){
                if(Fullsmessage.charAt(i)=='1'){
                    full[i]=1;

                }
                else {
                    full[i]=0;

                }
            }

            Random random = new Random();
            int randrand=random.nextInt(Integer.parseInt(String.valueOf(sentMessageInBinary.length()/10)));
            double ee=((double)randrand/sentMessageInBinary.length())*100;
            errorPercentage.setText(ee+"%");
            errorRate.setText(randrand+"/"+sentMessageInBinary.length());
            String Errordedection;
            if(randrand==0){
               Errordedection="No Errors !";
            }
            else {
                Errordedection = "Error at bits :";
            }
            StringBuilder stringBuilder = new StringBuilder(sentMessageInBinary);
            for(int i=0;i<randrand;i++) {
                int randomNumber = random.nextInt(sentMessageInBinary.length());
                char currentChar = sentMessageInBinary.charAt(randomNumber);
                char flippedChar = (currentChar == '0') ? '1' : '0';
                stringBuilder.setCharAt(randomNumber, flippedChar);
                if (filee) {
                    Errordedection+=(randomNumber+1+",");
                }
            }
            sentMessageInBinary = stringBuilder.toString();
            errormsg.setText(binaryToString( sentMessageInBinary));
            for(int i=0;i<fcs.getText().length();i++){
                sentMessageInBinary+=fcs.getText().charAt(i);
            }
            recevedMessage.setText(sentMessageInBinary);
            if(!filee) {
                for (int i = 0; i < crcsecnt.getText().length(); i++) {
                    System.out.print(Integer.parseInt(String.valueOf(crcsecnt.getText().charAt(i))));
                    System.out.print(Integer.parseInt(String.valueOf(sentMessageInBinary.charAt(i))));

                    int check = exorOperation(Integer.parseInt(String.valueOf(crcsecnt.getText().charAt(i))), Integer.parseInt(String.valueOf(sentMessageInBinary.charAt(i))));
                    if (check == 1) {
                        Errordedection += (i + 1 + ",");

                    }

                }
            }

            errorDedect.setText(Errordedection);











            int []earray=new int[recevedMessage.getText().length()];
            StringBuilder rm=new  StringBuilder(recevedMessage.getText());

            for (int i=0;i<recevedMessage.getText().length();i++){
                if(rm.charAt(i)=='1'){
                    earray[i]=1;

                }
                else{
                    earray[i]=0;

                }
            }
            String rrem="";
            int[] remm = divideDataWithDivisor(earray, divarray);

            for (int i=0;i<remm.length;i++){
                rrem+=remm[i];
            }
            reminder.setText(rrem);

        }
    }
}
    public  void file(ActionEvent e){
        filee=true;
        messagetype.setText("You will send: File");
    }
    public  void message(ActionEvent e){
    filee=false;
    messagetype.setText("You will send: Message");
    }

}
