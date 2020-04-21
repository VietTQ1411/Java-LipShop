/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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
public class Conditions {
    private String productName;
    private String productBrand;
    private String style;
    private String type;
    private String order;
    private String moneyFrom;
    private String moneyTo;
}
