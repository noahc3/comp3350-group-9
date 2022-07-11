package comp3350.cookit.application;

public class Main {
    public static final String dbName = "main";
    private static String dbPath = "database/main";

    public static void main(String[] args) {
        startUp();
        shutDown();
        System.out.println("All done");
    }

    public static void startUp() {
        Services.createDataStore(dbName);
    }

    public static void shutDown() {
        Services.closeDataAccess();
    }

    public static String getDBPath() {
        if (dbPath == null)
            return dbName;
        else
            return dbPath;
    }

    public static void setDBPath(String path) {
        System.out.println("Setting DB path to: " + path);
        dbPath = path;
    }
}
