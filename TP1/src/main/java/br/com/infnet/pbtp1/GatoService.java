package br.com.infnet.pbtp1;

import br.com.infnet.pbtp1.entity.Gato;
import br.com.infnet.pbtp1.repository.GatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatoService {
    private GatoRepository gatoRepository;

    public GatoService(GatoRepository gatoRepository) {
        this.gatoRepository = gatoRepository;
    }

    public boolean validarGatoDados(Gato gato){
        if (gato.getNome() == null || gato.getNome().isBlank() || gato.getNome().length() < 2) {
            System.out.println("O nome deve ter pelo menos 2 caracteres.");
            return false;
        }
        if (gato.getDescricao() != null && gato.getDescricao().length() > 300) {
            System.out.println("A descrição deve ter no máximo 300 caracteres.");
            return false;
        }
        if (gato.getGenero() == null || (gato.getGenero() != 'M' && gato.getGenero() != 'F')) {
            System.out.println("Gênero inválido. Use 'M' para macho ou 'F' para fêmea.");
            return false;
        }
        return true;
    }

    public Gato criarGato(Gato gato) {
        try {
            if (!validarGatoDados(gato)) return null;
            System.out.println("Gato Criado com sucesso.");
            return gatoRepository.save(gato);
        } catch (Exception e) {
            System.out.println("Erro ao salvar gato: " + e.getMessage());
            return null;
        }
    }

    public Gato buscarGatoPorId(Long id) {
        try {
            Gato gato = gatoRepository.findById(id).orElse(null);
            if (gato == null) {
                System.out.println("Não foi possível encontrar gato com ID: " + id);
            }
            return gato;
        } catch (Exception e) {
            System.out.println("Erro ao buscar gato: " + e.getMessage());
            return null;
        }
    }

    public boolean deletarGato(Long id) {
        try {
            Gato gato = buscarGatoPorId(id);
            if (gato == null) return false;
            System.out.println("Gato Deletado com sucesso.");
            gatoRepository.delete(gato);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao deletar gato: " + e.getMessage());
            return false;
        }
    }

    public List<Gato> listarGatos() {
        try {
            return gatoRepository.findAll();
        } catch (Exception e) {
            System.out.println("Erro ao listar gatos: " + e.getMessage());
            return List.of();
        }
    }

    public boolean atualizarDisponibilidadeAdocao(Long id, boolean disponivel) {
        try {
            Gato gato = buscarGatoPorId(id);
            if (gato == null) return false;
            gato.setDisponivelAdocao(disponivel);
            gatoRepository.save(gato);
            System.out.println("Gato Atualizado com sucesso.");
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar disponibilidade para adoção: " + e.getMessage());
            return false;
        }
    }

}
