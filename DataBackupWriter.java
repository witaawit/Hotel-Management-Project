class DataBackupWriter implements Runnable {
    private Holder hotelData;

    public DataBackupWriter(Holder hotelData) {
        this.hotelData = hotelData;
    }

    @Override
    public void run() {
        PersistenceManager.saveData(hotelData);
    }
}