package bg.fmi.cms.keys;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CipherUtils {
    public static String getClearPan(String encryptedPan, String key) {
        SecretKeySpec tDesKey = stringToDes3(key);
        byte[] encPanBytes = DatatypeConverter.parseHexBinary(encryptedPan);
        byte[] iv = Arrays.copyOfRange(encPanBytes, 0, 8);
        byte[] panBytes = Arrays.copyOfRange(encPanBytes, 8, encPanBytes.length);
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DESede/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, tDesKey, new IvParameterSpec(iv));
            panBytes = cipher.doFinal(panBytes);
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        return new String(panBytes, StandardCharsets.UTF_8);
    }

    public static String encryptPan(String clearPan, String key) {
        byte[] iv = new byte[]{0, 0, 0, 0, 0, 0, 0, 0};
        byte[] encPanBytes;
        SecretKeySpec tDesKey = stringToDes3(key);
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DESede/CBC/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, tDesKey, new IvParameterSpec(iv));
            encPanBytes = cipher.doFinal(clearPan.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

        return DatatypeConverter.printHexBinary(iv) + DatatypeConverter.printHexBinary(encPanBytes);

    }

    public static boolean checkPin(String cardPin, String authSystemPin, String authSystemKey, String cardKey) {
        return clearPin(cardPin, cardKey).equals(clearPin(authSystemPin, authSystemKey));
    }

    public static boolean checkPinClear(String cardPin, String authSystemPin, String pan, String cardKey) {
        String s = pinBlock(cardKey, authSystemPin, pan);
        return s.equals(cardPin);
    }

    private static String clearPin(String pinBlock, String key) {
        SecretKeySpec tDesKey = stringToDes3(key);
        Cipher cipher = null;
        byte[] pinBytes;
        try {
            cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, tDesKey);
            pinBytes = cipher.doFinal(DatatypeConverter.parseHexBinary(pinBlock));
        } catch (NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        return DatatypeConverter.printHexBinary(pinBytes);
    }

    private static SecretKeySpec stringToDes3(String key) {
        byte[] keyBytes = DatatypeConverter.parseHexBinary(key);
        return new SecretKeySpec(keyBytes, "DESede");
    }

    public static String pinBlock(String key, String clearPin, String pan) {
        byte[] iso2Pan = DatatypeConverter.parseHexBinary(pan.substring(pan.length() - 13, pan.length() - 1));
        byte[] pin = DatatypeConverter.parseHexBinary("04" + clearPin + "FFFFFFFFFF");
        for (int i = 0; i < iso2Pan.length; i++) {
            pin[i + 2] ^= iso2Pan[i];
        }
        try {
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, stringToDes3(key));
            return DatatypeConverter.printHexBinary(cipher.doFinal(pin));
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
    }
}

