package bawei.com.zhifu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button bt;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(final Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                final String s = msg.obj.toString();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask payTask = new PayTask(MainActivity.this);
                        Map<String, String> map = payTask.payV2(s, true);
                        Message message = new Message();
                        message.what = 1;
                        message.obj = map;
                        handler.sendMessage(message);
                    }
                };
                //异步调用
                Thread thread = new Thread(runnable);
                thread.start();
            }else if(msg.what == 1){
                Map<String,String> obj = (Map<String, String>) msg.obj;
                String resultStatus = obj.get("resultStatus");
                if("9000".equals(resultStatus)){
                    Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                }else if("6001".equals(resultStatus)){
                    Toast.makeText(MainActivity.this, "取消支付", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //http://169.254.64.79:8080/PayServer/AlipayDemo
        bt = (Button) findViewById(R.id.bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttp3Utils.doGet("http://169.254.64.79:8080/PayServer/AlipayDemo?subject="+"劳力士"+"&body="+"哈哈哈&"+"price="+"0.01", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String s = response.body().string();
                        Message message = new Message();
                        message.what = 0;
                        message.obj = s;
                        handler.sendMessage(message);
                    }
                });
            }
        });
    }
}
