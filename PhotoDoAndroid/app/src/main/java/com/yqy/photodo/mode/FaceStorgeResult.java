package com.yqy.photodo.mode;

import java.util.List;

/**
 * Created by yqy on 2019/11/15.
 */

public class FaceStorgeResult {

    /**
     * faceset_token : c462c351d320cc8ab42c2f01fad66229
     * tags :
     * time_used : 73
     * user_data :
     * display_name : photodo
     * face_tokens : ["fcb04c45f1eb6598df225124cfa393f9","f92fa05aa20f3c76b3f0969d36bd6c02"]
     * face_count : 2
     * request_id : 1573796625,407858a0-4470-42e1-8840-93dbb8acdcc9
     * outer_id :
     */

    private String faceset_token;
    private String tags;
    private int time_used;
    private String user_data;
    private String display_name;
    private int face_count;
    private String request_id;
    private String outer_id;
    private List<String> face_tokens;

    public String getFaceset_token() {
        return faceset_token;
    }

    public void setFaceset_token(String faceset_token) {
        this.faceset_token = faceset_token;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public String getUser_data() {
        return user_data;
    }

    public void setUser_data(String user_data) {
        this.user_data = user_data;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public int getFace_count() {
        return face_count;
    }

    public void setFace_count(int face_count) {
        this.face_count = face_count;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getOuter_id() {
        return outer_id;
    }

    public void setOuter_id(String outer_id) {
        this.outer_id = outer_id;
    }

    public List<String> getFace_tokens() {
        return face_tokens;
    }

    public void setFace_tokens(List<String> face_tokens) {
        this.face_tokens = face_tokens;
    }
}
