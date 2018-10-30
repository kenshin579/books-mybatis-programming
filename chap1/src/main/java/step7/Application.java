package step7;

import org.mybatis.domain.Shop;

import java.util.Map;

public class Application extends SqlMapper {
    public Shop view(Map<String, Object> parameters) throws Exception {
        return selectOne("select", parameters, "org.mybatis.domain.Shop");
    }
}
