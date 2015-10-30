/**
 * Created by vikasv on 10/8/2015.
 */


import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

waiting {
    timeout = 15

    presets {
        longwait {
            timeout = 25
            retryInterval = 1
        }

        mediumwait{
            timeout = 20
        }

        normalwait {
            timeout = 10
        }
    }
}

reportsDir = "target/geb-reports"

driver = {
    def ff = new FirefoxDriver()
    ff.manage().window().maximize()
    ff.manage().deleteAllCookies()
    return ff
}

environments {

    firefox {
        driver = {
            def ff = new FirefoxDriver()
            ff.manage().window().maximize()
            ff.manage().deleteAllCookies()
            return ff
        }
    }

    chrome {
        driver = {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +System.getProperty("file.separator")+"ChromeDriver"+System.getProperty("file.separator")+ "chromedriver.exe");
            def ch = new ChromeDriver()
            ch.manage().window().maximize()
            ch.manage().deleteAllCookies()
            return ch
        }
    }
}

baseUrl = "http://www.yatra.com/"