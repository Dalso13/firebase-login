package com.jdw.fb_login

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val joinBtn = findViewById<Button>(R.id.joinBtn)


        auth = Firebase.auth
        
        // 회원가입
        joinBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailArea)
            val pw = findViewById<EditText>(R.id.pwArea)
            
            auth.createUserWithEmailAndPassword(email.text.toString(), pw.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("join", "createUserWithEmail:success")
                        val user = auth.currentUser
                        Toast.makeText(
                            baseContext,
                            "가입 성공",
                            Toast.LENGTH_SHORT,
                        ).show()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("join", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "가입 실패",
                            Toast.LENGTH_SHORT,
                        ).show()
                        
                    }
                }
        }
        
        // 로그아웃
        val logoutBtn = findViewById<Button>(R.id.logoutBtn)
        logoutBtn.setOnClickListener {
            Firebase.auth.signOut()
            Toast.makeText(
                baseContext,
                "로그 아웃",
                Toast.LENGTH_SHORT,
            ).show()
        }
        
        // 로그인
        loginBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailArea)
            val pw = findViewById<EditText>(R.id.pwArea)

            auth.signInWithEmailAndPassword(email.text.toString(), pw.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Login", "signInWithEmail:success")
                        val user = auth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Login", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
    }
}