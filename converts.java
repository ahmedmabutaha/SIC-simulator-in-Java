
import java.math.BigInteger;

public class converts {
    
    public static String hexToBin(String s) {
    String Binary = new BigInteger(s, 16).toString(2);
    Integer length = Binary.length();
    if (length < 8) {
        for (int i = 0; i < 8 - length; i++) {
            Binary = "0" + Binary;
        }
    }
    return Binary;
    }
    
    public static String intToHex(int n) {
        return Integer.toHexString(n);
        
    }
    
    public static String binaryToHex(String bin) {
        return String.format("%21X", Long.parseLong(bin,2)) ;
    }
    public static int binaryTodec(String num) {
      int num2 = Integer.parseInt(num,2);
        return num2;
    }
}
