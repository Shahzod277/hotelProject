package com.example.hotelproject.Controller;

import com.example.hotelproject.Controller.payload.RoomDto;
import com.example.hotelproject.entity.Hotel;
import com.example.hotelproject.entity.Room;
import com.example.hotelproject.repository.HotelRepository;
import com.example.hotelproject.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping()
    public Page<Room> rooms(@RequestParam int page){
        Pageable pageable= PageRequest.of(page ,5);
      return  roomRepository.findAll(pageable);
    }
    @GetMapping("/{id}")
    public Page<Room> roomss(@PathVariable Integer id, @RequestParam int page){
        Pageable pageable=PageRequest.of(page,5);
        Page<Room> allById = roomRepository.findAllByHotelId(id, pageable);
        if (!allById.isEmpty()){
            return allById;
            }
                    return null;
    }
   @PostMapping( )
    public String add(@RequestBody RoomDto roomDto) {
       Optional<Room> roomByHotelId = roomRepository.findRoomByNumberAndHotelId(roomDto.getNumber(), roomDto.getHotelId());
       if (roomByHotelId.isPresent()) {
           return "already added";
       }
       Room room = new Room();
       room.setNumber(roomDto.getNumber());
       room.setFloor(roomDto.getFloor());
       room.setSize(roomDto.getSize());
       Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
       if (optionalHotel.isPresent()) {
           room.setHotel(optionalHotel.get());
           roomRepository.save(room);
       } else {
           return "can not find hotel ";
       }
       return "room added";
   }



}
