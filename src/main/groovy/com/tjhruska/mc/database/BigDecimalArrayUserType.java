/**
Copyright 2011-2015 Timothy James Hruska (tjhruska@yahoo.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.tjhruska.mc.database;

import static java.sql.Types.ARRAY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

public class BigDecimalArrayUserType implements UserType {

  @Override
  public int[] sqlTypes() {
    return new int[] { ARRAY };
  }

  @Override
  @SuppressWarnings("rawtypes")
  public Class returnedClass() {
    BigDecimal[] array = new BigDecimal[] {};
    return array.getClass();
  }

  @Override
  public boolean equals(Object x, Object y) throws HibernateException {
    if (x == null && y == null)
      return true;
    if (x == null || y == null)
      return false;
    if (!(x instanceof BigDecimal[]) || !(y instanceof BigDecimal[]))
      return false;
    BigDecimal[] xA = (BigDecimal[]) x;
    BigDecimal[] yA = (BigDecimal[]) y;

    if (xA.length != yA.length)
      return false;
    for (int i = 0; i < xA.length; i++)
      if (!xA[i].equals(yA[i]))
        return false;

    return true;
  }

  @Override
  public int hashCode(Object x) throws HibernateException {
    return ((BigDecimal[]) x).hashCode();
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
      throws HibernateException, SQLException {
    BigDecimal[] result = null;
    Array array = rs.getArray(names[0]);
    if (!rs.wasNull() && array != null)
      result = (BigDecimal[]) array.getArray();
    return result;
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
      throws HibernateException, SQLException {
    if (value == null)
      st.setNull(index, sqlTypes()[0]);
    else {
      st.setArray(index, st.getConnection().createArrayOf("numeric", (BigDecimal[]) value));
    }
  }

  @Override
  public Object deepCopy(Object value) throws HibernateException {
    BigDecimal[] x = (BigDecimal[]) value;
    BigDecimal[] result = new BigDecimal[x.length];
    for (int i = 0; i < x.length; i++)
      result[i] = x[0];
    return result;
  }

  @Override
  public boolean isMutable() {
    return true;
  }

  @Override
  public Serializable disassemble(Object value) throws HibernateException {
    return (Serializable) value;
  }

  @Override
  public Object assemble(Serializable cached, Object owner) throws HibernateException {
    return cached;
  }

  @Override
  public Object replace(Object original, Object target, Object owner) throws HibernateException {
    return original;
  }
}