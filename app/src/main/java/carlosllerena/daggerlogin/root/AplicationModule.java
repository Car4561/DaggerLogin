package carlosllerena.daggerlogin.root;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;


@Module
public class AplicationModule {

    private Application aplication;


    public  AplicationModule(Application aplication){
        this.aplication = aplication;
    }
    @Provides // siempre cuando se devuelve un valor en dagger
    @Singleton // solo ser instanciado una vez
    public Context providerContext(){
        return aplication;
    }


}
