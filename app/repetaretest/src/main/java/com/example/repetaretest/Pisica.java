package com.example.repetaretest;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Pisica implements Parcelable {
    private String rasa;
    private String culoare;
    private Float greutate;
    private Boolean arePedigree;
    private Boolean esteVaccinata;
    private Double pret;

    public Pisica(){
        this.rasa = null;
        this.culoare = null;
        this.greutate = null;
        this.arePedigree = null;
        this.esteVaccinata = null;
        this.pret = null;
    }

    public Pisica(String rasa, String culoare, Float greutate, Boolean arePedigree, Boolean esteVaccinata, Double pret) {
        this.rasa = rasa;
        this.culoare = culoare;
        this.greutate = greutate;
        this.arePedigree = arePedigree;
        this.esteVaccinata = esteVaccinata;
        this.pret = pret;
    }

    protected Pisica(Parcel in) {
        rasa = in.readString();
        culoare = in.readString();
        if (in.readByte() == 0) {
            greutate = null;
        } else {
            greutate = in.readFloat();
        }
        byte tmpArePedigree = in.readByte();
        arePedigree = tmpArePedigree == 0 ? null : tmpArePedigree == 1;
        byte tmpEsteVaccinata = in.readByte();
        esteVaccinata = tmpEsteVaccinata == 0 ? null : tmpEsteVaccinata == 1;
        if (in.readByte() == 0) {
            pret = null;
        } else {
            pret = in.readDouble();
        }
    }

    public static final Creator<Pisica> CREATOR = new Creator<Pisica>() {
        @Override
        public Pisica createFromParcel(Parcel in) {
            return new Pisica(in);
        }

        @Override
        public Pisica[] newArray(int size) {
            return new Pisica[size];
        }
    };

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public Float getGreutate() {
        return greutate;
    }

    public void setGreutate(Float greutate) {
        this.greutate = greutate;
    }

    public Boolean getArePedigree() {
        return arePedigree;
    }

    public void setArePedigree(Boolean arePedigree) {
        this.arePedigree = arePedigree;
    }

    public Boolean getEsteVaccinata() {
        return esteVaccinata;
    }

    public void setEsteVaccinata(Boolean esteVaccinata) {
        this.esteVaccinata = esteVaccinata;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(rasa);
        dest.writeString(culoare);
        if (greutate == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(greutate);
        }
        dest.writeByte((byte) (arePedigree == null ? 0 : arePedigree ? 1 : 2));
        dest.writeByte((byte) (esteVaccinata == null ? 0 : esteVaccinata ? 1 : 2));
        if (pret == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(pret);
        }
    }

    @Override
    public String toString() {
        return "Pisica{" +
                "rasa='" + rasa + '\'' +
                ", culoare='" + culoare + '\'' +
                ", greutate=" + greutate +
                ", arePedigree=" + arePedigree +
                ", esteVaccinata=" + esteVaccinata +
                ", pret=" + pret +
                '}';
    }
}
