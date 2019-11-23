import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class CaseTestsPos {
    
    @Test
    public void testPos1() throws Exception {
        
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        WebDriver Gdriver = new ChromeDriver();
    
        System.out.println("Iniciando teste: POS-2 - Realizar postagem.");
        Gdriver.get("http://http://localhost:8080/dashboard/dashboard.html");
        Thread.sleep(5000);
        
        System.out.println("Capturando campos.");
        WebElement texto = Gdriver.findElement(By.id("texto"));
        WebElement submit = Gdriver.findElement(By.id("submit"));
        
        System.out.println("Inserindo valores.");
        System.out.println("Texto...");
        texto.sendKeys("Postagem de teste. Abc123!@#");
        Thread.sleep(5000);
        
        System.out.println("Valores inseridos. Submetendo.");
        submit.click();
    }
    
    @Test
    public void testPos2() throws Exception {
        
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        WebDriver Gdriver = new ChromeDriver();
    
        System.out.println("Iniciando teste: POS-2 - Deletar postagem.");
        Gdriver.get("http://http://localhost:8080/dashboard/dashboard.html");
        Thread.sleep(5000);
        
        System.out.println("Capturando campos.");
        WebElement submitDel = Gdriver.findElement(By.id("submitDel"));
        
        System.out.println("Selecionando postagem a deletar.");
        Thread.sleep(5000);
        
        System.out.println("Postagem selecionada. Submetendo.");
        submitDel.click();
    }
    
    @Test
    public void testPos3() throws Exception {
        
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        WebDriver Gdriver = new ChromeDriver();
    
        System.out.println("Iniciando teste: POS-3 - Curtir postagem.");
        Gdriver.get("http://http://localhost:8080/dashboard/dashboard.html");
        Thread.sleep(5000);
        
        System.out.println("Capturando campos.");
        WebElement curtida = Gdriver.findElement(By.id("curtidas"));
        
        System.out.println("Selecionando postagem a curtir.");
        Thread.sleep(5000);
        
        System.out.println("Postagem selecionada. Curtindo.");
        curtida.click();
    }
     
    @Test
    public void testPos4() throws Exception {
        
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        WebDriver Gdriver = new ChromeDriver();
    
        System.out.println("Iniciando teste: POS-4 - Cancelar curtida de postagem.");
        Gdriver.get("http://http://localhost:8080/dashboard/dashboard.html");
        Thread.sleep(5000);
        
        System.out.println("Capturando campos.");
        WebElement curtida = Gdriver.findElement(By.id("curtidas"));
        
        System.out.println("Selecionando postagem a descurtir.");
        Thread.sleep(5000);
        
        System.out.println("Postagem selecionada. Removendo curtida.");
        curtida.click();
    }
    
    public void testPos5() throws Exception {
        
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        WebDriver Gdriver = new ChromeDriver();
    
        System.out.println("Iniciando teste: POS-5 - Comentar postagem.");
        Gdriver.get("http://http://localhost:8080/dashboard/dashboard.html");
        Thread.sleep(5000);
        
        System.out.println("Capturando campos.");
        WebElement comentario = Gdriver.findElement(By.id("comentario"));
        WebElement submitCom = Gdriver.findElement(By.id("submitCom"));
        
        System.out.println("Comentando postagem.");
        Thread.sleep(5000);
        
        System.out.println("Submetendo.");
        submitCom.click();
    }
    
    public void testPos6() throws Exception {
        
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        WebDriver Gdriver = new ChromeDriver();
    
        System.out.println("Iniciando teste: POS-6 - Remover comentário de postagem.");
        Gdriver.get("http://http://localhost:8080/dashboard/dashboard.html");
        Thread.sleep(5000);
        
        System.out.println("Capturando campos.");
        WebElement submitDelCom = Gdriver.findElement(By.id("submitDelCom"));
        
        System.out.println("Selecionando comentário a deletar.");
        Thread.sleep(5000);
        
        System.out.println("Postagem selecionada. Submetendo.");
        submitDelCom.click();
    }
    
}