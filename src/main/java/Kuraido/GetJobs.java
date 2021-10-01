package Kuraido;
import java.util.List;

public interface GetJobs
{
    List<Job> getJobs(String title, String location);
    List<Job> getJobs(String title);
}
