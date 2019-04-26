package com.mdr.petoffers.view.main

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.mdr.petoffers.models.AlertModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.loading.*
import android.net.Uri
import android.util.Log
import com.mdr.petoffers.view.forms.Login
import java.io.*
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mdr.petoffers.R.layout.activity_main)
        setSupportActionBar(toolbar)

        mainViewModel = ViewModelProviders.of(this)
            .get(MainViewModel::class.java)


        var id:String= readFileContent("dados.data")
        if(id.toString()!=null && id.toString().trim()!="") {
            id = UUID.randomUUID().toString()
            writeFileContent("dados.data", id)
        }

        registerObservers()

        mainViewModel.login(id)


        /*fab.setOnClickListener { view ->
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
            startActivityForResult(
                Intent(
                    this,
                    FormularioActivity::class.java
                ), 1
            )
        }*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            mainViewModel.buscarTodos()
        }
    }

    private fun registerObservers() {
        mainViewModel.isLoading.observe(this, isLoadingObserver)
        mainViewModel.isAllowed.observe(this, isAllowedObserver)
        mainViewModel.mensagemErro.observe(this, mensagemErroObserver)
        mainViewModel.alerts.observe(this, AlertsObserver)
    }

    private var AlertsObserver = Observer<List<AlertModel>> {
        rvAlerts.adapter = MainListAdapter(
            this,
            it!!
        ) {
            Toast.makeText(this, it.text, Toast.LENGTH_SHORT).show()
        }

        rvAlerts.layoutManager = LinearLayoutManager(this)
        //rvNotas.layoutManager = GridLayoutManager(this, 3)

    }

    private var mensagemErroObserver = Observer<String> {
        if (it!!.isNotEmpty()) {
            Toast.makeText(
                this,
                it, Toast.LENGTH_LONG
            ).show()
            tvError.visibility = View.VISIBLE
            tvError.text=it
            Log.d("ek", it)
        } else {
            tvError.visibility = View.GONE
        }
    }

    private var isLoadingObserver = Observer<Boolean> {
        if (it == true) {
            containerLoading.visibility = View.VISIBLE
        } else {
            containerLoading.visibility = View.GONE
        }
    }

    private var isAllowedObserver = Observer<Boolean> {
        if (it == true) {
            mainViewModel.buscarTodos()
        } else {
            startActivityForResult(
                Intent(
                    this,
                    Login::class.java
                ), 1
            )
        }
    }

    private fun writeFileContent(uri: String, textContent:String) {
        try {

            val fileOutputStream = FileOutputStream(uri)
            fileOutputStream.write(textContent.toByteArray())
            fileOutputStream.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun readFileContent(uri: String): String {
        return ""
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }*/
}
