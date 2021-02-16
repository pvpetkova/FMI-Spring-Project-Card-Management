package bg.fmi.cms.keys;

import bg.fmi.cms.model.Bin;
import bg.fmi.cms.model.SymmetricKey;
import bg.fmi.cms.model.constats.KeyType;
import bg.fmi.cms.model.constats.KeyUsage;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;

public class KeyGenerator {
    public static SymmetricKey generateDesEDEKey(KeyUsage usage, Bin bin) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[24];
        secureRandom.nextBytes(keyBytes);
        String keyValueHex = DatatypeConverter.printHexBinary(keyBytes);
        SymmetricKey key = new SymmetricKey();
        key.setKeyType(KeyType.DESEDE);
        key.setKeyValue(keyValueHex);
        key.setKeyUsage(usage);
        key.setBin(bin);
        key.setKeyName(usage.toString() + ":" + bin.getBin());
        return key;
    }
}
