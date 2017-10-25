package liangtong20170928.bawei.com.dshang.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.Urlpath_Utils;
import liangtong20170928.bawei.com.dshang.activity.SearchActicity;
import liangtong20170928.bawei.com.dshang.adapter.Fragment1_Adapter;
import liangtong20170928.bawei.com.dshang.app.GlideImageLoader;
import liangtong20170928.bawei.com.dshang.utils.OkHttp3Utils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by la on 2017/9/29.
 */

public class Fragment1 extends Fragment{

    private RecyclerView recyclerView;
    private EditText et_sousuo;
    private List<String> mListImage;
    Banner banner;
    private ImageView saoyisao;
    String TAG = "MainActivity";
    final int REQUEST_CODE = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String json = (String) msg.obj;
                    try {
                        JSONObject jsonObject =new JSONObject(json);

                        JSONObject data = jsonObject.getJSONObject("data");
                        JSONArray subjects=data.optJSONArray("subjects");
                        Log.e("SSSS",subjects+"请求的数据");
                        //设置适配器
                        Fragment1_Adapter adapter = new Fragment1_Adapter(getActivity(), subjects);
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        //获取组件id
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        et_sousuo = (EditText) view.findViewById(R.id.et_sousuo1);
        banner = view.findViewById(R.id.banner);
        saoyisao = view.findViewById(R.id.iv_saoyisao);
        banner= (Banner) view.findViewById(R.id.banner);

        getData();


        //设置LinearLayoutManager布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //设置GridLayoutManager布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        //设置StaggeredGridLayoutManager布局管理器
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        //把布局管理器给RecyclerView
        recyclerView.setLayoutManager(gridLayoutManager);


        //获取摄像头权限
        getCameraPermission();
        //ZXingLibrary初始化
        ZXingLibrary.initDisplayOpinion(getActivity());

        saoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        //加载图片的方法
        banner.setImageLoader(new GlideImageLoader());
        mListImage= new ArrayList<>();

        //想图片集合中添加网网址
        mListImage.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508339104220&di=edf1a1582db43bf3ab96ef196edc6be4&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F17%2F29%2F29%2F69y58PICCQ3_1024.jpg");
        mListImage.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508339104216&di=3fdec533f17eca412dc8e0489f7b3a04&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F18%2F04%2F37%2F55c61be8b2fc0_1024.jpg");
        mListImage.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508342080216&di=32df6cdd5e749effeafd09a2fbdc1cdb&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2Fallimg%2F160916%2F105-16091610443Y43.jpg");
        mListImage.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508342080215&di=825fb78c2d2c8b38f82aa299f3a52256&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01adbf554b40ed000001bf722da621.jpg");
        mListImage.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508342080210&di=a4fc6891c1066e74fd4b140e61f04c45&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01ea78558b662f0000001fa3e0542e.jpg%40900w_1l_2o_100sh.jpg");

        //设置Banner图片集合
        banner.setImages(mListImage);
        //切换的时间
        banner.setDelayTime(2000);
        //启动banner
        banner.start();

        //点击搜索框跳转
        et_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SearchActicity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void getData(){
        OkHttp3Utils.doGet(Urlpath_Utils.HOME_PAGE_PATH, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message = handler.obtainMessage(0, json);
                message.sendToTarget();
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void getCameraPermission()
    {
        if (Build.VERSION.SDK_INT>22){
            if (ContextCompat.checkSelfPermission(getActivity(),
                    android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.CAMERA},2);
            }else {
                //说明已经获取到摄像头权限了 想干嘛干嘛
            }
        }else {
            //这个说明系统版本在6.0之下，不需要动态获取权限。
        }
    }
}



