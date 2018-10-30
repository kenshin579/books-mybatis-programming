package step5;

import org.mybatis.domain.Shop;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

public class Application extends SqlMapper {
    public Shop view(Map<String, Object> parameters) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Shop shop = null;

        try {
            // Map 타입 객체에 담긴 파라미터를 쿼리문의 매개 변수에 바인딩
            String sql = parameterBindingByMap("select", parameters);

            // 쿼리문 처리 객체 생성
            preparedStatement = connect().prepareStatement(sql);

            // 쿼리문 실행 및 결과 반환
            resultSet = preparedStatement.executeQuery();

            // 결과 처리
            if (resultSet.next()) {
                shop = new Shop();

                shop.setShopNo(resultSet.getInt("SHOP_NO"));
                shop.setShopName(resultSet.getString("SHOP_NAME"));
                shop.setShopLocation(resultSet.getString("SHOP_LOCATION"));
                shop.setShopStatus(resultSet.getString("SHOP_STATUS"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // 자원 및 데이터베이스 연결 해제
            release();
        }

        return shop;
    }
}
