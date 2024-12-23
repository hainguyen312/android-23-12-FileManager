// MainActivity.kt
package com.example.filemanager

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FileListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val initialPath = intent.getStringExtra("path") ?: Environment.getExternalStorageDirectory().path
        val files = getFiles(initialPath)

        adapter = FileListAdapter(files) { file ->
            if (file.isDirectory) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("path", file.absolutePath)
                startActivity(intent)
            } else {
                val intent = Intent(this, FileViewerActivity::class.java)
                intent.putExtra("filePath", file.absolutePath)
                startActivity(intent)
            }
        }

        recyclerView.adapter = adapter
    }

    private fun getFiles(path: String): List<File> {
        val directory = File(path)
        return directory.listFiles()?.toList() ?: emptyList()
    }
}
