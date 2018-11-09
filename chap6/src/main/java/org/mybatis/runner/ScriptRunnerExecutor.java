package org.mybatis.runner;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import javax.sql.DataSource;

public class ScriptRunnerExecutor {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/mybatis";
    private static String username = "mybatis";
    private static String password = "mybatis$";

    private static DataSource dataSource;

    static {
        try {
            dataSource = new UnpooledDataSource(driver, url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        ScriptRunner scriptRunner = null;

        try {
            scriptRunner = new ScriptRunner(dataSource.getConnection());
            scriptRunner.setAutoCommit(true);
            scriptRunner.setStopOnError(true);
            scriptRunner.runScript(Resources.getResourceAsReader("mybatis/sql_script.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scriptRunner.closeConnection();
        }
    }
}
