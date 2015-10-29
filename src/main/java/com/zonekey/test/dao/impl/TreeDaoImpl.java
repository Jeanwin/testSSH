package com.zonekey.test.dao.impl;

import org.springframework.stereotype.Repository;

import com.zonekey.test.dao.TreeDao;
import com.zonekey.test.dao.base.impl.HibernateBaseDaoImpl;
import com.zonekey.test.entity.Tree;

@Repository
public class TreeDaoImpl extends HibernateBaseDaoImpl<Tree, Integer> implements TreeDao {

}
