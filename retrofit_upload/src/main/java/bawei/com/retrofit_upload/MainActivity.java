package bawei.com.retrofit_upload;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import bawei.com.retrofit_upload.inter.RetrofitService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn01;
    private Button btn02;
    private Button btn03;
    private String path=Environment.getExternalStorageDirectory().getPath()+"/meitong.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn01=(Button)findViewById(R.id.btn01);
        btn02=(Button)findViewById(R.id.btn02);
        btn03=(Button)findViewById(R.id.btn03);

        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
    }

    private void uploadPic2(File file) {
        //得到retrofit对象
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();

        //得到网络接口 通过动态代理的方式获得
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        //得到类型  把File 封装到请求体中
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"),file);

        //创建表单数据
        MultipartBody.Part body = MultipartBody.Part.createFormData("file",file.getName(),requestBody);

        Call<ResponseBody> userCall = retrofitService.uploadPic(body);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s = response.body().string();
                    Log.e("SSSS",s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this,"上传图片成功",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn01:
                File file = new File(Environment.getExternalStorageDirectory(),"/amg.jpg");
                uploadPic2(file);
                break;
            case R.id.btn02:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(intent,2000) ;
                break;
            case R.id.btn03:
                Intent it=new Intent(Intent.ACTION_PICK);
                it.setType("image/*");
                startActivityForResult(it, 4000);
                break;
        }
    }

    private void crop(Uri uri) {
        //调取裁剪
        Intent it=new Intent("com.android.camera.action.CROP");
        //获取图片
        it.setDataAndType(uri,"image/*");
        //是否进行裁剪
        it.putExtra("crop", true);
        //设置宽高比
        it.putExtra("aspaceX", 1);
        it.putExtra("aspaceY", 1);
        //设置大小
        it.putExtra("outputX", 250);
        it.putExtra("outputY", 250);
        //是否支持人脸识别
        it.putExtra("onFaceDetection", false);
        it.putExtra("return-data", true);

        startActivityForResult(it,3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //拍照裁剪
        if(requestCode==2000){
            File file = new File(path);
            crop(Uri.fromFile(file));
        }
        if(requestCode==3000){
            if(data!=null){
                Bitmap bitmap=data.getParcelableExtra("data");

                File file=new File(path);//将要保存图片的路径
                //缓冲输出流
                BufferedOutputStream bos = null;
                try {
                    bos = new BufferedOutputStream(new FileOutputStream(file));
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100, bos);
                    bos.flush();
                    bos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                uploadPic2(file);
            }

        }
        if(requestCode==4000){
            //得到相册里的图片
            Uri uri=data.getData();
            crop(uri);
        }
    }
}
