package jeese.helpme.application;

import jeese.helpme.R;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import cn.jpush.android.api.JPushInterface;
import android.app.Application;
import android.util.Log;

public class App extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		//JPush的初始化
		JPushInterface.setDebugMode(true);
	    JPushInterface.init(this);
		
		// 初始化。
		RongIM.init(this);
		
		//RongIM.init(this, "e7x8xycsx6flq", R.drawable.ic_launcher);
		
		// 从您的应用服务器请求，以获取 Token。在本示例中我们直接在下面 hardcode 给 token 赋值。
		// String token = getTokenFromAppServer();

		// 此处直接 hardcode 给 token 赋值，请替换为您自己的 Token。

		// 001
		// 小狗
		String token = "gmeGPMbpkNpRVFqmRk49jf9JXx3k/zTlRSJ1G8Af9CI3U4e9lF3jnvQKYp2ecC/dBVc7XyuL9skbqunN048Scg==";

		// 002
		// 小牛
		//String token = "yg9B/Wh0bZm9Wab05Ia5Af9JXx3k/zTlRSJ1G8Af9CI3U4e9lF3jnlvB84WBZ50UdgkvtOX7ULs5ATrHbF6P1g==";

		// 连接融云服务器。
		try {
			RongIM.connect(token, new RongIMClient.ConnectCallback() {

				@Override
				public void onSuccess(String s) {
					// 此处处理连接成功。
					Log.d("Connect:", "Login successfully.");
				}

				@Override
				public void onError(ErrorCode errorCode) {
					// 此处处理连接错误。
					Log.d("Connect:", "Login failed.");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
