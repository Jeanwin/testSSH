package com.zonekey.test.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.zonekey.test.dao.base.HibernateBaseDao;
import com.zonekey.test.dao.base.Page;

@Repository
public class HibernateBaseDaoImpl<T, PK extends Serializable> implements HibernateBaseDao<T, PK> {

	// slf4j日志输出
	protected static final Logger LOG = LoggerFactory.getLogger(HibernateBaseDaoImpl.class);

	// 泛型反射类的类对象
	private Class<T> entityClass;

	// 通过反射获得子类确定的泛型类
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HibernateBaseDaoImpl() {
		Type genType = this.getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
	}

	// 注入sessionFactory
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	// get session
	public Session getSession() {
		// 事务必须是开启的（required）,否则获取不到
		return sessionFactory.getCurrentSession();
	}

	@Override
	@SuppressWarnings("unchecked")
	public PK save(T entity) {
		return (PK) getSession().save(entity);
	}

	@Override
	public void saveAll(Collection<T> entities) {
		for (@SuppressWarnings("rawtypes")
		Iterator localIterator = entities.iterator(); localIterator.hasNext();) {
			Object entity = localIterator.next();
			getSession().save(entity);
		}
	}

	@Override
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<T> entities) {
		for (@SuppressWarnings("rawtypes")
		Iterator localIterator = entities.iterator(); localIterator.hasNext();) {
			Object entity = localIterator.next();
			getSession().saveOrUpdate(entity);
		}
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public void updateAll(Collection<T> entities) {
		for (@SuppressWarnings("rawtypes")
		Iterator localIterator = entities.iterator(); localIterator.hasNext();) {
			Object entity = localIterator.next();
			getSession().update(entity);
		}
	}

	@Override
	public void merge(T entity) {
		getSession().merge(entity);
	}

	@Override
	public void delete(PK id) {
		getSession().delete(this.get(id));
	}

	@Override
	public void deleteEntity(T entity) {
		getSession().delete(entity);
	}

	@Override
	public void deleteAll(Collection<T> entities) {
		if (entities == null || entities.size() == 0)
			return;
		for (T t : entities) {
			getSession().delete(t);
		}
	}

	@Override
	public boolean exists(PK id) {
		return this.get(id) != null;
	}

	/**
	 * 1.get()采用立即加载方式,而load()采用延迟加载; get()方法执行的时候,会立即向数据库发出查询语句,
	 * 而load()方法返回的是一个代理(此代理中只有一个id属性),只有等真正使用该对象属性的时候,才会发出sql语句
	 * 2.如果数据库中没有对应的记录,get()方法返回的是null.而load()方法出现异常ObjectNotFoundException
	 */
	@Override
	public T load(PK id) {
		return (T)getSession().load(this.entityClass, id);
	}

	@Override
	public T get(PK id) {
		return (T)getSession().get(this.entityClass, id);
	}

	@Override
	public long count() {
		Criteria criteria = createCriteria();
		return Integer.valueOf(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
	}

	@Override
	public long count(Criteria criteria) {
		return Integer.valueOf(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> list() {
		return createCriteria().list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> list(Criteria criteria) {
		return criteria.list();
	}

	@Override
	public List<T> list(DetachedCriteria criteria) {
		return (List<T>) list(criteria.getExecutableCriteria(getSession()));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> list(String orderBy, boolean isAsc) {
		Criteria criteria = createCriteria();
		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));
		return criteria.list();
	}

	@Override
	public List<T> list(String propertyName, Object value) {
		Criterion criterion = Restrictions.like(propertyName, "%" + value.toString() + "%");
		return list(criterion);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> list(Criterion criterion) {
		Criteria criteria = createCriteria();
		criteria.add(criterion);
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> list(Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T uniqueResult(String propertyName, Object value) {
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}

	/**
	 * 多条件查询一条记录
	 */
	@Override
	public T uniqueResult(Criterion... criterions) {
		Criteria criteria = createCriteria(criterions);
		return uniqueResult(criteria);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T uniqueResult(Criteria criteria) {
		return (T) criteria.uniqueResult();
	}

	@Override
	public Criteria distinct(Criteria criteria) {
		// 将结果集进行一次封装，封装成DISTINCT_ROOT_ENTITY对象，方便service层代码使用
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	@Override
	public void flush() {
		getSession().flush();
	}

	@Override
	public void clear() {
		getSession().clear();
	}

	/**
	 * Criteria是一种比hql更面向对象的查询方式。Criteria 可使用 Criterion 和 Projection 设置查询条件。可以设置
	 * FetchMode( 联合查询抓取的模式 ) ，设置排序方式，Criteria 还可以设置 FlushModel （冲刷 Session
	 * 的方式）和 LockMode
	 */
	@Override
	public Criteria createCriteria() {
		return getSession().createCriteria(entityClass);
	}

	/**
	 * 创建一个多条件的Criteria对象
	 */
	@Override
	public Criteria createCriteria(Criterion... criterion) {
		Criteria criteria = createCriteria();
		for (Criterion c : criterion) {
			criteria.add(c);
		}
		return criteria;
	}

	@Override
	public List<T> findPage(Criteria criteria, int pageNo, int pageSize) {
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		return list(criteria);
	}

	/**
	 * 支持Criteria查询的分页方法
	 */
	@Override
	public Page<T> pagedQuery(Criteria criteria, int pageNo, int pageSize) {
		Assert.isTrue(pageNo >= 1, "pageNo should starts from 1");
		List<T> list = findPage(criteria, pageNo, pageSize);
		// 注：因为finaPage方法改变了查询条件导致countALL方法查询为空， 所以必须重新设置setFirstResult为0
		criteria.setFirstResult(0);
		// 获得查询总数
		long totalCount = count(criteria);
		// 查询不到记录
		if (totalCount < 1) {
			return new Page<T>();
		}
		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		return new Page<T>(startIndex, totalCount, pageSize, list);
	}

	/**
	 * (1).Sesion.get()/load()查到某个对象后
	 * 用Session.update()，发现自己想更新的字段更新了，但是其他的字段却变成了空，为什么呢？
	 * 原因是Sesion.get()/load()和Session.update()用了不同的session. 会把这个表中的所有字段更新一遍。<br>
	 * (2).如果设置成为dynamic
	 * -update="true"的方式去update自己修改过的字段，是有前提的，也就是要更新的对象必须处于持久态(Persistent
	 * )的状态，这样hibernate才有的比较该对象是否已经修改过
	 * ，并且修改过哪些字段的哪些值。如果处于脱管态(Detached）（跨session），
	 * 则update的时候hibernate同样的回去更新所有的字段的值。<br>
	 * (3).如果要使用dynamic-update="true"的配置，并且想跨session
	 * ，而且还想只更新操作的对象的某些字段而不是全部字段，则还可以使用hibernate提供的另一个类似于更新的方法session2
	 * .merge(object
	 * )，但是，这个方法在操作合并之前，发一条sql语句去数据库中查找这样一个需要操作的对象，查询到以后，则会更想要更新的对象进行比较
	 * ，而后，才会发一条希望看到的更新的sql语句。
	 */
	@Override
	public void executeByHql(String hql) {
		getSession().createQuery(hql).executeUpdate();
	}

	/**
	 * 只更新部分字段： Query query = session.createQuery(
	 * "update Teacher t set t.name = 'yangtianb' where id = 3");<br>
	 * query.executeUpdate();<br>
	 */
	@Override
	public void executeByHql(String hql, Object parameters) {
		getSession().createQuery(hql).setProperties(parameters).executeUpdate();

	}

	/**
	 * 查询多个表中指定字段的2种方法: 1.创建带参数构造方法的实体类Temp，返回list<Temp> select new
	 * Temp(s.id,s.name,t.id,t.name) from User s,Useraddress t where t.id=s.id
	 * 2.返回list<Object[]>
	 */
	@Override
	public List<?> queryByHql(String hql) {
		return getSession().createQuery(hql).list();
	}

	/**
	 * 在hibernate中，用hql语句查询实体类，采用list方法的返回结果为一个List，该List中封装的对象分为以下三种情况：
	 * 1.查询全部字段的情况下，如"from 实体类"，list中封装的对象为实体类本身，各属性都将得到填充。
	 * 2.只查询一个字段，默认情况下，list中封装的是Object对象。
	 * 3.查询两个或两个以上的字段，默认情况下，list中封装的是Object[],长度与所查询的字段数一致。
	 * 对于后两种情况，用标签遍历时不太方便，因为无法直接转换成实体类的对象。比较简单的解决方法是：
	 * 
	 * の：在hql中使用select new 包名.类名(属性1，属性2……) from
	 * 实体类，同时在实体类中添加带参的构造方法，参数的个数和顺序与（属性1，属性2……)
	 * 保持一致，这样我们得到的list中存放的依然是实体类的对象，所查询到的属性得到了填充，使用起来更为方便。 の：hql查询多表部分字段，select
	 * new 包名.表1实体类名(表1.属性1，表2.属性2……) from 表1实体类,表2实体类 where
	 * 表1.ID=表2.ID（即相关联的字段），同时在要返回的表1实体类中添加表2的属性和带参的构造方法，参数的个数和顺序与（表1.属性1，表2.属性
	 * 2……) 保持一致
	 */
	@Override
	public List<?> queryByHql(String hql, Object parameters) {
		return getSession().createQuery(hql).setProperties(parameters).list();
	}

	@Override
	public void executeBySql(String sql) {
		getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public void executeBySql(String sql, Object parameters) {
		getSession().createSQLQuery(sql).setProperties(parameters).executeUpdate();
	}

	/**
	 * sql查询指定结果集类型的方法 ： String sql =
	 * "select u.userName as userName ，p.title as title ,p.addTime as addTime from user as u,post as p where u.id=p.userId"
	 * Query q =
	 * factory.getCurrentSession().createSQLQuery(sql).setResultTransformer
	 * (Transformers.aliasToBean(PostVO.class));
	 */
	@Override
	public List<?> queryBySql(String sql) {
		return getSession().createSQLQuery(sql).list();
	}

	@Override
	public List<?> queryBySql(String sql, Object parameters) {
		return getSession().createSQLQuery(sql).setProperties(parameters).list();
	}

}
