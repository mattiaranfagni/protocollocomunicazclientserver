/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientservercomunicazranfagni;

/**
 *
 * @author studente
 */
public class Pacchetto extends Packet{

    public Pacchetto(int command, int opcode, int length_buffer, byte[] buffer, String checksum) {
        super(command, opcode, length_buffer, buffer, checksum);
    }
    
}
