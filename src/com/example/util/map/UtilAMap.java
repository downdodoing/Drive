package com.example.util.map;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

public class UtilAMap implements AMapLocationListener {
    private MapInfo mapInfo;

    private LocationManagerProxy mLocationManagerProxy;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public UtilAMap() {
        if (mapInfo == null) {
            mapInfo = new MapInfo();
        }
    }

    public void init(Context context) {
        // 初始化定位，只采用网络定位
        mLocationManagerProxy = LocationManagerProxy.getInstance(context);
        mLocationManagerProxy.setGpsEnable(false);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用removeUpdates()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用destroy()方法
        // 其中如果间隔时间为-1，则定位只定一次,
        // 在单次定位情况下，定位无论成功与否，都无需调用removeUpdates()方法移除请求，定位sdk内部会移除
        mLocationManagerProxy.requestLocationData(LocationProviderProxy.AMapNetwork, -1, 15, this);
    }

    public MapInfo getMapInfo() {
        return mapInfo;
    }

    public void setMapInfo(MapInfo mapInfo) {
        this.mapInfo = mapInfo;
    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {

        if (amapLocation != null && amapLocation.getAMapException().getErrorCode() == 0) {
            // 定位成功回调信息，设置相关消息
            this.mapInfo.setLatitude(amapLocation.getLatitude());
            this.mapInfo.setLongitude(amapLocation.getLongitude());

            Date date = new Date(amapLocation.getTime());
            this.mapInfo.setDate(df.format(date));
            this.mapInfo.setAddress(amapLocation.getAddress());
            this.mapInfo.setCountry(amapLocation.getCountry());
            if (amapLocation.getProvince() == null) {
                this.mapInfo.setProvince("");
            } else {
                this.mapInfo.setProvince(amapLocation.getProvince());
            }
            this.mapInfo.setCity(amapLocation.getCity());
            this.mapInfo.setDistrict(amapLocation.getDistrict());
            this.mapInfo.setRoad(amapLocation.getRoad());
            this.mapInfo.setRoad(amapLocation.getPoiName());

        } else {
            Log.e("AmapErr", "Location ERR:" + amapLocation.getAMapException().getErrorCode());
        }
    }

}
