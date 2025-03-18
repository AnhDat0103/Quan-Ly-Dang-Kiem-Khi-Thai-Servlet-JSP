/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import model.enums.RoleEnums;

/**
 *
 * @author DAT
 */
public class ManagementEndPoint {

    public static final Map<RoleEnums, Set<String>> roleMap = new HashMap<>();

    public static Map<RoleEnums, Set<String>> getRoleMap() {
        // admin enpoint
        roleMap.put(RoleEnums.Admin, new HashSet<>(Arrays.asList("/quan-tri-vien", "/danh-sach-nhan-vien-kiem-dinh",
                "/chi-tiet-trung-tam-dang-kiem", "/he-thong-logs",
                "/chi-tiet-chu-phuong-tien", "/lay-lai-mat-khau","/StationServlet","/tao-moi-trung-tam-kiem-dinh", "/cap-nhat-inspector"

        )));

        // station endpoint
        roleMap.put(RoleEnums.Station, new HashSet<>(Arrays.asList("/quan-ly-lich-hen", "/thong-tin-ca-nhan",
                "/bao-cao-kiem-dinh", "/trung-tam-dang-kiem",
                "/xac-thuc-don-kiem-dinh"
        )));

        //Onwer endpoint        
        roleMap.put(RoleEnums.Owner, new HashSet<>(Arrays.asList("/chu-phuong-tien", "/ho-so-ca-nhan-1",
                "/kiem-tra-tai-khoan", "/that-bai", "/thanh-cong", "/update-profile",
                "/chinh-sua-phuong-tien", "/dang-ky-kiem-dinh", "/dang-ky-phuong-tien",
                "/lich-su", "/quan-ly-phuong-tien", "/timKiemPT1", "/xoa-phuong-tien"
        )));

        // Inspector endpoint        
        roleMap.put(RoleEnums.Inspector, new HashSet<>(Arrays.asList("/DeleteAccountServlet", "/lich-su-trang-chu",
                "/nguoi-kiem-dinh", "/thong-tin-nguoi-kiem-dinh"
        )));

        return roleMap;
    }

    public static boolean isValidEndPoint(RoleEnums re, String path, Map<RoleEnums, Set<String>> a) {
        return a.containsKey(re) && a.get(re).stream().anyMatch(path::contains);
    }

}
