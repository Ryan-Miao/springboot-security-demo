package com.test.springsecuritydemo.domain.service.impl;

import com.google.common.collect.ImmutableMap;
import com.test.springsecuritydemo.domain.dao.IRoomRepository;
import com.test.springsecuritydemo.domain.entity.RoomTable;
import com.test.springsecuritydemo.domain.log.SystemEvent;
import com.test.springsecuritydemo.domain.service.IRoomService;
import com.test.springsecuritydemo.domain.valueobject.request.RoomRequest;
import com.test.springsecuritydemo.domain.valueobject.response.BaseResponse;
import com.test.springsecuritydemo.domain.valueobject.response.ErrorMsg;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService implements IRoomService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    private final IRoomRepository roomRepository;

    public RoomService(IRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public BaseResponse<RoomTable> findOne(Integer id) {
        try {
            final RoomTable one = roomRepository.findOne(id);
            if (one == null) {
                ErrorMsg errorMsg = new ErrorMsg(SystemEvent.FIND_ONE_ROOM_NOT_EXIST.getId(),
                    SystemEvent.FIND_ONE_ROOM_NOT_EXIST.getDetail());
                return new BaseResponse<>(errorMsg);
            }

            return new BaseResponse<>(one);
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("event", SystemEvent.FIND_ONE_ROOM_FAILED);
            map.put("errorMsg", e.getMessage());
            map.put("data", ImmutableMap.of("id", id));

            LOGGER.error(map.toString(), e);

            return new BaseResponse<>(
                new ErrorMsg(SystemEvent.FIND_ONE_ROOM_FAILED.getId(), e.getMessage()));
        }

    }

    @Override
    public BaseResponse<Integer> saveRoom(RoomRequest roomRequest) {
        return handle(
            () -> roomRepository.save(
                new RoomTable(roomRequest.getName(), roomRequest.getComment(), new Date(),
                    new Date())).getId(),
            SystemEvent.SAVE_ONE_ROOM_FAILED,
            ImmutableMap.of("roomRequest", roomRequest));

    }

    @Override
    public BaseResponse<List<RoomTable>> findList() {
        return handle(roomRepository::findAll,
            SystemEvent.FIND_ALL_ROOMS_FAILED);
    }

    @Override
    public BaseResponse<Boolean> updateRoom(RoomRequest roomRequest) {
        final Supplier<Boolean> result = () -> {
            roomRepository.save(
                new RoomTable(roomRequest.getId(), roomRequest.getName(), roomRequest.getComment(),
                    new Date()));
            return true;
        };

        return handle(result,
            SystemEvent.UPDATE_ONE_ROOM_FAILED,
            ImmutableMap.of("roomRequest", roomRequest));
    }

    @Override
    public BaseResponse<Boolean> delete(Integer id) {
        return handle(() -> {
            roomRepository.delete(id);
            return true;
        }, SystemEvent.SAVE_ONE_ROOM_FAILED, ImmutableMap.of("id", id));
    }


    private <T> BaseResponse<T> handle(Supplier<T> result, SystemEvent event) {
        return handle(result, event, null);
    }

    private <T> BaseResponse<T> handle(Supplier<T> result, SystemEvent event,
        Map<String, Object> logData) {
        try {
            T t = result.get();
            return new BaseResponse<>(t);
        } catch (Exception e) {
            final Map<String, Object> map = new HashMap<>();
            map.put("event", event);
            map.put("errorMsg", e.getMessage());
            map.putAll(logData);
            LOGGER.error(map.toString(), e);

            return new BaseResponse<>(new ErrorMsg(event.getId(), e.getMessage()));
        }
    }
}
