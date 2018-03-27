package com.handel.handel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.handel.util.UtilLayout;
import com.handel.ws.AbstractWS;
import com.handel.ws.ExceptionWS;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import java.net.SocketTimeoutException;

/**
 * Created by Marco Antonio on 13/03/2018.
 */

public class AbstractActivity  extends AppCompatActivity {
    public static final String LOG_CLASS = "AbstractActivity";
    protected View rootView;
    protected View progressView;
    protected AbstractActivity(){
        super();
    }
    public AbstractActivity(View rootView, View progressView) {
        super();
        this.rootView = rootView;
        this.progressView = progressView;
    }

    protected String getMessageException(Exception ex){
        if  (ex instanceof ExceptionWS) {
            ExceptionWS wex = (ExceptionWS) ex;
            // ********** Si la sesion expiro (se cerro) redirigimos al login
            if (AbstractWS.ERROR_SESSION_EXPIRED.equals(wex.getStatus())){
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return null;
            } else {
                return ex.getMessage();
            }
        } else if (ex instanceof HttpServerErrorException) {
            // ************ HttpServerErrorException: 503 Service Unavailable
            return getString(R.string.error_server_down);
        } else if (ex instanceof HttpClientErrorException) {
            HttpClientErrorException hex = (HttpClientErrorException)ex;

            // ************ Si el token ha expirado mandando una excepcion 401 UNAUTHORIZED
            // ************ HttpClientErrorException: 404 Not Found
            // ************ HttpClientErrorException: 403 Forbidden si se reinicio el servidor

            if (HttpStatus.FORBIDDEN.equals(hex.getStatusCode())){
                // ************ Si el servidor se reinicio nuestro token ya no es valido mandando una excepcion 403 FORBIDEN
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return null;
            } else {
                return getString(R.string.error_connection);
            }
        } else if (ex instanceof RestClientException) {
            return getString(R.string.error_service);
        } else if (ex instanceof SocketTimeoutException) {
            return getString(R.string.error_timeout);
        } else {
            return getString(R.string.error_internal);
        }
    }
    public boolean showErrorToast(Exception e) {
        String msg = getMessageException(e);
        Log.i(LOG_CLASS,"El mensaje : "+msg);
        if (msg != null) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        return msg != null;
    }
    public boolean showErrorPrompt(Exception e) {
        String msg = getMessageException(e);
        if (msg != null) {
            UtilLayout.showPromptMsg(this, msg);
        }
        return msg != null;
    }
    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    protected void showProgress(final boolean show) {
        if (progressView == null){
            return;
        }
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            if (rootView != null) {
                rootView.setVisibility(show ? View.GONE : View.VISIBLE);
                rootView.animate().setDuration(shortAnimTime).alpha(
                        show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        rootView.setVisibility(show ? View.GONE : View.VISIBLE);
                    }
                });
            }

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
