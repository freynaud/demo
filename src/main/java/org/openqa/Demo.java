package org.openqa;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class Demo {

	private final String hubIp = "192.168.113.227";
	private final int demoWaitingTime = 10000;

	// private final String hubIp = "192.168.0.3";

	/**
	 * select chrome for a given environment using webdriver.
	 */
	@Test
	public void test() throws MalformedURLException, InterruptedException {
		WebDriver driver = null;
		try {
			DesiredCapabilities ff = DesiredCapabilities.firefox();
			ff.setPlatform(Platform.LINUX);
			driver = new RemoteWebDriver(new URL("http://" + hubIp + ":4444/grid/driver"), ff);
			driver.get("http://" + hubIp + ":4444/grid/console");
			Assert.assertEquals(driver.getTitle(), "Grid overview");
			Thread.sleep(demoWaitingTime);
		} finally {
			if (driver != null) {
				driver.quit();
			}

		}
	}

	/**
	 * select firefox on mac using selenium legacy protocol
	 */
	@Test(invocationCount = 2, threadPoolSize = 2)
	public void test2() throws MalformedURLException, InterruptedException {
		Selenium selenium = null;
		try {
			selenium = new DefaultSelenium(hubIp, 4444, "*firefox on mac", "http://" + hubIp + ":4444/grid/console");
			selenium.start();
			selenium.open("http://" + hubIp + ":4444/grid/console");
			selenium.windowMaximize();
			Assert.assertTrue("Grid overview".equals(selenium.getTitle()));
			Assert.assertEquals(selenium.getTitle(), "Grid overview");
			Thread.sleep(demoWaitingTime);
		} finally {
			if (selenium != null) {
				selenium.stop();
			}

		}
	}

	/**
	 * Start 6 firefox using webdriver, without specifying the OS
	 */
	@Test(invocationCount = 6, threadPoolSize = 6)
	public void test3() throws MalformedURLException, InterruptedException {
		WebDriver driver = null;
		try {
			DesiredCapabilities ff = DesiredCapabilities.firefox();
			driver = new RemoteWebDriver(new URL("http://" + hubIp + ":4444/grid/driver"), ff);
			driver.get("http://" + hubIp + ":4444/grid/console");
			Assert.assertEquals(driver.getTitle(), "Grid overview");
			Thread.sleep(demoWaitingTime);
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}
}
