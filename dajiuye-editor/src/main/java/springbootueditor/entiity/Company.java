package springbootueditor.entiity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("company")
public class Company {
    String comId;
    String comFullName;
    String comAuthCapital;
    String comIndustry;
    String comMail;
    String comAddr;
    String comWebsite;
    String comIntro;
    String comMinName;
    String comScale;
    String comLicense;
    String comLogo;
    String comClass;
    String IncorporationDate;
    String comWelfare;
    String comLinks;
}