package jeese.helpme.help;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jeese.helpme.R;
import jeese.helpme.service.LocationService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ListView;

public class ChooseLocation extends Activity {
	private ListView chooseList;
	private List<Map<String, Object>> data=new ArrayList<Map<String, Object>>();
	private ImageView chooseImg;
	private Button cancelChooseBtn;
	private String city;
	private String address;
	private static final int REQUESTCODE = 123;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_location);
		
		//取消定位
		cancelChooseBtn = (Button) findViewById(R.id.cancel_choose_btn);
		cancelChooseBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		city = LocationService.getCityCode();
		address = LocationService.getAddress();
		System.out.println("地址是："+address);	
		
		init();
	}
	
	public void init(){

		chooseList = (ListView) findViewById(R.id.choose_location_list);
		getData();
		
		SimpleAdapter listAdapter = new SimpleAdapter(this, data, R.layout.item_choose_location,
				new String[]{"name","img"}, new int[]{R.id.choose_text,R.id.choose_image});
		

		//设置listview的适配器和监听函数
		chooseList.setAdapter(listAdapter);
		chooseList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
//				chooseImg=(ImageView) arg1.findViewById(R.id.choose_image);
//				chooseImg.setImageDrawable(getResources().getDrawable(R.drawable.choose_checkmark));
				
				if(data.get(position).get("name").toString().equals("创建位置")){
					Intent it = new Intent(ChooseLocation.this, AddNewLocation.class);
					startActivityForResult(it, REQUESTCODE);
				}
				//调用setResult函数，回传数据
				Bundle bundle = new Bundle();
				bundle.putString("location", data.get(position).get("name").toString());
				Intent intent = new Intent(ChooseLocation.this, SendLifeHelpActivity.class);
				intent.putExtras(bundle);
				setResult(android.app.Activity.RESULT_OK, intent);
				finish();
			}
		});
	}
	//获得数据
	public void getData(){
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("name", "不显示位置");
//		map.put("img", R.drawable.choose_checkmark);
		data.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name","创建位置");
		data.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name", address);
		data.add(map);
		}
}