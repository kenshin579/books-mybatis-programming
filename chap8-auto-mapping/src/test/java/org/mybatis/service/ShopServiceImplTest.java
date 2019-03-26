package org.mybatis.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.domain.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/spring/context-*.xml" })
@WebAppConfiguration
@Transactional
public class ShopServiceImplTest {

	@Autowired
	ShopService shopService;

	@Before
	public void setUp() throws Exception {

	}

	/**
	 * rollback이 잘되는 것을 확인함
	 * todo : 저장하고 삽입 데이터 확인하는 부분이 잘 안됨 (Db에서 바로 rollback이 되는 건가?)
	 */
	@Test
	public void add() {
		Shop shop = new Shop();
		shop.setShopName("Frank's Shop");
		shopService.add(shop);

		//db에 저장되
	}
}