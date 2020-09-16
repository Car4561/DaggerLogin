package carlosllerena.daggerlogin.root;

import android.app.Application;

import carlosllerena.daggerlogin.login.LoginModule;

public class App extends Application {

    private AplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAplicationComponent.builder()
                .aplicationModule(new AplicationModule(this))
                .loginModule(new LoginModule())
                .build();

    }

    public AplicationComponent getComponent(){
        return component;
    }

}
