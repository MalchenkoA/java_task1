package ru.stqa.pft.mantis.appmanager;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import ru.stqa.pft.mantis.model.IssueStatus;

import java.io.IOException;
import java.util.Set;

public class RestHelper{
    private ApplicationManager app;

public RestHelper(ApplicationManager app) {
        this.app = app;
        }
    public String getIssueForBugifyById(int issueId) throws IOException {

        String json = getExecutor().execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json?limit=500", issueId)))
                .returnContent().asString();;
        JsonElement parsed = new JsonParser().parse(json);
        String status = parsed.getAsJsonObject().getAsJsonArray("issues").get(0).getAsJsonObject().get("state_name").getAsString();
        return status;


    }
    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");

    }
}
