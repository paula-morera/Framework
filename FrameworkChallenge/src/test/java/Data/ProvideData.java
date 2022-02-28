package Data;

import org.testng.annotations.DataProvider;

public class ProvideData {
    @DataProvider(name = "ListNames")
    public Object[][] InfoList(){
        return new Object[][]{
                {"Crime","This is the description for a comedy realted items"},
                {"Thriller","This is the description for a terror realted items"},
                {"Adventure","This is the description for a action realted items"}
        };
    }

    @DataProvider(name = "Movies")
    public Object[][] InfoMovies(){
        return new Object[][]{
                {8193360,512195},
                {8193360,335787},
                {8193360,138843},
                {8193358,259693},
                {8193358,615904},
                {8193358,425909},
                {8193361,860623},
                {8193361,423108},
                {8193361,482321}
        };
    }

    @DataProvider(name = "ListToClear")
    public Object[][] ListTC(){
        return  new Object[][]{
                {8193354}
        };
    }
    @DataProvider(name = "ListToDelete")
    public Object[][] ListD(){
        return  new Object[][]{
                {8193355}
        };
    }

    @DataProvider(name = "Query")
    public Object[][] QueryString(){
        return new Object[][]{
                {"marvel"},
                {"disney"},
                {"space"}
        };
    }
}
