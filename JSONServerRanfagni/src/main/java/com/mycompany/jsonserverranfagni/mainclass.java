package com.mycompany.jsonserverranfagni;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;
import java.util.Base64;
import javax.swing.JFileChooser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Mattia.Ranfagni
 */
public class mainclass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException{
        String jsonstring;
        ServerSocket welcomeSocket = new ServerSocket(6789); //same of tcpclient
        System.out.println("Socket di accesso aperta.");
        byte[] codeimg;
        String imgcode;
        File immagine;
        System.out.println("Scegli la cartella locale del server: ");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        while(fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
        {
            
        }
        String filepath = fileChooser.getSelectedFile().getAbsolutePath();
        System.out.println("Hai scelto la cartella con indirizzo "+filepath);
        while(true) {
            Socket connectionSocket= welcomeSocket.accept();
            connectionSocket.setSoTimeout(2000);
            BufferedReader inFromClient= new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient= new DataOutputStream(connectionSocket.getOutputStream());
            try {
                jsonstring = inFromClient.readLine();
                System.out.println("Connessione con il client effettuata, è stata ricevuta la seguente stringa: \n"+jsonstring);
                JSONParser parser = new JSONParser();
                JSONObject obj= (JSONObject) parser.parse(jsonstring);
                System.out.println("Username inserito dal client: "+obj.get("name"));
                System.out.println("File codificato scelto dal client : "+obj.get("image"));
                imgcode=(String) obj.get("image");
                codeimg = Base64.getDecoder().decode(imgcode);
                FileOutputStream imageOutFile = new FileOutputStream(filepath+"\\"+obj.get("name")+".jpg");
                imageOutFile.write(codeimg);
                imageOutFile.close();
                System.out.println("Il file scelto dal client è stato salvato sulla cartella locale del Server rinominato con l'username specificato.");
                outToClient.writeBytes(jsonstring+"\n");
                System.out.println("Conferma di ricezione inoltrata al client.");
            }
            catch (SocketTimeoutException ste) {
                System.out.println("TIMEOUT");
            }
            
        }
    }
    
}