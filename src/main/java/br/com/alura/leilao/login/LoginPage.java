package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

    private static final String URL_LOGIN = "http://localhost:8080/login";
    private static final String URL_LOGIN_ERROR = "http://localhost:8080/login?error";
    private static final String URL_LANCES = "http://localhost:8080/leiloes/2";

    private WebDriver browser;

    public LoginPage() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\luana\\Documents\\Drivers\\chromedriver.exe");
        this.browser = new ChromeDriver();
        this.browser.navigate().to(URL_LOGIN);
    }

    public void fechar() {
        this.browser.quit();
    }

    public void preencheFormularioDeLogin(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public void efetuaLogin() {
        browser.findElement(By.id("login-form")).submit();
    }

    public boolean isPaginaLogin() {
        return browser.getCurrentUrl().equals(URL_LOGIN);
    }

    public boolean isNotPaginaLogin() {
        return browser.getCurrentUrl().equals(URL_LOGIN_ERROR);
    }

    public Object getNomeUsuarioLogado() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void navegaParaPaginaLances() {
        browser.navigate().to(URL_LANCES);
    }


    public boolean contemTexto(String texto) {
        return browser.getPageSource().contains(texto);
    }

}
