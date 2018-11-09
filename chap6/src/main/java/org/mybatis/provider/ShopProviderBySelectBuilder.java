package org.mybatis.provider;

import java.util.Map;

import static org.apache.ibatis.jdbc.SelectBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SelectBuilder.FROM;
import static org.apache.ibatis.jdbc.SelectBuilder.SELECT;
import static org.apache.ibatis.jdbc.SelectBuilder.SQL;
import static org.apache.ibatis.jdbc.SelectBuilder.WHERE;

public class ShopProviderBySelectBuilder {
    /* 조회 쿼리문 생성 및 반환 */
    public String select(Map<String, Object> parameters) {
        BEGIN();

        SELECT("SHOP_NO, SHOP_NAME, SHOP_LOCATION, SHOP_STATUS");
        FROM("SHOP");
        WHERE("SHOP_NO = #{shopNo}");

        return SQL();
    }
}
