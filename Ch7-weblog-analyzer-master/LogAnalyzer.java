/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @author KaJuan Jones.
 * @version    2019.03.31
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Return number of accesses recorded in the log file.
     */
    public int numberofAccesses()
    {
        int total = 0;
        // Add the value in each element of hourCounts to total.
        for(int hour = 0; hour < hourCounts.length; hour++) {
            total = total + hourCounts[hour];
        }
        return total;
    }
    /**
     * Added busiestHour method
     */
    public int busiestHour()
    {
        int numberofAccessesAtBusiest = 0;
        int busiestHour = 0;
        int index = 0;
        
        while(index < hourCounts.length - 1){
            if(numberofAccessesAtBusiest < hourCounts[index]){
                busiestHour = index;
                numberofAccessesAtBusiest = hourCounts[index];
                index++;
            }
            else{
                index++;
            }
        }
        return busiestHour;
    }
    /**
     * Added quiestestHour method
     */
    public int quietestHour()
    {
        int numberOfAccessesAtQuietest = 9999;
        int quietestHour = 0;
        int index = 0;
        
        while(index < hourCounts.length - 1){
            if(numberOfAccessesAtQuietest > hourCounts[index]){
                quietestHour = index;
                numberOfAccessesAtQuietest = hourCounts[index];
                index++;
            }
            else {
                index++;
            }
        }
        return quietestHour;
    }
    /**
     * Added the twobusiestHour method
     */
    public int twobusiestHour()
    {
        int numberofAccessesAtBusiest = 0;
        int busiestHour = 0;
        int index = 0;
        
        while(index < hourCounts.length - 1){
            if(numberofAccessesAtBusiest < hourCounts[index] + hourCounts[index + 1]){
                busiestHour = index;
                numberofAccessesAtBusiest = hourCounts[index] + hourCounts[index + 1];
                index++;
            }
            else{
                index++;
            }
        }
        return busiestHour;
    }
        
    
}
