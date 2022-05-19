package com.qa.Quantic_sample.Pages;

import com.gemini.apitest.ApiClientConnect;
import com.gemini.dataProvider.QuanticDataProvider;
import com.gemini.generic.DriverAction;
import com.gemini.generic.QuanticAPIBase;
import com.gemini.generic.QuanticHealthCheckBase;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Set;

public class Test2 extends QuanticHealthCheckBase {

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)

    public static void Healthchecktesting08(JsonObject inputElement) throws FileNotFoundException {

//        JsonObject med = (JsonObject) JsonParser.parseString("{\n" + "\"problems\": [{\n" + "    \"Diabetes\":[{\n" + "        \"medications\":[{\n" + "            \"medicationsClasses\":[{\n" + "                \"className\":[{\n" + "                    \"associatedDrug\":[{\n" + "                        \"name\":\"asprin\",\n" + "                        \"dose\":\"\",\n" + "                        \"strength\":\"500 mg\"\n" + "                    }],\n" + "                    \"associatedDrug#2\":[{\n" + "                        \"name\":\"somethingElse\",\n" + "                        \"dose\":\"\",\n" + "                        \"strength\":\"500 mg\"\n" + "                    }]\n" + "                }],\n" + "                \"className2\":[{\n" + "                    \"associatedDrug\":[{\n" + "                        \"name\":\"asprin\",\n" + "                        \"dose\":\"\",\n" + "                        \"strength\":\"500 mg\"\n" + "                    }],\n" + "                    \"associatedDrug#2\":[{\n" + "                        \"name\":\"somethingElse\",\n" + "                        \"dose\":\"\",\n" + "                        \"strength\":\"500 mg\"\n" + "                    }]\n" + "                }]\n" + "            }]\n" + "        }],\n" + "        \"labs\":[{\n" + "            \"missing_field\": \"missing_value\"\n" + "        }]\n" + "    }],\n" + "    \"Asthma\":[{}]\n" + "}]}");
        //JsonArray payload = ProjectSampleJson.getSampleData("pope_sampleJson").getAsJsonArray();
//        JsonObject r = (JsonObject) JsonParser.parseString("{\n" + "\t\"glossary\": {\n" + "\t\t\"title\": \"example glossary\",\n" + "\t\t\"GlossDiv\": {\n" + "\t\t\t\"title\": \"S\",\n" + "\t\t\t\"GlossList\": {\n" + "\t\t\t\t\"GlossEntry\": {\n" + "\t\t\t\t\t\"ID\": \"SGML\",\n" + "\t\t\t\t\t\"SortAs\": \"SGML\",\n" + "\t\t\t\t\t\"GlossTerm\": \"Standard Generalized Markup Language\",\n" + "\t\t\t\t\t\"Acronym\": \"SGML\",\n" + "\t\t\t\t\t\"Abbrev\": \"ISO 8879:1986\",\n" + "\t\t\t\t\t\"GlossDef\": {\n" + "\t\t\t\t\t\t\"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\n" + "\t\t\t\t\t\t\"GlossSeeAlso\": [\"GML\", \"XML\"]\n" + "\t\t\t\t\t},\n" + "\t\t\t\t\t\"GlossSee\": \"markup\"\n" + "\t\t\t\t}\n" + "\t\t\t}\n" + "\t\t}\n" + "\t}\n" + "}");
//        getkey(med, "Diabetes");
//        getkey(r, "title");
//        deepSearch(med, "strength");
        ApiClientConnect.healthCheck("src/main/resources/health.json");
//        ApiClientConnect.healthCheck("src/main/resources/var_rep.json");
//        DeepSearch.deepSearch(med,"problems");
        System.out.println("--------------------------------------------------------------");

    }


    public static int getkey(JsonObject obj, String Key) {
        String pather = "";
        int f = 0;
        Set<?> listt = obj.keySet();
        if (obj.has(Key)) {
            System.out.println(obj.get(Key));
            System.out.println("Body" + Key);
        } else {
            Iterator<?> i = listt.iterator();
            do {
                String value = i.next().toString();
                try {
                    if (obj.get(value) instanceof JsonObject) {
                        JsonObject ok = (JsonObject) obj.get(value);
                        String p = getkey(ok, Key, pather + "." + value);
//                        if (p != null) {
////                            System.out.println(p + "." + Key);
//                            System.out.println(pather+"."+Key);
//                        } else {
//                            pather = "";
//                        }
                    } else if (obj.get(value) instanceof JsonArray) {
                        JsonArray jaa = obj.getAsJsonArray(value);
                        for (int l = 0; l < jaa.size(); l++) {
                            JsonObject poll = (JsonObject) jaa.get(l);
                            String lo = getkey(poll, Key, pather + "." + value + "." + l);
                            if (lo != null) {
                                lo += ("." + l + "." + Key);
//                                System.out.println(lo);
                                System.out.println("body" + pather + "." + Key);

                            } else {

                            }
                        }

                    }

                } catch (Exception e) {

                }
            } while (i.hasNext());

        }
        return f;
    }

    public static String getkey(JsonObject obj, String Key, String popo) {

        int f = 0;

        Set<?> listt = obj.keySet();
        if (obj.has(Key)) {
            f = 1;
            System.out.println(obj.get(Key));
            return popo;
        } else {
            Iterator<?> i = listt.iterator();
            do {
                String value = i.next().toString();
                try {
                    if (obj.get(value) instanceof JsonObject) {
                        JsonObject ok = (JsonObject) obj.get(value);
                        String po = getkey(ok, Key, popo + "." + value);
                        if (po != null) {
                            System.out.println("body" + popo + "." + value + "." + Key);
                            return po;
                        }

                    } else if (obj.get(value) instanceof JsonArray) {
                        JsonArray jaa = obj.getAsJsonArray(value);
                        for (int l = 0; l < jaa.size(); l++) {
                            JsonObject poll = (JsonObject) jaa.get(l);
                            // getkey(poll, Key);
                            String lo = getkey(poll, Key, popo + "." + value + "." + l);
                            //    value+=("."+l);
                            if (lo != null) {
                                System.out.println("body" + popo + "." + value + "." + l + "." + Key);
//                                return lo;
//                                System.out.println(lo + "." + l + "." + Key);
                            } else {

                            }
                        }

                    }

                } catch (Exception e) {

                }
            } while (i.hasNext());

        }
        return null;
    }

    public static int getkey(JsonArray arr, String Key) {
        for (int k = 0; k < arr.size(); k++) {
            JsonObject po = (JsonObject) arr.get(k);
            int l = getkey(po, Key);
            if (l == 1) {
                int t = k + 1;
                System.out.println("Number of the Json Object in which the element is found" + t);
            }
        }

        return 0;
    }

    public static void deepSearch(JsonElement request, String key) {
        if (request instanceof JsonArray) {
            deepSearch(request.getAsJsonArray(), key, "");
        } else if (request instanceof JsonObject) {
            deepSearch(request.getAsJsonObject(), key, "");
        }
    }

    private static void deepSearch(JsonArray asJsonArray, String key, String s) {//name:[{},{},{},[],[]]
        for (int l = 0; l < asJsonArray.size(); l++) {
            if (asJsonArray.get(l) instanceof JsonObject) {
                deepSearch((JsonObject) asJsonArray.get(l), key, s + "." + l);
            }
            if (asJsonArray.get(l) instanceof JsonArray) {
                deepSearch((JsonArray) asJsonArray.get(l), key, s + "." + l);
            }
        }
    }

    private static void deepSearch(JsonObject obj, String key, String s) {
        Set<?> listt = obj.keySet();
        Iterator<?> i = listt.iterator();
        if (listt.contains(key)) {
            System.out.println("value = " + obj.get(key).toString());
            System.out.println("loc = body" + s + "." + key);//{name:[name]}
        }
        do {
            try {
                String value = i.next().toString();
                if (obj.get(value) instanceof JsonObject) {
                    JsonObject subObj = (JsonObject) obj.get(value);
                    deepSearch(subObj, key, s + "." + value);
                } else if (obj.get(value) instanceof JsonArray) {
                    deepSearch((JsonArray) obj.get(value).getAsJsonArray(), key, s + "." + value);
                }
            } catch (Exception e) {
            }
        } while (i.hasNext());
    }


}