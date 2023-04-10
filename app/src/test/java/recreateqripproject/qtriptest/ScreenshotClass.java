package recreateqripproject.qtriptest;

import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotClass {
    DriverSingleton driverSingleton=DriverSingleton.getinstanceOfDriverSingletonClass();
    WebDriver driver=driverSingleton.getDriver();
    
    public String ScreenShot(WebDriver driver, String imageType, String string2) throws IOException {
        // String screenshotLocation = "src/test/java/qtriptest/capturedScreenShots/src/test/java/qtriptest/capturedScreenShots/";
        // File dir=new File(screenshotLocation);
        // if(!dir.exists()){
        //     dir.mkdir();
        // }
        // String time= String.valueOf(LocalDateTime.now());
        // String filename=screenshotLocation+String.format(string2+"_"+time) + "."+imageType;

        
        // TakesScreenshot  srcShot= ((TakesScreenshot)driver);
        // File getSrcShot= srcShot.getScreenshotAs(OutputType.FILE);
        // File destnation= new File(screenshotLocation+filename);
        // FileUtils.copyFile(getSrcShot, destnation);
        // return filename;
        try {
            File theDir = new File("/screenshots");
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            String timestamp = String.valueOf(java.time.LocalDateTime.now());
            String fileName = String.format("screenshot_%s_%s_%s.png", timestamp, imageType, string2);
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("screenshots/" + fileName);
            FileUtils.copyFile(SrcFile, DestFile);
            return DestFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

