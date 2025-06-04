import java.io.Serializable;
import java.time.LocalDateTime;

public class CleaningService implements Serializable {
    private static final long serialVersionUID = 1L;

    private int roomNumber;
    private String requestedBy;
    private LocalDateTime requestTime;
    private LocalDateTime completedTime;
    private boolean isCompleted;

    public CleaningService(int roomNumber, String requestedBy) {
        this.roomNumber = roomNumber;
        this.requestedBy = requestedBy;
        this.requestTime = LocalDateTime.now();
        this.isCompleted = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public LocalDateTime getCompletedTime() {
        return completedTime;
    }

    public void completeCleaning(String remarks) {
        this.isCompleted = true;
        this.completedTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "CleaningService{" +
                "roomNumber=" + roomNumber +
                ", requestedBy='" + requestedBy + '\'' +
                ", requestTime=" + requestTime +
                ", completedTime=" + completedTime +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
