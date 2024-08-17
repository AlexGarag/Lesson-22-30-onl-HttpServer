package by.tms.lesson22.onl30.other;

public final class Constants {
    public static final class Paths {
        public static final String TEST = "/test";
        public static final String CALCULATOR = "/calculator";
        public static final String HISTORY = "/history";
    }

    public static final class Ports {
        public static final int JAVA_PORT = 8080;
    }

    public static final class Sockets {
        public static final int DEFAULT = 0;
    }

    public static final class CodeResponse {
        public static final int OK = 200;
        public static final int NOT_FOUND = 404;
    }

    public static final class ResultTemplate {
        public static final String ERROR_BODY_TEMPLATE = "invalid operation type specified - %s";
        public static final String TEST_TEMPLATE = "the server is running";
        public static final String OPERAND_NOT_NUMBER = "one of the operands is not a number";
    }

    public static final class FormatCsvFile {
        public static final String CSV_FORMAT_TEMPLATE = "%s;%s;%s;%s\n";
    }

    public static final class NameFile {
        public static final String CSV_NAME_FILE = "operations.csv";
    }

    public static final class DateTimeFormat {
        public static final String DD_MM_YYYY_SLAG_HH_MM_SS_COLON = "dd/MM/yyyy, HH:mm:ss";
    }

    public static final class NumericTemplate {
        public static final String DOUBLE_2_STRING = "%.2f";
    }

}