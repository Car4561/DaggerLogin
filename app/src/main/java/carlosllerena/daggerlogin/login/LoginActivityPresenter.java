package carlosllerena.daggerlogin.login;

import android.util.Log;

import androidx.annotation.Nullable;

public class LoginActivityPresenter implements LoginActivityMVP.Presenter {

    @Nullable
    private LoginActivityMVP.View view;
    private LoginActivityMVP.Model model;

    public LoginActivityPresenter(LoginActivityMVP.Model model){
        this.model = model;
    }

    @Override
    public void setView(LoginActivityMVP.View loginActivity) {
        this.view = loginActivity;
    }

    @Override
    public void loginButtonClicked() {
        if(view != null){
            if(view.getFirstName().trim().equals("") || view.getLastName().trim().equals("")){
                view.showInputError();
            }else{
                model.createUser(view.getFirstName(),view.getLastName());
                view.showUserSaved();
            }
        }
    }

    @Override
    public void getCurrentUser() {
        User user = model.getUser();
        if(view != null){
            if(user == null){
               view.showUserNotAvailable();
            }else {
               view.setFirstName(user.getFirstName());
               view.setLastName(user.getLastName());
            }
        }
    }

}
