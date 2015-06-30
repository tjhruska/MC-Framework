/**
 * Copyright 2015 Timothy James Hruska (tjhruska@yahoo.com)
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

package com.tjhruska.mc.database

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Types

import org.hibernate.HibernateException
import org.hibernate.engine.spi.SessionImplementor
import org.hibernate.usertype.ParameterizedType
import org.hibernate.usertype.UserType
import org.postgresql.util.PGobject

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.type.CollectionLikeType
import com.fasterxml.jackson.databind.type.MapType
import com.fasterxml.jackson.datatype.joda.JodaMapper


public class JsonToListTypeUserType implements UserType, ParameterizedType {

  private Class<?> mappedClass
  private Class<?> valueClass
  private CollectionLikeType listType
  private ObjectMapper objectMapper

  public JsonToListTypeUserType() {
    objectMapper = new JodaMapper()
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
  }

  @Override
  public void setParameterValues(Properties parameters) {
    String mappedClassName = parameters.getProperty("mappedClass")
    try {
      mappedClass = List.class
      valueClass = Class.forName(mappedClassName)
      listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList, valueClass)
    } catch (ClassNotFoundException cfne) {
      throw new HibernateException("Mapped class not found", cfne)
    }
  }

  @Override
  public Class returnedClass() {
    return mappedClass
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
  throws HibernateException, SQLException {
    String jsonString = rs.getString(names[0])
    if (rs.wasNull()) {
      return null
    }

    try {
      return assemble(jsonString, null)
    } catch (Exception e) {
      throw new HibernateException("Exception while converting from JSON to " + mappedClass, e)
    }
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
  throws HibernateException, SQLException {
    try {
      if (value == null) {
        st.setNull(index, Types.OTHER)
      } else {
        String jsonString = (String) disassemble(value)

        PGobject dataObject = new PGobject()
        dataObject.setType("json")
        dataObject.setValue(jsonString)

        st.setObject(index, dataObject)
      }
    } catch (Exception e) {
      throw new HibernateException("Exception while converting from " + mappedClass + " to JSON.", e)
    }
  }

  @Override
  public Object assemble(Serializable cached, Object owner) throws HibernateException {
    try {
      return objectMapper.readValue(cached.toString(), listType)
    } catch (Exception e) {
      throw new RuntimeException("failed to deserialize object for class " + mappedClass.getName(), e)
    }
  }

  @Override
  public Object deepCopy(Object value) throws HibernateException {
    return assemble(disassemble(value), null)
  }

  @Override
  public Serializable disassemble(Object value) throws HibernateException {
    try {
      return objectMapper.writeValueAsString(value)
    } catch (Exception e) {
      throw new RuntimeException("failed to serialize object for class " + mappedClass.getName(), e)
    }
  }

  @Override
  public boolean equals(Object x, Object y) throws HibernateException {
    if (x == y) {
      return true
    } else if (x == null || y == null) {
      return false
    } else {
      return x.equals(y)
    }
  }

  @Override
  public int hashCode(Object x) throws HibernateException {
    return x.hashCode()
  }

  @Override
  public boolean isMutable() {
    return true
  }

  @Override
  public Object replace(Object original, Object target, Object owner) throws HibernateException {
    return original
  }

  @Override
  public int[] sqlTypes() {
    return [Types.OTHER]
  }
}