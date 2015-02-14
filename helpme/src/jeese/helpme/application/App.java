package jeese.helpme.application;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import cn.jpush.android.api.JPushInterface;
import android.app.Application;
import android.util.Log;

public class App extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		//JPush�ĳ�ʼ��
		JPushInterface.setDebugMode(true);
	    JPushInterface.init(this);
		
		// ��ʼ����
		RongIM.init(this);
		
		//RongIM.init(this, "k51hidwq1fspb", R.drawable.ic_launcher);
		
		// ������Ӧ�÷����������Ի�ȡ Token���ڱ�ʾ��������ֱ�������� hardcode �� token ��ֵ��
		// String token = getTokenFromAppServer();

		// �˴�ֱ�� hardcode �� token ��ֵ�����滻Ϊ���Լ��� Token��

		// 001
		// С��
		//String token = "gmeGPMbpkNpRVFqmRk49jf9JXx3k/zTlRSJ1G8Af9CI3U4e9lF3jnvQKYp2ecC/dBVc7XyuL9skbqunN048Scg==";

		// 002
		// Сţ
		//String token = "yg9B/Wh0bZm9Wab05Ia5Af9JXx3k/zTlRSJ1G8Af9CI3U4e9lF3jnlvB84WBZ50UdgkvtOX7ULs5ATrHbF6P1g==";

		// 003
		// С����
		String token = "wjfvk2IpBXF+sB/wrTU7icgXtCAuovQvahXBtnZqSZmQjRdyLfCC8elmntSLtDK7+ykyqIE2k4IxxMHaZ6bvcw==";

		
		// �������Ʒ�������
		try {
			RongIM.connect(token, new RongIMClient.ConnectCallback() {

				@Override
				public void onSuccess(String s) {
					// �˴��������ӳɹ���
					Log.d("Connect:", "Login successfully.");
				}

				@Override
				public void onError(ErrorCode errorCode) {
					// �˴��������Ӵ���
					Log.d("Connect:", "Login failed.");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
