package br.com.infnet.pbtp1;

import br.com.infnet.pbtp1.entity.Gato;
import br.com.infnet.pbtp1.repository.GatoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GatoServiceTests {
    private GatoRepository gatoRepository;
    private GatoService gatoService;
    private Gato gato;

    @BeforeEach
    void setUp() {
        gatoRepository = Mockito.mock(GatoRepository.class);
        gatoService = new GatoService(gatoRepository);
        gato = new Gato();
    }

    @Test
    @DisplayName("Chamando função validarGatoDados deve retornar true quando todos os campos forem válidos")
    void validarGatoDados_CamposValidos(){
        gato.setNome("Manon");
        gato.setDescricao("Tem um lindo kateyes");
        gato.setGenero('F');
        boolean result  = gatoService.validarGatoDados(gato);
        assertTrue(result);
    }

    @Test
    @DisplayName("Chamando função validarGatoDados deve retornar false quando nome for NULL")
    void validarGatoDados_NomeNull(){
       gato.setNome(null);
       gato.setGenero('F');
       boolean result  = gatoService.validarGatoDados(gato);
       assertFalse(result);
    }

    @Test
    @DisplayName("Chamando função validarGatoDados deve retornar false quando nome for vazio")
    void validarGatoDados_NomeIsBlank(){
        gato.setNome(" ");
        gato.setGenero('F');
        boolean result  = gatoService.validarGatoDados(gato);
        assertFalse(result);
    }

    @Test
    @DisplayName("Chamando função validarGatoDados deve retornar false quando nome for menor que 2 caracteres")
    void validarGatoDados_NomeLenght(){
        gato.setNome("Z");
        gato.setGenero('F');
        boolean result  = gatoService.validarGatoDados(gato);
        assertFalse(result);
    }

    @Test
    @DisplayName("Chamando função validarGatoDados deve retornar false quando descrição tiver mais de 300 caracteres")
    void validarGatoDados_DescricaoLenght(){
        gato.setNome("Zayra");
        gato.setDescricao("x".repeat(301));
        gato.setGenero('F');
        boolean result  = gatoService.validarGatoDados(gato);
        assertFalse(result);
    }

    @Test
    @DisplayName("Chamando função validarGatoDados deve retornar false quando genero for NULL")
    void validarGatoDados_GeneroNull(){
        gato.setNome("Zayra");
        gato.setGenero(null);
        boolean result  = gatoService.validarGatoDados(gato);
        assertFalse(result);
    }

    @Test
    @DisplayName("Chamando função validarGatoDados deve retornar false quando genero for invalido")
    void validarGatoDados_GeneroInvalido(){
        gato.setNome("Zayra");
        gato.setGenero('z');
        boolean result  = gatoService.validarGatoDados(gato);
        assertFalse(result);
    }

    @Test
    @DisplayName("Deve criar gato quando dados forem todos válidos")
    void criarGato_DadosValidos(){
        gato.setNome("Elsa");
        gato.setDescricao("Let it go let it go");
        gato.setGenero('F');

        when(gatoRepository.save(gato)).thenReturn(gato);
        Gato result = gatoService.criarGato(gato);

        assertNotNull(result);
        verify(gatoRepository, times(1)).save(gato);

    }

    @Test
    @DisplayName("Não deve criar gato quando dados forem inválidos")
    void criarGato_DadosInvalidos(){
        gato.setNome("E");

        Gato result = gatoService.criarGato(gato);

        assertNull(result);
        verify(gatoRepository,never()).save(any());

    }

    @Test
    @DisplayName("Não deve criar gato quando houve error de conexão com banco de dados")
    void criarGato_ErrorConexao(){
        gato.setNome("Elsa");
        gato.setDescricao("Let it go let it go");
        gato.setGenero('F');

        when(gatoRepository.save(any()))
                .thenThrow(new RuntimeException("Erro de conexão com o banco de dados"));

        Gato result = gatoService.criarGato(gato);

        assertNull(result);
        verify(gatoRepository, times(1)).save(any());

    }


    @Test
    @DisplayName("Buscar gato por ID deve retornar o gato quando id existir")
    void buscarGatoPorId_Existente(){
        gato.setId(1L);

        when(gatoRepository.findById(1L)).thenReturn(Optional.of(gato));
        Gato result = gatoService.buscarGatoPorId(1L);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Buscar gato por ID deve retornar null ao id inexistente")
    void buscarGatoPorId_Inexistente(){
        when(gatoRepository.findById(1L)).thenReturn(Optional.empty());
        Gato result = gatoService.buscarGatoPorId(1L);

        assertNull(result);
    }

    @Test
    @DisplayName("Buscar gato por ID deve retornar null quando dê error de conexão com o banco")
    void buscarGatoPorId_ErrorConexao() {
        when(gatoRepository.findById(1L))
                .thenThrow(new RuntimeException("Erro de conexão com o banco de dados"));

        Gato result = gatoService.buscarGatoPorId(1L);

        assertNull(result);
        verify(gatoRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve deletar gato quando id existir")
    void deletarGato_Existente(){
        gato.setId(1L);

        when(gatoRepository.findById(1L)).thenReturn(Optional.of(gato));
        boolean result = gatoService.deletarGato(1L);

        assertTrue(result);
        verify(gatoRepository, times(1)).delete(gato);
    }

    @Test
    @DisplayName("Deletar gato deve retornar false ao id inexistente")
    void deletarGato_Inexistente(){
        when(gatoRepository.findById(1L)).thenReturn(Optional.empty());
        boolean result = gatoService.deletarGato(1L);

        assertFalse(result);
        verify(gatoRepository, never()).delete(any());
    }

    @Test
    @DisplayName("Deletar gato deve retornar false quando houver error de conexão com o banco de daod")
    void deletarGato_ErrorConexao() {
        gato.setId(1L);

        when(gatoRepository.findById(1L)).thenReturn(Optional.of(gato));
        doThrow(new RuntimeException("Erro de conexão com banco de dados."))
                .when(gatoRepository).delete(gato);

        boolean result = gatoService.deletarGato(1L);

        assertFalse(result);
        verify(gatoRepository, times(1)).delete(gato);
    }


    @Test
    @DisplayName("Deve atualizar disponibilidade de adoção gato quando id existir")
    void atualizarGato_Existente(){
        gato.setId(1L);

        when(gatoRepository.findById(1L)).thenReturn(Optional.of(gato));
        boolean result = gatoService.atualizarDisponibilidadeAdocao(1L, false);

        assertTrue(result);
        verify(gatoRepository, times(1)).save(gato);
        assertFalse(gato.isDisponivelAdocao());
    }

    @Test
    @DisplayName("Atualizar disponibilidade de adoção deve retornar false ao id inexistente")
    void atualizarGato_Inexistente(){
        when(gatoRepository.findById(1L)).thenReturn(Optional.empty());
        boolean result = gatoService.atualizarDisponibilidadeAdocao(1L, false);

        assertFalse(result);
        verify(gatoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Atualizar disponibilidade de adoção deve retornar false quando houver error de conexão")
    void atualizarGato_ErrorConexao() {
        gato.setId(1L);

        when(gatoRepository.findById(1L)).thenReturn(Optional.of(gato));
        when(gatoRepository.save(any()))
                .thenThrow(new RuntimeException("Erro de conexão"));

        boolean result = gatoService.atualizarDisponibilidadeAdocao(1L, false);

        assertFalse(result);
    }

    @Test
    @DisplayName("Deve retornar a lista de gatos")
    void listarGatos(){
        List<Gato> lista = List.of(new Gato(), new Gato(), new Gato(), new Gato());
        when(gatoRepository.findAll()).thenReturn(lista);

        List<Gato> result = gatoService.listarGatos();
        assertEquals(4, result.size());
    }
}
