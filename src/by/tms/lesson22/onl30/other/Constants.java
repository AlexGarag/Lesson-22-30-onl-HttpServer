package by.tms.lesson22.onl30.other;

public final class Constants {
    public static class Ports {
        public static final int JAVA_PORT = 8080;
    }

    public static class CodeResponse {
        public static final int OK = 200;
        public static final int NOT_FOUND = 404;
    }

    public static class ResultTemplate {
        public static final String RESULT_TEMPLATE = "result: %s";
        public static final String ERROR_BODY_TEMPLATE = "invalid operation type specified - %s";
        public static final String TEST_TEMPLATE = "the server is running";
        public static final String HISTORY_TEMPLATE = "%s  --  %s";

    }

    public static class FormatCsvFile {
        public static final String CSV_TEMPLATE = "%s;%s;%s;%s\n";
    }

    public static class NameFile {
        public static final String CSV_NAME_FILE = "operations.csv";
    }

    public static final class DateTimeFormat {
        public static final String DD_MM_YYYY_SLAG_HH_MM_SS_COLON = "dd/MM/yyyy, HH:mm:ss";
    }
}
