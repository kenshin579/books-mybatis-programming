package step2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class SqlMapper {
    private static final Logger LOG = LoggerFactory.getLogger(SqlMapper.class);

    /* 데이터베이스 설정 프로퍼티 파일 경로 */
    private String configurationResource = "jdbc/config-jdbc.properties";
    private Properties configuration = new Properties();

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    /* 기본 생성자 */
    public SqlMapper() {
        try {
            // 데이터베이스 설정 프로퍼티 파일 로딩 메소드 호출
            configurationAsProperties();

            // JDBC 드라이버 로딩
            Class.forName(configuration.getProperty("driver"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* 데이터베이스 연결 */
    protected Connection connect() throws SQLException {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(
                        configuration.getProperty("url"),
                        configuration.getProperty("username"),
                        configuration.getProperty("password"));
            }

            return connection;
        } catch (SQLException e) {
            throw e;
        }
    }

    /* 자원 및 데이터베이스 연결 해제 */
    protected void release() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
            }
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }

    /*
     * 데이터베이스 설정 프로퍼티 파일 로딩
     * - 실행 안되어 재작성함
     * ㅁ. 참고: https://www.baeldung.com/java-properties
     */
    private void configurationAsProperties() throws IOException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream inputStream = contextClassLoader.getResourceAsStream(configurationResource)) {
            configuration.load(inputStream);
        } catch (IOException e) {
            throw e;
        }
    }
}
