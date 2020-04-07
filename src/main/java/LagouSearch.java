import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class LagouSearch {
    public static void main(String[] args) throws InterruptedException, IOException {
//        设置webdriver路径
//        System.setProperty("webdriver.chrome.drive",BossSearch.class.getClassLoader().getResource("chromedriver.exe").getPath());
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//        创建webdriver

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.lagou.com/gongsi/");

//        筛选条件
        ClickOption(webDriver, "西安");
        ClickOption(webDriver, "上市公司");
        ClickOption(webDriver, "50-150人");
        ClickOption(webDriver, "移动互联网");

//获取一个输出流
        File outfile = new File("CompanyName.txt");
        FileOutputStream fos = new FileOutputStream(outfile, true);

//        解析页面结果
        CompanyName(webDriver, fos);
        fos.close();

    }

    private static void CompanyName(WebDriver webDriver, FileOutputStream fos) throws InterruptedException, IOException {
//        遍历公司模块
        List<WebElement> jobelements = webDriver.findElements(By.className("company-item"));
        for (WebElement jobelement : jobelements) {
//            获取公司名称
            WebElement companyName = jobelement.findElement(By.className("top")).findElement(By.className("company-name")).findElement(By.tagName("a"));
            String s = companyName.getText();
//            输出到文件
            byte[] b = s.getBytes();
            fos.write(b);
            fos.write("\r\n".getBytes());
            System.out.println(s);
        }

//        点击下一页
        WebElement pager_next = webDriver.findElement(By.className("pager_next"));
        if (!pager_next.getAttribute("class").contains("pager_next_disabled")) {
            pager_next.click();
            System.out.println("解析下一页……");
            fos.write("解析下一页……\r\n".getBytes());
            fos.write("============================================\r\n".getBytes());

//            以免点击完由于网络问题没有跳出去就立刻开始解析页面
            Thread.sleep(1000L);
            CompanyName(webDriver, fos);
        } else {
            System.out.println("解析结束");
            fos.write("解析结束\r\n".getBytes());
        }
    }
//筛选条件页面
    private static void ClickOption(WebDriver webDriver, String chosen) {
        WebElement chosenElement = webDriver.findElement(By.xpath("//a[contains(text(),'" + chosen + "')]"));
        chosenElement.click();
    }
}
