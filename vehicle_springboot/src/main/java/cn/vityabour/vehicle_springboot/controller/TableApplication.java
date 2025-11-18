package cn.vityabour.vehicle_springboot.controller;

import cn.vityabour.vehicle_springboot.pojo.Car;
import cn.vityabour.vehicle_springboot.pojo.Result;
import cn.vityabour.vehicle_springboot.service.TableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/vehicle")
@CrossOrigin
public class TableApplication {

    @Autowired
    private TableService tableService;

    /**
     * 增加车辆信息
     */
    @PostMapping
    public Result add(@RequestBody Car car) {
        log.info("新增的车辆：{}", car);
        tableService.add(car);
        return Result.success();
    }

    /**
     * 删除指定车辆信息
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除车辆id：{}", id);
        tableService.delete(id);
        return Result.success();
    }

    /**
     * 查询所有车辆信息
     */
    @GetMapping
    public Result list() {
        List<Car> list = tableService.list();
        log.info("返回的列表：{}", list);
        return Result.success(list);
    }

    /**
     * 查询指定车辆信息
     */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        log.info("获取id为：", id);
        Car car = tableService.selectById(id);
        log.info("car:{}", car);
        return Result.success(car);
    }

    /**
     * 更新车辆信息
     */
    @PutMapping
    public Result update(@RequestBody Car car) {
        log.info("更新信息：{}", car);
        tableService.update(car);
        return Result.succes   s();
    }
}
