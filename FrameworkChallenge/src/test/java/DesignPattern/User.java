package DesignPattern;
import org.apache.commons.lang3.ObjectUtils;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;



public class User {

    String request_token, session_id, expires_at;

    public User() {
        expires_at = Timestamp.valueOf(LocalDateTime.now(ZoneOffset.UTC)).toString();
    }

    public boolean compare(){
        Timestamp tm1,tm2;
        tm1=Timestamp.valueOf(expires_at);
        LocalDateTime local=LocalDateTime.now(ZoneOffset.UTC);
        tm2=Timestamp.valueOf(local);
        if(tm1.compareTo(tm2)<0){
            return true;
        }else{
            return false;
        }

    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }
}
