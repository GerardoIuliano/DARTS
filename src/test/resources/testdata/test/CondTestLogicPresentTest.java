package test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CondTestLogicPresentTest {

    @Test
    public void test1() {
        if(1==1)
            Assert.assertEquals(1,1);
        else
            Assert.assertEquals(2,2 );
        if(1==1)
            Assert.assertEquals(1,1);
        else
            Assert.assertEquals(2,2 );

    }

    @Test
    public void test2() {
        for(int i=0;i<1;i++){
            Assert.assertEquals(1,1);
        }
    }

    @Test
    public void test3() {
        switch (1){
            case 1: Assert.assertEquals(1,1);
        }
    }
}