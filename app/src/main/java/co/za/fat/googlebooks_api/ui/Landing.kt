package co.za.fat.googlebooks_api.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import co.za.fat.googlebooks_api.MainActivity
import co.za.fat.googlebooks_api.R

class Landing : AppCompatActivity() {
    private lateinit var etSearchBox: EditText
    private lateinit var btnSearch: Button
    private lateinit var clLanding: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        etSearchBox = findViewById(R.id.et_search_view)
        btnSearch = findViewById(R.id.btn_search)
        clLanding = findViewById(R.id.cl_landing)
        supportActionBar?.hide()

        clLanding.setOnClickListener {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }

        btnSearch.setOnClickListener {
            val searchTerm = etSearchBox.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("searchTerm", searchTerm)

            etSearchBox.text.clear()

            if (searchTerm.isEmpty()) {
                Toast.makeText(this, "Enter a search term", Toast.LENGTH_LONG).show()
            } else {
                println(searchTerm)
                startActivity(intent)
            }

        }

    }
}