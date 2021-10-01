package Kuraido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class GetJobsReed implements GetJobs
{
    private Document document=null;
    List<Job> Jobs= new ArrayList<>();
    String url ="https://www.reed.co.uk/jobs/";
    int page=1;

    public List<Job> getJobs(String title, String location)
    {
        setUpUrl(title,location);
        getPage();
        return null;
    }

    @Override
    public List<Job> getJobs(String title) {
        setUpUrl(title,"");
        getPage();
        return null;
    }

    private void setUpUrl(String title, String location)
    {
        setUpUrlJob(title);
        setUpUrlLocation(location);
        setUpUrlSetPage();
    }
    private void setUpUrlJob(String title)
    {
        if(!title.isBlank() || title.isEmpty())
        {
            url+=title.replaceAll("\\s+","-")+"-jobs";
        }
        else
        {
            url+="jobs";
        }
    }

    private void setUpUrlLocation(String location)
    {
        if(!location.isBlank() && !location.isEmpty())
        {
            url+="-in-"+location.replaceAll("\\s+","-");
        }
    }

    private void setUpUrlSetPage()
    {
        url+="?pageno="+page;
    }

    private void nextPage()
    {
        page++;
        url=url.replace("pageno="+(page-1),"pageno="+page);

    }

    private void getPage()
    {
        try
        {
            this.document = Jsoup.connect(url).get();
        }
        catch (IOException e)
        {
            System.out.println("Page not found");
        }
    }

    private boolean hasNextPage()
    {
        Elements button = document.getElementsByClass("page disabled");
        for (Element e: button)
        {
            Elements nextDisabled= e.getElementsByClass("next");
            if(!nextDisabled.isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    private void getJobsFromPage()
    {
        Elements jobsFromPage = document.getElementsByClass("job-result");
        for (Element jobElement:jobsFromPage)
        {
            Jobs.add(setJobData(jobElement));
        }
        if(hasNextPage())
        {
            nextPage();
            getPage();
            getJobsFromPage();
        }
    }

    private Job setJobData(Element jobData)
    {
        String name=setName(jobData);
        String location=setLocation(jobData);
        String salary=setSalary(jobData);
        String description=setDescription(jobData);
        String company=setCompany(jobData);
        String link=setLink(jobData);
        Job job = new Job(name,location,salary,description,company,link);
        return job;
    }
    private String setName(Element jobData)
    {
        Elements titleValues = jobData.getElementsByClass("title");
        Elements linkValues = titleValues.get(0).getElementsByTag("a");
        String result = linkValues.get(0).attr("title").toString();
        return result;
    }

    private String setLocation(Element jobData)
    {
        Elements locationValues = jobData.getElementsByClass("location");
        String result =locationValues.text();
        return result;
    }
    private String setSalary(Element jobData)
    {
        Elements locationValues = jobData.getElementsByClass("salary");
        String result =locationValues.text();
        return result;
    }
    private String setDescription(Element jobData)
    {
        Elements descriptionValues = jobData.getElementsByClass("description");
        Elements linkValues = descriptionValues.get(0).getElementsByTag("p");
        String result = linkValues.get(0).text();
        return result;
    }
    private String setCompany(Element jobData)
    {
        Elements postedByValues = jobData.getElementsByClass("posted-by");
        Elements paragraphValues = postedByValues.get(0).getElementsByTag("a");
        String result = paragraphValues.get(0).text();
        return result;
    }

    private String setLink(Element jobData)
    {
        Elements titleValues = jobData.getElementsByClass("title");
        Elements linkValues = titleValues.get(0).getElementsByTag("a");
        String result = "https://www.reed.co.uk/"+linkValues.get(0).attr("href").toString();
        return result;
    }
    public void test()
    {
        setUpUrl("Java Developer","NewCastle");
        System.out.println(url);
        getPage();
        getJobsFromPage();
        System.out.println(Jobs.size());
    }
}
