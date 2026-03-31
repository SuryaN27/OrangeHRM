package org.example.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int recount=0;
    public static final int maxretrycount=3;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(recount<maxretrycount){
            recount++;
            return true;

        }
        return false;
    }
}
