package edu.cmn.deepdive.animals.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cmn.deepdive.animals.model.Animal;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface WebServiceProxy {

  @GET("images")
  Call<List<Animal>> getAnimals();

  static WebServiceProxy getInstance() {
    return InstanceHolder.INSTANCE;
  }

  //a singleton - only one instance will ever be created
  class InstanceHolder {

    private static final WebServiceProxy INSTANCE;

    //static initialization block; anything put in there is initialized first at compile time.  Then instance fields, then constructor...
    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();

      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();

      //baseUrl must end with a slash
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl("https://ddc-java.services/animals/")
          .addConverterFactory(GsonConverterFactory.create(gson))
          .client(client)
          .build();
      INSTANCE = retrofit.create(WebServiceProxy.class);
    }
  }
}
