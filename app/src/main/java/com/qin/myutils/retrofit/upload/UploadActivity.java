package com.qin.myutils.retrofit.upload;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.qin.myutils.R;
import com.qin.myutils.retrofit.common.RetrofitUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadActivity extends RxAppCompatActivity implements View.OnClickListener {
    private TextView tvMsg;
    private NumberProgressBar progressBar;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        tvMsg = (TextView) findViewById(R.id.tv_msg);
        findViewById(R.id.btn_simple).setOnClickListener(this);
        findViewById(R.id.btn_rx).setOnClickListener(this);
        findViewById(R.id.btn_rx_mu_down).setOnClickListener(this);
        findViewById(R.id.btn_rx_uploade).setOnClickListener(this);
        img = (ImageView) findViewById(R.id.img);
        progressBar = (NumberProgressBar) findViewById(R.id.number_progress_bar);
        verifyStoragePermissions(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_simple:
                break;
            case R.id.btn_rx:
                break;
            case R.id.btn_rx_uploade:
                uploadeDo();
                break;
            case R.id.btn_rx_mu_down:
               // Intent intent = new Intent(this, DownLaodActivity.class);
               // startActivity(intent);
                break;
        }
    }

    private void uploadeDo() {
        File file = new File("/storage/emulated/0/Download/11.jpg");
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file_name", file.getName(), new ProgressRequestBody
                (requestBody,
                        new UploadProgressListener() {
                            @Override
                            public void onProgress(final long currentBytesCount, final long totalBytesCount) {

                                /*回到主线程中，可通过timer等延迟或者循环避免快速刷新数据*/
                                Observable.just(currentBytesCount).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Long aLong) {
                                        tvMsg.setText("提示:上传中");
                                        progressBar.setMax((int) totalBytesCount);
                                        progressBar.setProgress((int) currentBytesCount);
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                            }
                        }));
        UploadApi uplaodApi = new UploadApi(httpOnNextListener, this);
        uplaodApi.setPart(part);
        RetrofitUtils.doUpload(uplaodApi, "https://www.izaodao.com/Api/");
    }


    /**
     * 上传回调
     */
    HttpOnNextListener httpOnNextListener = new HttpOnNextListener<UploadResulte>() {
        @Override
        public void onNext(UploadResulte o) {
            tvMsg.setText("成功");
            Glide.with(UploadActivity.this).load(o.getHeadImgUrl()).into(img);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            tvMsg.setText("失败：" + e.toString());
        }

    };




    private final int REQUEST_EXTERNAL_STORAGE = 1;
    private String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    /**
     * 申请权限
     *
     * @param activity
     */
    public void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
