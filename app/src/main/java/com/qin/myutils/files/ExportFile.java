package com.qin.myutils.files;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ExportFile {

    public void export(Context context, File file) {
        InputStream mIs = null;
        OutputStream mOs = null;
        try {
            mIs = new FileInputStream(file);
            mOs = new FileOutputStream(context.getCacheDir().getAbsoluteFile() + "/" + file.getName());
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = mIs.read(bytes)) != -1) {
                mOs.write(bytes, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (mIs != null)
                    mIs.close();
                if (mOs != null)
                    mOs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
