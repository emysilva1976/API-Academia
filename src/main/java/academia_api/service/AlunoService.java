package academia_api.service;

import academia_api.dto.AlunoDTO;
import academia_api.entity.Aluno;
import academia_api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public Aluno salvar(AlunoDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setIdade(dto.getIdade());
        return repository.save(aluno);
    }

    public List<Aluno> listar() {
        return repository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
    }

    public Aluno atualizar(Long id, AlunoDTO dto) {
        Aluno aluno = buscarPorId(id);
        aluno.setNome(dto.getNome());
        aluno.setIdade(dto.getIdade());
        return repository.save(aluno);
    }

    public void deletar(Long id) {
        Aluno aluno = buscarPorId(id);
        repository.delete(aluno);
    }
}
