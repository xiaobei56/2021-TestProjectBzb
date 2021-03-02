package cn.xiaobei56.testprojectbzb;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static String getMd5Hash(String path) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = path.getBytes();
            messageDigest.update(bytes);
            return new BigInteger(1, messageDigest.digest()).toString(32);
        } catch (Exception e) {
            throw new RuntimeException("Could not hash input string.", e);
        }

    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("cn.xiaobei56.testprojectbzb", appContext.getPackageName());
        String path = "https://services.gradle.org/distributions/gradle-6.8.3-all.zip";
        System.out.println("-bzb-" + getMd5Hash(path));
        File f = appContext.getExternalFilesDir(null);
        System.out.println("-bzb- " + f.toString());

        System.out.println("-bzb-" + appContext.getExternalFilesDir(null) + "/xuexin/apks/");
    }

////        Gradle 版本代码  ///
//        import java.security.MessageDigest
//
//        def distribution = 'https://services.gradle.org/distributions/gradle-2.8-all.zip'
//
//        task getWrapperDiskCacheName << {
//            MessageDigest messageDigest = MessageDigest.getInstance('MD5')
//            messageDigest.update(distribution.bytes)
//            name = new BigInteger(1, messageDigest.digest()).toString(32)
//            println("source: $distribution")
//            println("wrapper disk cache name : $name")
//
//        }
}