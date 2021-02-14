package bg.fmi.cms;

import bg.fmi.cms.keys.CipherUtils;
import org.junit.Test;

public class KeyTest {

    @Test
    public void testEncrypt() {
        String pan = "5400121234345566";
        String key = "000102030405060708090A0B0C0D0E0F0001020304050607";
        String encrypted = CipherUtils.encryptPan(pan, key);
        System.out.println(encrypted);
        String decrypted = CipherUtils.getClearPan(encrypted, key);
        System.out.println(decrypted);
    }

    @Test
    public void randomTest() {
        String pan = "5400121234345566";
        System.out.println(pan.substring(pan.length() - 13, pan.length() - 1));
    }

    @Test
    public void pinTest() {
        String pan = "5400121234345566";
        String acKey = "001122334455667700112233445566770011223344556677";
        String cmsKey = "00112233445566770A0B0C0D0E0F00112233445566770011";
        String pin = "1234";
        String pinAc = CipherUtils.pinBlock(acKey, pin, pan);
        String pinCms = CipherUtils.pinBlock(cmsKey, pin, pan);
        System.out.println("pinAc = " + pinAc);
        System.out.println("pinCms = " + pinCms);
        System.out.println(CipherUtils.checkPin(pinCms, pinAc, acKey, cmsKey));
    }
}
