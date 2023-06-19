package com.example.myfoodplanner.MealScreen.MealScreenView;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myfoodplanner.MealScreen.MealScreenPresenter.MealPresenter;
import com.example.myfoodplanner.MealScreen.MealScreenPresenter.MealPresenterInterface;
import com.example.myfoodplanner.Model.Meal;
import com.example.myfoodplanner.Model.Repository;
import com.example.myfoodplanner.NetworkConnection.MealClient;
import com.example.myfoodplanner.R;
import com.example.myfoodplanner.SignInActivity;
import com.example.myfoodplanner.db.ConcreteLocalSource;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import java.util.List;


public class MealFragment extends Fragment implements MealViewInterface{

    TextView mealName;
    TextView mealArea;
    ImageView mealImg;
    ImageView ingImg1,ingImg2,ingImg3,ingImg4,ingImg5,ingImg6,ingImg7,ingImg8,ingImg9,ingImg10,ingImg11,ingImg12,ingImg13,ingImg14,ingImg15;
    TextView ingName1,ingName2,ingName3,ingName4,ingName5,ingName6,ingName7,ingName8,ingName9,ingName10,ingName11,ingName12,ingName13,ingName14,ingName15;
    TextView instructions;
    MealPresenterInterface mealPresenterInterface;
    YouTubePlayerView youTubePlayerView;
    Button saveMeal;
    Boolean saved;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        saved = MealFragmentArgs.fromBundle(getArguments()).getSaved();
        mealPresenterInterface = new MealPresenter(this, Repository.getInstance(MealClient.getInstance(),
                ConcreteLocalSource.getInstance(this.getContext())));
        mealPresenterInterface.getMeal();
        mealName = view.findViewById(R.id.nameMeal);
        mealArea = view.findViewById(R.id.areaMeal);
        mealImg = view.findViewById(R.id.imgMeal);
        instructions = view.findViewById(R.id.mealInstructors);
        ingImg1 = view.findViewById(R.id.ingImg1);
        ingImg2 = view.findViewById(R.id.ingImg2);
        ingImg3 = view.findViewById(R.id.ingImg3);
        ingImg4 = view.findViewById(R.id.ingImg4);
        ingImg5 = view.findViewById(R.id.ingImg5);
        ingImg6 = view.findViewById(R.id.ingImg6);
        ingImg7 = view.findViewById(R.id.ingImg7);
        ingImg8 = view.findViewById(R.id.ingImg8);
        ingImg9 = view.findViewById(R.id.ingImg9);
        ingImg10 = view.findViewById(R.id.ingImg10);
        ingImg11 = view.findViewById(R.id.ingImg11);
        ingImg12 = view.findViewById(R.id.ingImg12);
        ingImg13 = view.findViewById(R.id.ingImg13);
        ingImg14 = view.findViewById(R.id.ingImg14);
        ingImg15 = view.findViewById(R.id.ingImg15);
        ingName1 = view.findViewById(R.id.ingName1);
        ingName2 = view.findViewById(R.id.ingName2);
        ingName3 = view.findViewById(R.id.ingName3);
        ingName4 = view.findViewById(R.id.ingName4);
        ingName5 = view.findViewById(R.id.ingName5);
        ingName6 = view.findViewById(R.id.ingName6);
        ingName7 = view.findViewById(R.id.ingName7);
        ingName8 = view.findViewById(R.id.ingName8);
        ingName9 = view.findViewById(R.id.ingName9);
        ingName10 = view.findViewById(R.id.ingName10);
        ingName11 = view.findViewById(R.id.ingName11);
        ingName12 = view.findViewById(R.id.ingName12);
        ingName13 = view.findViewById(R.id.ingName13);
        ingName14 = view.findViewById(R.id.ingName14);
        ingName15 = view.findViewById(R.id.ingName15);
        youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        saveMeal = view.findViewById(R.id.saveMeal);
        if(!saved){
            saveMeal.setText("Remove");
        }

    }

    @Override
    public void showMeal(List<Meal> meal) {

        mealName.setText(meal.get(0).getName());
        mealArea.setText(meal.get(0).getArea());
        Glide.with(this.getContext()).load(meal.get(0).getThumbnail())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.top_background)
                .error(R.drawable.top_background).into(mealImg);
        instructions.setText(meal.get(0).getInstructions());
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                String[] videoID=(meal.get(0).getYoutubeLink()).split("=");
                youTubePlayer.loadVideo(videoID[1], 0);
            }
        });

        if(!(meal.get(0).getIngredient1()).equals(null)){
            Glide.with(this.getContext()).load("https://www.themealdb.com/images/ingredients/"+meal.get(0).getIngredient1()+"-Small.png")
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(0)
                    .error(0).into(ingImg1);
            ingName1.setText(meal.get(0).getIngredient1()+" "+meal.get(0).getMeasure1());
        }
        if(!(meal.get(0).getIngredient2()).equals(null)){
            Glide.with(this.getContext()).load("https://www.themealdb.com/images/ingredients/"+meal.get(0).getIngredient2()+"-Small.png")
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(0)
                    .error(0).into(ingImg2);
            ingName2.setText(meal.get(0).getIngredient2()+" "+meal.get(0).getMeasure2());
        }
        if(!(meal.get(0).getIngredient3()).equals(null)){
            Glide.with(this.getContext()).load("https://www.themealdb.com/images/ingredients/"+meal.get(0).getIngredient3()+"-Small.png")
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(0)
                    .error(0).into(ingImg3);
            ingName3.setText(meal.get(0).getIngredient3()+" "+meal.get(0).getMeasure3());
        }
        if(!(meal.get(0).getIngredient4()).equals(null)){
            Glide.with(this.getContext()).load("https://www.themealdb.com/images/ingredients/"+meal.get(0).getIngredient4()+"-Small.png")
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(0)
                    .error(0).into(ingImg4);
            ingName4.setText(meal.get(0).getIngredient4()+" "+meal.get(0).getMeasure4());
        }
        if(!(meal.get(0).getIngredient5()).equals(null)){
            Glide.with(this.getContext()).load("https://www.themealdb.com/images/ingredients/"+meal.get(0).getIngredient5()+"-Small.png")
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(0)
                    .error(0).into(ingImg5);
            ingName5.setText(meal.get(0).getIngredient5()+" "+meal.get(0).getMeasure5());
        }
        if(!(meal.get(0).getIngredient6()).equals(null)){
            Glide.with(this.getContext()).load("https://www.themealdb.com/images/ingredients/"+meal.get(0).getIngredient6()+"-Small.png")
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(0)
                    .error(0).into(ingImg6);
            ingName6.setText(meal.get(0).getIngredient6()+" "+meal.get(0).getMeasure6());
        }
        if(!(meal.get(0).getIngredient7()).equals(null)){
            Glide.with(this.getContext()).load("https://www.themealdb.com/images/ingredients/"+meal.get(0).getIngredient7()+"-Small.png")
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(0)
                    .error(0).into(ingImg7);
            ingName7.setText(meal.get(0).getIngredient7()+" "+meal.get(0).getMeasure7());
        }
        if(!(meal.get(0).getIngredient8()).equals(null)){
            Glide.with(this.getContext()).load("https://www.themealdb.com/images/ingredients/"+meal.get(0).getIngredient8()+"-Small.png")
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(0)
                    .error(0).into(ingImg8);
            ingName8.setText(meal.get(0).getIngredient8()+" "+meal.get(0).getMeasure8());
        }
        if(!(meal.get(0).getIngredient9()).equals(null)){
            Glide.with(this.getContext()).load("https://www.themealdb.com/images/ingredients/"+meal.get(0).getIngredient9()+"-Small.png")
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(0)
                    .error(0).into(ingImg9);
            ingName9.setText(meal.get(0).getIngredient9()+" "+meal.get(0).getMeasure9());
        }
        if(!(meal.get(0).getIngredient10()).equals(null)){
            Glide.with(this.getContext()).load("https://www.themealdb.com/images/ingredients/"+meal.get(0).getIngredient10()+"-Small.png")
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(0)
                    .error(0).into(ingImg10);
            ingName10.setText(meal.get(0).getIngredient10()+" "+meal.get(0).getMeasure10());
        }
        if(!(meal.get(0).getIngredient11()).equals(null)){
            Glide.with(this.getContext()).load("https://www.themealdb.com/images/ingredients/"+meal.get(0).getIngredient11()+"-Small.png")
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(0)
                    .error(0).into(ingImg11);
            ingName11.setText(meal.get(0).getIngredient11()+" "+meal.get(0).getMeasure11());
        }
        if(!(meal.get(0).getIngredient12()).equals(null)){
            Glide.with(this.getContext()).load("https://www.themealdb.com/images/ingredients/"+meal.get(0).getIngredient12()+"-Small.png")
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(0)
                    .error(0).into(ingImg12);
            ingName12.setText(meal.get(0).getIngredient12()+" "+meal.get(0).getMeasure12());
        }
        if(!(meal.get(0).getIngredient13()).equals(null)){
            Glide.with(this.getContext()).load("https://www.themealdb.com/images/ingredients/"+meal.get(0).getIngredient13()+"-Small.png")
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(0)
                    .error(0).into(ingImg13);
            ingName13.setText(meal.get(0).getIngredient13()+" "+meal.get(0).getMeasure13());
        }
        if(!(meal.get(0).getIngredient14()).equals(null)){
            Glide.with(this.getContext()).load("https://www.themealdb.com/images/ingredients/"+meal.get(0).getIngredient14()+"-Small.png")
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(0)
                    .error(0).into(ingImg14);
            ingName14.setText(meal.get(0).getIngredient14()+" "+meal.get(0).getMeasure14());
        }
        if(!(meal.get(0).getIngredient15()).equals(null)){
            Glide.with(this.getContext()).load("https://www.themealdb.com/images/ingredients/"+meal.get(0).getIngredient15()+"-Small.png")
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(0)
                    .error(0).into(ingImg15);
            ingName15.setText(meal.get(0).getIngredient15()+" "+meal.get(0).getMeasure15());
        }
        saveMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saved){
                    mealPresenterInterface.addToFav(meal.get(0));
                    saved = false;
                    saveMeal.setText("Remove");
                }else{
                    mealPresenterInterface.deleteFromFave(meal.get(0));
                    saved = true;
                    saveMeal.setText("Save");
                    Navigation.findNavController(v).popBackStack();
                }

            }
        });

    }

}