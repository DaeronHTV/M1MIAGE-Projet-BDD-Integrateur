package asmr.dataUtilities.log;

public enum LogLevel{
    INFO("INFO"),
    ERROR("ERROR"),
    WARNING("WARNING");

    private String code;

    LogLevel(String code){this.code = code;}

    public String getCode(){return code;}
}
