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
public abstract class Packet {
    public int command;
    public int opcode;
    public static int lb = 512;
    public byte[] buffer;
    public String checksum;
    public static int MAX_LENGTH=2056; 
    public static int MIN_LENGTH=8; 

    public Packet(int command, int opcode, int lb, byte[] buffer, String checksum) {
        this.command = command;
        this.opcode = opcode;
        this.lb = lb;
        this.buffer = buffer;
        this.checksum = checksum;
    }

    
    
    public byte[] getBuffer() {
        return buffer;
    }

    public int getOpcode() {
        return opcode;
    }

    public String getChecksum() {
        return checksum;
    }

    public int getCommand() {
        return command;
    }

    public int getLb() {
        return lb;
    }

    
    
    
    
    
    
    
    
}
