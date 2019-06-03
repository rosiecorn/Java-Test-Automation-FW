package security;

import java.util.Base64;

public class Bases64 {

    private Bases64()
    {}

    public static String decode (String encodedText)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedText);
        return new String(decodedBytes);

    }

    public static String endcode (String plainText)
    {
        return Base64.getEncoder().encodeToString(plainText.getBytes());
    }

}
