package pages

import geb.Page
import modules.LoginPageModule

/**
 * Created by vikasv on 10/15/2015.
 */
class LoginPage extends Page{

    static at = {waitFor("mediumwait") {
        loginPageModule.emailAddressField.displayed

        }
    }

    static content={
        loginPageModule{module(LoginPageModule)}
    }

    def logInToYatraWithValidCredentials(String sUsername, String sPassword){
        loginPageModule.emailAddressField.value(sUsername)
        loginPageModule.passwordField << sPassword
        loginPageModule.signInBtn.click()
    }


}
