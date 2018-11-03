package org.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.custom.CustomClobTypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

public class TypeHandlersExecutor {
    private static final Logger log = LoggerFactory.getLogger(TypeHandlersExecutor.class);

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 마이바티스 환경 설정 XML 파일 경로 (타입 핸들러 실습)
            String resource = "mybatis/typehandlers/config-mybatis.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Configuration configuration = sqlSessionFactory.getConfiguration();

            TypeHandlerRegistry typeHandlerRegistry =
                    configuration.getTypeHandlerRegistry();

            TypeHandler<?> typeHandler =
                    typeHandlerRegistry.getMappingTypeHandler(
                            CustomClobTypeHandler.class);

            log.debug(String.valueOf(typeHandler.getClass()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
