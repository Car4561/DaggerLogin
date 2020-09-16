package carlosllerena.daggerlogin.login;

public interface LoginActivityMVP {


    interface View {

        String getFirstName();
        String getLastName();

        void showUserNotAvailable();
        void showInputError();
        void showUserSaved();

        void setFirstName(String firstName);
        void setLastName(String lastName);

    }

    interface Presenter {

        void setView(LoginActivityMVP.View  loginActivity);

        void loginButtonClicked();

        void getCurrentUser();

    }

    interface Model {

        User getUser();

        void createUser(String firstName,String lastName);

    }

}
