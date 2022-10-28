package kof.kof.control;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@MultipartConfig(maxFileSize = 1024*1024*100)

@WebServlet(name = "ServletKof",  urlPatterns = {
        "/ServletKof",
        "/list-kof", //get
        "/get-kof", //Get
        "/create-kof", //get
        "/save-kof", //Post
        "/update-kof", //Post
        "/delete-kof", //Delete,
        "/change-image",
        "/updateImage"
}  )
public class ServletKof extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        kof.kof.model.DaoKof daoKof = new kof.kof.model.DaoKof();
        String option = request.getServletPath();


        switch (option) {
            case "/list-kof":

                List<kof.kof.model.BeanKof> listKof = daoKof.listKof();
                request.setAttribute("list", listKof);
                request.getRequestDispatcher("/WEB-INF/view/list-kof.jsp").forward(request, response);

                break;



            case "/create-kof":

                request.getRequestDispatcher("/WEB-INF/view/create-kof.jsp").forward(request, response);

                break;


            case "/get-kof":
                String idString = request.getParameter("id") != null ? request.getParameter("id") : "0";
                try {
                    int id = Integer.parseInt(idString);
                    kof.kof.model.BeanKof kof = daoKof.consultKof(id); //DAO consulta a la db
                    request.setAttribute("kofX", kof);
                    request.getRequestDispatcher("/WEB-INF/view/get-kof.jsp").forward(request,response);
                } catch (Exception e) {
                    response.sendRedirect("list-persons");
                }

                break;
            default:

        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        kof.kof.model.DaoKof daoKof = new kof.kof.model.DaoKof();
        String option = request.getServletPath();

        switch (option) {

            case "/save-kof":

                try {
                    //recibir valores del formulario
                    String name = request.getParameter("name") != null ? (request.getParameter("name")) : "";
                    String description = request.getParameter("description") != null ? (request.getParameter("description")) : "";
                    String birthday = request.getParameter("birthday") != null ? (request.getParameter("birthday")) : "";
                    Boolean utiliza_magia = Boolean.valueOf(request.getParameter("utiliza_magia") != null ? (request.getParameter("utiliza_magia")) : "");
                    String peso = request.getParameter("peso") != null ? (request.getParameter("peso")) : "";
                    String estatura = request.getParameter("estatura") != null ? (request.getParameter("estatura")) : "";
                    Part filePart = request.getPart("image");
                    InputStream image = filePart.getInputStream();
                    //aqui es para que pueda yo leer la fecha
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);

                    kof.kof.model.BeanKof kof = new kof.kof.model.BeanKof();
                    kof.setName(name);
                    kof.setLastname(description);
                    kof.setBirthday(date);
                    kof.setUtiliza_magia(utiliza_magia);
                    kof.setEstatura(Integer.parseInt(estatura));
                    kof.setPeso(Integer.parseInt(peso));

                    boolean result = daoKof.saveKof(kof);
                    response.sendRedirect("list-kof?result-save=" + (result ? "ok" : "error"));
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("list-kof");
                }
                break;



            case "/update-kof":

                try {
                    String idUpdate = request.getParameter("id") != null ? request.getParameter("id") : "0";

                    String name = request.getParameter("name") != null ? (request.getParameter("name")) : "";
                    String description = request.getParameter("description") != null ? (request.getParameter("description")) : "";
                    String birthday = request.getParameter("birthday") != null ? (request.getParameter("birthday")) : "";
                    Boolean utiliza_magia = Boolean.valueOf(request.getParameter("utiliza_magia") != null ? (request.getParameter("utiliza_magia")) : "");
                    String estatura = request.getParameter("estatura") != null ? (request.getParameter("estatura")) : "";
                    String peso = request.getParameter("peso") != null ? (request.getParameter("peso")) : "";
                    String equipo = request.getParameter("equipo") != null ? (request.getParameter("equipo")) : "";
                    String magia_id = request.getParameter("magia_id") != null ? (request.getParameter("magia_id")) : "";
                    String tipo_lucha_id = request.getParameter("tipo_lucha_id") != null ? (request.getParameter("tipo_lucha_id")) : "";


                    //aqui es para que pueda yo leer la fecha
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);

                    kof.kof.model.BeanKof kof = new kof.kof.model.BeanKof();
                    kof.setId(Integer.parseInt(idUpdate));
                    kof.setName(name);
                    kof.setLastname(description);
                    kof.setBirthday(date);
                    kof.setUtiliza_magia(utiliza_magia);
                    kof.setEstatura(Integer.parseInt(estatura));
                    kof.setPeso(Integer.parseInt(peso));
                    kof.setEstatura(Integer.parseInt(equipo));
                    kof.setEstatura(Integer.parseInt(magia_id));
                    kof.setEstatura(Integer.parseInt(tipo_lucha_id));

                  //  System.out.println(kof.toString());

                    boolean result = daoKof.updateKof(kof);


                    response.sendRedirect("list-kof?result-update="+(result ? "ok" : "error"));


                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("list-kof");
                }

                break;


            default:
                System.out.println("post");
        }

    }
}
