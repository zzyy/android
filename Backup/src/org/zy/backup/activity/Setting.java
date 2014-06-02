package org.zy.backup.activity;

import org.zy.backup.R;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class Setting extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.settingpreference);
		CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference("checkbox");
		
		checkBoxPreference.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				// TODO Auto-generated method stub
				Toast.makeText(Setting.this, "changed", Toast.LENGTH_SHORT).show();
				return true;
			}
		});
	}
}
