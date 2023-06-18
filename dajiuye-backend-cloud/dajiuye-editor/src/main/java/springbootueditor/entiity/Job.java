package springbootueditor.entiity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@ToString
@TableName("job")
public class Job implements Serializable {
    String jobId= UUID.randomUUID().toString().replace("-","");
    String jobSalary;
    String jobName;
    String jobPlace;
    String jobEdu;
    String jobAge;
    String jobDayPerWeek;
    String jobImg;
    String jobComId;
    String jobSid;
    Date jobUpdateTime;
    Date jobReleaseTime;
    String email;
    Integer jobType;
    String jobAuthorId;
    String jobDetails;
    String jobStat;
    String jobViewCnt;
    String jobPriority;
    String jobIndustry;
    String jobTimeSpan;
    String jobDeadLine;
    String jobWelfare;
    @TableField(exist = false)
    Company company;
}
