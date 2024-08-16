package by.tms.lesson22.onl30.other;

import static by.tms.lesson22.onl30.other.Constants.CodeResponse.OK;
import static by.tms.lesson22.onl30.other.Constants.ResultTemplate.RESULT_TEMPLATE;
import static java.lang.String.format;

public class Response {
    private int codeResponse;
    private String bodyResponse;

    public Response() {
        this.codeResponse = OK;
        this.bodyResponse = "";
    }

    public int getCodeResponse() {
        return codeResponse;
    }

    public String getBodyResponse() {
        return bodyResponse;
    }

    public void setCodeResponse(int codeResponse) {
        this.codeResponse = codeResponse;
    }

    public void setBodyResponse(String bodyResponse) {
        this.bodyResponse = format(RESULT_TEMPLATE, bodyResponse);
    }

    @Override
    public String toString() {
        return "Response{\n" +
                "\t\"codeResponse=\"" + codeResponse + ",\n" +
                "\t\"bodyResponse=\"\"" + bodyResponse + "\"\n" +
                "}";
    }
}
