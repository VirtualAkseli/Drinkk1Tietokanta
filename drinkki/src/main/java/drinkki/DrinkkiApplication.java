package drinkki;

import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class DrinkkiApplication {

    public static void main(String[] args) throws Exception {

        if (System.getenv("PORT") != null) {
            Spark.port(Integer.valueOf(System.getenv("PORT")));
        }

        Database database = new Database("jdbc:sqlite:drinkkitietokanta.db");
        DrinkkiDao drinkit = new DrinkkiDao(database);

        Spark.get("/drinkit", (req, res) -> {
            HashMap map = new HashMap<>();
            
            map.put("drinkit", drinkit.findAll());
            
            return new ModelAndView(map, "drinkit");
        }, new ThymeleafTemplateEngine());

        Spark.post("/drinkit", (req, res) -> {
            Drinkki drinkki = new Drinkki(-1, req.queryParams("nimi"), req.queryParams("lasityyppi"), req.queryParams("resepti"));

            drinkit.saveOrUpdate(drinkki);

            res.redirect("/drinkit");
            return "";
        });
    }

}
