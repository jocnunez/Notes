package com.jocnunez.notes.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.jocnunez.notes.R
import com.jocnunez.notes.databinding.ActivityLoginBinding
import com.jocnunez.notes.firebase.FirebaseService
import com.jocnunez.notes.menu.MenuHandler

class LoginActivity : AppCompatActivity() {
    private lateinit var _binding:ActivityLoginBinding
    private val binding get() = _binding
    private val REQ_ONE_TAP = 1
    private var showOneTapUI = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

/*        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
        startActivityForResult(googleSignInClient.signInIntent, 1)
*/
//        initFirebase()

        val firebaseService = FirebaseService(this)

        Log.d("Debug", "LOGIN ")

        var resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                try {
                    val credential = firebaseService.oneTapClient.getSignInCredentialFromIntent(result.data)
                    val idToken = credential.googleIdToken
                    Log.d("Debug", "TOKEN: "+idToken.toString())
                } catch (e: ApiException) {
                    Log.d("Error", e.message.toString())
                }
            } else {
                Log.d("Debug", "CODE: "+result.resultCode)
            }
        }

    }


    // Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var menuHandler = MenuHandler(this,"login")
        menuHandler.itemHandler(item)
        if (menuHandler.intent != null) {
            startActivity(menuHandler.intent)
        }
        return super.onOptionsItemSelected(item)
    }

    // Firebase
    private fun initFirebase() {
        val firebaseService = FirebaseService(this)
    }


    fun loadFragmentFirebase() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.loginContainer.id, LoginFormFragment())
        transaction.commit()
    }
}