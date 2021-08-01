package com.example.youtube.languagePopup

import android.content.Intent
import android.content.SharedPreferences
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.youtube.R
import com.example.youtube.databinding.ActivityLanguagePopupBinding
import com.example.youtube.loginPopup.LoginPopupActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class LanguagePopupActivity : AppCompatActivity(), GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private lateinit var binding : ActivityLanguagePopupBinding
    private lateinit var mapFragment : SupportMapFragment
    private lateinit var geocoder: Geocoder

    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var spEditor: SharedPreferences.Editor

    private var latitude = 0f
    private var longitude = 0f
    private lateinit var country : String
    private lateinit var mMap : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguagePopupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        geocoder = Geocoder(this)
        sharedPreferences = getSharedPreferences("youtube", MODE_PRIVATE)
        spEditor = sharedPreferences.edit()

        latitude = sharedPreferences.getFloat("latitude", 37.56f)
        longitude = sharedPreferences.getFloat("longitude", 126.97f)
        country = sharedPreferences.getString("country", "KR").toString()

        binding.backButton.setOnClickListener { onBackPressed() }
        binding.cancelButton.setOnClickListener { onBackPressed() }
        binding.confirmButton.setOnClickListener { onConfirmPressed() }

        binding.latitude.text = latitude.toString()
        binding.longitude.text = longitude.toString()
        binding.country.text = country

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        return false
    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map

        val start = LatLng(latitude.toDouble(), longitude.toDouble())

        val markerOptions = MarkerOptions()
        markerOptions.position(start)
        markerOptions.title("여기")
        markerOptions.draggable(true)
        mMap.addMarker(markerOptions)
        map.setOnMarkerClickListener(this)

        mMap.moveCamera(CameraUpdateFactory.newLatLng(start));                  // 초기 위치
        mMap.animateCamera(CameraUpdateFactory.zoomTo(7f));                    // 줌의 정도
        map.mapType = GoogleMap.MAP_TYPE_HYBRID;                                // 지도 유형 설정

        map.setOnMarkerDragListener(dragEvent())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.none, R.anim.vertical_exit)
    }

    private fun onConfirmPressed(){
        spEditor.putFloat("latitude", latitude)
        spEditor.putFloat("longitude", longitude)
        spEditor.putString("country", country)
        spEditor.commit()
        onBackPressed()
    }

    inner class dragEvent : GoogleMap.OnMarkerDragListener {
        override fun onMarkerDragStart(p0: Marker) {}

        override fun onMarkerDrag(p0: Marker) {}

        override fun onMarkerDragEnd(marker: Marker) {
            val lat = marker.position.latitude
            val lon = marker.position.longitude
            var address : List<Address>? = null

            latitude = lat.toFloat()
            longitude = lon.toFloat()
            binding.latitude.text = latitude.toString()
            binding.longitude.text = longitude.toString()

            try {
                address = geocoder.getFromLocation(lat, lon, 1)
            } catch (e : IOException) {
                e.printStackTrace()
                //Log.d("geoCoder", "I/O error occurred")
            }

            if (address != null){
                if (address.isNotEmpty()){
                    country = address[0].countryCode
                    binding.country.text = country
                } else {
                    //Log.d("geoCoder", "cannot found address")
                }
            }
        }
    }
}