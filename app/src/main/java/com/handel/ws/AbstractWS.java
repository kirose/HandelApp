package com.handel.ws;

import android.os.AsyncTask;
import android.util.Log;

import com.handel.util.UtilRutes;
import com.handel.vo.ParametersVO;
import com.handel.vo.ResponseVO;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by Marco Antonio on 13/02/2018.
 */

public abstract class AbstractWS {


    public static String LOG_CLASS = "AbstractWS";
    public static String OK = "S";
    public static String ERROR_VALIDACION = "EV";
    public static String INVALID_PARAMETERS = "EINPA";
    public static String NO_DATA_FOUND = "ENDAT";
    public static String ERROR_EXIRED_TOKEN = "ETKEX";
    public static String SERVICE_NOT_FOUND = "ENSER";
    public static String UNAUTHORIZED = "ENPER";
    public static String ERROR_INTERNAL = "EI";
    public static String ERROR_SESSION_EXPIRED = "ESEEX";

    /**
     * token el token con el que se autorizo el uso de los servicios
     */
    private static ResponseVO token;
    //private static final String BASE_URL = UtilRutes.APP_URL + "ws/";
    private static final int TIMEOUT_CONNECTION = 5000;
    private String entityName;

    public AbstractWS() {
        this.entityName = getClass().getSimpleName().substring(0,getClass().getSimpleName().length()-2);
    }

    /**
     * Este metodo ejecuta un Web Service no asincrono
     * @param serviceName el nombre del servicio que se quiere invocqr
     * @param params los parametros que se enviaran en el web service
     * @return WsResponseVO la respuesta del Web Service
     */
    protected ResponseVO execute(String serviceName, ParametersVO params){
        if (UsuarioWS.getUsuarioSesion() != null && UsuarioWS.getUsuarioSesion().getIdSesion() != null){
            if (params == null){
                params = new ParametersVO();
            }
            params.add("idSesion", UsuarioWS.getUsuarioSesion().getIdSesion());
        }
        return execute(UtilRutes.RESTFUL_URL + serviceName,null,params.toJSON());
    }

    /**
     * Este metodo ejecuta un Web Service de forma asincrona
     * @param serviceName el nombre del servicio que se quiere invocqr
     * @param handler el handler que se invocara al finalizar la llamada del web service
     * @param params lo parametros que se enviaran en el web service
     */
    protected void executeAsync(String serviceName, HandlerWS handler, ParametersVO params){
        if (UsuarioWS.getUsuarioSesion() != null && UsuarioWS.getUsuarioSesion().getIdSesion() != null){
            if (params == null){
                params = new ParametersVO();
            }
            params.add("idSesion", UsuarioWS.getUsuarioSesion().getIdSesion());
        }
        new WSAbstractTask().execute(UtilRutes.RESTFUL_URL + serviceName,handler,params);
    }

    public void findUniqueAsync(HandlerWS handler, ParametersVO args){
        executeAsync("find"+entityName+"Unique", handler, args);
    }
    public void findMultipleAsync(HandlerWS handler, ParametersVO args){
        executeAsync("find"+entityName+"Multiple", handler, args);
    }
    public void saveAsync(HandlerWS handler, ParametersVO args){
        executeAsync("save"+entityName, handler, args);
    }
    public void updateAsync(HandlerWS handler, ParametersVO args){
        executeAsync("update"+entityName, handler, args);
    }
    public void deleteAsync(HandlerWS handler, ParametersVO args){
        executeAsync("delete"+entityName, handler, args);
    }
    private void login(){

        String url = UtilRutes.WEBSERVICES_URL +"Authentication/login";
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        HttpHeaders headers = new HttpHeaders();
        //headers.setAuthorization(HttpAuthentication);
        headers.set("service_key","f80ebc87-vf34-4b29-cR45-2345hhyt5434");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic Og==");
        headers.set("Cache-Control", "no-cache");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("username", "cliente");
        map.add("password", "password!01");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        Log.i("URL:",url);
        Log.i("REQUEST:",request.toString());
        ResponseEntity<ResponseVO> rs = restTemplate.exchange(url, HttpMethod.POST, request, ResponseVO.class);
        ResponseVO vo = rs.getBody();
        if (OK.equals(vo.getStatus())){
            // Se genero el tooken correctamente
            token = vo;
        } else {
            throw new ExceptionWS(rs.getBody().getStatus(),vo.getMessage());
        }
    }
    private void logout(){

        if (token == null){
            // Nada que hacer aun no se ha iniciado sesion
            return;
        }
        String url = UtilRutes.WEBSERVICES_URL + "Authentication/logout";
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        HttpHeaders headers = new HttpHeaders();
        //headers.setAuthorization(HttpAuthentication);
        headers.set("token",token.getToken());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic Og==");
        headers.set("Cache-Control", "no-cache");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, headers);

