package com.jocnunez.notes.storage.firebase

import android.content.Context
import android.util.Log
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import com.jocnunez.notes.R

class FirebaseService(val context: Context) {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    lateinit var oneTapClient: SignInClient
    lateinit var signInRequest: BeginSignInRequest

    init {
        signIn()
        auth = Firebase.auth
    }

    private fun signIn() {
//        Log.d("Debug", "LOG IN: " + context.getString(R.string.firebase_id))
        oneTapClient = Identity.getSignInClient(context)
        signInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                .setSupported(true)
                .build())
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(context.getString(R.string.firebase_id))
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .setAutoSelectEnabled(true)
            .build()

        //oneTapClient.beginSignIn(signInRequest)
    }
}