package com.alcozone.utils;

public class SimpleLinearRegression {
        private double slope = 0;
        private double intercept = 0;

        public void fit(double[] x, double[] y) {
            int n = x.length;
            double sumX = 0, sumY = 0, sumXY = 0, sumXX = 0;

            for (int i = 0; i < n; i++) {
                sumX += x[i];
                sumY += y[i];
                sumXY += x[i] * y[i];
                sumXX += x[i] * x[i];
            }

            double xBar = sumX / n;
            double yBar = sumY / n;

            slope = (sumXY - n * xBar * yBar) / (sumXX - n * xBar * xBar);
            intercept = yBar - slope * xBar;
        }

        public double predict(double x) {
            return intercept + slope * x;
        }
    }
