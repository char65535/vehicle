package cn.vityabour.vehicle_springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private Integer id;
    private String brand;
    private String type;
    private Integer price;
    private String color;
    private String dataPurchase;
}
