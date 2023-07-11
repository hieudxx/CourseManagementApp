package fpoly.hieudxph21411.assignment;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import fpoly.hieudxph21411.assignment.databinding.ActivityMaps2Binding;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMaps2Binding binding;
    TextView tvLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMaps2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tvLoc = binding.tvCurrentLoc;
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(21.040215800263436, 105.74667410971675);
        mMap.addMarker(new MarkerOptions().position(sydney).title("FPT POLY HN"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng hcm = new LatLng(10.7553411, 106.415029);
        mMap.addMarker(new MarkerOptions().position(hcm).title("Tp.HCM"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hcm));


        // check sử dụng quyền cái này thì alt + enter sẽ tự thêm vào
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(getBaseContext(), "Hãy cấp quyền truy cập GPS cho ứng dụng", Toast.LENGTH_SHORT).show();
//            return;
//        }
        // hiển thị nút tọa độ của tôi trên bản đồ cho người dùng bấm chọn
//        mMap.setMyLocationEnabled(true); //lệnh này yêu cầu kiểm tra quyền ở trên
//        mMap.setOnMyLocationButtonClickListener(this);
//        mMap.setOnMyLocationClickListener(this);

        getCurrentLocation(); // lấy tọa độ hiện tại cho vào biến và show maker trên bản đồ

    }

//    @Override
//    public void onMyLocationClick(@NonNull Location location) {
//        // sự kiện bấm vào biểu tượng market trên bản đồ; cái này chưa làm gì cả
//        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public boolean onMyLocationButtonClick() {
//        getCurrentLocation(); // bấm nút định vị trên bản đồ sẽ load lại tọa độ cho vào biến
//        return false;
//    }

    public void getCurrentLocation() {
        //============ lấy tọa dộ vị trí hiện tại
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getBaseContext(), "Hãy cấp quyền truy cập GPS cho ứng dụng", Toast.LENGTH_SHORT).show();
            return;
        }

        // khởi tạo mấy đối tượng để đọc lại vị trí định vị đã nhận diện
        // nếu app khởi động lần đầu chưa xác định vị trí thì đợi người dùng bấm nút định vị trên bản đồ
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));

        if (location != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
//                    .bearing(90)                // Sets the orientation of the camera to east
//                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            // tạo maker hiển thị biểu tượng trên màn hình: Cần truyền vào lat, long
            LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
            LatLng currentUserLocation = myLocation;
            tvLoc.setText("Toa do hien tai: " + location.getLatitude() + " : " + location.getLongitude());

            // cách tạo Maker để gắn lên bản đồ
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(myLocation)
                    .title("Vị trí của tôi ở đây")
                    .snippet("Viết cái gì đó thì viết") // thích thêm dòng này thì thêm
                    .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_delete)); // thích thêm dòng này thì thêm
//            currentUser = mMap.addMarker(markerOptions); // lệnh này gắn biểu tượng vào bản đồ, cứ lưu tạm vào biến  currentUser sau này điều khiển

        } else {
            Toast.makeText(getBaseContext(), "Không lấy được thông tin định vị, hãy bật GPS và bấm nút định vị trên bản đồ", Toast.LENGTH_LONG).show();
        }


    }
}