package com.launch;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Method method = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        Test test = method.getAnnotation(Test.class);
        String description = test.description();
        System.out.println("- " + description + " . . . . . ЗАПУСК ТЕСТА");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Method method = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        Test test = method.getAnnotation(Test.class);
        String description = test.description();
        System.out.println("- " + description + " . . . . . ПРОЙДЕНО");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Method method = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        Test test = method.getAnnotation(Test.class);
        String description = test.description();

        System.out.println("- " + description + " . . . . . ПРОВАЛ");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Method method = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        Test test = method.getAnnotation(Test.class);
        String description = test.description();
        System.out.println("- " + description + " . . . . . ПРОПУЩЕН");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Method method = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        Test test = method.getAnnotation(Test.class);
        String description = test.description();
        System.out.println("- " + description + " . . . . . ПРОВАЛ, НО В ПРЕДЕЛАХ ПРОЦЕНТА УСПЕХА");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        String className = iTestContext.getCurrentXmlTest().getName();
        System.out.println("-------------------------------------------------------");
        System.out.println(className);
        System.out.println("-------------------------------------------------------");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        int allTests = iTestContext.getAllTestMethods().length;
        int passedTests = iTestContext.getPassedTests().size();
        long startDate = iTestContext.getStartDate().getTime();
        long endDate = new Date().getTime();
        Date date = new Date(endDate - startDate);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC+3"));
        String duration = formatter.format(date);

        System.out.println("-------------------------------------------------------");
        System.out.println("Успешно пройдено " + passedTests + " из " + allTests);
        System.out.println("Время выполнения " + duration);
        System.out.println("-------------------------------------------------------\n\n");
    }
}
