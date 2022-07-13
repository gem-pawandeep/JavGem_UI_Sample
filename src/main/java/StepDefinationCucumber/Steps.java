package StepDefinationCucumber;

import Assertion.Assert;
import com.gemini.apitest.ApiClientConnect;
import com.gemini.apitest.ApiHealthCheckUtils;
import com.gemini.apitest.ProjectApiUrl;
import com.gemini.apitest.ProjectSampleJson;
import com.gemini.generic.QuanticGlobalVar;
import com.gemini.quartzReporting.GemTestReporter;
import com.gemini.quartzReporting.STATUS;
import com.google.gson.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.docstring.DocString;
import io.cucumber.java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.gemini.generic.QuanticGenericUtils.initializeQuanticGlobalVariables;

public class Steps {
    @BeforeAll
    public static void beforeAll() throws InterruptedException {
        initializeQuanticGlobalVariables();
        QuanticGlobalVar.report_type = "Cucumber Automation";
        String urlFileName = QuanticGlobalVar.projectName + "_" + QuanticGlobalVar.environment + "_Url.properties";
        InputStream ip = ClassLoader.getSystemResourceAsStream(urlFileName);
        ProjectApiUrl.initializeApiUrl(ip);
        ProjectSampleJson.loadSampleJson();
        GemTestReporter.startSuite(QuanticGlobalVar.projectName, QuanticGlobalVar.environment);

    }

    @Before
    public void before(Scenario scenario) {
        String testcaseName = scenario.getName();
        String testClassName = "Cucumber HealthCheck";
        GemTestReporter.startTestCase(testcaseName, testClassName, false);
    }

    @After
    public void after() {
        GemTestReporter.endTestCase();
    }

    @AfterAll
    public static void afterAll() {
        GemTestReporter.endSuite(QuanticGlobalVar.reportLocation);

    }

    public JsonElement recentResponse = new JsonObject();

