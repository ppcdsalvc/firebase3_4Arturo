package com.ittepic.tdm_actividad34serviciosbackend_ivanleobardoestradasalinas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText nom,nctrl;
    Button ingresar,registrar;
    String nombreInicio, ncontrolInicio,nombreRegistro, ncontrolRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom=findViewById(R.id.nombre);
        nctrl=findViewById(R.id.nControl);
        ingresar=findViewById(R.id.ingresar);
        registrar=findViewById(R.id.registrar);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user !=null){
                    Log.i("Sesion","Sesion Iniciada con el Email: "+user.getEmail());
                }else{
                    Log.i("Sesion","Sesion Cerrada");
                }
            }
        };

        registrar.setOnClickListener(this);
        ingresar.setOnClickListener(this);

    }
    private void registrar(String email, String pass){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.i("SESION","Usuario Creado Correctamente");
                }else{
                    Log.e("SESION",task.getException().getMessage()+"");
                }
            }
        });

    }
    private void iniciarSesion(String email, String pass){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.i("SESION","Usuario Iniciada Correctamente");
                    Intent abrirVentana = new Intent(MainActivity.this,Alumnos.class);
                    startActivity(abrirVentana);
                }else{
                    Log.e("SESION",task.getException().getMessage()+"");
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ingresar:
                nombreInicio = nom.getText().toString();
                ncontrolInicio = nctrl.getText().toString();
                iniciarSesion(nombreInicio,ncontrolInicio);
                break;
            case R.id.registrar:
                nombreRegistro = nom.getText().toString();
                ncontrolRegistro = nctrl.getText().toString();
                registrar(nombreRegistro,ncontrolRegistro);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }
}
