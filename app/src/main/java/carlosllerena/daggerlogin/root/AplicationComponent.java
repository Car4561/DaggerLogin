package carlosllerena.daggerlogin.root;

import javax.inject.Singleton;

import carlosllerena.daggerlogin.http.TwitchModule;
import carlosllerena.daggerlogin.login.LoginActivity;
import carlosllerena.daggerlogin.login.LoginModule;
import dagger.Component;

@Singleton
@Component(modules = {AplicationModule.class, LoginModule.class, TwitchModule.class})
public interface AplicationComponent {

    void inject (LoginActivity target);

}