    @Given("^Request\\h:\\h([^\"]*)\\h:$")
    public void request(String testcaseName, Object str) {
        try {
            String className = String.valueOf((str.getClass()));
            if (className.equals("class io.cucumber.docstring.DocString")) {
                GemTestReporter.addTestStep("Create Request", testcaseName, STATUS.INFO);
                str = str.toString().replace("null", "");
                str = str.toString().replace("\"\"\"", "");
                String string = getResultantJson(str.toString());
                JsonParser parser = new JsonParser();
                JsonObject json = (JsonObject) parser.parse(string);
                json.addProperty("test_name", "");
                JsonArray jsonArray = new JsonArray();
                jsonArray.add(json);
                recentResponse = ApiClientConnect.healthCheckJsonWithoutNewTC(jsonArray).get(0).getAsJsonObject().get("responseBody");

            } else {
                DataTable dataTable = null;
                dataTable = (DataTable) str;
                List<Map<String, String>> mapList = dataTable.asMaps(String.class, String.class);
                GemTestReporter.addTestStep("Create Request", testcaseName, STATUS.INFO);
                JsonArray array = new JsonArray();
                GsonBuilder gsonMapBuilder = new GsonBuilder();
                Gson gsonObject = gsonMapBuilder.create();
                String JSONObject = gsonObject.toJson(mapList.get(0));
                JsonParser parser = new JsonParser();
                JsonObject json = (JsonObject) parser.parse(JSONObject);
                json.addProperty("test_name", "");
                ////
                json = (JsonObject) parser.parse(getResultantJson(json.toString()));
                ////
                array.add(json);
                recentResponse = ApiClientConnect.healthCheckJsonWithoutNewTC(array).get(0).getAsJsonObject().get("responseBody");
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Some error occurred", "Some error occurred", STATUS.FAIL);
        }
    }

    @Given("^Request\\h:\\h(.+)\\h:\\hreadfile\\((.+)\\)$")
    public void requestReadDataFromFile(String step, String filepath) {
        GemTestReporter.addTestStep("Create Request", step, STATUS.INFO);
        try {
            File file = new File(filepath);
            String payload = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            payload = getResultantJson(payload);
            JsonObject req = JsonParser.parseString(payload).getAsJsonObject();
            req.addProperty("test_name", "");
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(req);
            recentResponse = ApiClientConnect.healthCheckJsonWithoutNewTC(jsonArray).get(0).getAsJsonObject().get("responseBody");
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Some error occurred", "Some error occurred", STATUS.FAIL);
        }
    }

    @Given("^Request\\h:\\h(.+)\\h:\\h\\{(.+)\\}$")
    public void request(String step, String str) {
        GemTestReporter.addTestStep("Create Request", step, STATUS.INFO);
        str = "{" + str + "}";
        str = getResultantJson(str.toString());

        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(str);
        json.addProperty("test_name", "");

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(json);
        recentResponse = ApiClientConnect.healthCheckJsonWithoutNewTC(jsonArray).get(0).getAsJsonObject().get("responseBody");

    }

    @And("^Assert\\h:\\h(.+)\\h:")
    public void Assert(String assertStatement, String str) {
        GemTestReporter.addTestStep("AssertClassCucumber.Assert Statement", assertStatement, STATUS.INFO);
        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(str);
        Assert.assertion(recentResponse, json);
    }

    @And("^Assert\\h:\\h(.+)\\h:\\hreadfile\\((.+)\\)$")
    public void AssertFile(String assertStatement, String filePath) {
        GemTestReporter.addTestStep("Assert Statement", assertStatement, STATUS.INFO);
        try {
            File file = new File(filePath);
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(content);
            Assert.assertion(recentResponse, json);

        } catch (IOException e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Some error occurred", "Some error occurred", STATUS.FAIL);

        }
    }

    @Given("^Assert\\h:\\h(.+)\\h:\\h(.+)\\h(?i)(?<= |^)contains(?= |$)\\h(.+)$")
    public void assertContains(String name, String filePath, String key) throws IOException {
       try{
           GemTestReporter.addTestStep("Contains", name, STATUS.INFO);
           JsonParser parser = new JsonParser();
           JsonObject json = (JsonObject) parser.parse(String.valueOf(recentResponse));
           Assert.postAssertion(json,filePath,"contains",key);
           System.out.println(recentResponse);
       }catch (Exception e){
           e.printStackTrace();
           GemTestReporter.addTestStep("Some error occurred", "Some error occurred", STATUS.FAIL);
       }
    }

    @Given("^Assert\\h:\\h(.+)\\h:\\h(.+)\\h(?i)(?<= |^)in(?= |$)\\h(.+)$")
    public void assertIN(String name, String filePath, String key) {
        try{
            GemTestReporter.addTestStep("IN", name, STATUS.INFO);
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(String.valueOf(recentResponse));
            Assert.postAssertion(json,filePath,"in",key);
        }catch (Exception e){
            e.printStackTrace();
            GemTestReporter.addTestStep("Some error occurred", "Some error occurred", STATUS.FAIL);
        }
    }


    @Given("^Assert\\h:\\h(.+)\\h:\\h(.+)\\h(?i)(?<= |^)equals(?= |$)\\h(.+)$")
    public void assertEquals(String str, String str2, String str3) {
        try{
            GemTestReporter.addTestStep("Equals", str, STATUS.INFO);
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(String.valueOf(recentResponse));
            Assert.postAssertion(json,str2,"equals",str3);
        }catch (Exception e){
            e.printStackTrace();
            GemTestReporter.addTestStep("Some error occurred", "Some error occurred", STATUS.FAIL);
        }
    }

    public void assertion(JsonObject validationQueries) {
        try {
            Set<String> keySet = validationQueries.keySet();
            Iterator keys = keySet.iterator();
//					System.out.println("keySet ---> "+ keySet);
            while (keys.hasNext()) {
                String query = keys.next().toString();
                String targetQuery = validationQueries.get(query).getAsString();
                //target.trim();
                String[] targetArray = targetQuery.trim().split("\\s+");
                int index = targetQuery.indexOf(" ");
                String operator = targetQuery.substring(0, index);
                String target = targetQuery.substring(index + 1);
                if (query.toUpperCase().contains("DEEPSEARCH")) {
                    String deepSearchQuery = query.substring(query.indexOf("(") + 1, query.indexOf(")"));
                    // Call the deepSearch function here with keyname as "deepSearchQuery"
                    JsonArray result = ApiHealthCheckUtils.deepSearch(recentResponse, deepSearchQuery);
                    if (result.size() == 0) {
                        GemTestReporter.addTestStep("DeepSearch of key ~ " + deepSearchQuery, "DeepSearch Failed <BR> No Such Key Exist in Response", STATUS.FAIL);
                    } else {
//								GemTestReporter.addTestStep("DeepSearch of key ~ '"+deepSearchQuery+"'","DeepSearch Successful <BR>"+result.toString(),STATUS.PASS);
                        Boolean f = false;
                        for (int j = 0; j < result.size(); j++) {
                            String value = result.get(j).getAsJsonObject().keySet().iterator().next();
                            String loc = result.get(j).getAsJsonObject().get(value).getAsString();
//									GemTestReporter.addTestStep("DeepSearch of key ~ '"+deepSearchQuery+"'","Value of the Key ~ "+value+"<BR> Location ~ "+loc,STATUS.INFO);
                            Boolean temp = ApiHealthCheckUtils.assertionMethods(deepSearchQuery, value, target, operator, loc);
                            if (temp) {
                                f = temp;
                            }
                        }
                        if (!f) {
                            GemTestReporter.addTestStep("DeepSearch of key ~ " + deepSearchQuery, "DeepSearch Failed <BR> Expected value does not match actual value <BR> Expected value ~ " + target, STATUS.FAIL);
                        }
                    }
                } else {

                    ApiHealthCheckUtils.postAssertion(recentResponse, query, operator, target);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Some error occurred in Assertion", "Some error occurred", STATUS.FAIL);

        }
    }

    ////////////SET
    private static Map<String, String> globalVariable = new HashMap<String, String>();

    @Given("^set\\h(.+)\\h=\\h(.+)")
    public void setKeyValueInline(String key, String value) {
        if (value.contains("readfile(")) {
            int start = value.indexOf("(");
            int end = value.indexOf(")");
            String filePath = value.substring(start + 1, end);
            File file = new File(filePath);
            String content = "";
            try {
                content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            } catch (Exception e) {
                System.out.println("Some error occur while reading file");
            }
            globalVariable.put(key, content);
        } else {
            globalVariable.put(key, value);
        }
        System.out.println(globalVariable);

    }


    @Given("^set\\h(.+)\\h=$")
    public void setKeyValueFromObject(String key, DocString object) {
        globalVariable.put(key, object.getContent());
        System.out.println(globalVariable);

    }

    public static String getResultantJson(String requestBody) {
        String requestBodyString = requestBody.toString();
        char search = '#';             // Character to search is '#'.
        int HashCount = 0;
        for (int i = 0; i < requestBodyString.length(); i++) {
            if (requestBodyString.charAt(i) == search)
                HashCount++;
        }
        int number = HashCount / 2;
        for (int k = 0; k < number; k++) {
            String tempString = getResultantString(requestBodyString);
            requestBodyString = tempString;
        }
//        JsonElement postResultantJson = (JsonElement) JsonParser.parseString(requestBodyString);
        return requestBodyString;
    }

    public static String getResultantString(String requestBodyString) {
        int first = requestBodyString.indexOf("#");
        int second = requestBodyString.indexOf("#", first + 1);
        String start = requestBodyString.substring(0, first);
        String end = requestBodyString.substring(second + 1);
        String buffer = requestBodyString.substring(first + 1, second);
        if (globalVariable.containsKey(buffer)) {
            String t = globalVariable.get(buffer);
            return start + t + end;
        }
        return requestBodyString;
    }

}
