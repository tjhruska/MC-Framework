
package com.tjhruska.mc.domain.system;


import com.tjhruska.mc.database.BaseDomain;
import com.tjhruska.mc.domain.system.EnumerationLink
//import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

//@EqualsAndHashCode
@ToString
public class EnumerationLinkValue extends BaseDomain {

    EnumerationLink enumerationLink
    Integer enumerationValueAId
    Integer enumerationValueBId
    String name
    String description
    Short sequence
    String column1Value
    String column2Value
    String column3Value
    String column4Value
    String column5Value

}