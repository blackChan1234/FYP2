package com.example.fyp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.ListPreference;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    public void toggleNightMode() {
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.main_preference, rootKey);

            // 找到夜间模式偏好设置项并设置监听器
            Preference nightModePref = findPreference("night_mode");
            if (nightModePref != null) {
                nightModePref.setOnPreferenceClickListener(preference -> {
                    // 切换夜间模式
                    int nightMode = AppCompatDelegate.getDefaultNightMode();
                    if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }
                    return true;
                });
            }

            // 找到语言偏好设置项并设置监听器
            Preference languagePref = findPreference("language_preference");
            if (languagePref != null) {
                languagePref.setOnPreferenceChangeListener((preference, newValue) -> {
                    String language = newValue.toString();
                    updateLanguage(language);
                    return true;
                });
            }
        }

        private void updateLanguage(String lang) {
            Locale locale = "zh".equals(lang) ? Locale.SIMPLIFIED_CHINESE : new Locale(lang);
            Locale.setDefault(locale);

            Configuration config = new Configuration();
            config.setLocale(locale);

            Resources res = getActivity().getResources();
            res.updateConfiguration(config, res.getDisplayMetrics());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                getActivity().createConfigurationContext(config);
            }

            refreshActivity();
        }

        private void refreshActivity() {
            Intent intent = getActivity().getIntent();
            getActivity().finish();
            startActivity(intent);
        }
    }
}
