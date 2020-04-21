/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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
public class Toast implements Serializable{
    private String content;
    private int exist;//0 mới tạo 1 qua filter 2 đã xóa
}
