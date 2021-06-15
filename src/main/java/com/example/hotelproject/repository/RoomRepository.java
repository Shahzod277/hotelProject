package com.example.hotelproject.repository;

import com.example.hotelproject.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
    Page<Room> findAllByHotelId(Integer hotel_id, Pageable pageable);
        Optional<Room> findRoomByNumberAndHotelId(Integer number, Integer hotel_id);


}
