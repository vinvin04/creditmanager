package com.example.vinithreddy.creditmanger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

class Block implements java.io.Serializable
{
    int index;
    String from;
    String to;
    int amount;
    String hash;
    String phash;
    Block(int index,String from,String to,int amount,String prevhash)
    {
        this.index=index;
        this.from=from;
        this.to=to;
        this.amount=amount;
        this.hash=this.hash(this);
        phash=prevhash;
    }
    @Override
    public String toString()
    {
        return this.index+"\n"+this.from+"\n"+this.to+"\n"+this.amount+"\n"+this.phash+"\n"+this.hash;
    }
    public String hash(Block b)
    {
        String s=this.index+this.from+this.to+this.amount;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(s.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
public class Blockchain implements java.io.Serializable
{
    String prevhash;
    ArrayList<Block> chain;
    Blockchain()
    {
        //creating genesis block
        chain=new ArrayList<>();
        chain.add(new Block(0,"genesis","genesis",0,"0"));
        prevhash=chain.get(0).hash;
    }
    void addBlock(String from,String to,int amount)
    {
        chain.add(new Block(chain.size(),from,to,amount,prevhash));
    }
}
