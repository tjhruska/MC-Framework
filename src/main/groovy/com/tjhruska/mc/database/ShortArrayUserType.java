package com.tjhruska.mc.database;

import static java.sql.Types.ARRAY;

import java.io.Serializable;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

public class ShortArrayUserType implements UserType{

  public int[] sqlTypes() {
    return new int[]{ARRAY};
  }

  @SuppressWarnings("rawtypes")
  public Class returnedClass() {
    Short[] array = new Short[]{};
    return array.getClass();
  }

  public boolean equals(Object x, Object y) throws HibernateException {
    if (x == null && y == null) return true;
    if (x == null || y == null) return false;
    if (!(x instanceof Short[]) || !(y instanceof Short[])) return false;
    Short[] xA = (Short[])x;
    Short[] yA = (Short[])y;
    
    if (xA.length != yA.length) return false;
    for (int i = 0; i < xA.length; i++)
      if (!xA[i].equals(yA[i])) return false;
    
    return true;
  }

  public int hashCode(Object x) throws HibernateException {
    return ((Short[])x).hashCode();
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
    Short[] result = null;
    Array array = rs.getArray(names[0]);
    if (!rs.wasNull() && array != null){
        Integer[] intArray = (Integer[]) array.getArray();
        result = new Short[intArray.length];
        for (int i = 0; i < intArray.length; i++){
          result[i] = intArray[i].shortValue();
        }
    }
    return result;
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
    if (value == null)
      st.setNull(index, sqlTypes()[0]);
    else {
      st.setArray(index, st.getConnection().createArrayOf("int2", (Short[])value));
    }
  }

  public Object deepCopy(Object value) throws HibernateException {
    Short[] x = (Short[]) value;
    Short[] result = new Short[x.length];
    for (int i = 0; i < x.length; i++)
      result[i] = x[0];
    return result;
  }

  public boolean isMutable() {
    return true;
  }

  public Serializable disassemble(Object value) throws HibernateException {
    return (Serializable) value;
  }

  public Object assemble(Serializable cached, Object owner) throws HibernateException {
    return cached;
  }

  public Object replace(Object original, Object target, Object owner) throws HibernateException {
    return original;
  }
} 