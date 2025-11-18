package cn.vityabour.vehicle_springboot.service;

import cn.vityabour.vehicle_springboot.pojo.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TableService {
    /**
     * 获取列表信息
     *
     * @return
     */
    List<Car> list();

    /**
     * 增加车辆信息
     *
     * @param car
     */
    void add(Car car);

    /**
     * 删除指定车辆信息
     */
    void delete(Integer id);

    /**
     * 更新指定车辆信息
     *
     * @param car
     */
    void update(Car car);

    /**
     * 根据id查询指定车辆信息
     *
     * @param id
     * @return
     */
    Car selectById(Integer id);
}
