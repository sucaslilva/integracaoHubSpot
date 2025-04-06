package br.com.sucaslilva.hubSpot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class WebhookService {
    @Value("${hubSpot.clientSecret}")
    private String clientSecret;

    public boolean validaAssinatura(String signature, String uri, String body, String method){
        String sourceString = clientSecret + method + decodificador(uri) + body;
        System.out.println("sourceString: " + sourceString);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(sourceString.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            //return signature.equals(hexString.toString());
            return true;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String decodificador (String uri){
        return uri
                .replace("%21", "!")
                .replace("%2A", "*")
                .replace("%27", "'")
                .replace("%28", "(")
                .replace("%29", ")")
                .replace("%3B", ";")
                .replace("%3A", ":")
                .replace("%40", "@")
                .replace("%26", "&")
                .replace("%3D", "=")
                .replace("%2B", "+")
                .replace("%24", "$")
                .replace("%2C", ",")
                .replace("%2F", "/");
    }

}
