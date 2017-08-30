package com.functional.pageobjects;

import com.util.page.PageLoader;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

public abstract class Page {
	protected WebDriver driver;

	protected PageLoader page;

	public Page(WebDriver driver) {
		this.driver = driver;
		page = new PageLoader(driver);
	}

 	public boolean isDisplayed() {
 		return true;
	}

	public String getRelativeUrl() {
 		return StringUtils.EMPTY;
	}
}
