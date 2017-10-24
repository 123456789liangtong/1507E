package com.example.newsinfo.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newsinfo.R;
import com.example.newsinfo.utils.Hint;

import java.io.File;
import java.math.BigDecimal;

import androidkun.com.versionupdatelibrary.entity.VersionUpdateConfig;


public class SettingActivity extends Hint implements View.OnClickListener{
    private LinearLayout clear;
    private TextView dangqianhuancun;
    private ImageView sz_fanhui;
    private LinearLayout gengxin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        clear = (LinearLayout) findViewById(R.id.clear);
        dangqianhuancun = (TextView) findViewById(R.id.dangqianhuancun);
        sz_fanhui = (ImageView) findViewById(R.id.sz_fanhui);
        sz_fanhui.setOnClickListener(this);
        gengxin = (LinearLayout) findViewById(R.id.gengxin);

        //计算当前缓存
        try {
            String size = getTotalCacheSize();
            dangqianhuancun.setText("当前缓存"+size);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //清理缓存
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllCache(SettingActivity.this);
                String size = null;
                try {
                    size = getTotalCacheSize();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dangqianhuancun.setText("当前缓存:"+size);
            }
        });

        //版本更新
        gengxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub

                new AlertDialog.Builder(SettingActivity.this).setTitle("系统提示")//设置对话框标题

                        .setMessage("请确认所有数据都保存后再推出系统！")//设置显示的内容

                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮

                            @Override

                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                // TODO Auto-generated method stub

                                VersionUpdateConfig.getInstance()//获取配置实例
                                        .setContext(SettingActivity.this)//设置上下文
                                        .setDownLoadURL("http://gdown.baidu.com/data/wisegame/65f486476dcc3567/jinritoutiao_634.apk")//设置文件下载链接
                                        .setNotificationTitle("版本升级Demo")//设置通知标题
                                        .startDownLoad();//开始下载
                            }

                        }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮

                    @Override

                    public void onClick(DialogInterface dialog, int which) {//响应事件

                        // TODO Auto-generated method stub

                        Log.i("alertdialog"," 请保存数据！");

                    }

                }).show();//在按键响应事件中显示此对话框

            }
        });

    }

    /**
     * 计算app的缓存大小其实计算的是 getCacheDir()这个file和getExternalCacheDir()这个file大小的和
     */
    public String getTotalCacheSize() throws Exception {
        long cacheSize = getFolderSize(this.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(this.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }
    /**
     * 计算文件夹的大小
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 清理app的缓存 其实是清除的getCacheDir 和getExternalCacheDir这两个文件
     *
     * @param context
     */
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }
    /**
     * 格式化得到的总大小 默认是byte,  然后根据传入的大小,自动转化成合适的大小单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

    /**
     * 删除一个文件夹
     *
     * @param dir
     * @return
     */
    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }


    @Override
    public void onClick(View view) {
        finish();
    }
}
