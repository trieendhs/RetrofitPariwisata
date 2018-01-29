package com.example.trieendah.retrofitpariwisata;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trie Endah on 1/27/2018.
 */

public class ResultUser {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<User> result = new ArrayList<User>();
    @SerializedName("message")
    private String message;
    public ResultUser() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
