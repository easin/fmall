package dao.common;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

public class GenericDaoImpl<T, PK extends Serializable> extends SqlSessionDaoSupport implements GenericDao<T,PK>{
	
	// sqlmap.xml定义文件中对应的sqlid  
	public static final String SQLID_INSERT = "insert";  
	public static final String SQLID_INSERT_BATCH = "insertBatch";  
	public static final String SQLID_UPDATE = "update";  
	public static final String SQLID_UPDATE_BATCH = "updateBatch";  
	public static final String SQLID_DELETE = "delete";  
	public static final String SQLID_DELETE_BATCH = "deleteBatch";  
	public static final String SQLID_DELETE_PARAM = "deleteParam";  
	public static final String SQLID_TRUNCATE = "truncate";  
	public static final String SQLID_SELECT = "select";  
	public static final String SQLID_SELECT_PAGE = "selectPage";  
	public static final String SQLID_SELECT_PK = "selectPk";  
	public static final String SQLID_SELECT_PK_PAGE = "selectPkPage";  
	public static final String SQLID_SELECT_PARAM = "selectParam";  
	public static final String SQLID_SELECT_FK = "selectFk";  
	public static final String SQLID_COUNT = "count";  
	public static final String SQLID_COUNT_PARAM = "countParam"; 

	private String sqlmapNamespace = "";  

	
	private SqlSessionTemplate getBatchSqlSession() {
		SqlSessionFactory sqlSessionFactory=((SqlSessionTemplate)getSqlSession()).getSqlSessionFactory();
		return new SqlSessionTemplate(sqlSessionFactory,ExecutorType.BATCH);
	}
	
	/** 
	* sqlmapNamespace，对应sqlmap.xml中的命名空间 
	*  
	* @return 
	*/  
	public String getSqlmapNamespace() {  
		return sqlmapNamespace;  
	}  

	/** 
	* sqlmapNamespace的设置方法，可以用于spring注入 
	*  
	* @param sqlmapNamespace 
	*/  
	public void setSqlmapNamespace(String sqlmapNamespace) {  
		this.sqlmapNamespace = sqlmapNamespace;  
	}  

	/** 
	* 数据库方言，缺省为MYSQL 
	*/  
	private String dbDialect = "MYSQL";  

	/** 
	* 数据库方言dbDialect的get方法 
	*  
	* @return 
	*/  
	public String getDbDialect() {  
		return dbDialect;  
	}  

	/** 
	* 数据库方言dbDialect的set方法 
	*  
	* @return 
	*/  
	public void setDbDialect(String dbDialect) {  
	if (dbDialect == null  
	        || (!dbDialect.equals("MYSQL") && !dbDialect.equals("ORACLE")))  
	    throw new DaoException("错误的数据库方言设置：本系统只支持MYSQL和ORACLE");  
		this.dbDialect = dbDialect;  
	}  


	@Override
	public void insert(T entity) {
		getSqlSession().insert(sqlmapNamespace + "." + SQLID_INSERT,  
		        entity);
	}

	@Override
	public int update(Object param) {
		return getSqlSession().update(  
		        sqlmapNamespace + "." + SQLID_UPDATE, param);  
	}

	@Override
	public int delete(PK primaryKey) {
		int rows = getSqlSession().delete(  
		        sqlmapNamespace + "." + SQLID_DELETE, primaryKey);  
		return rows;
	}

	@Override
	public int delete(Object param) {
		int rows = getSqlSession().delete(  
		        sqlmapNamespace + "." + SQLID_DELETE_PARAM, param);  
		return rows;  
	}

	@Override
	public int truncate() {
		int rows = getSqlSession().delete(  
		        sqlmapNamespace + "." + SQLID_TRUNCATE);  
		return rows;  
	}

	@Override
	public int count() {
		Integer count = (Integer) getSqlSession().selectOne(  
		        sqlmapNamespace + "." + SQLID_COUNT);  
		return count.intValue();  
	}

	@Override
	public int count(Object param) {
		Integer count = (Integer) getSqlSession().selectOne(  
		        sqlmapNamespace + "." + SQLID_COUNT_PARAM, param);  
		return count.intValue();  
	}

	@Override
	public T get(PK primaryKey) {
		return (T) getSqlSession().selectOne(  
		        sqlmapNamespace + "." + SQLID_SELECT_PK, primaryKey);  
	}

	@Override
	public T load(PK primaryKey) throws DataAccessException {
		Object o = getSqlSession().selectOne(  
		        sqlmapNamespace + "." + SQLID_SELECT_PK, primaryKey);  
		if(o==null){return null;}
		return (T) o; 
	}

	@Override
	public List<T> select() {
		return getSqlSession().selectList(  
		        sqlmapNamespace + "." + SQLID_SELECT);  
	}

	@Override
	public List<T> select(Object param) {
		return getSqlSession().selectList(  
		        sqlmapNamespace + "." + SQLID_SELECT_PARAM, param);  
	}

	@Override
	public PaginationResult<T> selectPagination(Object param) {
		RowBounds paramRowBounds=new RowBounds();//TODO
		List<T> list=getSqlSession().selectList(sqlmapNamespace+"."+SQLID_SELECT_PAGE, param, paramRowBounds);
		return null;
	}

	@Override
	public List<T> selectFk(Object param) {
		return getSqlSession().selectList(sqlmapNamespace+"."+SQLID_SELECT_FK, param);
	}

	@Override
	public PaginationResult<T> selectFkPagination(Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchInsert(List<T> list) {
		for(T t:list){
			getBatchSqlSession().insert(sqlmapNamespace+"."+SQLID_INSERT_BATCH, t);
		}
	}

	@Override
	public void batchUpdate(List<T> list) {
		for(T t:list){
			getBatchSqlSession().update(sqlmapNamespace+"."+SQLID_UPDATE_BATCH, t);
		}
	}

	@Override
	public void batchDelete(List<PK> list) {
		for(PK key:list){
			getBatchSqlSession().delete(sqlmapNamespace+"."+SQLID_DELETE_BATCH, key);
		}
	}
	


}
	

