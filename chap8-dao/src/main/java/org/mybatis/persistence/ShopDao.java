package org.mybatis.persistence;

import org.mybatis.domain.Shop;

import java.util.List;

public interface ShopDao {
    public List<Shop> list(Shop shop);

    public void insert(Shop shop);

    public Shop select(String shopNo);

    public void update(Shop shop);

    public void delete(String shopNo);
}
