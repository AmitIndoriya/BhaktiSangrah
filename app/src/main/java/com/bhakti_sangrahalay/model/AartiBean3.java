package com.bhakti_sangrahalay.model;

import java.io.Serializable;

public class AartiBean3 implements Serializable {
    int id;
    String title;
    int image;
    String url;
    String singer;
    String duration;
    String audiofilesize;
    String audiofilename;
    String desc;
    boolean isDownLoaded;
    boolean isDownLoading;
    int progressStatus;
    String localSaveUrl;
    boolean isPlaying;
    boolean isPaused;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAudiofilesize() {
        return audiofilesize;
    }

    public void setAudiofilesize(String audiofilesize) {
        this.audiofilesize = audiofilesize;
    }

    public String getAudiofilename() {
        return audiofilename;
    }

    public void setAudiofilename(String audiofilename) {
        this.audiofilename = audiofilename;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isDownLoaded() {
        return isDownLoaded;
    }

    public void setDownLoaded(boolean downLoaded) {
        isDownLoaded = downLoaded;
    }

    public boolean isDownLoading() {
        return isDownLoading;
    }

    public void setDownLoading(boolean downLoading) {
        isDownLoading = downLoading;
    }

    public int getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(int progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getLocalSaveUrl() {
        return localSaveUrl;
    }

    public void setLocalSaveUrl(String localSaveUrl) {
        this.localSaveUrl = localSaveUrl;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }
}
