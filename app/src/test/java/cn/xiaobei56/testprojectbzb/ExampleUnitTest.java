package cn.xiaobei56.testprojectbzb;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
//        Context appContext = InstrumentationRegistry.getInstrumentation().targetContext;
//        assertEquals("cn.xiaobei56.myapplication", appContext.packageName);

       if( isNumber("10.2")){
           System.out.println(true);
       }else {
           System.out.println("false");
       }
    }
    //金额验证
    public static boolean isNumber(String str){

            Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){4})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(str);
        return match.matches();
    }

    /**
     * 获取 gradle 的sub file 的名字
     *
     */
//    test path https://services.gradle.org/distributions/gradle-6.8.3-all.zip


}