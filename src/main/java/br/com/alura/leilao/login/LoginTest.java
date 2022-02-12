package br.com.alura.leilao.login;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private LoginPage paginaLogin;

    @BeforeEach
    public void beforeEach() {
        this.paginaLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        this.paginaLogin.fechar();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        paginaLogin.preencheFormularioDeLogin("fulano", "pass");
        paginaLogin.efetuaLogin();

        Assertions.assertFalse(paginaLogin.isPaginaLogin());
        Assertions.assertEquals("fulano", paginaLogin.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaEfetuarLoginComDadosInvalidos() {
        paginaLogin.preencheFormularioDeLogin("invalido", "123");
        paginaLogin.efetuaLogin();

        Assertions.assertTrue(paginaLogin.isNotPaginaLogin());
        Assertions.assertNull(paginaLogin.getNomeUsuarioLogado());
        Assertions.assertTrue(paginaLogin.contemTexto("Usuário e senha inválidos."));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        paginaLogin.navegaParaPaginaLances();

        Assertions.assertTrue(paginaLogin.isPaginaLogin());
        Assertions.assertFalse(paginaLogin.contemTexto("Dados do Leilão"));
    }

}
