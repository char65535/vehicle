package cn.vityabour.vehicle_springboot.service.impl;

import cn.vityabour.vehicle_springboot.mapper.TableMapper;
import cn.vityabour.vehicle_springboot.pojo.Car;
import cn.vityabour.vehicle_springboot.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableMapper tableMapper;

    /**
     * 获取所有车辆信息
     *
     * @return
     */
    @Override
    public List<Car> list() {
        return tableMapper.findALl();
    }

    /**
     * 增加车辆信息
     *
     * @param car
     */
    @Override
    public void add(Car car) {
        tableMapper.insert(car);
    }

    /**
     * 删除指定车辆信息
     */
    @Override
    public void delete(Integer id) {
        tableMapper.delete(id);
    }

    /**
     * 更新指定车辆信息
     * @param car
     */
    @Override
    public void update(Car car) {
        tableMapper.update(car);
    }

    /**
     * 根据id查询指定车辆信息
     * @param id
     * @return
     */
    @Override
    public Car selectById(Integer id) {
        return tableMapper.selectById(id);
    }

}
