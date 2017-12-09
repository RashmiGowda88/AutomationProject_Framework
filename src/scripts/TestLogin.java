package scripts;

import generic.BaseTest;
import generic.Lib;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pompages.LoginPage;


public class TestLogin extends BaseTest{
	 
	@Test
	public void testlogin() throws InterruptedException
	{
		LoginPage l=new LoginPage(driver);
		
		String un=Lib.getCellValue(EXCEL_PATH, "ValidLogin", 1, 0);
		String pw=Lib.getCellValue(EXCEL_PATH, "ValidLogin", 1, 1);
		String expectedTitle=Lib.getCellValue(EXCEL_PATH, "ValidLogin", 1, 2);
				
		l.setUsername(un);
		l.setPassword(pw);
		l.clickLogin();
		Thread.sleep(3000);
		
		String actualTitle=driver.getTitle();
		System.out.println(actualTitle);
		SoftAssert s=new SoftAssert();
		Thread.sleep(3000);
		s.assertEquals(actualTitle, expectedTitle);
		s.assertAll();
			
  }
}

