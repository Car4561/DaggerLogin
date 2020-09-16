package carlosllerena.daggerlogin.login;

import android.util.Log;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {



    @Provides
    public LoginActivityMVP.Presenter providerLoginActivityPresenter(LoginActivityMVP.Model model){
        return  new LoginActivityPresenter(model);
    }

    @Provides
    public LoginActivityMVP.Model provideLoginActivityModel(LoginRepository repository){
        return new LoginActivityModel(repository);
    }

    @Provides
    public LoginRepository provideLoginRepository(){
        return new MemoryRepository();
    }


}
