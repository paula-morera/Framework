package DesignPattern;

public class UserBuilder implements Builder {
    String request_token, session_id, expires_at;

    public UserBuilder() {
    }

    public UserBuilder request(String val){
        request_token=val;
        return this;
    }

    public UserBuilder session(String val){
        session_id=val;
        return this;
    }

    public UserBuilder expires(String val){
        String[] values = val.split(" ");
        expires_at= values[0]+" "+values[1];;
        return this;
    }

    @Override
    public User build() {
        User user = new User();
        user.setExpires_at(this.expires_at);
        user.setSession_id(this.session_id);
        user.setRequest_token(this.request_token);
        return user;
    }
}
