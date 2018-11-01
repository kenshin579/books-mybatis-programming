package org.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class Executor {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 마이바티스 환경 설정 XML 파일 경로
            String resource = "mybatis/config-mybatis.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 세션 및 트랜잭션 시작

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 조회 매핑 구문 실행
            sqlSession.selectList("org.mybatis.ShopMapper.list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 세션 및 트랜잭션 종료
    }
}
