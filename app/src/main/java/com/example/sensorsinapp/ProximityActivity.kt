package com.example.sensorsinapp

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class ProximityActivity: AppCompatActivity(),SensorEventListener {
lateinit var sensormanager : SensorManager
lateinit var updatetext : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proximity)
        updatetext = findViewById(R.id.show1)
        var lightbtn: Button = findViewById(R.id.light)

        lightbtn.setOnClickListener {

            var intent = Intent(this, AmbientLightActivity::class.java)
            startActivity(intent)
        }
        sensormanager = getSystemService(SENSOR_SERVICE) as SensorManager
        if(sensormanager!= null){

            var proxSensor : Sensor? = sensormanager.getDefaultSensor(Sensor.TYPE_PROXIMITY)


            if(proxSensor != null){
                sensormanager.registerListener(this,proxSensor,SensorManager.SENSOR_DELAY_NORMAL)



            }
        }else{
            Toast.makeText(this, "Proximity Sensor does not detected ",Toast.LENGTH_SHORT).show()

        }

    }

     override fun onSensorChanged(event: SensorEvent?) {
         if (event != null) {
             if(event.sensor.type == Sensor.TYPE_PROXIMITY)
                 updatetext.text = " Values : ${event.values[0]}"

                 if(event.values[0] >0 ){
                     Toast.makeText(this,"Object is far", Toast.LENGTH_SHORT).show()
                 }
                else{
                    Toast.makeText(this, "Object is near" ,Toast.LENGTH_SHORT).show()
                 }

         }
     }

     override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

     }
 }
