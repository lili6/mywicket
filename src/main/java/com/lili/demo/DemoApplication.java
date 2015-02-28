package com.lili.demo;

import com.lili.WicketExampleApplication;
import org.apache.wicket.Page;

/**
 * Created by liguofang on 2015/2/12.
 */
public class DemoApplication extends WicketExampleApplication {
	@Override
	public Class<? extends Page> getHomePage() {
		return DemoPage.class;
				}
}
