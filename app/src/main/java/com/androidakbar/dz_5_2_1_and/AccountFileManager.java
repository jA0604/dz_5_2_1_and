package com.androidakbar.dz_5_2_1_and;

import android.content.Context;
import android.os.Environment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AccountFileManager {
    private String filename;

    public AccountFileManager(String filename) {
        this.filename = filename;
    }

    public void SaveToFile(Context context, Account account) {
        FileOutputStream fos = null;
        String s = account.getLogin().toString() + context.getResources().getString(R.string.split_symbol) + account.getPassword().toString() + "\n";
        try {
            fos = context.openFileOutput(filename, Context.MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(s);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public  boolean ReadFromFile(Context context, Account account) {
        FileInputStream fis = null;
        boolean result = false;
        try {
            fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] acc = line.split(context.getResources().getString(R.string.split_symbol));
                if (acc[0].equals(account.getLogin()) && acc[1].equals(account.getPassword())) {
                    result = true;
                    break;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}
