/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jsonclientranfagni;

import java.util.Base64;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import java.io.*;
import java.net.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 *
 * @author Mattia.Ranfagni
 */
public class JSONClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket= new Socket("localhost",6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("JPG images (*.jpg)","jpg"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG images (*.png)","png"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("JPEG images (*.jpeg)","jpeg"));
        System.out.println("Seleziona un file.");
        while(fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
        {
            
        }
        String filepath = fileChooser.getSelectedFile().getAbsolutePath();
        File miofile = new File(filepath);
        String code=Base64.getEncoder().encodeToString((FileUtils.readFileToByteArray(miofile)));
        System.out.println(code);
        JSONObject obj= new JSONObject();
        System.out.println("Inserisci un username: ");
        String username=inFromUser.readLine();
        obj.put("name", username);
        obj.put("image", code);
        
        String jsonstring=obj.toJSONString();
        System.out.println(jsonstring);
        System.out.println("Invio in corso...");
        outToServer.writeBytes(jsonstring + "\n");
        System.out.println("Invio completato.");
        String servresponse= inFromServer.readLine();
        System.out.println("Conferma della ricezione da parte del server. \n"+servresponse);
        clientSocket.close();
        
    }
        
   
}
