package com.example.sensorsinapp

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.SensorPrivacyManager.Sensors
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener{
    lateinit var updatetext: TextView
    lateinit var sensorManager : SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var proximityBtn : Button = findViewById<Button>(R.id.proxy)

        proximityBtn.setOnClickListener {
            var intent = Intent (this, ProximityActivity ::class.java)
            startActivity(intent)
        }

        updatetext = findViewById(R.id.show)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        if(sensorManager!= null){

            var accSensor : Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            if(accSensor != null){
                //Registers a SensorEventListener for the given sensor at the given sampling frequency.
                //The events will be delivered to the provided SensorEventListener as soon as they are available.
                sensorManager.registerListener(this,accSensor, SensorManager.SENSOR_DELAY_NORMAL)

            }


        }else{
         Toast.makeText(this,"Sensor Service not detected",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            if(event.sensor.type == Sensor.TYPE_ACCELEROMETER)
                updatetext.text = "X dimension : ${event.values[0]}  , Y dimension : ${event.values[1]} , Z dimension : ${event.values[2]}"

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}
