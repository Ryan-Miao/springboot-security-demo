package com.test.springsecuritydemo.domain.dao;

import com.test.springsecuritydemo.domain.entity.RoomTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IRoomRepository extends JpaRepository<RoomTable, Integer>,
    JpaSpecificationExecutor<RoomTable> {

}
