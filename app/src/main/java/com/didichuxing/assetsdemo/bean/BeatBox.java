package com.didichuxing.assetsdemo.bean;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by didi on 16/9/13.
 */
public class BeatBox {

    private static final String SOUNDS_FOLDER = "sample_sounds";

    private static final int MAX_SOUNDS = 5;

    private SoundPool mSoundPool;


    private AssetManager mAssetManager;
    private List<Sound> mSounds = new ArrayList<>();

    public BeatBox(Context context) {
        mAssetManager = context.getAssets();


        mSoundPool = new SoundPool(MAX_SOUNDS , AudioManager.STREAM_MUSIC , 0);
        loadSounds();
    }


    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssetManager.list(SOUNDS_FOLDER);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        mSounds = new ArrayList<>();

        for (String filename : soundNames) {
            String assetPath = SOUNDS_FOLDER + "/" + filename;
            Sound sound = new Sound(assetPath);
            try {
                load(sound);
                mSounds.add(sound);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();

        if (soundId == null) {
            return;
        }

        mSoundPool.play(soundId , 1.0f , 1.0f , 1, 0, 1.0f);
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    private void load(Sound sound) throws IOException {
        AssetFileDescriptor assetFileDescriptor =
                mAssetManager.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(assetFileDescriptor , 1);
        sound.setSoundId(soundId);
    }

    public void release() {
        mSoundPool.release();
    }
}