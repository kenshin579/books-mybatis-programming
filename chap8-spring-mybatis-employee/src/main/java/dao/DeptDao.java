package dao;

import model.Dept;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeptDao {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
//    SqlSessionFactory를 직접 만들게 아니라 컨테이너에 등록되어 있기때문에 날려버림

    public List<Dept> selectAll() {
        SqlSession session = sqlSessionFactory.openSession();
//        sqlSessionFactory는 지가 쓸꺼 다 쓰고나서 session.close()하면서 소멸
        try {
//            여기로 가서 처리해라
            return session.selectList("dao.deptMapper.selectAll");
        } finally {
            session.close();
        }
    }

    public void insertDept(Dept dept) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            int result = session.insert("dao.deptMapper.insertDept", dept);
        } finally {
            session.close();
        }
    }

    public void updateDept(Dept dept) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("dao.deptMapper.updateDept", dept);
        } finally {
            session.close();
        }
    }

    public void deleteDept(int deptno) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("dao.daptMapper.updateDept", deptno);
        } finally {
            session.close();
        }
    }
}
