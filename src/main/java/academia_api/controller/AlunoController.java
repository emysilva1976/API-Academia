package academia_api.controller;

import academia_api.dto.AlunoDTO;
import academia_api.entity.Aluno;
import academia_api.response.ApiResponse;
import academia_api.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Aluno>>> listar() {
        return ResponseEntity.ok(ApiResponse.success("Alunos listados", service.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Aluno>> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Aluno encontrado", service.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Aluno>> criar(@Valid @RequestBody AlunoDTO dto) {
        return ResponseEntity.status(201).body(ApiResponse.success("Aluno criado", service.salvar(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Aluno>> atualizar(@PathVariable Long id, @Valid @RequestBody AlunoDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Aluno atualizado", service.atualizar(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok(ApiResponse.success("Aluno removido", null));
    }
}
