/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Lenovo
 */
public class ViolationMap {
    private HashMap<Integer, String> list = new HashMap<>();

    public HashMap<Integer, String> getList() {
        return list;
    }

    public void setList(HashMap<Integer, String> list) {
        this.list = list;
    }

    public ViolationMap() {
        list.put(1, "Điều khiển xe không đủ hệ thống hoặc có đủ hệ thống nhưng không có tác dụng, không đúng tiêu chuẩn an toàn kỹ thuật (Đăng kiểm Fail quá 3 lần).");
        list.put(2, "Người sử dụng ô tô quá hạn đăng kiểm dưới 10 ngày có thể bị phạt hành chính 3-4 triệu đồng và bị tước quyền sử dụng giấy phép lái xe từ 1 đến 3 tháng.");
    }
    
    
}
