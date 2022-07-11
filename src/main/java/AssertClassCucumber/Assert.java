package AssertClassCucumber;

import com.gemini.apitest.ApiHealthCheckUtils;
import com.gemini.quartzReporting.GemTestReporter;
import com.gemini.quartzReporting.STATUS;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Iterator;
import java.util.Set;

public class Assert {

    /*
    equals
    contains
    in
    not equal
    not contain
     */

    private final String PASS = "PASS";
    private final String FAIL = "FAIL";

    public Assert() {

    }

    private static void assertEquals(JsonElement tempResponseBody, String target, String tempQuery) {
        if (tempResponseBody != null && tempResponseBody.getAsString().equalsIgnoreCase(target)) {
            GemTestReporter.addTestStep("Post_Validation", "<b>Validation Query: </b>" + tempQuery + "<br>Expected_Value: " + target + "<br>Actual_Value: " + tempResponseBody.getAsString(), STATUS.PASS);
        } else {
            GemTestReporter.addTestStep("Post_Validation", "<b>Validation Query: </b>" + tempQuery + "<br>Expected_Value: " + target + "<br>Actual_Value: " + tempResponseBody.getAsString(), STATUS.FAIL);
        }
    }

    private static void assertIn(JsonElement tempResponseBody, String target, String tempQuery) {
        JsonArray targetArray = JsonParser.parseString(target).getAsJsonArray();
        if (targetArray.contains(tempResponseBody)) {
            GemTestReporter.addTestStep("Post_Validation", "<b>Validation Query: </b>" + tempQuery + "<br>Expected_Value: " + target + "<br>Actual_Value: " + tempResponseBody.getAsString(), STATUS.PASS);
        } else {
            GemTestReporter.addTestStep("Post_Validation", "<b>Validation Query: </b>" + tempQuery + "<br>Expected_Value: " + target + "<br>Actual_Value: " + tempResponseBody.getAsString(), STATUS.FAIL);
        }
    }

    private static void assertContains(JsonElement tempResponseBody, String target, String tempQuery) {
        if (tempResponseBody != null && tempResponseBody.getAsString().toUpperCase().contains(target.toUpperCase())) {
            GemTestReporter.addTestStep("Post_Validation", "<b>Validation Query: </b>" + tempQuery + "<br>Expected_Value: " + target + "<br>Actual_Value: " + tempResponseBody.getAsString(), STATUS.PASS);
        } else {
            GemTestReporter.addTestStep("Post_Validation", "<b>Validation Query: </b>" + tempQuery + "<br>Expected_Value: " + target + "<br>Actual_Value: " + tempResponseBody.getAsString(), STATUS.FAIL);
        }
    }


    private void assertIngnoreCaseEquals(String value1, String value2) {
    }

    public static void assertion(JsonElement recentResponse, JsonObject validationQueries) {
        System.out.println("IN ASSERT CLASS");
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

                    postAssertion(recentResponse, query, operator, target);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Some error occurred in Assertion", "Some error occurred", STATUS.FAIL);

        }
    }

    public static void postAssertion(JsonElement responseBody, String query, String operator, String target) {
        boolean flag = true;
        JsonElement tempResponseBody = responseBody;
        String tempQuery = query;

        while (flag) {
            int startIndex = query.indexOf("(");
            int endIndex = query.indexOf(")");
            String key = query.substring(startIndex + 1, endIndex);
            tempResponseBody = getLeftQuery(tempResponseBody, key);
            query = query.substring(endIndex + 1);
            if (query.length() <= 0) {
                flag = false;
            }
        }

        try {
            switch (operator.toUpperCase()) {
                case "EQUALS":
                    assertEquals(tempResponseBody, target, tempQuery);
                    break;
                case "IN":
                    assertIn(tempResponseBody, target, tempQuery);
                    break;
                case "CONTAINS":
                    assertContains(tempResponseBody, target, tempQuery);
            }
        } catch (NullPointerException var10) {
            GemTestReporter.addTestStep("Some error occurred in Post Assertion", "Some error occurred <BR>error message ~ " + var10.getMessage(), STATUS.FAIL);
            var10.printStackTrace();
        }
    }

    private static JsonElement getLeftQuery(JsonElement request, String key) {
        boolean isNumeric = key.chars().allMatch(Character::isDigit);
        if (request instanceof JsonArray && isNumeric) {
            return request.getAsJsonArray().get(Integer.parseInt(key));
        } else {
            return request instanceof JsonObject && !isNumeric ? request.getAsJsonObject().get(key) : null;
        }
    }

}
