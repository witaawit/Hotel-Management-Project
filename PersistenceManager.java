import java.io.*;

class PersistenceManager {
    private static final String FILENAME = "backup";

    public static Holder loadData() {
        File f = new File(FILENAME);
        if (f.exists() && f.length() > 0) { // Check if file exists and is not empty
            try (FileInputStream fin = new FileInputStream(f);
                 ObjectInputStream ois = new ObjectInputStream(fin)) {
                Object obj = ois.readObject();
                if (obj instanceof Holder) {
                    System.out.println("Data loaded successfully from " + FILENAME);
                    return (Holder) obj;
                } else {
                    System.out.println("Error: Backup file content is not of expected type (Holder). Starting fresh.");
                }
            } catch (FileNotFoundException e) {
                // This case should ideally not be hit if f.exists() is true, but good for robustness
                System.out.println("Warning: Backup file not found, though it existed a moment ago. Starting fresh. " + e.getMessage());
            } catch (EOFException e) {
                 System.out.println("Warning: Backup file is empty or corrupted (EOF). Starting fresh. " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error reading from backup file: " + e.getMessage() + ". Starting fresh.");
            } catch (ClassNotFoundException e) {
                System.out.println("Error: Class 'Holder' not found during deserialization. " + e.getMessage() + ". Starting fresh.");
            }
        } else if (f.exists() && f.length() == 0) {
             System.out.println("Backup file '" + FILENAME + "' exists but is empty. Starting with a new session.");
        }
        else {
            System.out.println("No backup file '" + FILENAME + "' found. Starting with a new session.");
        }
        return new Holder(); // Return a new Holder if loading fails or file doesn't exist/is empty
    }

    public static void saveData(Holder hotelData) {
        try (FileOutputStream fout = new FileOutputStream(FILENAME);
             ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            oos.writeObject(hotelData);
            System.out.println("Data saved successfully to " + FILENAME);
        } catch (IOException e) {
            System.err.println("Error writing to backup file '" + FILENAME + "': " + e.getMessage());
            e.printStackTrace();
        }
    }
}