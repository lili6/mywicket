/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lili.frames;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Test page for the right frame.
 * 
 * @author Eelco Hillenius
 */
public class Page1 extends WebPage
{
	/**
	 * Constructor
	 */
	public Page1()
	{
		add(new InputForm("inputForm"));
	}
	/** Relevant locales wrapped in a list. */
	public static final List<Locale> LOCALES = Arrays.asList(Locale.ENGLISH,
			new Locale("nl", "NL"), Locale.GERMAN, Locale.SIMPLIFIED_CHINESE, Locale.JAPANESE,
			new Locale("pt", "BR"), new Locale("fa", "IR"), new Locale("da", "DK"),
			new Locale("th", "TH"), new Locale("ru"), new Locale("ko", "KR"));

	private class InputForm extends Form<FormInputModel> {
		/**
		 * Construct.
		 *
		 * @param name
		 *            Component name
		 */
		@SuppressWarnings("serial")
		public InputForm(String name) {
			super(name, new CompoundPropertyModel<FormInputModel>(new FormInputModel()));

			// Dropdown for selecting locale
			add(new LocaleDropDownChoice("localeSelect"));

			// Link to return to default locale
//			add(new Link<Void>("defaultLocaleLink")
			add(new TextField<String>("test1").setRequired(true).setLabel(
					new Model<String>("String")));
			add(new TextField<String>("test2").setRequired(true).setLabel(
					new Model<String>("String")));
		}
	}

	/**
	 * Dropdown with Locales.
	 */
	private final class LocaleDropDownChoice extends DropDownChoice<Locale>
	{
		/**
		 * Construct.
		 *
		 * @param id
		 *            component id
		 */
		public LocaleDropDownChoice(String id)
		{
			super(id, LOCALES, new LocaleChoiceRenderer());

			// set the model that gets the current locale, and that is used for
			// updating the current locale to property 'locale' of FormInput
			setModel(new PropertyModel<Locale>(Page1.this, "locale"));
		}

		/**
		 * @see org.apache.wicket.markup.html.form.DropDownChoice#onSelectionChanged(java.lang.Object)
		 */
		@Override
		public void onSelectionChanged(Locale newSelection)
		{
			// note that we don't have to do anything here, as our property
			// model allready calls FormInput.setLocale when the model is
			// updated

			// force re-render by setting the page to render to the bookmarkable
			// instance, so that the page will be rendered from scratch,
			// re-evaluating the input patterns etc
			setResponsePage(Page1.class);
		}

		/**
		 * @see org.apache.wicket.markup.html.form.DropDownChoice#wantOnSelectionChangedNotifications()
		 */
		@Override
		protected boolean wantOnSelectionChangedNotifications()
		{
			// we want roundtrips when a the user selects another item
			return true;
		}
	}
	/* Choice for a locale.
		*/
	private final class LocaleChoiceRenderer extends ChoiceRenderer<Locale>
	{
		/**
		 * Constructor.
		 */
		public LocaleChoiceRenderer()
		{
		}

		/**
		 * @see org.apache.wicket.markup.html.form.IChoiceRenderer#getDisplayValue(Object)
		 */
		@Override
		public Object getDisplayValue(Locale locale)
		{
			return locale.getDisplayName(getLocale());
		}
	}

	/**
	 * Sets locale for the user's session (getLocale() is inherited from Component)
	 *
	 * @param locale
	 *            The new locale
	 */
	public void setLocale(Locale locale)
	{
		if (locale != null)
		{
			getSession().setLocale(locale);
		}
	}
}