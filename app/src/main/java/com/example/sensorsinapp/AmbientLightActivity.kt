package com.example.sensorsinapp

import android.app.Activity
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AmbientLightActivity : AppCompatActivity(),SensorEventListener {
lateinit var updatetext : TextView
lateinit var sensorManager : SensorManager
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_light)

        updatetext = findViewById(R.id.showcc)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if(sensorManager != null){

            var lightSensor : Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
            if(lightSensor != null){
                sensorManager.registerListener(this,lightSensor,SensorManager.SENSOR_DELAY_NORMAL)


            }
        }
        else{
            Toast.makeText(this,"Sensor does not detected" , Toast.LENGTH_SHORT).show()
        }


    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
           if(event.sensor.type == Sensor.TYPE_LIGHT){
               updatetext.text = "Values : ${event.values[0]}"
           }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}
