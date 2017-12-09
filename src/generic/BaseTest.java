package generic;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest implements IAutoConstant
{
	public static WebDriver driver;

	static 
	{
		System.setProperty(GECKO_KEY,GECKO_VALUE);
		System.setProperty(CHROME_KEY,CHROME_VALUE);
	}

	@BeforeMethod
	public void openApplication()
	{
		driver=new FirefoxDriver();
		String url= Lib.getProperty(CONFIG_PATH, "URL");
		driver.get(url);

		String ITO = Lib.getProperty(CONFIG_PATH, "ImplicitTimeOut");
		//Converting to int as the above was in string format
		int timeoutPeriod = Integer.parseInt(ITO);
        System.out.println(timeoutPeriod);

		driver.manage().timeouts().implicitlyWait(timeoutPeriod, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void closeApplication()
	{
		//driver.close();
	}
	
	public void takeScreenshot(String testname)
	{
	 //To avoid overriding of files we creaTE timestamp
		
		Date d=new Date();
		String currentdate = d.toString().replaceAll(":", "_");
		//Taking screnshot
		TakesScreenshot ts=(TakesScreenshot)driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File(".\\Screenshots\\"+currentdate+"\\"+testname+"_screenshot.png");
		try
		{
			FileUtils.copyFile(srcFile, destFile);
		}catch(IOException e)
		{
			
		}
		
	}

}


