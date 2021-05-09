package com.example.geofiremutilocation

interface IOnLoadLocationListener {
    fun onLocationLoadSuccess(latLings:List<MyLatLng>)
    fun onLocationLoadFailed(message:String)
}