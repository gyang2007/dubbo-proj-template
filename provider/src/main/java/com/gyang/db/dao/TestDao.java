package com.gyang.db.dao;

import org.springframework.stereotype.Repository;

@Repository(value = "testDao")
public interface TestDao {
    int select();
}
