/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author PC
 */
@Builder
@Getter
@Setter
@ToString
public class CustomerDetail implements Serializable{

    private int id;
    private int accID;
    private String name;
    private String mobile;
    private String email;
    private int gender;
    private String address;
    private String avatar;

    public String getDisplayGender() {
        return (gender == 0) ? "Nữ" : (gender == 1) ? "Nam" : "Khác";
    }
     public String getValiName(String name) {
        String result = "";
        String[] temp = name.trim().toUpperCase().replaceAll("[\\d\\.\\:\"\\+\\-\\_\\)\\(\\+\'\\>\\<\\?]+", "").replaceAll("\\s+", " ").split(" ");
        for (String string : temp) {
            result = result + string.charAt(0) + string.substring(1).toLowerCase()+ " ";
        }
        return result.trim();
    }
}
