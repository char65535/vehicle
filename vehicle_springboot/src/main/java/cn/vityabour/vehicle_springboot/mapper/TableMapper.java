package cn.vityabour.vehicle_springboot.mapper;

import cn.vityabour.vehicle_springboot.pojo.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableMapper {
    /**
     * 查询所有车辆信息
     * @return
     */
    List<Car> findALl();

    /**
     * 增加车辆信息
     * @param car
     */
    void insert(Car car);

    /**
     * 删除指定车辆信息
     * @param id
     */
    void delete(Integer id);

    /**
     * 更新指定车辆信息
     * @param car
     */
    void update(Car car);

    /**
     * 根据id查询指定车辆信息
     * @param id
     * @return
     */
    Car selectById(Integer id);
}
