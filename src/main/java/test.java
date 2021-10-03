import Kuraido.*;

import java.util.List;

public class test
{
    public static void main(String[] args)
    {
        GetJobs scraper = new GetJobsReed();
        List<Job> jobs = scraper.getJobs("","Newcastle");
        System.out.println(jobs.size());
    }
}
