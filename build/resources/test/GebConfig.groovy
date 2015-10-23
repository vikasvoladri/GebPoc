/**
 * Created by vikasv on 10/8/2015.
 */

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

baseUrl = "http://www.yatra.com/"