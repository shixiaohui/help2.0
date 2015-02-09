package jeese.helpme.discover;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnCameraChangeListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.lidroid.xutils.BitmapUtils;

import jeese.helpme.R;
import jeese.helpme.service.LocationService;
import jeese.helpme.view.CircleImageView;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class Discover_Fragment extends Fragment implements
		OnMarkerClickListener, OnMapLoadedListener, OnCameraChangeListener {

	private View mView;
	private MapView mapView;
	private AMap aMap;
	private UiSettings mUiSettings;

	private BitmapUtils bitmapUtils;
	private View marker_icon;
	
	private int color = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mView = View.inflate(getActivity(), R.layout.discover_fragment, null);
		mapView = (MapView) mView.findViewById(R.id.discover_fragment_mapview);
		mapView.onCreate(savedInstanceState);// ����Ҫд
		bitmapUtils = new BitmapUtils(getActivity());
		bitmapUtils.configDefaultLoadingImage(R.drawable.user_head);
		init();
		setMarker();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup parent = (ViewGroup) mView.getParent();
		if (parent != null) {
			parent.removeView(mView);
		}
		return mView;
	}

	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
			mUiSettings = aMap.getUiSettings();
			setUpMap();
		}
	}

	/**
	 * amap���һЩ�¼�������
	 */
	private void setUpMap() {
		// ���õ�ͼlogo��ʾ�����·�
		mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
		// ����Ĭ�����ű���
		aMap.moveCamera(CameraUpdateFactory.zoomTo((float) 16));
		// ����Ĭ�ϷŴ���С��ť�Ƿ���ʾ
		mUiSettings.setZoomControlsEnabled(false);
		// ����ָ�����Ƿ���ʾ
		mUiSettings.setCompassEnabled(true);

		aMap.moveCamera(CameraUpdateFactory
				.newCameraPosition(new CameraPosition(LocationService
						.getLatLng(), 16, 30, 0)));
		aMap.setOnMarkerClickListener(this);
		aMap.setOnMapLoadedListener(this);
		aMap.setOnCameraChangeListener(this);
	}

	private void setMarker() {

		marker_icon = LayoutInflater.from(getActivity()).inflate(
				R.layout.discover_marker_icon, null);

		CircleImageView markicon_headimage = (CircleImageView) marker_icon
				.findViewById(R.id.discover_marker_icon_headimage);

		ImageView markicon_bg = (ImageView) marker_icon
				.findViewById(R.id.discover_marker_icon_bg);
		bitmapUtils.display(markicon_headimage,
				"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
		markicon_bg.setImageResource(R.drawable.markericon_1);

		// ��ʼ��marker����
		MarkerOptions markerOptions = new MarkerOptions();
		BitmapDescriptor markerIcon = BitmapDescriptorFactory
				.fromView(marker_icon);
		markerOptions.position(LocationService.getLatLng()).icon(markerIcon)
				.title("icon");

		// ��ӵ���ͼ��

		aMap.addMarker(markerOptions);
	}

	/**
	 * ����������д
	 */
	@Override
	public void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * ����������д
	 */
	@Override
	public void onPause() {
		super.onPause();
		mapView.onPause();
	}

	/**
	 * ����������д
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * ����������д
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		if (marker.getTitle().toString().contentEquals("icon")) {
			Toast.makeText(getActivity(), marker.getTitle().toString(),
					Toast.LENGTH_SHORT).show();

		}
		return true;
	}

	@Override
	public void onMapLoaded() {
		LatLng left = aMap.getProjection().fromScreenLocation(new Point(0, 0));
		LatLng right = aMap.getProjection().fromScreenLocation(
				new Point(mapView.getWidth(), mapView.getHeight()));
		System.out.println("left:" + left + "\n" + "right" + right + "\n"
				+ "center" + LocationService.getLatLng());
	}

	@Override
	public void onCameraChange(CameraPosition arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCameraChangeFinish(CameraPosition arg0) {
		LatLng right = aMap.getProjection().fromScreenLocation(
				new Point((mapView.getWidth() / 2), (mapView.getHeight() / 2)));
		marker_icon = LayoutInflater.from(getActivity()).inflate(
				R.layout.discover_marker_icon, null);

		CircleImageView markicon_headimage = (CircleImageView) marker_icon
				.findViewById(R.id.discover_marker_icon_headimage);

		ImageView markicon_bg = (ImageView) marker_icon
				.findViewById(R.id.discover_marker_icon_bg);
		bitmapUtils.display(markicon_headimage,
				"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
		if(color == 0){
			markicon_bg.setImageResource(R.drawable.markericon_1);
			color++;
		}else if(color == 1){
			markicon_bg.setImageResource(R.drawable.markericon_2);
			color++;
		}
		else if(color == 2){
			markicon_bg.setImageResource(R.drawable.markericon_3);
			color++;
		}
		else if(color == 3){
			markicon_bg.setImageResource(R.drawable.markericon_4);
			color = 0;
		}
		

		// ��ʼ��marker����
		MarkerOptions markerOptions = new MarkerOptions();
		BitmapDescriptor markerIcon = BitmapDescriptorFactory
				.fromView(marker_icon);
		markerOptions.position(right).icon(markerIcon).title("icon");

		// ��ӵ���ͼ��

		aMap.addMarker(markerOptions);
	}
}
