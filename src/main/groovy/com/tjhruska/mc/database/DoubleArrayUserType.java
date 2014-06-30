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

public class DoubleArrayUserType implements UserType{

  public int[] sqlTypes() {
    return new int[]{ARRAY};
  }

  @SuppressWarnings("rawtypes")
  public Class returnedClass() {
    Double[] array = new Double[]{};
    return array.getClass();
  }

  public boolean equals(Object x, Object y) throws HibernateException {
    if (x == null && y == null) return true;
    if (x == null || y == null) return false;
    if (!(x instanceof Double[]) || !(y instanceof Double[])) return false;
    Double[] xA = (Double[])x;
    Double[] yA = (Double[])y;
    
    if (xA.length != yA.length) return false;
    for (int i = 0; i < xA.length; i++)
      if (!xA[i].equals(yA[i])) return false;
    
    return true;
  }

  public int hashCode(Object x) throws HibernateException {
    return ((Double[])x).hashCode();
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
    Double[] result = null;
    Array array = rs.getArray(names[0]);
    if (!rs.wasNull() && array != null)
        result = (Double[]) array.getArray();
    return result;
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
    if (value == null)
      st.setNull(index, sqlTypes()[0]);
    else {
      st.setArray(index, st.getConnection().createArrayOf("float8", (Double[])value));
    }
  }

  public Object deepCopy(Object value) throws HibernateException {
    if (value == null) return null;
    Double[] x = (Double[]) value;
    Double[] result = new Double[x.length];
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