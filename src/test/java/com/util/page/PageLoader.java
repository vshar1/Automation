package com.util.page;

import com.functional.pageobjects.Page;
import org.openqa.selenium.WebDriver;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class PageLoader {
	public static final String BASE_APP_URL_KEY = "base.app.url";
	public static String baseApplicationUrl = validateApplicationUrl(ResourceLoader.getProperty(BASE_APP_URL_KEY));
	private WebDriver webDriver;

	public PageLoader(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void load(String relativeUrl) {
 		String url = baseApplicationUrl + relativeUrl;
		System.out.printf("Going to url: {%s} %n", url);
		webDriver.get(url);
	}

	public <PageObject extends Page> PageObject load(Class<PageObject> pageObjectClass) {
  		PageObject page = instantiatePage(webDriver, pageObjectClass);
		if (isNotEmpty(page.getRelativeUrl())) {
			load(page.getRelativeUrl());
		}
		return page;
	}

	public <PageObject extends Page> PageObject init(Class<PageObject> pageObjectClass) {
 		return instantiatePage(webDriver, pageObjectClass);
	}

	public void reload() {
 		webDriver.navigate().refresh();
	}

	private static <T> T instantiatePage(WebDriver driver, Class<T> pageClassToProxy) {
 		try {
			try {
				Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
				return constructor.newInstance(driver);
			} catch (NoSuchMethodException e) {
				return pageClassToProxy.newInstance();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String validateApplicationUrl(String applicationUrl) {
		if (isNotBlank(applicationUrl)) {
			try {
				new URL(applicationUrl);
			} catch (MalformedURLException e) {
				throw new RuntimeException("application.url had a malformed URL: " + applicationUrl);
			}
		} else {
			throw new RuntimeException("application.url is not available.");
		}

		return applicationUrl;

	}
}
