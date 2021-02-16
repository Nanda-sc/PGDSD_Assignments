import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.Queue;

class SiteStats {
    private String url;
    private int numVisits;

    /**
     * Constructor for the SiteStats class
     *
     * @param url
     *            String that represents an URL that the user has visited
     * @param numVisits
     *            An int that represents the number of times that the user has
     *            visited the url
     */
    public SiteStats(String url, int numVisits) {
        this.url = url;
        this.numVisits = numVisits;
    }

    /**
     * This method returns the number of times that the user has visited the url.
     *
     * @return An int that represents the number of times that the user has visited
     *         the url
     */
    public int getNumVisits() {
        return this.numVisits;
    }

    /**
     * This method returns the url that we are currently tracking
     *
     * @return A String that represents the url that we are currently tracking
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * This method updates the number of times that we have visited the url
     *
     * @param //an
     *            int that represents the number that we want to set numVisits to
     */
    public void setNumVisits(int updatedNumVisits) {
        this.numVisits = updatedNumVisits;
    }

    public String toString() {
        return this.url + " | " + this.numVisits;
    }

}

public class SolutionB {
    private static Queue<SiteStats> sites = new LinkedList<SiteStats>();

    //func to find the max based on numVisits
    public static int maxIndex(Queue<SiteStats> q, int idx){
        int maxIdx = -1;
        int maxVal = Integer.MIN_VALUE;
        int n = q.size();
        for(int i = 0 ; i < n ; i++){
            int val = q.peek().getNumVisits();
            String url = q.peek().getUrl();
            q.remove();
            //to fid the maxValue and the index
            //the check i <= idx, so that we don't repeat
            //the comparisons
            if( val >= maxVal && i <= idx){
                maxVal = val;
                maxIdx = i;
            }
            q.add(new SiteStats(url,val));
        }
        return maxIdx;
    }

    //func to insert the maxValue based on numVisits
    public static void insertMaxToRear(Queue<SiteStats> q, int maxIdx){
        int maxValue = 0;
        String maxValURL = "";
        int s = q.size();
        for (int i = 0; i < s; i++)
        {
            int current = q.peek().getNumVisits();
            String currentURL = q.peek().getUrl();
            q.poll();
            if (i != maxIdx) {
                q.add(new SiteStats(currentURL, current));
            }
            else {
                maxValue = current;
                maxValURL = currentURL;
            }
        }
        q.add(new SiteStats(maxValURL,maxValue));
    }

    // Main method to list top n most visited sites based on numVisits
    public static void listTopVisitedSites(Queue<SiteStats> sites, int n) {
        // WRITE CODE HERE

        //if the queue is empty, i.e if the user
        //does not have browsing history
        if(sites.isEmpty()){
            System.out.println(" ");
            return;
        }

        int sizeSites = sites.size();

        //To check if the number of sites to be displayed
        //is greater than size of the queue than
        //display all the sites in history
        if(n > sizeSites){
            for(SiteStats s : sites){
                System.out.println(s.toString());
            }
            return;
        }

        //logic to sort the queue based on numVisits
        for (int i = 1; i <= sites.size(); i++)
        {
            //call the function maxIndex to find the index
            //of max value
            int maxIdx = maxIndex(sites, sites.size() - i);
            //To insert the max value in the queue
            insertMaxToRear(sites, maxIdx);

        }

        //print the top n most visited sites
        //from the sorted queue
        int i = 1;
        for(SiteStats s : sites){
            if(i <= n){
                System.out.println(s.toString());
            }else{
                break;
            }
            i++;
        }
    }

    // Method to find the website in the queue and increment the visited count by 1,
    // adding new node in case website is not found
    public static void updateCount(String url) {
        //To check if the url is new or not
        int updateflag = 0;

        //Looping through the Queue to check if
        //the url is already part of the queue
        //if yes, update the numVisits by 1
        for(SiteStats s : sites){
            if(s.getUrl().compareTo(url) == 0){
                s.setNumVisits(s.getNumVisits() + 1);
                updateflag = 1;
            }
        }

        //If the URL is not in the queue, add it
        if(updateflag == 0){
            sites.add(new SiteStats(url,1));
        }

    }

    public static void main(String[] args) {
        String[] visitedSites = { "www.google.co.in", "www.google.co.in", "www.facebook.com", "www.upgrad.com", "www.google.co.in", "www.youtube.com",
                "www.facebook.com", "www.upgrad.com", "www.facebook.com", "www.google.co.in", "www.microsoft.com", "www.9gag.com", "www.netflix.com",
                "www.netflix.com", "www.9gag.com", "www.microsoft.com", "www.amazon.com", "www.amazon.com", "www.uber.com", "www.amazon.com",
                "www.microsoft.com", "www.upgrad.com" };

        for (String url : visitedSites) {
            updateCount(url);
        }
        listTopVisitedSites(sites, 5);


    }
}




