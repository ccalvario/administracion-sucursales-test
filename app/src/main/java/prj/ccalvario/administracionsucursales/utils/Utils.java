package prj.ccalvario.administracionsucursales.utils;

public class Utils {
    public static boolean isRfcValid(String rfc) {
        boolean result = false;
        if(rfc.length() == 12 || rfc.length() == 13) {
            result = true;
        }
        return result;
    }

    public static boolean isEmailValid(String email) {
        boolean result = true;
        return result;
    }
}
