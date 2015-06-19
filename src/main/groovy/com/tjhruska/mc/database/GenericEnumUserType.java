/**
 * Copyright 2011-2015 Timothy James Hruska (tjhruska@yahoo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tjhruska.mc.database;

//http://community.jboss.org/wiki/Java5EnumUserType
//https://github.com/regis-leray/hibernate-jackson/blob/master/src/main/java/org/rayjars/hibernate/JacksonUserType.java

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.BasicType;
import org.hibernate.type.TypeResolver;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

public class GenericEnumUserType implements UserType, ParameterizedType {
  private static final String DEFAULT_IDENTIFIER_METHOD_NAME = "name";
  private static final String DEFAULT_VALUE_OF_METHOD_NAME = "valueOf";

  @SuppressWarnings("rawtypes")
  private Class<? extends Enum> enumClass;
  private Class<?> identifierType;
  private Method identifierMethod;
  private Method valueOfMethod;
  private BasicType type;
  private int sqlType = Types.INTEGER;

  @Override
  public void setParameterValues(Properties parameters) {
    String enumClassName = parameters.getProperty("enumClass");
    try {
      enumClass = Class.forName(enumClassName).asSubclass(Enum.class);
    } catch (ClassNotFoundException cfne) {
      throw new HibernateException("Enum class not found", cfne);
    }

    String identifierMethodName = parameters.getProperty("identifierMethod", DEFAULT_IDENTIFIER_METHOD_NAME);

    try {
      identifierMethod = enumClass.getMethod(identifierMethodName, new Class[0]);
      identifierType = identifierMethod.getReturnType();
    } catch (Exception e) {
      throw new HibernateException("Failed to obtain identifier method", e);
    }

    type = new TypeResolver().basic(identifierType.getName());

    if (type == null)
      throw new HibernateException("Unsupported identifier type " + identifierType.getName());

    String valueOfMethodName = parameters.getProperty("valueOfMethod", DEFAULT_VALUE_OF_METHOD_NAME);

    try {
      valueOfMethod = enumClass.getMethod(valueOfMethodName, new Class[] { identifierType });
    } catch (Exception e) {
      throw new HibernateException("Failed to obtain valueOf method", e);
    }
  }

  @Override
  @SuppressWarnings("rawtypes")
  public Class returnedClass() {
    return enumClass;
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
      throws HibernateException, SQLException {
    Integer identifier = rs.getInt(names[0]);
    if (rs.wasNull()) {
      return null;
    }

    try {
      return valueOfMethod.invoke(enumClass, new Object[] { identifier });
    } catch (Exception e) {
      throw new HibernateException("Exception while invoking valueOf method '" + valueOfMethod.getName() + "' of "
          + "enumeration class '" + enumClass + "'", e);
    }
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
      throws HibernateException, SQLException {
    try {
      if (value == null) {
        st.setNull(index, sqlType);
      } else {
        Integer identifier = (Integer) identifierMethod.invoke(value, new Object[0]);
        st.setInt(index, identifier);
      }
    } catch (Exception e) {
      throw new HibernateException("Exception while invoking identifierMethod '" + identifierMethod.getName() + "' of "
          + "enumeration class '" + enumClass + "'", e);
    }
  }

  @Override
  public Object assemble(Serializable cached, Object owner) throws HibernateException {
    return cached;
  }

  @Override
  public Object deepCopy(Object value) throws HibernateException {
    return value;
  }

  @Override
  public Serializable disassemble(Object value) throws HibernateException {
    return (Serializable) value;
  }

  @Override
  public boolean equals(Object x, Object y) throws HibernateException {
    if (x == y) {
      return true;
    } else if (x == null || y == null) {
      return false;
    } else {
      return x.equals(y);
    }
  }

  @Override
  public int hashCode(Object x) throws HibernateException {
    return x.hashCode();
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Object replace(Object original, Object target, Object owner) throws HibernateException {
    return original;
  }

  @Override
  public int[] sqlTypes() {
    return new int[] { Types.INTEGER };
  }
}