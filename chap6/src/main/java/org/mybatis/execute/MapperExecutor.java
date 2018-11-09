package org.mybatis.execute;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.persistence.ShopMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class MapperExecutor {
    private static final Log log = LogFactory.getLog(MapperExecutor.class);

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/mybatis";
    private static String username = "mybatis";
    private static String password = "mybatis$";
    private static String environmentId = "default";

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            DataSource dataSource = new UnpooledDataSource(driver, url, username, password);
            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment(environmentId, transactionFactory, dataSource);

            Configuration configuration = new Configuration(environment);
            configuration.addMapper(ShopMapper.class);

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("shopNo", 1);

            // Map<String, Object> result = sqlSession.getMapper(ShopMapper.class).select(parameters);
            // log.debug(String.valueOf(result.get("shopName")));

            Map<String, Object> result = sqlSession.selectOne("org.mybatis.persistence.ShopMapper.select", parameters);
            log.debug(String.valueOf(result.get("shopName")));

            result = sqlSession.getMapper(ShopMapper.class).select(parameters);
            log.debug(String.valueOf(result.get("shopName")));


            parameters = new HashMap<String, Object>();
            parameters.put("shopName", "Dad Store");
            parameters.put("shopLocation", "D Tower Seocho dong");
            parameters.put("shopStatus", "Y");
            sqlSession.getMapper(ShopMapper.class).insert(parameters);
            int shopNo = Integer.parseInt(parameters.get("shopNo").toString());


            parameters = new HashMap<String, Object>();
            parameters.put("shopNo", shopNo);
            parameters.put("shopStatus", "N");
            sqlSession.getMapper(ShopMapper.class).update(parameters);


            parameters = new HashMap<String, Object>();
            parameters.put("shopNo", shopNo);
            sqlSession.getMapper(ShopMapper.class).delete(parameters);
            sqlSession.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}
