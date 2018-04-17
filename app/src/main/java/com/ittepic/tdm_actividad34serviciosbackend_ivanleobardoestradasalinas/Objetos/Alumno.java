package com.ittepic.tdm_actividad34serviciosbackend_ivanleobardoestradasalinas.Objetos;

public class Alumno {
    String nombre;
    String ncontrol;

    public Alumno(){

    }
    public Alumno(String nombre, String ncontrol){
        this.nombre = nombre;
        this.ncontrol = ncontrol;
    }
    public String getnombre(){
        return nombre;
    }
    public String getncontrol(){
        return ncontrol;
    }
}
