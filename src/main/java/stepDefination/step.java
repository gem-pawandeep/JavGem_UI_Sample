package stepDefination;

import com.gemini.apitest.ApiClientConnect;
import com.gemini.apitest.ProjectApiUrl;
import com.gemini.apitest.ProjectSampleJson;
import com.gemini.generic.QuanticGlobalVar;
import com.gemini.quartzReporting.GemTestReporter;
import com.gemini.quartzReporting.STATUS;
import com.google.gson.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static com.gemini.generic.QuanticGenericUtils.initializeQuanticGlobalVariables;

public class step {

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

    @Given("^Request\\h:\\h([^\"]*)\\h:$")
    public void request(String testcaseName, Object str) {
        String steer = String.valueOf((str.getClass()));
        if (steer.equals("class io.cucumber.docstring.DocString")) {
            GemTestReporter.addTestStep("Create Request", testcaseName, STATUS.INFO);
            str = str.toString().replace("null", "");
            str = str.toString().replace("\"\"\"", "");
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(str.toString());
            json.addProperty("test_name", "");
            JsonArray po = new JsonArray();
            po.add(json);
            ApiClientConnect.healthCheckJsonWithoutNewTC(po);
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
            array.add(json);
            ApiClientConnect.healthCheckJsonWithoutNewTC(array);
        }

    }

}
