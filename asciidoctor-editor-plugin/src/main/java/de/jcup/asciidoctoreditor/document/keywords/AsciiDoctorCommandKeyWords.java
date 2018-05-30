/*
 * Copyright 2017 Albert Tregnaghi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 */
package de.jcup.asciidoctoreditor.document.keywords;

public enum AsciiDoctorCommandKeyWords implements StartLineAndHavingDoubleColonsDocumentKeyword {


	IFDEF("https://asciidoctor.org/docs/user-manual/#ifdef-directive"),
	IFNDEF("https://asciidoctor.org/docs/user-manual/#ifndef-directive"),
	ENDIF("https://asciidoctor.org/docs/user-manual/#ifdef-directive"),
	
	IMAGE("https://asciidoctor.org/docs/user-manual/#images"),
	;

	private String text;
	private String tooltip;
	private String linkToDocumentation;

	private AsciiDoctorCommandKeyWords() {
		this(null);
	}

	private AsciiDoctorCommandKeyWords(String linkToDocumentation) {
		this.text = name().toLowerCase()+"::";
		this.tooltip = TooltipTextSupport.getTooltipText(name().toLowerCase());
		this.linkToDocumentation = linkToDocumentation;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public boolean isBreakingOnEof() {
		return true;
	}


	@Override
	public String getTooltip() {
		return tooltip;
	}

	@Override
	public String getLinkToDocumentation() {
		return linkToDocumentation;
	}

}
