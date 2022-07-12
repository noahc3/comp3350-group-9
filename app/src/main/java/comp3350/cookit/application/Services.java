package comp3350.cookit.application;

import comp3350.cookit.persistence.HsqldbDataStore;
import comp3350.cookit.persistence.IDataStore;

public class Services {
    private static IDataStore activeDataStore = null;

    public static IDataStore createDataStore() {
        if (activeDataStore == null) {
            activeDataStore = new HsqldbDataStore();
            activeDataStore.open(Main.getDBPath());
        }
        return activeDataStore;
    }

    public static IDataStore createDataStore(IDataStore alternateDataStore) {
        activeDataStore = alternateDataStore;
        activeDataStore.open(Main.getDBPath());
        return activeDataStore;
    }

    public static IDataStore getDataStore() {
        if (activeDataStore == null) {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return activeDataStore;
    }

    public static void closeDataStore() {
        if (activeDataStore != null) {
            activeDataStore.close();
        }
        activeDataStore = null;
    }
}
