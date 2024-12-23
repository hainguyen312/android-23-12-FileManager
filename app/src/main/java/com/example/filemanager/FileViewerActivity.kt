package com.example.filemanager

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class FileViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_viewer)

        val filePath = intent.getStringExtra("filePath")
        val textView: TextView = findViewById(R.id.textView)

        filePath?.let {
            val content = readTextFile(it)
            textView.text = content
        }
    }

    private fun readTextFile(path: String): String {
        return File(path).readText()
    }
}
