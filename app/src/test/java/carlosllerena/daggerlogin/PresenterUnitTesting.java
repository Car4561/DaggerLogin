package carlosllerena.daggerlogin;


import android.util.Log;

import org.junit.Before;

import org.junit.Test;
import org.junit.runners.JUnit4;
import org.junit.runners.model.InitializationError;

import carlosllerena.daggerlogin.login.LoginActivity;
import carlosllerena.daggerlogin.login.LoginActivityMVP;
import carlosllerena.daggerlogin.login.LoginActivityPresenter;
import carlosllerena.daggerlogin.login.User;
import carlosllerena.daggerlogin.root.App;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PresenterUnitTesting {

    LoginActivityPresenter presenter;
    User user;

    LoginActivityMVP.Model mockedModel;
    LoginActivityMVP.View mockedView;

    @Before
    public  void initialization(){
        mockedModel = mock(LoginActivityMVP.Model.class);
        mockedView = mock(LoginActivityMVP.View.class);
         user = new User("Manolo","Escobar");

      //  when(mockedModel.getUser()).thenReturn(user);
      //  when(mockedView.getFirstName()).thenReturn("Antonio");
      //  when(mockedView.getFirstName()).thenReturn("Banderas");

        presenter = new LoginActivityPresenter(mockedModel);
        presenter.setView(mockedView);

    }

    @Test
    public void noExistInteractionWithView(){
        presenter.getCurrentUser();
        verify(mockedView,times(1)).showUserNotAvailable();

    }

    @Test
    public void loadUserFromRepositoryWhenValidUserIsPresent(){
        when(mockedModel.getUser()).thenReturn(user);
        presenter.getCurrentUser();
        verify(mockedModel,times(1)).getUser();
        verify(mockedView,times(1)).setFirstName("Manolo");
        verify(mockedView,times(1)).setLastName("Escobar");
        verify(mockedView,never()).showUserNotAvailable();
    }

    @Test
    public void showErrorMensageWhenUserIsNull(){
        when(mockedModel.getUser()).thenReturn(null);
        presenter.getCurrentUser();
        verify(mockedModel,times(1)).getUser();
        verify(mockedView,never()).setFirstName("Manolo");
        verify(mockedView,never()).setLastName("Escobar");
        verify(mockedView,times(1)).showUserNotAvailable();
    }



    @Test
    public void createErrorMessageIfAnyFieldEmpty(){
        //primera prueba poniendo el campo firstname vacio
        when(mockedView.getFirstName()).thenReturn("");

        presenter.loginButtonClicked();
        verify(mockedView,times(1)).getFirstName();
        verify(mockedView,never()).getLastName();
        verify(mockedView,times(1)).showInputError();

        //segunda prueba poniendo un valor en el campo firstname y dejando el lastname

        when(mockedView.getFirstName()).thenReturn("Antonio");
        when(mockedView.getLastName()).thenReturn("");

        presenter.loginButtonClicked();
        verify(mockedView,times(2)).getFirstName();
        verify(mockedView,times(1)).getLastName();
        verify(mockedView,times(2)).showInputError();


    }

    @Test
    public void saveValidUser(){
        when(mockedView.getFirstName()).thenReturn("Manolo");
        when(mockedView.getLastName()).thenReturn("Escobar");
        presenter.loginButtonClicked();
        //Las llamadas deben ser dobles, una en el if, otro cuando se crea el usuario y el mensaje de guadado
        verify(mockedView,times(2)).getFirstName();
        verify(mockedView,times(2)).getLastName();

        verify(mockedModel,times(1)).createUser("Manolo","Escobar");
        verify(mockedView,times(1)).showUserSaved();
        verify(mockedView,never()).showInputError();
    }



}
