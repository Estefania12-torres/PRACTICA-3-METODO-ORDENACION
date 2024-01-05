/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.dao;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.File;

/**
 *
 * @author Usuario
 */
public class Bridge {
    
    private static String URL = "files" + File.separatorChar;
    private static XStream conection;
    
    public static XStream getConection(){
        if(conection == null){
            conection = new XStream(new JettisonMappedXmlDriver());
            conection.addPermission(AnyTypePermission.ANY);
        }
        return conection;
    }

    /**
     * @return the URL
     */
    public static String getURL() {
        return URL;
    }

    /**
     * @param aURL the URL to set
     */
    public static void setURL(String aURL) {
        URL = aURL;
    }

    /**
     * @param aConection the conection to set
     */
    public static void setConection(XStream aConection) {
        conection = aConection;
    }
}
