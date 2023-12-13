package asmr.dataUtilities.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import asmr.annotation.Service;
import asmr.dataUtilities.FileTools;

@Service(name="Logger")
public final class LogHelper {
    private static boolean logFile;
    private static String filepath = "logs/VisioPadLog.log";

    public static final void write(String seq) { 
        try{
            if(FileTools.createFolder(FileTools.getRootPath(), "logs")){
                FileWriter pw = new FileWriter(FileTools.getRootPath() + File.separatorChar + filepath, true);
                pw.write(seq); pw.close();
            }
        } 
        catch(IOException ioe){ ioe.printStackTrace(); } 
    }

    private static String Now(){
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return format.format(new Date());
    }

    private static String traceToString(Exception e){
        String result = "";
        for(StackTraceElement stl : e.getStackTrace()){ result += stl.toString() + "\n"; }
        return result;
    }
    
    public static boolean fileActivate(){return logFile;}

    public static void setLogFile(boolean activate){ logFile = activate; }

    public static void log(LogLevel level, CharSequence seq){
        CharSequence message = Now() +" - "+ level.getCode() + " : " + seq;
        System.out.println(message);
        if(logFile){ write(message + "\n"); }
    }

    public static void log(LogLevel level, CharSequence seq, Exception e){
        CharSequence message = Now() +" - "+ level.getCode() + " : " + seq + "\n" + traceToString(e);
        System.out.println(message);
        if(logFile){ write(message + "\n"); }
    }

    public static void log(LogLevel level, Exception e){
        CharSequence message = Now() +" - "+ level.getCode() + " : " + traceToString(e);
        System.out.println(message);
        if(logFile){ write(message + "\n"); }
    }

    public static void info(CharSequence seq){ log(LogLevel.INFO, seq); }

    public static void warning(CharSequence seq){ log(LogLevel.WARNING, seq); }

    public static void error(CharSequence seq){ log(LogLevel.ERROR, seq); }

    public static void info(Exception e){ log(LogLevel.INFO, e); }

    public static void warning(Exception e){ log(LogLevel.WARNING, e); }

    public static void error(Exception e){ log(LogLevel.ERROR, e); }

    public static void info(CharSequence seq, Exception e){ log(LogLevel.INFO, seq, e); }

    public static void warning(CharSequence seq, Exception e){ log(LogLevel.WARNING, seq, e); }

    public static void error(CharSequence seq, Exception e){ log(LogLevel.ERROR, seq, e); }
}
