package sample;
import java.math.BigInteger;
import java.util.Random;

public class RSA {
    private BigInteger p, q, n, phi, e, d;

    RSA() {
        Random random = new Random();
        p = new BigInteger(16, 100,random);
        q = new BigInteger(16,100,random);
        while(p.equals(q)) {
            q = new BigInteger(16,100,random);
        }

        n = p.multiply(q);
        phi = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));

        e = new BigInteger("2");
        while(! e.gcd(phi).equals(new BigInteger("1"))) {
           e = e.add(new BigInteger("1"));
        }

        d = e.modInverse(phi);
    }

    public String getInfoString() {
        return
            "Prime p = " + p + "\n"
            + "Prime q = " + q + "\n"
            + "N = p * q = " + n + "\n"
            + "phi(N) = phi(" + n + ") = " + phi + "\n"
            + "e = " + e + "\n"
            + "d = " + d;
    }

    private BigInteger encryptNum(BigInteger m) {
        return m.modPow(e, n);
    }

    private BigInteger decryptNum(BigInteger c) {
        return c.modPow(d, n);
    }

    public String encrypt(String plain) {
        String cipher = "";
        for(int i = 0; i < plain.length(); i++) {
            cipher += encryptNum(new BigInteger(""+(int)plain.charAt(i))) + " ";
        }
        return cipher;
    }

    public String decrypt(String cipher) {
        String plain = "";
        String[] ciphers = cipher.split(" ");
        for(String c : ciphers) {
            plain += (char) decryptNum(new BigInteger(c)).intValue();
        }
        return plain;
    }
}
