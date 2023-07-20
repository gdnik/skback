import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Email;
import models.Link;

//GET http://localhost:4567/
//POST http://localhost:4567/check/email
public class Back {
    public static void main(String[] args) {

        //secure("deploy/keystore.jks", "Skilyl#inC@734", null, null);

        get("/", (req, res) -> {
            System.out.println("Skillsy Secure Backend");
            return "Skillsy Backend";
        });

        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
        });

        post("/check/email", (req, res) -> {
            System.out.println("Received POST request");
            Gson gson = new GsonBuilder().create();
            System.out.println(req.body());
            Email e = gson.fromJson(req.body(), Email.class);
            System.out.println("email = " + e.getEmail());
            Link link = new Link();
            link.setLink("privet from backend");
            System.out.println("link = " + link.getLink());
            return gson.toJson(link);
        }
        );
    }
}