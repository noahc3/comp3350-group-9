package comp3350.cookit.application;

public class Main {
    private static final String dbName = "main";
    private static final String imgAssetsPath = "img";
    private static final String dbAssetsPath = "db";
    private static String dbPath = "db/main";

    public static void main(String[] args) {
        startUp();
        shutDown();
        System.out.println("All done");
    }

    public static void startUp() {
        Services.createDataStore();
    }

    public static void shutDown() {
        Services.closeDataStore();
    }

    public static String getDbName() {
        return dbName;
    }

    public static String getDBPath() {
        return dbPath;
    }

    public static String getDbAssetsPath() {
        return dbAssetsPath;
    }

    public static String getImgAssetsPath() {
        return imgAssetsPath;
    }

    public static void setDBPath(String path) {
        System.out.println("Setting DB path to: " + path);
        dbPath = path;
    }
}
