package carlosllerena.daggerlogin.http;


import java.util.List;

import carlosllerena.daggerlogin.http.twitch.Game;
import carlosllerena.daggerlogin.http.twitch.Streams;
import carlosllerena.daggerlogin.http.twitch.Twitch;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TwitchAPI {

    @GET("games/top")
    Call<Twitch> getTopGames(@Header("Client-ID") String clientId,@Header("Authorization") String token);

    @GET("games/top")
    Observable<Twitch> getTopGamesObservable(@Header("Client-ID") String clientId, @Header("Authorization") String token);

    @GET("streams")
    Observable<Streams> getStreamsObservable(@Header("Client-ID") String clientId, @Header("Authorization") String token);

    @GET("games")
    Observable<Twitch> getGameObservable(@Header("Client-ID") String clientId, @Header("Authorization") String token, @Query("id") String idGame);

    @GET("games")
    Call<Twitch> getGame(@Header("Client-ID") String clientId, @Header("Authorization") String token, @Query("id") String idGame);


}
