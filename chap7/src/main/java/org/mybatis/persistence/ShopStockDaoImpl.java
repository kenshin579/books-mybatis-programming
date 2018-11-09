package org.mybatis.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ShopStockDaoImpl implements ShopStockDao {
    private static final Logger log = LoggerFactory.getLogger(ShopStockDaoImpl.class);

    private SqlSession sqlSession;

    /* 가게 장난감 재고량 조회 */
    public int select(int shopNo) {
//        return sqlSession.selectOne("org.mybatis.persistence.ShopStockMapper.select", shopNo);
        int result = 100;
        log.info("select: {}", result);
        return result;
    }

    /* 가게 장난감 재고량 수정 */
    public void update(Map<String, Object> parameters) {
//        sqlSession.update("org.mybatis.persistence.ShopStockMapper.update", parameters);
        log.info("updated : {}", parameters);
    }
}