package pe.edu.cibertec.patitasfrontendwc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import pe.edu.cibertec.patitasfrontendwc.dto.LoginRequestDTO;
import pe.edu.cibertec.patitasfrontendwc.dto.LoginResponseDTO;
import pe.edu.cibertec.patitasfrontendwc.viewmodel.LoginModel;

/*1*/
@Controller
@RequestMapping("/login")
public class LoginController {
  /*part2 num2*/
    @Autowired
    RestTemplate restTemplateAutenticacion;

/*part2 num 2*/
    @GetMapping("/inicio")
    public String inicio(Model model) {
        LoginModel loginModel = new LoginModel("00", "", "");
        model.addAttribute("loginModel", loginModel);
        return "inicio";
    }

    @PostMapping("/autenticar")
    public String autenticar(@RequestParam("tipoDocumento") String tipoDocumento,
                             @RequestParam("numeroDocumento") String numeroDocumento,
                             @RequestParam("password") String password,
                             Model model) {

        // Validar campos de entrada
        if (tipoDocumento == null || tipoDocumento.trim().length() == 0 ||
                numeroDocumento == null || numeroDocumento.trim().length() == 0 ||
                password == null || password.trim().length() == 0){

            LoginModel loginModel = new LoginModel("01", "Error: Debe completar correctamente sus credenciales", "");
            model.addAttribute("loginModel", loginModel);
            return "inicio";
        }
     try {
         //INVOCAR API DE VALIDACION DE USUARIO
         /*part 2num 5*/

         LoginRequestDTO loginRequestDTO = new LoginRequestDTO(tipoDocumento, numeroDocumento, password);
         LoginResponseDTO loginResponseDTO = restTemplateAutenticacion.postForObject("/login", loginRequestDTO, LoginResponseDTO.class);

         //validar respuesta
         if(loginResponseDTO.codigo().equals("00")){

             LoginModel loginModel = new LoginModel("00", "", loginResponseDTO.nombreUsuario());
             model.addAttribute("loginModel", loginModel);
             return "principal";
         } else {


             /**/
             LoginModel loginModel = new LoginModel("02", "Error: Autenticacion fallida", "");
             model.addAttribute("loginModel", loginModel);
             return "inicio";
         }

            } catch (Exception e) {

             LoginModel loginModel = new LoginModel("99", "Error: Ocurrio un problema con la autenticacion", "");
             model.addAttribute("loginModel", loginModel);
             System.out.println(e.getMessage());
             return "inicio";
                 }
              }

            }

