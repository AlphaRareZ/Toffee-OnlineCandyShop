import javax.swing.plaf.IconUIResource;
import java.io.IOException;
import java.awt.geom.PathIterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.security.Key;
import java.security.KeyPair;
import java.sql.*;
import java.util.*;

/**
 * this is the main class which operates the full system data
 */
public class Main {

    public static void main(String[] args) {
        SystemData systemData = new SystemData();
//        systemData.makeReport();
        systemData.main_page();

    }

}
