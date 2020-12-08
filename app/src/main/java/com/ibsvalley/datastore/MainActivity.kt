package com.ibsvalley.datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.ibsvalley.datastore.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

private var _binding: ActivityMainBinding? = null
private val binding get() = _binding!!

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            lifecycleScope.launch {
                DataStoreUtil.getDataStore(applicationContext)?.write(
                        binding.etSaveKey.text.toString(),
                        binding.etSaveValue.text.toString()
                )
            }
        }

        binding.btnRead.setOnClickListener {
            lifecycleScope.launch {
                val value =
                        DataStoreUtil.getDataStore(applicationContext)?.read(
                                binding.etReadkey.text.toString(),"khaled")
                binding.tvReadValue.text = value ?: "No value found"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}