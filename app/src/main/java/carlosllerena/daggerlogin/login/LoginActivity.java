package carlosllerena.daggerlogin.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.internal.$Gson$Preconditions;

import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import carlosllerena.daggerlogin.http.twitch.Game;
import carlosllerena.daggerlogin.http.twitch.Stream;
import carlosllerena.daggerlogin.http.twitch.Streams;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;

import javax.inject.Inject;

import carlosllerena.daggerlogin.R;
import carlosllerena.daggerlogin.http.TwitchAPI;
import carlosllerena.daggerlogin.http.twitch.Twitch;
import carlosllerena.daggerlogin.root.App;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements  LoginActivityMVP.View {

    @Inject
    LoginActivityMVP.Presenter presenter;

    @Inject
    TwitchAPI twitchAPI;

    @BindView(R.id.txtName)
    EditText firstName;

    @BindView(R.id.txtSurname)
    EditText lastName;

    @BindView(R.id.btnEntrar)
    Button logginButton;

    @BindString(R.string.main_activity_main)
    String mainActivity;

    @BindDrawable(R.drawable.ic_launcher_background)
    Drawable imageForeground;

    @BindColor(R.color.colorActiviyMain)
    ColorStateList colorActivityMain;

    @BindDimen(R.dimen.spacing)
    int spacing;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);

        logginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loginButtonClicked();
            }
        });

       /* Call<Twitch> call = twitchAPI.getTopGames("gp762nuuoqcoxypju8c569th9wz7q5","Bearer tvyvg5hx2nmx17jnx4n02tujoa3fa4");
        call.enqueue(new Callback<Twitch>() {
            @Override
            public void onResponse(Call<Twitch> call, Response<Twitch> response) {
                assert response.body() != null;
                System.out.println(response);

                List<Game> topGames = response.body().getGame();
                for (Game game : topGames) {
                    System.out.println(game.getName());
                }

            }

            @Override
            public void onFailure(Call<Twitch> call, Throwable t) {

                t.printStackTrace();

            }
        });

        Call<Twitch> call2 = twitchAPI.getGame("gp762nuuoqcoxypju8c569th9wz7q5","Bearer tvyvg5hx2nmx17jnx4n02tujoa3fa4","493057");
        call2.enqueue(new Callback<Twitch>() {
            @Override
            public void onResponse(Call<Twitch> call2, Response<Twitch> response) {
                assert response.body() != null;
                System.out.println(response);
                Game game = response.body().getGame().get(0);
                System.out.println(game.getName());

            }

            @Override
            public void onFailure(Call<Twitch> call2, Throwable t) {

                t.printStackTrace();

            }
        });*/

      /* twitchAPI.getTopGamesObservable("gp762nuuoqcoxypju8c569th9wz7q5",
                                        "Bearer tvyvg5hx2nmx17jnx4n02tujoa3fa4").flatMap(new Function<Twitch, Observable<Game>>() {
            @Override
           public Observable<Game> apply(Twitch twitch)   {
               return Observable.fromIterable(twitch.getGame());
           }
        }).flatMap(new Function<Game, Observable<String>>() {
           @Override
           public Observable<String> apply(Game game) throws Throwable {
               return Observable.just(game.getName());
           }
       }).filter(new Predicate<String>() {
            @Override
            public boolean test(String s){
                return s.contains("W")||s.contains("W");
            }
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<String>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull String name) {
                    System.out.println("Next: "+name);
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }

                @Override
                public void onComplete() {
                    System.out.println("Completado");
                }
            });

*/

        twitchAPI.getStreamsObservable("gp762nuuoqcoxypju8c569th9wz7q5",
                  "Bearer tvyvg5hx2nmx17jnx4n02tujoa3fa4").flatMap(new Function<Streams, Observable<Stream>>() {
              @Override
                  public Observable<Stream> apply(Streams streams) {
                      return Observable.fromIterable(streams.getStreams());
                  }
          }).filter(new Predicate<Stream>() {

              @Override
              public boolean test(Stream stream) {
                  return (stream).getLanguage().equals("es") && ( stream).getViewerCount() > 10000;
              }
          }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Stream>() {

                @Override
                public void onComplete() {
                    System.out.println("Completado");
                }

                @Override
                public void onNext(@NonNull final Stream stream) {
                    Call<Twitch> callGame = twitchAPI.getGame("gp762nuuoqcoxypju8c569th9wz7q5",
                            "Bearer tvyvg5hx2nmx17jnx4n02tujoa3fa4",stream.getGameId());
                    callGame.enqueue(new Callback<Twitch>() {
                        @Override
                        public void onResponse(Call<Twitch> call, Response<Twitch> response) {
                            Log.d("Ejercicio","Nombre: " + response.body().getGame().get(0).getName()
                                    + " Visitas: "
                                    + stream.getViewerCount()
                                    + " Lenguaje: "
                                    + stream.getLanguage());

                        }
                        @Override
                        public void onFailure(Call<Twitch> call, Throwable t) {

                        }
                    });

                }

                @Override
                public void onError(@NonNull Throwable e) {
                    e.printStackTrace();
                }


                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

            })
          ;



    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getCurrentUser();

    }

    @Override
    public String getFirstName() {
        return firstName.getText().toString().trim();
    }

    @Override
    public String getLastName() {
        return lastName.getText().toString().trim();
    }

    @Override
    public void showUserNotAvailable() {
        Toast.makeText(this, "Error, el usuario no está disponible", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this, "Error, el  nombre ni el apellido pueden estar vacios", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSaved() {
        Toast.makeText(this, "Usuario guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName.setText(lastName);
    }
}
/*
class Employee {
    @Expose
    public String name;
    @Expose
    public int age;
    @Expose(serialize = true, deserialize = false)
    public double salary;
    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}*/