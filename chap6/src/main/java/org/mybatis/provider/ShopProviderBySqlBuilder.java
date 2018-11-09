package org.mybatis.provider;

import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class ShopProviderBySqlBuilder {
    public String select(Map<String, Object> parameters) {
        BEGIN();

        SELECT("SHOP_NO, SHOP_NAME, SHOP_LOCATION, SHOP_STATUS");
        FROM("SHOP");
        WHERE("SHOP_NO = #{shopNo}");

        return SQL();
    }

    public String insert(Map<String, Object> parameters) {
        BEGIN();

        INSERT_INTO("SHOP");
        VALUES("SHOP_NO", "#{shopNo}");
        VALUES("SHOP_NAME", "#{shopName}");
        VALUES("SHOP_LOCATION", "#{shopLocation}");
        VALUES("SHOP_STATUS", "#{shopStatus}");

        return SQL();
    }

    public String update(Map<String, Object> parameters) {
        BEGIN();

        UPDATE("SHOP");
        SET("SHOP_STATUS = #{shopStatus}");
        WHERE("SHOP_NO = #{shopNo}");

        return SQL();
    }

    public String delete(Map<String, Object> parameters) {
        BEGIN();

        DELETE_FROM("SHOP");
        WHERE("SHOP_NO = #{shopNo}");

        return SQL();
    }
}
