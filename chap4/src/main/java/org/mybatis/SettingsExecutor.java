package org.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

public class SettingsExecutor {
    private static final Logger log = LoggerFactory.getLogger(SettingsExecutor.class);

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 마이바티스 환경 설정 XML 파일 경로 (셋팅 실습)
            String resource = "mybatis/settings/config-mybatis.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Configuration configuration = sqlSessionFactory.getConfiguration();

            log.debug(Boolean.toString(configuration.isLazyLoadingEnabled()));
            log.debug(Boolean.toString(configuration.isUseGeneratedKeys()));
            log.debug(Boolean.toString(configuration.isMapUnderscoreToCamelCase()));
            log.debug(Boolean.toString(configuration.isCallSettersOnNulls()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
