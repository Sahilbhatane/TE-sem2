class Solution {
    public:
        vector<int> JobScheduling(Job arr[], int n) {
            // Sort jobs by profit in descending order
            sort(arr, arr + n, [](Job &j1, Job &j2) {
                return j1.profit > j2.profit;
            });
            
            vector<bool> daysUsed(101, false);
            int ansProfit = 0;
            int jobsUsed = 0;
            
            // Iterate through jobs
            for (int i = 0; i < n; i++) {
                int deadline = arr[i].dead;
                int profit = arr[i].profit;
                
                // Try to schedule the job on the latest possible day before its deadline
                for (int j = deadline; j >= 1; j--) {
                    if (daysUsed[j] == false) {
                        daysUsed[j] = true;
                        ansProfit += profit;
                        jobsUsed++;
                        break;
                    }
                }
            }
            
            return {jobsUsed, ansProfit};
        }
};
