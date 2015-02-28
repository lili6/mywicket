package com.lili.demo;

import com.lili.WicketExamplePage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;

/**
 * Created by liguofang on 2015/2/12.
 */
public class DemoPage extends WicketExamplePage {

	public DemoPage(){
		Form form = new Form("form"){
			protected void onSubmit() {
				info("Form.onSubmit 被执行了。。");
			}
		};
		Button button1 = new Button("button1") {
			public void onSubmit() {
				info("按钮1提交执行~~");
			}
		};
		form.add(button1);
		Button button2 = new Button("button2") {
			public void onSubmit() {
				info("按钮2提交执行~~");
			}
		};
		button2.setDefaultFormProcessing(false);
		form.add(button2);
		add(form);



	}

}
