package com.xriamer.store.service.back;

import com.xriamer.store.vo.Item;

import java.util.List;
import java.util.Set;

public interface IItemServiceBack {
    public boolean insert(Item it) throws Exception;

    public boolean update(Item it) throws Exception;

    public boolean delete(Set<Integer> ids) throws Exception;

    public List<Item> list() throws Exception;
}
