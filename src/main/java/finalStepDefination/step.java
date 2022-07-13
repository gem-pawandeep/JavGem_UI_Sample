package finalStepDefination;
import Assertion.Assert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gemini.apitest.ApiClientConnect;
import com.gemini.apitest.ProjectApiUrl;
import com.gemini.apitest.ProjectSampleJson;
import com.gemini.generic.QuanticGlobalVar;
import com.gemini.quartzReporting.GemTestReporter;
import com.gemini.quartzReporting.STATUS;
import com.google.gson.*;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static com.gemini.generic.QuanticGenericUtils.initializeQuanticGlobalVariables;

public class step {
    public HashMap<String, String> momo = new HashMap<>();

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


    @Given("^(?i)(?<= |^)baseurl(?= |$)\\h(.+)$")
    public void asser_akash(String str) {
        ini("baseurl", str);
        System.out.println("baseurl");
    }

    @Given("^(?i)(?<= |^)path(?= |$)\\h(.+)$")
    public void asskash(String str) {
        ini("path", str);
        System.out.println("path");
    }

    @Given("^(?i)(?<= |^)endpoint(?= |$)\\h(.+)$")
    public void url(String str) {
        ini("endpoint", str);
    }

    @Given("^(?i)(?<= |^)expected_status(?= |$)\\h(.+)$")
    public void status(String str) {
        ini("expected_status", str);
    }

    @Given("^(?i)(?<= |^)headers(?= |$)\\h(.+)$")
    public void headers(String str) {
        ini("headers", str);
    }


    @Given("^(?i)(?<= |^)parameters(?= |$)\\h(.+)=(.+)$")
    public void params(String str, String str2) {
        ini("parameters", str + "@" + str2);

    }

    @Given("^(?i)(?<= |^)request_body(?= |$)\\h(.+)$")
    public void reqbody(String str) {
        ini("request_body", str);
    }

    @Given("^(?i)(?<= |^)test_name(?= |$)\\h(.+)$")
    public void testname(String str) {

        ini("test_name", str);
    }

    JsonElement Responses = new JsonObject() ;

    @Given("^(?i)(?<= |^)method(?= |$)\\h(.+)$")
    public void met(String str) throws JsonProcessingException {

        System.out.println(str);
        ini("method", str);
        JsonObject bai = Tri();

        System.out.println(bai);
        JsonArray respone = new JsonArray();
        respone.add(bai);
        JsonArray popo = ApiClientConnect.healthCheckJsonWithoutNewTC(respone);
        System.out.println(popo);

        momo.clear();
        JsonElement Response = popo.get(0).getAsJsonObject().get("responseBody");
        Responses = Response;
        System.out.println(Responses);
    }

    public JsonObject Tri() {
        JsonObject objo = new JsonObject();

        boolean mo = momo.containsKey("test_name");
        if (mo) {
            objo.addProperty("test_name", momo.get("test_name"));
        }

        boolean a = momo.containsKey("method");
        if (a) {
            objo.addProperty("method", momo.get("method"));
        }

        boolean am = momo.containsKey("endpoint");
        if (am) {
            objo.addProperty("endpoint", momo.get("endpoint"));
        }
        boolean w = momo.containsKey("expected_status");
        if (w) {
            objo.addProperty("expected_status", momo.get("expected_status"));
        }
        boolean p = momo.containsKey("request_body");
        if (p) {
            JsonObject body;
            String bpdy = momo.get("request_body");


            Gson g = new Gson();

            JsonObject popos = g.fromJson(bpdy, JsonObject.class);

            objo.add("request_body", popos);
        }
        boolean q = momo.containsKey("headers");
        if (q) {

            String lo = momo.get("headers").toString();

            Gson g = new Gson();

            JsonObject popos = g.fromJson(lo, JsonObject.class);

            objo.add("headers", popos);
        }
        ///////////////////////////////////////////////////////////////////////
        boolean wion = momo.containsKey("path");
        if (wion) {
            objo.addProperty("path", momo.get("path"));
        }
        boolean b = momo.containsKey("url");
        if (b) {
            objo.addProperty("url", momo.get("url"));
        }
        boolean vivi = momo.containsKey("parameters");
        if (vivi) {

            String bpdy = momo.get("parameters");//page@2,page@3

            char search = ',';             // Character to search is ','.

            int count = 0;
            for (int i = 0; i < bpdy.length(); i++) {
                if (bpdy.charAt(i) == search)
                    count++;
            }
            int input = count + 1;//input=2
            String[] param = bpdy.split(",", input);
            JsonObject kokos = new JsonObject();
            for (int i = 0; i < input; i++) {
                String ko = param[i];
                String[] stp = ko.split("@", 2);
                String first = stp[0];
                String second = stp[1];
                kokos.addProperty(first, second);
            }
            objo.add("parameters", kokos);

        }


        return objo;

    }

    public void ini(String str, String stu) {
        if (str.equals("parameters"))
            if (momo.containsKey("parameters")) {
                String hellos = momo.get("parameters");
                String fin = hellos + "," + stu;
                momo.put(str, fin);
            } else
                momo.put(str, stu);
        else {
            momo.put(str, stu);
        }
    }

    @Given("^Assert\\h:\\h(.+)\\h:\\h(.+)\\h(?i)(?<= |^)contains(?= |$)\\h(.+)$")
    public void assertContains(String name, String filePath, String key) throws IOException {
        try {
            GemTestReporter.addTestStep("Contains", name, STATUS.INFO);

            JsonParser parser = new JsonParser();
            JsonElement json =  parser.parse(String.valueOf(Responses));

            Assert.postAssertion(json, filePath, "contains", key);
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Some error occurred", "Some error occurred", STATUS.FAIL);
        }
    }

    @Given("^Assert\\h:\\h(.+)\\h:\\h(.+)\\h(?i)(?<= |^)in(?= |$)\\h(.+)$")
    public void assertIN(String name, String filePath, String key) {
        try {
            GemTestReporter.addTestStep("IN", name, STATUS.INFO);
            JsonParser parser = new JsonParser();

            JsonElement json =  parser.parse(String.valueOf(Responses));
            System.out.println(json);
            System.out.println(Responses);
            Assert.postAssertion(json, filePath, "in", key);
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Some error occurred", "Some error occurred", STATUS.FAIL);
        }
    }

    @Given("^Assert\\h:\\h(.+)\\h:\\h(.+)\\h(?i)(?<= |^)equals(?= |$)\\h(.+)$")
    public void assertEquals(String str, String str2, String str3) {
        try {
            GemTestReporter.addTestStep("Equals", str, STATUS.INFO);
            JsonParser parser = new JsonParser();

            JsonObject json = (JsonObject) parser.parse(String.valueOf(Responses));
            Assert.postAssertion(json, str2, "equals", str3);
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Some error occurred", "Some error occurred", STATUS.FAIL);
        }
    }

}
