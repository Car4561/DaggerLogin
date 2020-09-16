package carlosllerena.daggerlogin.login;

import android.util.Log;

public class LoginActivityModel implements LoginActivityMVP.Model {

    User user;

    LoginRepository repository;

    public LoginActivityModel(LoginRepository loginRepository){
        this.repository = loginRepository;
    }

    @Override
    public User getUser() {
        return repository.getUser();
    }

    @Override
    public void createUser(String firstName, String lastName) {
        repository.saveUser(new User(firstName,lastName));
        Log.d("TAG1","asdsad");
    }

}
