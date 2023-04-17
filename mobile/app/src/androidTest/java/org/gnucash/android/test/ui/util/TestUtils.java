package org.gnucash.android.test.ui.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.kobakei.ratethisapp.RateThisApp;

import org.gnucash.android.R;
import org.gnucash.android.ui.account.AccountsActivity;
import org.gnucash.android.ui.settings.PreferenceActivity;

public class TestUtils {

    /**
     * Prevents the first-run dialogs (Whats new, Create accounts etc) from being displayed when testing
     * @param context Application context
     */
    public static void preventFirstRunDialogs(Context context) {
        AccountsActivity.rateAppConfig = new RateThisApp.Config(10000, 10000);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        //do not show first run dialog
        editor.putBoolean(context.getString(R.string.key_first_run), false);
        editor.putInt(AccountsActivity.LAST_OPEN_TAB_INDEX, AccountsActivity.INDEX_TOP_LEVEL_ACCOUNTS_FRAGMENT);

        //do not show "What's new" dialog
        String minorVersion = context.getString(R.string.app_minor_version);
        int currentMinor = Integer.parseInt(minorVersion);
        editor.putInt(context.getString(R.string.key_previous_minor_version), currentMinor);
        editor.commit();


    }

    public static void setDoubleEntryEnabled(Context context, boolean enabled){
        SharedPreferences prefs = PreferenceActivity.getActiveBookSharedPreferences();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(context.getString(R.string.key_use_double_entry), enabled);
        editor.apply();
    }

    /**
     * Sleep the thread for a specified period
     * @param millis Duration to sleep in milliseconds
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
