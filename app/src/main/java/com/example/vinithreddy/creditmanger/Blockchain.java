package com.example.vinithreddy.creditmanger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
class Block
{
    int index;
    String from;
    String to;
    int amount;
    String hash;
    String prevhash;
    Block(int index,String from,String to,int amount)
    {
        this.index=index;
        this.from=from;
        this.to=to;
        this.amount=amount;
        this.hash=this.hash(this);
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
public class Blockchain
{
    Blockchain()
    {

    }
}
