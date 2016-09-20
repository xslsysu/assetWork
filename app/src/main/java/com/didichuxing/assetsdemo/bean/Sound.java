package com.didichuxing.assetsdemo.bean;

/**
 * Created by didi on 16/9/13.
 */
public class Sound {
    private String mAssetPath;
    private String mName;
    private Integer mSoundId;

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String fileName = components[components.length -1];
        mName = fileName.replace(".wav" , "");


    }

    public String getAssetPath() {
        return mAssetPath;
    }



    public String getName() {
        return mName;
    }


}
