package com.tjhruska.mc.database;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tjhruska.mc.database.restrictions.Restriction;

@SuppressWarnings("rawtypes")
public interface TransactionalTestSetupServiceIface {

  @Transactional
  public abstract void save(DaoDomain dao, BaseDomain domain);

  @Transactional
  public abstract void saveOrUpdate(DaoDomain dao, BaseDomain domain);

  public abstract BaseDomain findByPK(DaoDomain dao, Serializable id);
  
  public abstract List<BaseDomain> findByCriteria(DaoDomain dao, Restriction restriction);
  
  @Transactional
  public abstract void delete(DaoDomain dao, BaseDomain domain);
}