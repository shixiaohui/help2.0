package jeese.helpme.help;

import jeese.helpme.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewLocation extends Activity {
	private EditText newAddress;
	private Button cancelBtn;
	private Button confirmBtn;
	private String address;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new_location);
		
		newAddress = (EditText) findViewById(R.id.edit_new_address);
		cancelBtn=(Button) findViewById(R.id.cancel_add_btn);
		confirmBtn=(Button) findViewById(R.id.confirm_button);
		
		address = newAddress.getText().toString();
		
		cancelBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		confirmBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(address.equals("")){
					Toast.makeText(getApplicationContext(), "地址不能为空", Toast.LENGTH_LONG).show();
					return;
				}
			}
		});	
}
}
