package springbootueditor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springbootueditor.Result.Result;
import springbootueditor.entiity.Goods;
import springbootueditor.entiity.Job;
import springbootueditor.service.JobService;

@RestController
@RequestMapping("/job")
@CrossOrigin
public class JobController {
    @Autowired
    JobService jobService;

    /**
     * 保存
     * @param goods
     * @return
     */
//    @PostMapping("/save")
//    public Result goodssave(Goods goods){
//
//        return  service.add(goods);
//    }

    /**
     * 找到一个
     * @param id
     * @return
     */
//    @GetMapping("/findone")
//    public Result findOne(String id){
//
//        return  service.findById(Integer.parseInt(id));
//    }

    /**
     * 修改
     * @param entity
     * @return
     */
    @PutMapping("/save")
    public void update(Job entity){
        jobService.save(entity);
    }
}
