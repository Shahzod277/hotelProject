package com.example.hotelproject.Controller;

import com.example.hotelproject.entity.Hotel;
import com.example.hotelproject.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping()
    public Page<Hotel> hotelList(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Hotel> all = hotelRepository.findAll(pageable);
        return all;
    }

    @PostMapping()
    public String add(@RequestBody String name) {
        boolean b = hotelRepository.existsByName(name);
        if (b) {
            return "already exits";
        }
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotelRepository.save(hotel);
        return "hotel added";
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody String name) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()) {
            Hotel hotel = byId.get();
            hotel.setName(name);
            return "edited hotel";
        }
        return "hotel doesn't found ";
    }

    @DeleteMapping()
    public  String delete(@PathVariable Integer id) {
        boolean b = hotelRepository.existsById(id);
        if (b) {
            hotelRepository.deleteById(id);
            return "hotel can delete";
        }
    return "hotel can not delete";
    }



}
