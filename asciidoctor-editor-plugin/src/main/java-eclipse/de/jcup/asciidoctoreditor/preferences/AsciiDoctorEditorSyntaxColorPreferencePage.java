package de.jcup.asciidoctoreditor.preferences;
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

import static de.jcup.asciidoctoreditor.preferences.AsciiDoctorEditorSyntaxColorPreferenceConstants.*;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.jcup.asciidoctoreditor.AsciiDoctorEditorColorConstants;
import de.jcup.asciidoctoreditor.AsciiDoctorEditorUtil;
import de.jcup.asciidoctoreditor.EclipseDevelopmentSettings;

public class AsciiDoctorEditorSyntaxColorPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public AsciiDoctorEditorSyntaxColorPreferencePage() {
		setPreferenceStore(AsciiDoctorEditorUtil.getPreferences().getPreferenceStore());
	}
	
	@Override
	public void init(IWorkbench workbench) {
		
	}

	@Override
	protected void createFieldEditors() {
		Composite parent = getFieldEditorParent();
		Map<AsciiDoctorEditorSyntaxColorPreferenceConstants, ColorFieldEditor> editorMap = new HashMap<AsciiDoctorEditorSyntaxColorPreferenceConstants, ColorFieldEditor>();
		for (AsciiDoctorEditorSyntaxColorPreferenceConstants colorIdentifier: AsciiDoctorEditorSyntaxColorPreferenceConstants.values()){
			ColorFieldEditor editor = new ColorFieldEditor(colorIdentifier.getId(), colorIdentifier.getLabelText(), parent);
			editorMap.put(colorIdentifier, editor);
			addField(editor);
		}
		/*
		 * It seems #60 #63 does work well. But I had a problem with an older version of eclipse where the CSS parts did not work well. So if there are
		 * any users having the same problem I want to have the possibility to give them a fast workaround by setting system property on eclipse start
		 */
		if (EclipseDevelopmentSettings.OLD_STUFF_ENABLED_DARK_PREFERENCE_DEFAULTS){
			addOldStuffDarkThemeDefaultsButton(parent, editorMap);
		}
			
		
	}

	
	private void addOldStuffDarkThemeDefaultsButton(Composite parent,
			Map<AsciiDoctorEditorSyntaxColorPreferenceConstants, ColorFieldEditor> editorMap) {
		Button restoreDarkThemeColorsButton= new Button(parent,  SWT.PUSH);
		restoreDarkThemeColorsButton.setText("Restore Defaults for Dark Theme");
		restoreDarkThemeColorsButton.setToolTipText("Same as 'Restore Defaults' but for dark themes.\n Editor makes just a suggestion, you still have to apply or cancel the settings.");
		restoreDarkThemeColorsButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				/* editor colors */
				changeColor(editorMap, COLOR_NORMAL_TEXT, AsciiDoctorEditorColorConstants.GRAY_JAVA);
				changeColor(editorMap, COLOR_ASCIIDOCTOR_HEADLINES, AsciiDoctorEditorColorConstants.DARK_THEME_LIHT_RED);
				
				changeColor(editorMap, COLOR_COMMENT, AsciiDoctorEditorColorConstants.GREEN_JAVA);
				changeColor(editorMap, COLOR_INCLUDE_KEYWORD, AsciiDoctorEditorColorConstants.MIDDLE_BROWN);
				changeColor(editorMap, COLOR_ASCIIDOCTOR_COMMAND, AsciiDoctorEditorColorConstants.TASK_CYAN);
				changeColor(editorMap, COLOR_KNOWN_VARIABLES, AsciiDoctorEditorColorConstants.DARK_THEME_GRAY);
				changeColor(editorMap, COLOR_TEXT_BOLD, AsciiDoctorEditorColorConstants.BRIGHT_CYAN);
				changeColor(editorMap, COLOR_TEXT_BLOCKS, AsciiDoctorEditorColorConstants.DARK_THEME_CYAN);
				changeColor(editorMap, COLOR_HYPERLINK, AsciiDoctorEditorColorConstants.DARK_THEME_CYAN);
				changeColor(editorMap, COLOR_TEXT_ITALIC, AsciiDoctorEditorColorConstants.DARK_THEME_BRIGHT_CYAN);
				
			}

			private void changeColor(Map<AsciiDoctorEditorSyntaxColorPreferenceConstants, ColorFieldEditor> editorMap,
					AsciiDoctorEditorSyntaxColorPreferenceConstants colorId, RGB rgb) {
				editorMap.get(colorId).getColorSelector().setColorValue(rgb);
			}
			
		});
	}
	
}