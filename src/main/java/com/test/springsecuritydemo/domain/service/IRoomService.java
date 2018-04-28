package com.test.springsecuritydemo.domain.service;

import com.test.springsecuritydemo.domain.entity.RoomTable;
import com.test.springsecuritydemo.domain.valueobject.request.RoomRequest;
import com.test.springsecuritydemo.domain.valueobject.response.BaseResponse;
import java.util.List;

public interface IRoomService {

    BaseResponse<RoomTable> findOne(Integer id);

    BaseResponse<Integer> saveRoom(RoomRequest roomRequest);

    BaseResponse<List<RoomTable>> findList();

    BaseResponse<Boolean> updateRoom(RoomRequest roomRequest);

    BaseResponse<Boolean> delete(Integer id);
}
