package xiexingzhang.bawei.com.okhttpdome;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            text.setText(msg.obj.toString());
        }
    };
    private OkHttpClient mOkHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        AsynpostHttp();
//        asynGetHttp();
        // getAsynHttp();
//        postAsynHttp();
    }

    private void asynGetHttp() {
        String url = "https://www.baidu.com/";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void postAsynHttp() {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody
                .Builder()
                .add("page", "1")
                .add("code", "news")
                .add("pageSize", "20")
                .add("parentid", "0")
                .add("type", "1")
                .build();
        final Request request = new Request.Builder()
                .url("http://admin.wap.china.com/user/NavigateTypeAction.do?processID=getNavigateNews&qq-pf-to=pcqq.group")
                .post(body)
                .build();
        Call back = client.newCall(request);
        back.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Message message = Message.obtain();
                message.what = 1;
                message.obj = str;
                handler.sendMessage(message);
            }
        });
    }

    private void AsynpostHttp() {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody
                .Builder()
                .add("page", "1")
                .add("code", "news")
                .add("pageSize", "20")
                .add("parentid", "0")
                .add("type", "1")
                .build();
        final Request request = new Request.Builder()
                .url("http://admin.wap.china.com/user/NavigateTypeAction.do?processID=getNavigateNews&qq-pf-to=pcqq.group")
                .post(body)
                .build();
        final Call back = client.newCall(request);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = back.execute();
                    String str = response.body().string();
                    Message message = Message.obtain();
                    message.what = 1;
                    message.obj = str;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

       /* back.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();

            }
        });*/

    }

    private Response asynHttp() {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news")
                .build();
        Call call = client.newCall(request);
        Response execute = null;
        try {
            execute = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return execute;
    }

    private void initView() {
        text = (TextView) findViewById(R.id.mytext);

    }

    private void getAsynHttp() {
        mOkHttpClient = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url("http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news");
        //可以省略，默认是GET请求
        requestBuilder.method("GET", null);
        Request request = requestBuilder.build();
        Call mcall = mOkHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null != response.cacheResponse()) {
                    String str = response.cacheResponse().toString();
                    Log.i("wangshu", "cache---" + str);
                } else {
                    response.body().string();
                    String str = response.networkResponse().toString();
                    Message message = Message.obtain();
                    message.what = 1;
                    message.obj = str;
                    handler.sendMessage(message);
                    Log.i("wangshu", "network---" + str);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initOK() {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder().url("http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news")
                .build();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    response.body().string();
                    String str = request.body().toString();
                    Message message = Message.obtain();
                    message.what = 1;
                    message.obj = str;
                    handler.sendMessage(message);
//                    response.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    Log.d("zgx","response====="+response.body().string());
                    response.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
    }
}
