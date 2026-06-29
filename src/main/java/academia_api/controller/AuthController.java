package academia_api.controller;

import academia_api.dto.LoginDTO;
import academia_api.entity.Usuario;
import academia_api.response.ApiResponse;
import academia_api.security.JwtUtil;
import academia_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody Usuario user) {
        if (user.getRole() == null) {
            user.setRole("ROLE_USER");
        }
        service.salvar(user);
        return ResponseEntity.status(201).body(ApiResponse.success("Usuário criado com sucesso", null));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginDTO login) {
        Usuario user = service.buscarPorUsername(login.getUsername());

        if (!service.validarSenha(login.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body(ApiResponse.error("Senha inválida", null));
        }

        String token = JwtUtil.gerarToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok(ApiResponse.success("Login realizado", token));
    }
}
