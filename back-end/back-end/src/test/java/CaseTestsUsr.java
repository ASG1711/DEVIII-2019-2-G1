import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class CaseTestsUsr {
    
    @Test
    public void testReg1() throws Exception {
        
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        WebDriver Gdriver = new ChromeDriver();
    
        System.out.println("Iniciando teste: USR-1 - Criar usuário no sistema. Usando primeiro set.");
        Gdriver.get("http://localhost:8080/index.html#!/home");
        Thread.sleep(5000);
        
        System.out.println("Capturando campos.");
        WebElement nome = Gdriver.findElement(By.id("nome"));
        WebElement apelido = Gdriver.findElement(By.id("apelido"));
        WebElement dtNasc = Gdriver.findElement(By.id("dtNascimento"));
        WebElement email = Gdriver.findElement(By.id("email"));
        WebElement password = Gdriver.findElement(By.id("senha"));
        WebElement submit = Gdriver.findElement(By.id("submit"));
        
        System.out.println("Inserindo valores.");
        System.out.println("Nome...");
        nome.sendKeys("Teste1");
        Thread.sleep(5000);
        System.out.println("Apelido...");
        apelido.sendKeys("Teste1");
        Thread.sleep(5000);
        System.out.println("Data de nascimento...");
        dtNasc.sendKeys("01/01/2001");
        Thread.sleep(5000);
        System.out.println("Email...");
        email.sendKeys("Teste1@abc.com");
        Thread.sleep(5000);
        System.out.println("Senha...");
        password.sendKeys("abcd1234");
        Thread.sleep(5000);
        
        System.out.println("Valores inseridos. Submetendo.");
        submit.click();
    }
    
    @Test
    public void testReg2() throws Exception {
        
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        WebDriver Gdriver = new ChromeDriver();
    
        System.out.println("Iniciando teste: USR-1 - Criar usuário no sistema. Usando segundo set.");
        Gdriver.get("http://localhost:8080/index.html#!/home");
        Thread.sleep(5000);
        
        System.out.println("Capturando campos.");
        WebElement nome = Gdriver.findElement(By.id("nome"));
        WebElement apelido = Gdriver.findElement(By.id("apelido"));
        WebElement dtNasc = Gdriver.findElement(By.id("dtNascimento"));
        WebElement email = Gdriver.findElement(By.id("email"));
        WebElement password = Gdriver.findElement(By.id("senha"));
        WebElement submit = Gdriver.findElement(By.id("submit"));
        
        System.out.println("Inserindo valores.");
        System.out.println("Nome...");
        nome.sendKeys("Teste#2");
        Thread.sleep(5000);
        System.out.println("Apelido...");
        apelido.sendKeys("Teste#2");
        Thread.sleep(5000);
        System.out.println("Data de nascimento...");
        dtNasc.sendKeys("01/01/2001");
        Thread.sleep(5000);
        System.out.println("Email...");
        email.sendKeys("Teste#2@abc.com");
        Thread.sleep(5000);
        System.out.println("Senha...");
        password.sendKeys("@bcd!2#4");
        Thread.sleep(5000);
        
        System.out.println("Valores inseridos. Submetendo.");
        submit.click();
    }
    
    @Test
    public void testAuth1() throws Exception {
        
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        WebDriver Gdriver = new ChromeDriver();
        
        System.out.println("Iniciando teste: USR-2 - Logar no sistema. Usando primeiro set.");
        Gdriver.get("http://localhost:8080/index.html#!/home");
        Thread.sleep(5000);
        
        System.out.println("Capturando campos (Email e senha).");
        WebElement email = Gdriver.findElement(By.id("email"));
        WebElement password = Gdriver.findElement(By.id("senha"));
        WebElement submit = Gdriver.findElement(By.id("submit"));
        
        System.out.println("Inserindo valores.");
        System.out.println("Email...");
        email.sendKeys("Teste1@abc.com");
        Thread.sleep(5000);
        System.out.println("Senha...");
        password.sendKeys("abcd1234");
        Thread.sleep(5000);
        
        System.out.println("Valores inseridos. Submetendo.");
        submit.click();
    }
    
    @Test
    public void testAuth2() throws Exception {
        
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        WebDriver Gdriver = new ChromeDriver();
        
        System.out.println("Iniciando teste: USR-2 - Logar no sistema. Usando segundo set.");
        Gdriver.get("http://localhost:8080/index.html#!/home");
        Thread.sleep(5000);
        
        System.out.println("Capturando campos (Email e senha).");
        WebElement email = Gdriver.findElement(By.id("email"));
        WebElement password = Gdriver.findElement(By.id("senha"));
        WebElement submit = Gdriver.findElement(By.id("submit"));
        
        System.out.println("Inserindo valores.");
        System.out.println("Email...");
        email.sendKeys("Teste#2@abc.com");
        Thread.sleep(5000);
        System.out.println("Senha...");
        password.sendKeys("@bcd!2#4");
        Thread.sleep(5000);
        
        System.out.println("Valores inseridos. Submetendo.");
        submit.click();
    }
    
}
