package org.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class TypeAliasesExecutor {
    private static final Logger log = LoggerFactory.getLogger(TypeAliasesExecutor.class);

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 마이바티스 환경 설정 XML 파일 경로 (타입 알리아스 실습)
            String resource = "mybatis/typealiases/config-mybatis.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Map<String, Class<?>> typeAliases =
                    sqlSessionFactory.getConfiguration()
                            .getTypeAliasRegistry().getTypeAliases();

            log.debug(String.valueOf(typeAliases.get("shop")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
