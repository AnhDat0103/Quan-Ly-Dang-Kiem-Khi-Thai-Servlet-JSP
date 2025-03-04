/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author DAT
 */
public class Configuration {

    public static String hashPasswordByMD5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            return myHash;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pass;
    }

    public static boolean verifyPasswordAfterHashed(String pass, String hash) {
        return hashPasswordByMD5(pass).equals(hash);
    }

    public static Date convertStringToDate(String stringDate) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        sd.setLenient(false);
        try {
            return sd.parse(stringDate);
        } catch (ParseException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean checkInspectionDate(String inspectionDate) {
        try {
            java.util.Date today = new java.util.Date();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            sd.setLenient(false);
            Date date = sd.parse(inspectionDate);
            if (today.compareTo(date) > 0) {
                return false;
            }
        } catch (ParseException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    public static Date getNextInspectionDate(String inspectionDate) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        sd.setLenient(false);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sd.parse(inspectionDate));
        } catch (ParseException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        cal.add(Calendar.YEAR, 1);
        java.sql.Date nextInspectionDate = new java.sql.Date(cal.getTimeInMillis());
        return  nextInspectionDate;
    }
    
    public static String getCurrentTimeByFormat(Date date) {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       sdf.setLenient(false);
       return sdf.format(date);
    }
}