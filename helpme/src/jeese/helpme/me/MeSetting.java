package jeese.helpme.me;

import jeese.helpme.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MeSetting extends Activity {
	private Button cancelBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_setting);
		
		init();
	}
	public void init(){
		cancelBtn=(Button) findViewById(R.id.cancel_btn);
		cancelBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
