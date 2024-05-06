package com.wbw1537;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
@Component
public class EnumTest {
    enum Color {
        RED, GREEN, BLUE;
        private Color() {
            System.out.println("111");
        }
    }

    @Test
    public void test(){
        System.out.println(Color.RED);
    }

}
