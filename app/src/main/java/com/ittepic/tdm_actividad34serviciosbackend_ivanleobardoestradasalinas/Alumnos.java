package com.ittepic.tdm_actividad34serviciosbackend_ivanleobardoestradasalinas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittepic.tdm_actividad34serviciosbackend_ivanleobardoestradasalinas.Objetos.Alumno;
import com.ittepic.tdm_actividad34serviciosbackend_ivanleobardoestradasalinas.Objetos.FirebaseReferences;

public class Alumnos extends AppCompatActivity {
    EditText nombreAlm,nControlAlm;
    Button rAlm,cAlm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        nombreAlm=findViewById(R.id.nombre);
        nControlAlm=findViewById(R.id.nCrl);
        rAlm=findViewById(R.id.rAlumno);
        cAlm=findViewById(R.id.cAlumno);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference(FirebaseReferences.ALUMNO_REFERENCE);

        rAlm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nAlm, ncAlm;
                nAlm=nombreAlm.getText().toString();
                ncAlm=nControlAlm.getText().toString();
                Alumno alumno = new Alumno(""+nAlm,""+ncAlm);
                myRef.child(FirebaseReferences.ALUMNO_REFERENCE).push().setValue(alumno);
            }
        });

        cAlm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child(FirebaseReferences.ALUMNO_REFERENCE).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Alumno alumno = dataSnapshot.getValue(Alumno.class);
                        Log.i("ALUMNO",alumno.getnombre());
                        Log.i("ALUMNO",alumno.getncontrol());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
