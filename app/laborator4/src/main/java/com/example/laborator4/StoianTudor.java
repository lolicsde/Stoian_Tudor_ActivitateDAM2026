package com.example.laborator4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class StoianTudor implements Parcelable {
    //Sa zicem ca obiectul este o tastatura -> contine si S si T

    String brand;
    String nume;
    Boolean tenkey;
    Boolean mecanica;
    String culoareLED;
    String culoareSwitch;

    public StoianTudor(String brand, String nume, Boolean tenkey, Boolean mecanica, String culoareLED) {
        this.brand = brand;
        this.nume = nume;
        this.tenkey = tenkey;
        this.mecanica = mecanica;
        this.culoareLED = culoareLED;
        this.culoareSwitch = null;
    }

    public StoianTudor(String brand, String nume, Boolean tenkey, Boolean mecanica, String culoareLED, String culoareSwitch){
        this.brand = brand;
        this.nume = nume;
        this.tenkey = tenkey;
        this.mecanica = mecanica;
        this.culoareLED = culoareLED;
        this.culoareSwitch = culoareSwitch;
    }
    public StoianTudor(){
        this.brand = null;
        this.nume = null;
        this.tenkey = null;
        this.mecanica = null;
        this.culoareLED = null;
        this.culoareSwitch = null;
    }

    protected StoianTudor(Parcel in) {
        brand = in.readString();
        nume = in.readString();
        byte tmpTenkey = in.readByte();
        tenkey = tmpTenkey == 0 ? null : tmpTenkey == 1;
        byte tmpMecanica = in.readByte();
        mecanica = tmpMecanica == 0 ? null : tmpMecanica == 1;
        culoareLED = in.readString();
        culoareSwitch = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeString(nume);
        dest.writeByte((byte) (tenkey == null ? 0 : tenkey ? 1 : 2));
        dest.writeByte((byte) (mecanica == null ? 0 : mecanica ? 1 : 2));
        dest.writeString(culoareLED);
        dest.writeString(culoareSwitch);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StoianTudor> CREATOR = new Creator<StoianTudor>() {
        @Override
        public StoianTudor createFromParcel(Parcel in) {
            return new StoianTudor(in);
        }

        @Override
        public StoianTudor[] newArray(int size) {
            return new StoianTudor[size];
        }
    };

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Boolean getTenkey() {
        return tenkey;
    }

    public void setTenkey(Boolean tenkey) {
        this.tenkey = tenkey;
    }

    public Boolean getMecanica() {
        return mecanica;
    }

    public void setMecanica(Boolean mecanica) {
        this.mecanica = mecanica;
    }

    public String getCuloareLED() {
        return culoareLED;
    }

    public void setCuloareLED(String culoareLED) {
        this.culoareLED = culoareLED;
    }

    public String getCuloareSwitch() {
        return culoareSwitch;
    }

    public void setCuloareSwitch(String culoareSwitch) {
        this.culoareSwitch = culoareSwitch;
    }

    @Override
    public String toString() {
        String are_TK;
        if(this.tenkey == true){
            are_TK = "are";
        }
        else{
            are_TK = "nu are";
        }

        String este_mecanica;
        if(this.mecanica == true){
            este_mecanica = "este";
        }
        else{
            este_mecanica = "nu este";
        }

        String are_switch;
        if(this.culoareSwitch == null){
            are_switch = "";
        }
        else{
            are_switch = " Are switch-uri de culoare " + this.culoareSwitch + ".";
        }

        return "Tastatura este de la brandul " + this.brand.toUpperCase() + ", se numeste " + this.nume.toUpperCase()
                + ", " + are_TK + " numpad, " + este_mecanica + " mecanica, iar culoarea ledurilor este " +
                this.culoareLED.toLowerCase() + "." + are_switch;
    }
}
