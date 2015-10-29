package com.zonekey.test.dao.base;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

/**
 * hibernate basedao interface
 * 
 * @author jeanwinhuang@live.com
 * @version 1.0
 * @param <T>
 * @param <PK>
 */
public interface HibernateBaseDao<T, PK extends java.io.Serializable> {

	/**
	 * 保存实体类，并返回主键
	 * 
	 * @param entity
	 * @return
	 */
	public PK save(T entity);

	/**
	 * 批量添加
	 * 
	 * @param entities
	 */
	public void saveAll(Collection<T> entities);

	/**
	 * 保存或更新
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(T entity);

	/**
	 * 批量保存或更新
	 * 
	 * @param entities
	 */
	public void saveOrUpdateAll(Collection<T> entities);

	/**
	 * 更新
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * 批量保存
	 * 
	 * @param entity
	 */
	public void updateAll(Collection<T> entity);

	/**
	 * 合并
	 * 
	 * @param entity
	 */
	public void merge(T entity);

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 */
	public void delete(PK id);

	/**
	 * 删除实体
	 * 
	 * @param entity
	 */
	public void deleteEntity(T entity);

	/**
	 * 删除所有
	 * 
	 * @param entities
	 */
	public void deleteAll(Collection<T> entities);

	/**
	 * 根据id判断是否存在
	 * 
	 * @param id
	 * @return
	 */
	public boolean exists(PK id);

	/**
	 * 根据id加载POJO
	 * 
	 * @param id
	 * @return
	 */
	public T load(PK id);

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public T get(PK id);

	/**
	 * 无条件统计记录条数
	 * 
	 * @return
	 */
	public long count();

	/**
	 * 根据条件统计记录数
	 * 
	 * @param criteria
	 * @return
	 */
	public long count(Criteria criteria);

	/**
	 * 统计hql或sql查询记录数
	 * 
	 * @param iByHql
	 * @param hql
	 * @param parameters
	 * @return
	 */
	public long count(boolean iByHql, String hqlOrHql, Map<String, String> parameters);

	/**
	 * 获取全部对象
	 * 
	 * @return
	 */
	public List<T> list();

	/**
	 * 根据条件获取列表
	 * 
	 * @param criteria
	 * @return
	 */
	public List<T> list(Criteria criteria);

	/**
	 * 离线查询
	 * 
	 * @param criteria
	 * @return
	 */
	public List<T> list(DetachedCriteria criteria);

	/**
	 * 支持排序
	 * 
	 * @param orderBy
	 * @param isAsc
	 * @return
	 */
	public List<T> list(String orderBy, boolean isAsc);

	/**
	 * 根据属性查找
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> list(String propertyName, Object value);

	/**
	 * 根据查询条件获取
	 * 
	 * @param criterion
	 * @return
	 */
	public List<T> list(Criterion criterion);

	/**
	 * 根据多个查询条件获取
	 * 
	 * @param criterion
	 * @return
	 */
	public List<T> list(Criterion... criterion);

	/**
	 * 按属性查找唯一对象
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public T uniqueResult(String propertyName, Object value);

	/**
	 * 根据条件查询唯一记录
	 * 
	 * @param criterions
	 * @return
	 */
	public T uniqueResult(Criterion... criterions);

	/**
	 * 根据Criteria查询唯一记录
	 * 
	 * @param criteria
	 * @return
	 */
	public T uniqueResult(Criteria criteria);

	/**
	 * 为Criteria添加distinct transformer
	 * 
	 * @param criteria
	 * @return
	 */
	public Criteria distinct(Criteria criteria);

	/**
	 * 强制清空session
	 */
	public void flush();

	/**
	 * 清空session
	 */
	public void clear();

	/**
	 * 创建Criteria查询实例
	 * 
	 * @return
	 */
	public Criteria createCriteria();

	/**
	 * 根据条件创建查询
	 * 
	 * @param criterion
	 * @return
	 */
	public Criteria createCriteria(Criterion... criterion);

	/**
	 * 条件分页查询
	 * 
	 * @param criteria
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<T> findPage(Criteria criteria, int pageNo, int pageSize);

	/**
	 * 条件分页查询
	 * 
	 * @param isByHql
	 *            取值true时使用hql查询,取值为false时使用sql查询
	 * @param hqlOrSql
	 *            hql或sql查询语句
	 * @param page
	 *            分页参数，前台需指定pageNo,pageSize,keywords
	 * @return
	 */
	public List<?> findPage(boolean isByHql, String hqlOrSql, Page page);

	/**
	 * 条件分页,通过Criteria可以设置各种查询条件 Criterion是Criteria的查询条件
	 * 
	 * @return
	 */
	public Page pagedQuery(Criteria criteria, int pageNo, int pageSize);

	/**
	 * 使用hql或者sql分页
	 * 
	 * @param isByHql
	 * @param hqlOrSql
	 * @param page
	 * @return
	 */
	public Page pagedQuery(boolean isByHql, String hqlOrSql, String countSql, Page page);

	/**
	 * 使用HQL增删改
	 * 
	 * @param hql
	 */
	public void executeByHql(String hql);

	/**
	 * 运行带参数的hql
	 * 
	 * @param hql
	 * @param parameters
	 */
	public void executeByHql(String hql, Object parameters);

	/**
	 * 执行不带动态参数的hql语句
	 * 
	 * @param hql
	 * @return
	 */
	public List<?> queryByHql(String hql);

	/**
	 * 运行带参数的hql语句
	 * 
	 * @param hql
	 * @param parameters
	 * @return
	 */
	public List<?> queryByHql(String hql, Object parameters);

	/**
	 * 使用原始sql语句执行增删改
	 * 
	 * @param sql
	 */
	public void executeBySql(String sql);

	/**
	 * 使用原始sql语句执行增删改
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public void executeBySql(String sql, Object parameters);

	/**
	 * 使用原始sql查询
	 * 
	 * @param sql
	 * @return
	 */
	public List<?> queryBySql(String sql);

	/**
	 * 使用原始sql查询
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public List<?> queryBySql(String sql, Object parameters);
}
