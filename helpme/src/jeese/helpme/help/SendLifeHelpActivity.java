package jeese.helpme.help;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jeese.helpme.R;
import jeese.helpme.photoUtil.Bimp;
import jeese.helpme.photoUtil.ChoosePicActivity;
import jeese.helpme.photoUtil.FileUtils;
import jeese.helpme.photoUtil.PhotoActivity;
import jeese.helpme.service.LocationService;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class SendLifeHelpActivity extends Activity {

	private TextView chooseFriend;
	private Button sendLifeHelpBtn;
	private EditText editHelpContent;
	private Button sendCancelBtn;
	private ImageView camera;
	private TextView showLocation;
	private GridView noScrollgridview;;
	private GridAdapter adapter;

	private static final String SERVER_URL = "http://120.24.208.130:8080/api/";

	/*
	 * public List<String> drr = new ArrayList<String>();//存放压缩后的图片的路径 public
	 * List<Bitmap> bmp = new ArrayList<Bitmap>();//存放在主界面显示的圆角矩形图片
	 */
	// private float dp;
	// requestCode
	private static final int TAKE_PICTURE = 0;// 拍照的请求码
	// private static final int RESULT_LOAD_IMAGE = 1;//从相册选取的请求码
	// private static final int CUT_PHOTO_REQUEST_CODE = 2;//裁剪图片的请求码
	private static final int CHOOSE_FRIEND = 4;// 选择求助范围的请求码
	private static final int CHOOSE_LOCATION = 5;

	/**
	 * Activity创建时被调用
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_life_help);

		init();
	}

	public void init() {
		// dp = getResources().getDimension(R.dimen.dp);//边框大小

		chooseFriend = (TextView) findViewById(R.id.choose_friend);
		showLocation = (TextView) findViewById(R.id.send_tvlocation);
		editHelpContent = (EditText) findViewById(R.id.edit_lifehelp_text);
		sendLifeHelpBtn = (Button) findViewById(R.id.send_btn);
		sendCancelBtn = (Button) findViewById(R.id.cancel_send_btn);
		camera = (ImageView) findViewById(R.id.send_cose_camera);

		// 初始化gridview
		noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
		noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new GridAdapter(this);
		adapter.update();
		noScrollgridview.setAdapter(adapter);
		noScrollgridview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (position == Bimp.bmp.size()) {
					new PopupWindows(SendLifeHelpActivity.this,
							noScrollgridview);
				} else {
					Intent intent = new Intent(SendLifeHelpActivity.this,
							PhotoActivity.class);
					intent.putExtra("ID", position);
					startActivity(intent);
				}
			}
		});

		// editHelpContent.setFocusable(true);
		// editHelpContent.setFocusableInTouchMode(true);

		/**
		 * 点击左下角设置显示地理位置
		 */
		showLocation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SendLifeHelpActivity.this,
						ChooseLocation.class);
				startActivityForResult(intent, CHOOSE_LOCATION);
			}
		});

		/**
		 * 点击右下角设置对谁公开求助内容
		 */
		chooseFriend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SendLifeHelpActivity.this,
						ChooseHelpRange.class);
				startActivityForResult(intent, CHOOSE_FRIEND);
			}
		});

		/**
		 * 点击camera图标，弹出选择框进行拍照上传或者从相册选择照片上传或者取消
		 */
		camera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				new PopupWindows(SendLifeHelpActivity.this, camera);
			}
		});

		/**
		 * 根据输入文本的变化更改确认按钮的字体颜色
		 */
		editHelpContent.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// 根据输入文本的变化更改确认按钮的字体颜色
				String str = editHelpContent.getText().toString();
				if (str.equals("")) {
					sendLifeHelpBtn.setTextColor(getResources().getColor(
							R.color.gray));
				} else {
					sendLifeHelpBtn.setTextColor(getResources().getColor(
							R.color.dark_gray));
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void afterTextChanged(Editable arg0) {
			}
		});

		/**
		 * 点击取消按钮关闭当前界面
		 */
		sendCancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		/**
		 * 点击发送按钮，把数据发送出去
		 */
		sendLifeHelpBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Double latitude = LocationService.getGeoLat();
				Double longitude = LocationService.getGeoLng();
				String city = LocationService.getCityCode();
				String address = LocationService.getAddress();
				System.out.println("纬度是：" + latitude);
				System.out.println("经度是：" + longitude);
				System.out.println("城市是：" + city);
				System.out.println("地址是：" + address);

				String content = editHelpContent.getText().toString();

				if (content.equals("")) {
					Toast.makeText(getApplicationContext(), "发布的内容不能为空",
							Toast.LENGTH_SHORT).show();
				} else {
					// 高清的压缩图片路径全部就在 list 路径里面了
					// 高清的压缩过的 bmp 对象 都在 Bimp.bmp里面
					// 获取图片路径
//					List<String> list = new ArrayList<String>();
//					for (int i = 0; i < Bimp.drr.size(); i++) {
//						String Str = Bimp.drr.get(i).substring(
//								Bimp.drr.get(i).lastIndexOf("/") + 1,
//								Bimp.drr.get(i).lastIndexOf("."));
//						list.add(FileUtils.SDPATH + Str + ".JPEG");
//					}
//
//					AjaxParams params = new AjaxParams();
//					// 上传图片.文件数据库存图片已经很少用 图片转化成字节的方式了，代替它的，把文件上传到服务器的硬盘中
//					// ，把路径存到数据库中 ，查询时 查到数据库中该图片的路径然后去硬盘中找查询出来的对应路径就可以了
//					// ，省了数据库的资源 ，提高了速度
//					/*
//					 * for(int i=0;i<Bimp.bmp.size();i++){ ByteArrayOutputStream
//					 * stream = new ByteArrayOutputStream(); Bitmap bitmap =
//					 * Bimp.getLoacalBitmap(list.get(i));
//					 * //Bitmap的compress方法，一般用于保存一个Bitmap对象
//					 * ，转为字节输出流，但是compress目前编码由两种
//					 * ，比如JPG，一般处理照片和PNG，PNG一般处理带Alpha透明通道的图片
//					 * ，后面的第二个参数是清晰度，一般100是最高
//					 * ，0是最低，这个值越大图片越清晰，同时文件体积越大，JPG和PNG都是压缩的图片
//					 * ，所以和原始的直接通过BitmapFactory.decodeByteArray解码后的大小会有很大的不同。
//					 * bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//					 * byte[] bytes = stream.toByteArray();
//					 * params.put("profile_picture", new
//					 * ByteArrayInputStream(bytes)); // 提交字节流 }
//					 */
//					for (int i = 0; i < list.size(); i++) {
//						String path = list.get(i);
//						try {
//							params.put("profile_picture" + i, new File(path));
//						} catch (FileNotFoundException e) {
//							e.printStackTrace();
//						}
//
//						params.put("username", "xiaoming");
//						params.put("datetime", "2014-12-16");
//						params.put("type", "1");
//						params.put("content", content);
//						params.put("longitude", longitude.toString());
//						params.put("latitude", latitude.toString());
//						params.put("city", city);
//						params.put("address", address);
//
//						FinalHttp finalHttp = new FinalHttp();
//						finalHttp.post(SERVER_URL, params,
//								new AjaxCallBack<Object>() {
//									@Override
//									public void onLoading(long count,
//											long current) {
//										System.out.println(current + "/"
//												+ count);
//										super.onLoading(count, current);
//									}
//
//									@Override
//									public void onSuccess(Object t) {
//										System.out.println("返回信息是："
//												+ t.toString());
//									}
//								});
//						// 完成上传服务器后 .........
//						FileUtils.deleteDir();
//					}
				}
			}
		});
	}

	private Uri photoUri;
	private String path = "";

	/**
	 * 拍照上传
	 */
	public void photo() {
		try {
			// image的获取可以通过调Android自带的Camera应用来完成
			Intent openCameraIntent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);

			// 如果我们想要读取或者向SD卡写入，这时就必须先要判断一个SD卡的状态，否则有可能出错。
			String sdcardState = Environment.getExternalStorageState();
			String sdcardPathDir = android.os.Environment
					.getExternalStorageDirectory().getPath() + "/tempImage/";
			File file = null;

			// Environment.MEDIA_MOUNTED表示sd卡正常挂载
			if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
				/* 下面就指定image存储在SDCard上的tempImage文件夹下面 */
				File fileDir = new File(sdcardPathDir);
				if (!fileDir.exists()) {
					fileDir.mkdirs();// 若文件夹已经存在就不需要新建文件夹了
				}
				/* 创建一个文件 */
				file = new File(sdcardPathDir + System.currentTimeMillis()
						+ ".JPEG");
			}
			if (file != null) {
				path = file.getPath();// getpath 得到缩写的路径，根据当前目录位置可以缩写路径。得到相对路径。
				// uri的作用是根据这个URI找到某个资源文件，基本格式如： file:///sdcard/temp.jpg
				photoUri = Uri.fromFile(file);
				/*
				 * 由于Camara返回的是缩略图，我们可以传递给他一个参数EXTRA_OUTPUT,
				 * 来将用Camera获取到的图片存储在一个指定的URI位置处。 这样就将文件的存储方式和uri指定到了Camera应用中
				 */
				openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
				/*
				 * 由于我们需要调用完Camera后，可以返回Camera获取到的图片，
				 * 所以，我们使用startActivityForResult来启动Camera，之后对于获取的图片进行裁剪
				 */
				startActivityForResult(openCameraIntent, TAKE_PICTURE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 为了获取Camera返回的图片信息，重写该方法。
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case TAKE_PICTURE:
			if (Bimp.drr.size() < 9 && resultCode == -1) {
				Bimp.drr.add(path);
				// startPhotoZoom(photoUri);
			}
			break;
		case CHOOSE_FRIEND:
			if (resultCode == RESULT_OK) {
				String result = data.getExtras().getString("group").toString();
				chooseFriend.setText(result);
			}
			break;
		case CHOOSE_LOCATION:
			if (resultCode == RESULT_OK) {
				String result = data.getExtras().getString("location")
						.toString();
				showLocation.setText(result);
			}
		}
	}

	/**
	 * 点击camera图标从下面弹出来选择框
	 * */
	public class PopupWindows extends PopupWindow {

		public PopupWindows(Context mContext, View parent) {

			View view = View
					.inflate(mContext, R.layout.item_popupwindows, null);
			view.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.fade_ins));
			LinearLayout ll_popup = (LinearLayout) view
					.findViewById(R.id.ll_popup);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.push_bottom_in_2));

			setWidth(LayoutParams.FILL_PARENT);
			setHeight(LayoutParams.FILL_PARENT);
			setBackgroundDrawable(new BitmapDrawable());
			setFocusable(true);
			setOutsideTouchable(true);
			setContentView(view);
			showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			update();

			Button bt1 = (Button) view
					.findViewById(R.id.item_popupwindows_camera);
			Button bt2 = (Button) view
					.findViewById(R.id.item_popupwindows_photo);
			Button bt3 = (Button) view
					.findViewById(R.id.item_popupwindows_cancel);
			bt1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					photo();
					dismiss();
				}
			});
			bt2.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					/*
					 * Intent i = new
					 * Intent(Intent.ACTION_PICK,android.provider.
					 * MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					 * startActivityForResult(i, RESULT_LOAD_IMAGE);
					 */
					Intent intent = new Intent(SendLifeHelpActivity.this,
							ChoosePicActivity.class);
					startActivity(intent);
					dismiss();
				}
			});
			bt3.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dismiss();
				}
			});
		}
	}

	/**
	 * 一个继承BaseAdapter的自定义适配器
	 * 
	 * @author Administrator
	 * 
	 */

	public class GridAdapter extends BaseAdapter {
		/*
		 * 事先写好的布局文件往往不能满足我们的需求，有时会根据情况在代码中自定义控件，这就需要用到LayoutInflater。
		 * LayoutInflater在Android中是“扩展”的意思，作用类似于findViewById().
		 * 不同的是LayoutInflater是用来获得布局文件对象的，而findViewById()是用来获得具体控件的。
		 * LayoutInflater经常在BaseAdapter的getView方法中用到，用来获取整个View并返回。
		 */
		private LayoutInflater inflater; // 视图容器
		private int selectedPosition = -1;// 选中的位置
		private boolean shape;

		public boolean isShape() {
			return shape;
		}

		public void setShape(boolean shape) {
			this.shape = shape;
		}

		public GridAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		public void update() {
			loading();
		}

		public int getCount() {
			// return (Bimp.bmp.size() + 1);
			if (Bimp.bmp.size() < 9) {
				return Bimp.bmp.size() + 1;
			} else {
				return Bimp.bmp.size();
			}
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int arg0) {
			return 0;
		}

		public void setSelectedPosition(int position) {
			selectedPosition = position;
		}

		public int getSelectedPosition() {
			return selectedPosition;
		}

		/**
		 * ListView Item设置
		 */
		public View getView(int position, View convertView, ViewGroup parent) {
			final int sign = position;
			// 自定义视图
			ViewHolder holder = null;
			if (convertView == null) {// 一开始，显示在屏幕内的item都没有进入Recyclor，所以，convertView作为老的View是空的。
				convertView = inflater.inflate(R.layout.item_published_grida,
						parent, false);// 创建了一个View之后，这些convertView就会进入Recyclor，而Recyclor的大小依据屏幕上显示对象的多少而定。
				holder = new ViewHolder();
				// 获取控件对象:image
				holder.image = (ImageView) convertView
						.findViewById(R.id.item_grida_image);
				// 设置控件集到convertView,在View中绑定一个tag，作为附加的数据
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			if (position == Bimp.bmp.size()) {
				holder.image.setImageBitmap(BitmapFactory.decodeResource(
						getResources(), R.drawable.icon_addpic_unfocused));// 没有添加图片的时候不显示加号图片
				if (position == 0) {
					holder.image.setVisibility(View.GONE);
				}
				if (position == 9) {
					holder.image.setVisibility(View.GONE);// 数量达到9，不显示加号图片，不能再添加图片
				}
			} else {
				holder.image.setImageBitmap(Bimp.bmp.get(position));// 显示添加的图片
			}

			return convertView;
		}

		public class ViewHolder {
			public ImageView image;
		}

		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					adapter.notifyDataSetChanged();
					break;
				}
				super.handleMessage(msg);
			}
		};

		public void loading() {
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						if (Bimp.max == Bimp.drr.size()) {
							Message message = new Message();
							message.what = 1;
							handler.sendMessage(message);
							break;
						} else {
							try {
								String path = Bimp.drr.get(Bimp.max);
								System.out.println(path);
								Bitmap bm = Bimp.revitionImageSize(path);
								Bimp.bmp.add(bm);
								String newStr = path.substring(
										path.lastIndexOf("/") + 1,
										path.lastIndexOf("."));
								FileUtils.saveBitmap(bm, "" + newStr);
								Bimp.max += 1;
								Message message = new Message();
								message.what = 1;
								handler.sendMessage(message);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}).start();
		}
	}

	/**
	 *** Activity从后台重新回到前台时被调用
	 * ->当前Activity转到新的Activity界面或按Home键回到主屏，自身退居后台：系统会先调用onPause方法
	 * ，然后调用onStop方法，进入停滞状态。
	 * ->用户后退回到此Activity：系统会先调用onRestart方法，然后调用onStart方法，最后调用onResume方法
	 * ，再次进入运行状态。
	 */
	protected void onRestart() {
		adapter.update();
		System.out.println("SendLifeHelpActivity is onRestart!!!!!!!!!!!");
		super.onRestart();
	}

	/**
	 * 退出当前Activity时被调用,调用之后Activity就结束了
	 */
	protected void onDestroy() {

		FileUtils.deleteDir();
		// 清理图片缓存
		for (int i = 0; i < Bimp.bmp.size(); i++) {
			Bimp.bmp.get(i).recycle();
		}
		for (int i = 0; i < Bimp.bmp.size(); i++) {
			Bimp.bmp.get(i).recycle();
		}
		Bimp.bmp.clear();
		Bimp.max = 0;
		Bimp.act_bool = true;
		Bimp.drr.clear();
		System.out.println("SendLifeHelpActivity is onDestory!!!!!!!!!!!");
		super.onDestroy();
	}

	/**
	 * 初始化和更新主界面的gridview
	 */
	/*
	 * public void gridviewInit() { adapter = new GridAdapter(this);
	 * adapter.setSelectedPosition(0);
	 * 
	 * //gridview的size最大是9 int size = 0; if (bmp.size() < 9) { size = bmp.size()
	 * + 1;//选中的图片的数量和一个用来添加图片的图片 } else { size = bmp.size(); }
	 * noScrollgridview.setAdapter(adapter);
	 * noScrollgridview.setOnItemClickListener(this); }
	 */
	/**
	 * 点击item继续添加图片
	 */
	/*
	 * public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long
	 * arg3) { ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
	 * .hideSoftInputFromWindow(SendLifeHelpActivity.this
	 * .getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	 * if (arg2 == bmp.size()) { String sdcardState =
	 * Environment.getExternalStorageState(); if
	 * (Environment.MEDIA_MOUNTED.equals(sdcardState)) { new
	 * PopupWindows(SendLifeHelpActivity.this, noScrollgridview); } else {
	 * Toast.makeText(getApplicationContext(), "sdcard已拔出，不能选择照片",
	 * Toast.LENGTH_SHORT).show(); } } else { Intent intent = new
	 * Intent(SendLifeHelpActivity.this, PhotoActivity.class);
	 * 
	 * intent.putExtra("ID", arg2); startActivity(intent); } }
	 */

	/*
	 * public View getView(int position, View convertView, ViewGroup parent) {
	 * final int sign = position; // 自定义视图 ViewHolder holder = null; if
	 * (convertView == null) { holder = new ViewHolder(); // 获取list_item布局文件的视图
	 * convertView = listContainer.inflate( R.layout.item_gridview, null);
	 * 
	 * // 获取控件对象:image和右上角的小圆角删除按钮 holder.image = (ImageView) convertView
	 * .findViewById(R.id.item_grida_image); holder.bt = (Button) convertView
	 * .findViewById(R.id.item_grida_bt); // 设置控件集到convertView
	 * convertView.setTag(holder); } else { holder = (ViewHolder)
	 * convertView.getTag(); }
	 * 
	 * if (position == bmp.size()) {
	 * holder.image.setImageBitmap(BitmapFactory.decodeResource( getResources(),
	 * R.drawable.icon_addpic_unfocused)); // holder.image.setLayoutParams(new
	 * LinearLayout.LayoutParams(screenW, screenH));
	 * holder.bt.setVisibility(View.GONE);//隐藏右上角红色删除小按钮 if(position == 0){
	 * holder.image.setVisibility(View.GONE);
	 * holder.bt.setVisibility(View.GONE);//隐藏右上角红色删除小按钮 } if (position == 9) {
	 * holder.image.setVisibility(View.GONE);//数量达到9，不能再添加图片 } } else {
	 * holder.image.setImageBitmap(bmp.get(position)); //点击右上角按钮，删除当前图片
	 * holder.bt.setOnClickListener(new OnClickListener() {
	 * 
	 * public void onClick(View v) { PhotoActivity.bitmap.remove(sign);
	 * bmp.get(sign).recycle(); bmp.remove(sign); drr.remove(sign);
	 * 
	 * gridviewInit(); } }); } return convertView; }
	 */

	/**
	 * 缩放图片
	 * */
	/*
	 * private void startPhotoZoom(Uri uri) { try { //SimpleDateFormat
	 * 使得可以选择任何用户定义的日期-时间格式的模式。 SimpleDateFormat sDateFormat = new
	 * SimpleDateFormat("yyyyMMddhhmmss");
	 * //SimpleDateFormat中的format方法可以把Date型的字符串转换成特定格式的String类型 String address =
	 * sDateFormat.format(new java.util.Date());
	 * 
	 * if (!FileUtils.isFileExist("")) { FileUtils.createSDDir(""); }
	 * 在数组drr中增加一个新的图片路径 drr.add(FileUtils.SDPATH + address + ".JPEG");
	 * //这是用的隐式意图激活，就是没有指定具体的组件名称，而是通过intent在系统中找到一个最合适的Activity，
	 * //通过这个URI可以访问一个本地的资源，即图片资源 Uri imageUri =
	 * Uri.parse("file:///sdcard/formats/" + address + ".JPEG");
	 * 
	 * final Intent intent = new Intent("com.android.camera.action.CROP");
	 * intent.setDataAndType(uri, "image/*");
	 * 
	 * intent.putExtra("crop", "true"); intent.putExtra("aspectX", 1);
	 * intent.putExtra("aspectY", 1); intent.putExtra("outputX", 480);
	 * intent.putExtra("outputY", 480); intent.putExtra(MediaStore.EXTRA_OUTPUT,
	 * imageUri);
	 * intent.putExtra("outputFormat",Bitmap.CompressFormat.JPEG.toString());
	 * intent.putExtra("noFaceDetection", false); intent.putExtra("return-data",
	 * false); // startActivityForResult(intent, CUT_PHOTO_REQUEST_CODE); }
	 * catch (IOException e) { e.printStackTrace(); } }
	 */
}
