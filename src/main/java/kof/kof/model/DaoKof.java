package kof.kof.model;

import kof.kof.utils.MySQLConnection;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoKof {



    //Aqui haremos la conexxio´n de la base de datos

    //Esta variable es el puente (Conexión) entre la base y la app
    Connection conexion = new MySQLConnection().getConnection(); //Aqui llamamos a la funcion


    //Esta variable es para preparar la consulta (la query que deseeas en la bd)
    PreparedStatement preparedStatement;

    //En esta guardaremos todos los resultados
    ResultSet result ;

    //Listo , Ahora prepararemos las QUERY (consultas)  en dado caso lo mas comun es el CRUD

    private final String INSERT_KOF = "INSERT INTO `kof`.`personaje`(`name`,`lastname`,`birthday`,`utiliza_magia`,`estatura`,`peso`,`equipo`,`magia_id`, `tipo_lucha_id`)VALUES(?,?,?,?,?,?,?,?,?)";
    private final String GENERAL_KOF = "SELECT * FROM kof.personaje;";
    private final String CONSULT_UNIQUE = "SELECT * FROM kof.personaje  where id = ?";
    private final String UPDATE_KOF = "UPDATE `kof`.`personaje` SET `name` = ?, `lastname` = ?, `birthday` = ?, `utiliza_magia` = ?, `peso` = ?, `equipo` =  ?, `magia_id` =  ?, `tipo_lucha_id` =  ? WHERE (`id` = ?);";
    private final String DELETE_KOF = "DELETE FROM `kof`.`personaje` WHERE (`id` = ?);";




    public List<BeanKof> listKof() {
        List<BeanKof> listKofs = new ArrayList<>();
        BeanKof kof = null;
        try{

            preparedStatement = conexion.prepareStatement(GENERAL_KOF);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                kof = new BeanKof();
                kof.setId(result.getInt("id"));
                kof.setName(result.getString("name"));
                kof.setLastname(result.getString("lastname"));
                kof.setBirthday(result.getDate("birthday"));
                kof.setUtiliza_magia(result.getBoolean("utiliza_magia"));
                kof.setEstatura(result.getInt("estatura"));
                kof.setPeso(result.getInt("peso"));

                kof.setEquipo(result.getInt("equipo"));
                listKofs.add(kof);
            }

        }catch (SQLException e){
            System.out.println(e);
        }

        return listKofs;
    }





    //----------------------------------------------------------------------

    public boolean saveKof(BeanKof kof){
        try {
            preparedStatement = conexion.prepareStatement(INSERT_KOF);
            preparedStatement.setString(1, kof.getName());
            preparedStatement.setString(2, kof.getLastname());
            preparedStatement.setDate(3, new Date(kof.getBirthday().getTime()));
            preparedStatement.setBoolean(4,kof.getUtiliza_magia());
            preparedStatement.setDouble(5 , kof.getEstatura());
            preparedStatement.setDouble(6, kof.getPeso());
            return preparedStatement.executeUpdate()==1 ;//1==1

        }catch (SQLException e){
            System.out.println(e);
        }
        return false;

    }

    //------------------------------------------------------------------------------
    public BeanKof consultKof (int id){
        BeanKof kof = null;
        try{

            preparedStatement = conexion.prepareStatement(CONSULT_UNIQUE);
            preparedStatement.setInt(1,id);
            result = preparedStatement.executeQuery();
            if (result.next()) {
                kof = new BeanKof();
                kof.setId(result.getInt("id"));
                kof.setName(result.getString("name"));
                kof.setLastname(result.getString("lastname"));
                kof.setBirthday(result.getDate("birthday"));
                kof.setUtiliza_magia(result.getBoolean("utiliza_magia"));
                kof.setEstatura(result.getInt("estatura"));
                kof.setPeso(result.getInt("peso"));

                kof.setEquipo(result.getInt("equipo"));

            }
            //System.out.println(song.toString());
        }catch (SQLException e){
            System.out.println(e);
        }

       return kof;
    }




    public boolean deleteKof(int id) {
        try {
            preparedStatement = conexion.prepareStatement(DELETE_KOF);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate()==1; //1==1
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;

    }

    //---------------------------------------------
    public boolean updateKof(BeanKof kof){
        try {
            preparedStatement = conexion.prepareStatement(UPDATE_KOF);
            preparedStatement.setString(1, kof.getName());
            preparedStatement.setString(2, kof.getLastname());
            preparedStatement.setDate(3, new Date(kof.getBirthday().getTime()));
            preparedStatement.setBoolean(4,kof.getUtiliza_magia());
            preparedStatement.setDouble(6 , kof.getEstatura());
            preparedStatement.setDouble(5, kof.getPeso());
            preparedStatement.setInt(7, kof.getId());
            return preparedStatement.executeUpdate()==1 ;//1==1

        }catch (SQLException e){
            System.out.println(e);
        }
        return false;

    }
}
