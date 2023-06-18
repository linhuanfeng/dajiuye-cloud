package springbootueditor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import springbootueditor.dao.Jobdao;
import springbootueditor.entiity.Job;
import springbootueditor.service.JobService;

@Service
public class JobServiceImpl extends ServiceImpl<Jobdao, Job> implements JobService {
}
