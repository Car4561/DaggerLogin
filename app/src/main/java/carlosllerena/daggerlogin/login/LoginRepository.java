package carlosllerena.daggerlogin.login;

public interface LoginRepository {

    void saveUser(User user);

    User getUser();

}
