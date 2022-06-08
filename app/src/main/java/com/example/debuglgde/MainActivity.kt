package com.example.debuglgde

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.debuglgde.backdropactivity.BackdropActivity
import com.example.debuglgde.databinding.ActivityMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Debug"
            subtitle = "La geekepedia de ernesto"
            elevation = 10f
            //setBackgroundDrawable(resources.getDrawable(R.drawable.ic_launcher_background))
        }

        setListeners()
        getCountries()
    }

    private fun setListeners() {
        binding.btnPromediar.setOnClickListener {
            calcularPromedio()
        }

        binding.btnBottomSheetDialog.setOnClickListener {
            val fragment = BottomSheet()
            fragment.show(
                supportFragmentManager.beginTransaction(),
                BottomSheet::class.java.simpleName
            )
        }

        binding.btnGoToBackDropActivity.setOnClickListener {
            startActivity(Intent(this, BackdropActivity::class.java))
        }
    }

    private fun calcularPromedio(){
        val test1 = binding.etTest1.text.toString()
        val test2 = binding.etTest2.text.toString()
        val test3 =  binding.etTest3.text.toString()
        var promedio = 0.0

        if (!test1.isNullOrEmpty() && !test2.isNullOrEmpty() && !test3.isNullOrEmpty()){
            promedio = (test1.toDouble() + test2.toDouble() + test3.toDouble()) / 3

            binding.progressHorizontal.visibility = View.VISIBLE
            binding.parentContainer.alpha = 0.5f

            //Contador de tiempo regresivo
            val timer = object : CountDownTimer(2000, 1000){
                override fun onFinish() {
                    binding.progressHorizontal.visibility = View.INVISIBLE
                    binding.parentContainer.alpha = 1f

                    if (promedio >= 6.0){
                        Toast.makeText(this@MainActivity, "Aprobado con $promedio", Toast.LENGTH_SHORT).show()
                    } else if (promedio < 6.0) {
                        Toast.makeText(this@MainActivity, "Desaprobado con $promedio", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onTick(millisUntilFinished: Long) {}
            }
            timer.start()

        } else {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCountries(){
        val countries = resources.getStringArray(R.array.country_location)
        val adapter = ArrayAdapter(this, R.layout.list_item, countries)

        with(binding.autoCompleteTxt){
            setAdapter(adapter)
            onItemClickListener = this@MainActivity
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()
        val contextView = binding.autoCompleteTxt

        Snackbar.make(contextView, item , Snackbar.LENGTH_LONG)
            //.setAnchorView(binding.autoCompleteTxt)
            .setActionTextColor(resources.getColor(R.color.teal_200))
            .setTextColor(resources.getColor(R.color.teal_200))
            .setBackgroundTint(resources.getColor(R.color.purple_700))
            .setAnimationMode(ANIMATION_MODE_SLIDE)
            .setAction("Ok"){}
            .show()
    }
}