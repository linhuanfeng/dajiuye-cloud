package springbootueditor.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import springbootueditor.entiity.Goods;
import springbootueditor.entiity.Job;

@Mapper
public interface Jobdao extends BaseMapper<Job> {

}
