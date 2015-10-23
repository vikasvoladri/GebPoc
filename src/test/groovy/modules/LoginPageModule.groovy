package modules

import geb.Module

/**
 * Created by vikasv on 10/15/2015.
 */
class LoginPageModule extends Module{

    static content={
        emailAddressField{$("#emailId")}
        passwordField{$("#password")}
        signInBtn{$("#signInBtn")}


    }
}
