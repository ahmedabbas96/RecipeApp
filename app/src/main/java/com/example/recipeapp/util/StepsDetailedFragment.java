package com.example.recipeapp.util;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipeapp.R;
import com.example.recipeapp.model.Recipes;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class StepsDetailedFragment extends Fragment {

    public Recipes.RecipeSteps recipeSteps ;

    private static final String APP_NAME = "RecipeApp";
    private static final String PLAYER_POSITION = "player position";
    private static final String PLAYER_GET_PLAY_WHEN_READY = "get play";
    private static final long DEFAULT_PLAYER_POSITION = 0;
    private static final Boolean DEFAULT_PLAY_WHEN_READY = true;
    public long position = 0;
    private Boolean playWhenReady = true;

    PlayerView mediaPlayerView;
    SimpleExoPlayer player;
    ImageView videoThumbnail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            position = savedInstanceState.getLong(PLAYER_POSITION, DEFAULT_PLAYER_POSITION);
            playWhenReady = savedInstanceState.getBoolean(PLAYER_GET_PLAY_WHEN_READY, DEFAULT_PLAY_WHEN_READY);
            recipeSteps= (Recipes.RecipeSteps) savedInstanceState.getSerializable("Recipe");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detailed_steps, container, false);

        TextView videoDescriptionTV = rootView.findViewById(R.id.video_step_description);

        mediaPlayerView = rootView.findViewById(R.id.video_player_view);

        videoThumbnail = rootView.findViewById(R.id.video_thumbnail);

        mediaPlayerView.setVisibility(View.VISIBLE);

        videoDescriptionTV.setVisibility(View.VISIBLE);

       videoDescriptionTV.setText(recipeSteps.getDescription());

        return rootView;


    }



    @Override
    public void onStart() {
        super.onStart();

        player = ExoPlayerFactory.newSimpleInstance(getActivity(),
                new DefaultTrackSelector());

        mediaPlayerView.setPlayer(player);

        DefaultDataSourceFactory factory = new DefaultDataSourceFactory(Objects.requireNonNull(getActivity())
                , Util.getUserAgent(getActivity(), APP_NAME));
        // If there is no video thumbnail will be shown
        if (!TextUtils.isEmpty(recipeSteps.getVideoURL())) {

            ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(factory)
                    .createMediaSource(Uri.parse(recipeSteps.getVideoURL()));

            player.prepare(mediaSource);
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(position);
        } else if(!TextUtils.isEmpty(recipeSteps.getThumbnailURL())){

            mediaPlayerView.setVisibility(View.GONE);
            videoThumbnail.setVisibility(View.VISIBLE);

            Picasso.with(getContext()).load(recipeSteps.getThumbnailURL()).into(videoThumbnail);

        } else {

            mediaPlayerView.setVisibility(View.GONE);
            videoThumbnail.setVisibility(View.VISIBLE);
            videoThumbnail.setImageResource(R.drawable.loading_image);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mediaPlayerView != null) {
            mediaPlayerView.setPlayer(null);
        }
        if (player != null) {
            player.release();
        }

    }

    @Override
    public void onStop() {
        if (player != null) {
            player.release();
        }
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save player position for config change
        outState.putLong(PLAYER_POSITION, player.getCurrentPosition());
        outState.putBoolean(PLAYER_GET_PLAY_WHEN_READY, player.getPlayWhenReady());
        outState.putSerializable("Recipe",recipeSteps);

    }


}
