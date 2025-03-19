/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

import dao.UserDao;

/**
 *
 * @author DAT
 */
public class Validate {
    
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9+_.%-]+@[a-zA-Z0-9+_.%-]+\\.+[a-zA-Z]{2,}+$";
    private static final String REGEX_TELEPHONE = "[0-9]{10,11}";
    private static final String REGEX_PLATE_NUMBER = "^[0-9]{2}[A-Z]-[0-9]{5}$";
    private static final String REGEX_PASSWORD = ".{5,}";
    
    private static final String REGEX_NUMBER =  "^(0|[1-9]\\d*)(\\.\\d+)?$"; // Regex để kiểm tra số 
    private final static UserDao userDao = new UserDao();
    
    
    public static boolean checkEmail(String email){
        return email.matches(REGEX_EMAIL);
    }
    
    public static boolean checkPassword(String pass){
        return pass.matches(REGEX_PASSWORD);
    }
    
    public static boolean checkPlateNumber(String plateNumber){
        return plateNumber.matches(REGEX_PLATE_NUMBER);
    }
    
    
    public static boolean checkTele(String tel){
        return tel.matches(REGEX_TELEPHONE);
    }
    
    public static boolean checkConfirmPassword(String password, String confirPassword){
        return password.equals(confirPassword);
    }
    
    public static boolean emailIsExist(String emailRequest){
        return userDao.existedUserWithEmail(emailRequest);
    }
      
    public static int getInteger(String a) {
        int b;
        try {
            b = Integer.parseInt(a);
        } catch (NumberFormatException e) {
            b = -1;
        }
        return b;
    }
    
     public static boolean checkHCAndCO2(String value) {
        // Kiểm tra xem giá trị có bị để trống không
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        // Kiểm tra xem giá trị có phải là số và không chứa ký tự đặc biệt, khoảng trống, hoặc chữ cái
        return value.matches(REGEX_NUMBER);
    }
    
}
