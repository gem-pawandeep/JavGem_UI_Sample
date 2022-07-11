package Main;

import com.gemini.quartzReporting.GemTestReporter;

import java.util.stream.Stream;

public class MainClass {

    private static String path;

    @SuppressWarnings({"deprecation", "rawtypes"})
    public static void main(String args[]) throws Exception {
        System.out.println("insipath");
        Thread.sleep(Long.parseLong("200"));
        String projectName = System.getProperty("projectName");
        if (projectName == null) {
            projectName = "GemJaR";
        }
        String env = System.getProperty("env");
        if (env == null) {
            env = "BETA";
        }
        String reportLocation = System.getProperty("loc");
        if (reportLocation == null) {
            reportLocation = System.getProperty("user.home") + "/GemJaR";
        }
        String step = System.getProperty("Step");
        GemTestReporter.startSuite(projectName, env);
        //    path = System.getProperty("path");
        //   String path ="src/main/java/feature_file/file.feature";
        String[] defaultOptions = {
                "--glue", "stepDefinationforCucumber",
                "src/main/java/featurefile/Feature.feature"
        };
        try {
            Stream<String> cucumberOptions = Stream.concat(Stream.of(defaultOptions), Stream.of(args));
            io.cucumber.core.cli.Main.main(cucumberOptions.toArray(String[]::new));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

