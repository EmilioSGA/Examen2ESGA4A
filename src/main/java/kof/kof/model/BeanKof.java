package kof.kof.model;

import java.io.InputStream;
import java.util.Date;

public class BeanKof {
    private int id;
    private String name;
    private String lastname;
    private Date birthday;
    private boolean utiliza_magia;
    private double estatura;
    private double peso;
    private int equipo;

    public BeanKof() {
    }

    public BeanKof(int id, String name, String lastname, Date birthday, boolean utiliza_magia, double estatura, double peso, int equipo, int magia_id, int tipo_lucha_id) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.utiliza_magia = utiliza_magia;
        this.estatura = estatura;
        this.peso = peso;
        this.equipo = equipo;
        this.equipo = magia_id;
        this.equipo = tipo_lucha_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean getUtiliza_magia() {
        return utiliza_magia;
    }

    public void setUtiliza_magia(boolean utiliza_magia) {
        this.utiliza_magia = utiliza_magia;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getEquipo() {
        return equipo;
    }

    public void setEquipo(int equipo) {
        this.equipo = equipo;
    }
    public int getMagia_id() {
        return equipo;
    }

    public void setMagia_id(int magia_id) {
        this.magia_id = magia_id;
    }
    public int getTipo_lucha_id() {
        return equipo;
    }

    public void setTipo_lucha_id(int equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "BeanKof{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday=" + birthday +
                ", utiliza_magia='" + utiliza_magia + '\'' +
                ", estatura='" + estatura + '\'' +
                ", peso='" + peso + '\'' +
                ", equipo=" + equipo +
                '}';
    }
}
