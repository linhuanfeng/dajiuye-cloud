package com.lhf.dajiuye.order;

//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;

import com.lhf.dajiuye.order.utils.SnowflakeIdGenerator;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DajiuyeOrderApplicationTests {

    @Test
    public void TestSnowSingleTon() {
        System.out.println(SnowflakeIdGenerator.getInstance()==SnowflakeIdGenerator.getInstance());
        SnowflakeIdGenerator instance = SnowflakeIdGenerator.getInstance();
        System.out.println(instance.nextId());
    }

}
