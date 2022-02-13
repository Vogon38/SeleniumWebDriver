package leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest {

    private LeiloesPage paginaLeiloes;
    private CadastroLeilaoPage paginaCadastro;

    @BeforeEach
    public void beforeEach() {
        LoginPage paginaLogin = new LoginPage();
        paginaLogin.preencheFormularioDeLogin("fulano", "pass");
        this.paginaLeiloes = paginaLogin.efetuarLogin();
        this.paginaCadastro = paginaLeiloes.carregarFormulario();
    }

    @AfterEach
    public void afterEach() {
        this.paginaLeiloes.fechar();
    }

    @Test
    public void deveriaCadastrarLeilao() {
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilão do dia " + hoje;
        String valor = "500.00";

        this.paginaLeiloes = paginaCadastro.cadastrarLeilao(nome, valor, hoje);
        Assertions.assertTrue(paginaLeiloes.isLeilaoCadastrado(nome, valor, hoje));
    }

    @Test
    public void deveriaValidarCadastrarLeilao() {
        this.paginaLeiloes = paginaCadastro.cadastrarLeilao("", "", "");
        Assertions.assertFalse(this.paginaCadastro.isPaginaAtual());
        Assertions.assertTrue(this.paginaLeiloes.isPaginaAtual());
        Assertions.assertTrue(this.paginaCadastro.isMensagensValidacoesVisiveis());
    }

}
