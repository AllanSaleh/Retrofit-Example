package com.example.retrofitclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create()).build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

        //getPosts(jsonPlaceHolder);

        Call<Post> call = jsonPlaceHolder.getPostWithId(5);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }
                Post post = response.body();

                String content = "";
                content+="User ID: "+post.getUserId()+"\n";
                content+="ID: "+post.getId()+"\n";
                content+="Title: "+post.getTitle()+"\n";
                content+="Text: "+post.getText()+"\n\n";

                result.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                result.setText(t.getMessage());
            }
        });
    }

    private void getPosts(JsonPlaceHolder jsonPlaceHolder) {
        Call<List<Post>> call = jsonPlaceHolder.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful())
                {
                    result.setText("Code: "+response.code());
                    return;
                }

                List<Post> post = response.body();

                for (Post item:post)
                {
                    String content = "";
                    content+="User ID: "+item.getUserId()+"\n";
                    content+="ID: "+item.getId()+"\n";
                    content+="Title: "+item.getTitle()+"\n";
                    content+="Text: "+item.getText()+"\n\n";

                    result.append(content);


                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                result.setText(t.getMessage());
            }
        });
    }
}