        ResponseEntity<ResponseVO> rs = restTemplate.exchange(url, HttpMethod.POST, request, ResponseVO.class);
        ResponseVO vo = rs.getBody();
        if (!OK.equals(vo.getStatus())){
            Log.i("WARM",vo.getMessage());
        }
        token = null;
    }


    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(TIMEOUT_CONNECTION);
        factory.setConnectTimeout(TIMEOUT_CONNECTION);
        return factory;
    }
    private ResponseVO execute(String url, String jsonParams){

        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        HttpHeaders headers = new HttpHeaders();
        //headers.setAuthorization(HttpAuthentication);
        headers.set("token", token.getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cache-Control", "no-cache");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(jsonParams, headers);
        Log.i("URL:", url);
        Log.i("REQUEST:", request.toString());
        ResponseEntity<ResponseVO> rs = restTemplate.exchange(url, HttpMethod.POST, request, ResponseVO.class);
        if (!HttpStatus.OK.equals(rs.getStatusCode())){
            throw new HttpClientErrorException(rs.getStatusCode());
        }
        if (rs.getBody() == null){
            throw new NullPointerException();
        }
        Log.i(LOG_CLASS,"RESPONSE: "+rs.getBody());
        if (!OK.equals(rs.getBody().getStatus()) && !ERROR_EXIRED_TOKEN.equals(rs.getBody().getStatus())){
            throw new ExceptionWS(rs.getBody().getStatus(),rs.getBody().getMessage());
        }
        return rs.getBody();
    }

    /**
     * Metodo principal pqra invocar Web Services asincrono y no asincrono
     * Si no hay conexion con el servidor u ocurre algun error se lanzara un ExceptionWS
     * @param url el url del web service
     * @param handler el handler que se invocara al finalizar la llamada del web service
     * @param jsonParams los parametros que se incluiran en la llamada del web service
     * @return La respuesta
     */
    private ResponseVO execute(String url, HandlerWS handler, String jsonParams) {
        ResponseVO response = null;
        Exception error = null;
        try {
            if (token == null) {
                login();
            }
            try {
                response = execute(url, jsonParams);
            } catch (RestClientException ex){
                // Si al invocar el servicio
                Log.e(LOG_CLASS,ex.getMessage(),ex);
                //error = ex;
            }

            // ******** Si el token ha expirado volvemos a iniciar sesion
            if (response == null || ERROR_EXIRED_TOKEN.equals(response.getStatus())) {
                // ********** Si hay token removemos el registro en el server
                logout();
                login();
                response = execute(url, jsonParams);
            }

        } catch (Exception e) {
            error = e;
        }

        try {
            if (error == null) {
                Log.i("Service Success",""+response.getStatus() + " - " + response.getMessage());
                if (handler != null) {
                    handler.onSuccess(response);
                }
            } else {
                Log.e("Service Error",error.getMessage(),error);
                if (handler != null) {
                    handler.onError(error);
                }
            }
        } catch (Exception ex){
            Log.e(LOG_CLASS,ex.getMessage(),ex);
        }
        return response;
    }
    private class WSAbstractTask extends AsyncTask<Object, Void, ResponseVO> {

        /**
         *
         * @param params: [String url,HandlerWS handler,String jsonParams]
         * @return
         */
        @Override
        protected ResponseVO doInBackground(Object... params) {
            if (params == null || params.length == 0){
                throw new NullPointerException();
            }
            String url = (String) params[0];
            HandlerWS handler = params.length > 1 ? (HandlerWS) params[1] : null;
            String jsonParams = params[2] != null ? ((ParametersVO)params[2]).toJSON() : null;
            return AbstractWS.this.execute(url,handler,jsonParams);
        }

    }
}
