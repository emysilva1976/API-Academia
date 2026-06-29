package academia_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlunoDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "A idade é obrigatória")
    @Min(value = 1, message = "A idade deve ser maior que 0")
    private Integer idade;
}
