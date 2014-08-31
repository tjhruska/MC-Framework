package com.tjhruska.mc.database;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tjhruska.mc.database.restrictions.Restriction;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TransactionalTestSetupService implements
    TransactionalTestSetupServiceIface {

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.tjhruska.mc.database.TransactionalTestSetupServiceIface#save(com.tjhruska
   * .mc.database.DaoDomain, com.tjhruska.mc.database.BaseDomain)
   */
  @Override
  @Transactional
  public void save(DaoDomain dao, BaseDomain domain) {
    dao.save(domain);
    dao.flush();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.tjhruska.mc.database.TransactionalTestSetupServiceIface#saveOrUpdate
   * (com.tjhruska.mc.database.DaoDomain, com.tjhruska.mc.database.BaseDomain)
   */
  @Override
  @Transactional
  public void saveOrUpdate(DaoDomain dao, BaseDomain domain) {
    dao.saveOrUpdate(domain);
    dao.flush();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.tjhruska.mc.database.TransactionalTestSetupServiceIface#findByPK(com
   * .tjhruska.mc.database.DaoDomain, java.io.Serializable)
   */
  @Override
  @Transactional
  public BaseDomain findByPK(DaoDomain dao, Serializable id) {
    return dao.findByPK(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.tjhruska.mc.database.TransactionalTestSetupServiceIface#findByCriteria
   * (com.tjhruska.mc.database.DaoDomain,
   * com.tjhruska.mc.database.restrictions.Restriction)
   */
  @Override
  public List<BaseDomain> findByCriteria(DaoDomain dao, Restriction restriction) {
    return dao.findByCriteria(restriction);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.tjhruska.mc.database.TransactionalTestSetupServiceIface#findOneByCriteria
   * (com.tjhruska.mc.database.DaoDomain,
   * com.tjhruska.mc.database.restrictions.Restriction)
   */
  @Override
  @Transactional
  public BaseDomain findOneByCriteria(DaoDomain dao, Restriction restriction) {
    return dao.findOneByCriteria(restriction);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.tjhruska.mc.database.TransactionalTestSetupServiceIface#delete(com.
   * tjhruska.mc.database.DaoDomain, com.tjhruska.mc.database.BaseDomain)
   */
  @Override
  @Transactional
  public void delete(DaoDomain dao, BaseDomain domain) {
    dao.delete(domain);
  }
}
