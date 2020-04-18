package com.xriamer.store.service.front;

import com.xriamer.store.vo.Books;

import java.util.List;
import java.util.Set;

public interface IShopcarServiceFront {
    public List<Books> listCar(Set<Integer> ids) throws Exception;
}
