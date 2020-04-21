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
public class Account implements Serializable{
    private int id;
    private String user;
    private String pass;
    private String name;
    private String date;
    private int type;
    private int status;
    
     public String getValiName(String name) {
        String result = "";
        String[] temp = name.trim().toUpperCase().replaceAll("[\\d\\.\\:\"\\+\\-\\_\\)\\(\\+\'\\>\\<\\?]+", "").replaceAll("\\s+", " ").split(" ");
        for (String string : temp) {
            result = result + string.charAt(0) + string.substring(1).toLowerCase()+ " ";
        }
        return result.trim();
    }
}
