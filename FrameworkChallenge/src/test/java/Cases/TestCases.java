package Cases;

import Data.ProvideData;
import DesignPattern.User;
import DesignPattern.UserBuilder;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.*;


public class TestCases {
    String key,username,password;
    UserBuilder builder = new UserBuilder();
    User user = new User();
    @BeforeSuite
    public void ReadJson(){

        System.out.println("Extracting info form json");
        baseURI = "https://api.themoviedb.org/3";
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("./src/test/java/Credentials/Info.json"))
        {
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            key = (String) obj.get("Key");
            username = (String) obj.get("Username");
            password =(String) obj.get("Password");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Test(priority = 1)
    public void TokenAndSession(){
        System.out.println("Request token");
        String request_token,session_id,expires_at;
        if (user.compare()){
            request_token = given().params("api_key",key)
                    .when().get("/authentication/token/new")
                    .then().statusCode(200).and().extract().path("request_token");

            System.out.println("Validate token");
            JSONObject body = new JSONObject();
            body.put("username",username);
            body.put("password",password);
            body.put("request_token",request_token);
            expires_at=given().contentType(ContentType.JSON).accept(ContentType.JSON).body(body.toJSONString())
                    .when().post("/authentication/token/validate_with_login?api_key="+key)
                    .then().statusCode(200).and().extract().path("expires_at");

            System.out.println("Create session");
            body = new JSONObject();
            body.put("request_token",request_token);
            session_id= given().contentType(ContentType.JSON).accept(ContentType.JSON).body(body.toJSONString())
                    .when().post("/authentication/session/new?api_key="+key)
                    .then().statusCode(200).and().extract().path("session_id");

            user = builder.session(session_id)
                    .request(request_token)
                    .expires(expires_at)
                    .build();
        }

    }


    @Test(priority = 2,
            dataProvider = "ListNames",
            dataProviderClass = ProvideData.class,
            dependsOnMethods = {"TokenAndSession"})
    public void CreateList(String genre, String description){
        System.out.println("Creating list: "+genre);
        JSONObject body = new JSONObject();
        body.put("name",genre);
        body.put("description",description);
        body.put("language","en");
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(body.toJSONString())
                .when().post("/list?api_key="+key+"&session_id="+user.getSession_id())
                .then().statusCode(201).and().log().body();
    }

    @Test(priority = 3,
            dataProvider = "Movies",
            dataProviderClass = ProvideData.class,
            dependsOnMethods = {"TokenAndSession"})
    public void AddMovieToList(int list, int movie){
        System.out.println("Adding movie id "+movie+ " to list "+ list);
        JSONObject body = new JSONObject();
        body.put("media_id",movie);
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(body.toJSONString())
                .when().post("/list/"+list+"/add_item?api_key="+key+"&session_id="+user.getSession_id())
                .then().statusCode(201).and().log().body();
    }

    @Test(priority = 4,
            dataProvider = "ListToClear",
            dataProviderClass = ProvideData.class,
            dependsOnMethods = {"TokenAndSession"})
    public void ClearList(int list){
        System.out.println("Clearing list "+ list);
        given()
                .when().post("/list/"+list+"/clear?api_key="+key+"&session_id="+user.getSession_id()+"&confirm="+true)
                .then().statusCode(201).and().log().body();
    }

    @Test(priority = 5,
            dataProvider = "ListToDelete",
            dataProviderClass = ProvideData.class,
            dependsOnMethods = {"TokenAndSession"})
    public void DeleteList(int list){
        System.out.println("Deleting list "+ list);
        given()
                .when().delete("/list/"+list+"?api_key="+key+"&session_id="+user.getSession_id())
                .then().statusCode(201).and().log().body();
    }

    @Test(priority = 6,
            dataProvider = "Query",
            dataProviderClass = ProvideData.class,
            dependsOnMethods = {"TokenAndSession"})
    public void SearchBy(String query){
        System.out.println("Searching movie by "+ query);
        given()
                .when().get("/search/movie?api_key="+key+"&query="+query)
                .then().statusCode(200).and().log().body();
    }
}
