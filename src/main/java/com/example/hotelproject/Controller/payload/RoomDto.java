package com.example.hotelproject.Controller.payload;

import lombok.Data;

@Data
public class RoomDto {
    private Integer number;
    private Integer floor;
    private Double size;
    private Integer hotelId;
}
