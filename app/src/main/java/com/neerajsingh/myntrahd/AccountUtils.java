package com.neerajsingh.myntrahd;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.provider.Settings;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by neeraj.singh on 17/04/16.
 */
public class AccountUtils {
    public static String getEmail(Context context) {
        Pattern email = Patterns.EMAIL_ADDRESS;
        Account[] accounts = AccountManager.get(context).getAccounts();
        for (Account account : accounts) {
            if (email.matcher(account.name).matches()) {
                String possibleEmail = account.name;
                return possibleEmail;
            }
        }
        return null;
    }

    public static final String getAccontId(Context context){
        String uid = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return uid;

    }
}
