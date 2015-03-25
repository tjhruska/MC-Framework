/**
 *
 */
package com.tjhruska.mc.database

import groovy.transform.Canonical
import groovy.transform.ToString

import java.io.Serializable

import org.joda.time.DateTime

/**
 * The Abstract Class BaseDomain
 *
 * Cuts down on repeated code across all domain classes using the BaseDomain class
 *
 * @author tjhruska
 */
@Canonical
@ToString (includeNames = true, includePackage=false)
abstract class BaseDomain implements Serializable {
  Integer id
  DateTime addDate

  /**
   * @return the PK, in most cases the id, but on some objects it may be a "template" with the pk fields set
   */
  public Serializable getPK() {
    return id
  }
}